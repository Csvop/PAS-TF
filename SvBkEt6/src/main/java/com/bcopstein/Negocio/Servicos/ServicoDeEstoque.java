package com.bcopstein.Negocio.Servicos;

import java.util.Collection;
import java.util.List;

import com.bcopstein.Negocio.Entidades.ItemEstoque.ItemEstoque;
import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IEstoque;
import com.bcopstein.Negocio.Repositorio.IProdutos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicoDeEstoque {
    private IProdutos produtos;
    private IEstoque estoque;

    @Autowired
    public ServicoDeEstoque(IProdutos produtos, IEstoque estoque) {
        this.produtos = produtos;
        this.estoque = estoque;
    }

    public void cadastra(Long codigoProduto, int quantidade) {
        if (quantidade < 0) {
            throw new SistVendasException(SistVendasException.Causa.QUANTIDADE_INVALIDA);
        }
        if (!produtos.existente(codigoProduto)) {
            throw new SistVendasException(SistVendasException.Causa.PRODUTO_INEXISTENTE);
        }
        if (estoque.existente(codigoProduto)) {
            throw new SistVendasException(SistVendasException.Causa.CODIGO_DUPLICADO);
        }
        ItemEstoque it = new ItemEstoque(codigoProduto, quantidade);
        estoque.cadastra(it);
    }

    public void entradaDeProduto(int codigoProduto, int qtdade) {
        Collection<ItemEstoque> itens = estoque.pesquisa(ie -> ie.getCodigoProduto() == codigoProduto);
        if (itens.size() == 0) {
            throw new SistVendasException(SistVendasException.Causa.PRODUTO_NAO_CADASTRADO_ESTOQUE);
        } else {
            ItemEstoque it = (ItemEstoque) itens.toArray()[0];
            it.entrada(qtdade);
        }
    }

    public void saidaDeProduto(Long codigoProduto, int qtdade) {
        Collection<ItemEstoque> itens = estoque.pesquisa(ie -> ie.getCodigoProduto() == codigoProduto);
        if (itens.size() == 0) {
            throw new SistVendasException(SistVendasException.Causa.PRODUTO_NAO_CADASTRADO_ESTOQUE);
        } else {
            ItemEstoque it = (ItemEstoque) itens.toArray()[0];
            it.saida(qtdade);
        }
    }

    public void saidaDeProdutos(List<ItemVenda> itens){
        itens.forEach(it->saidaDeProduto(it.getCodigoProduto(),it.getQuantidade()));
    }

    public ItemEstoque recupera(Long codigoProduto) {
        Collection<ItemEstoque> itens = estoque.pesquisa(ie -> ie.getCodigoProduto() == codigoProduto);
        if (itens.size() == 0) {
            throw new SistVendasException(SistVendasException.Causa.PRODUTO_NAO_CADASTRADO_ESTOQUE);
        }
        return (ItemEstoque) itens.toArray()[0];
    }

    public boolean disponivel(Long codProd, int quantidade) {
        ItemEstoque it = recupera(codProd);
        return it.disponivel(quantidade);
    }
}
