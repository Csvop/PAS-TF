package com.bcopstein.Aplicacao.CasosDeUso;

import com.bcopstein.Negocio.Servicos.ServicoDeVendas;
import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Repositorio.IProdutos;
import com.bcopstein.Aplicacao.Validacao.FactoryValidacao;
import com.bcopstein.Interface.EstoqueProxy;
import com.bcopstein.Interface.NotaFiscalProxy;
import com.bcopstein.Interface.DTO.ItemEstoqueDTO;
import com.bcopstein.Negocio.Entidades.Venda.Venda;
import com.bcopstein.Negocio.Exception.SistVendasException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ConfirmaVendaUC {
    private ServicoDeVendas servicoDeVendas;
    private IProdutos produtos;
    private FactoryValidacao factoryValidacao;

    @Autowired
    EstoqueProxy estoque;

    @Autowired
    NotaFiscalProxy notaFiscal;

    @Autowired 
    public ConfirmaVendaUC(IProdutos produtos,FactoryValidacao factoryValidacao, ServicoDeVendas servicoDeVendas) {
        this.servicoDeVendas = servicoDeVendas;
        this.produtos = produtos;
        this.factoryValidacao = factoryValidacao;
    }

    public boolean execute(final List<ItemVenda> itensVenda) {
        // Verifica se todos os itens são válidos
        try{
          factoryValidacao.getRegraValidacao().valida(produtos, itensVenda, estoque);
        }catch(SistVendasException s){
          return false;
        }

        // Cria a venda
        Venda venda = new Venda();
        venda.addItens(itensVenda);

        // Transformando de ItemVenda para ItemEstoqueDTO
        Collection<ItemEstoqueDTO> itensEstoque = new ArrayList<>();
        itensVenda.forEach((item) -> {
            itensEstoque.add(new ItemEstoqueDTO((long) item.getNro(), item.getCodigoProduto(), item.getQuantidade()));
        });

        // Dá baixa no estoque
        estoque.saidaDeProdutos(itensEstoque);    

        // Fecha a venda
        venda.fechaVenda(servicoDeVendas.calculaSubtotal(itensVenda),
                         servicoDeVendas.calculaImpostos(itensVenda),
                         servicoDeVendas.calculaPrecoFinal(itensVenda));
        // Persiste a venda
        notaFiscal.registraVenda(venda);
        return true;
      }
    
    
    
}
