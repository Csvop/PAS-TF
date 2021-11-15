package com.bcopstein.Aplicacao.CasosDeUso;

import com.bcopstein.Negocio.Servicos.ServicoDeEstoque;
import com.bcopstein.Negocio.Servicos.ServicoDeVendas;
import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Repositorio.IProdutos;
import com.bcopstein.Aplicacao.Validacao.FactoryValidacao;
import com.bcopstein.Negocio.Entidades.Venda.Venda;
import com.bcopstein.Negocio.Exception.SistVendasException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ConfirmaVendaUC {
    private ServicoDeEstoque servicoDeEstoque;
    private ServicoDeVendas servicoDeVendas;
    private IProdutos produtos;
    private FactoryValidacao factoryValidacao;

    @Autowired 
    public ConfirmaVendaUC(IProdutos produtos,FactoryValidacao factoryValidacao,ServicoDeEstoque servicoDeEstoque, ServicoDeVendas servicoDeVendas) {
        this.servicoDeEstoque = servicoDeEstoque;
        this.servicoDeVendas = servicoDeVendas;
        this.produtos = produtos;
        this.factoryValidacao = factoryValidacao;
    }

    public boolean execute(final List<ItemVenda> itensVenda) {
        // Verifica se todos os itens são válidos
        try{
          factoryValidacao.getRegraValidacao().valida(produtos, servicoDeEstoque, itensVenda);
        }catch(SistVendasException s){
          return false;
        }
        // Cria a venda
        Venda venda = new Venda();
        venda.addItens(itensVenda);
        // Dá baixa no estoque
        servicoDeEstoque.saidaDeProdutos(itensVenda);    
        // Fecha a venda
        venda.fechaVenda(servicoDeVendas.calculaSubtotal(itensVenda),
                         servicoDeVendas.calculaImpostos(itensVenda),
                         servicoDeVendas.calculaPrecoFinal(itensVenda));
        // Persiste a venda
        servicoDeVendas.cadastra(venda);
        return true;
      }
    
    
    
}
