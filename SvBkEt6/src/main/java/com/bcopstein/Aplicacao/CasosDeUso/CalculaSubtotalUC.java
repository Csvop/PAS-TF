package com.bcopstein.Aplicacao.CasosDeUso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import com.bcopstein.Aplicacao.Validacao.FactoryValidacao;
import com.bcopstein.Negocio.Repositorio.IProdutos;
import com.bcopstein.Negocio.Servicos.ServicoDeEstoque;
import com.bcopstein.Negocio.Servicos.ServicoDeVendas;
import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Exception.SistVendasException;


@Component
public class CalculaSubtotalUC {
    private IProdutos produtos;
    private ServicoDeEstoque servicoDeEstoque;
    private FactoryValidacao factoryValidacao;
    private ServicoDeVendas servicoDeVendas;

    @Autowired
    public CalculaSubtotalUC(IProdutos produtos, ServicoDeEstoque servicoDeEstoque, FactoryValidacao factoryValidacao,
            ServicoDeVendas servicoDeVendas) {
        this.produtos = produtos;
        this.servicoDeEstoque = servicoDeEstoque;
        this.factoryValidacao = factoryValidacao;
        this.servicoDeVendas = servicoDeVendas;
    }

    public Integer[] execute(final List<ItemVenda> itensVenda) throws SistVendasException {
        // Verifica se todos os itens são válidos
        factoryValidacao.getRegraValidacao().valida(produtos, servicoDeEstoque, itensVenda);
        // Calcula os valores e retorna
        return servicoDeVendas.todosValores(itensVenda);
      }
    

    




    
}
