package com.bcopstein.Aplicacao.CasosDeUso;

import java.util.List;

import com.bcopstein.Aplicacao.Validacao.FactoryValidacao;
import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Entidades.Venda.Venda;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Exception.SistVendasException.Causa;
import com.bcopstein.Negocio.Repositorio.IProdutos;
import com.bcopstein.Negocio.Servicos.ServicoDeEstoque;
import com.bcopstein.Negocio.Servicos.ServicoDeVendas;

import org.springframework.stereotype.Component;

@Component
public class RegistraVendaUC {
    private ServicoDeEstoque servicoDeEstoque;
    private ServicoDeVendas servicoDeVendas;
    private IProdutos produtos;
    private FactoryValidacao factoryValidacao;

    public RegistraVendaUC(ServicoDeEstoque servicoDeEstoque, ServicoDeVendas servicoDeVendas, IProdutos produtos, FactoryValidacao factoryValidacao) {
        this.servicoDeEstoque = servicoDeEstoque;
        this.servicoDeVendas = servicoDeVendas;
        this.produtos = produtos;
        this.factoryValidacao = factoryValidacao;
    }

    public void registraVenda(Venda venda) throws SistVendasException {
        List<ItemVenda> itens = venda.getItens();
        for (ItemVenda item : itens) {
            if (!servicoDeEstoque.existeProduto(item.getCodigo())) {
                throw new SistVendasException(Causa.PRODUTO_INEXISTENTE);
            }
            if (!servicoDeEstoque.existeQuantidade(item.getProduto().getCodigo(), item.getQuantidade())) {
                throw new SistVendasException(Causa.QUANTIDADE_INSUFICIENTE);
            }
        }
        servicoDeVendas.registraVenda(venda);
        for (ItemVenda item : itens) {
            servicoDeEstoque.removeProduto(item.getProduto().getCodigo(), item.getQuantidade());
        }
    }
}
