package com.bcopstein.Interface.DTO;

import java.util.ArrayList;
import java.util.List;

import com.bcopstein.Negocio.Entidades.Produto.Produto;
import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IProdutos;

import org.springframework.stereotype.Component;

@Component
public class MapeadorItemCarrinho {
    private IProdutos produtos;

    public MapeadorItemCarrinho(IProdutos produtos) {
        this.produtos = produtos;
    }

    public List<ItemVenda> ItemCarrinhoToItemvenda(ItemCarrinhoDTO[] itens) {
        List<ItemVenda> itensVenda = new ArrayList<>(itens.length);
        for (ItemCarrinhoDTO item : itens) {
            final Produto produto = produtos.recupera((long)item.getCodigo());
            if (produto == null)
                throw new SistVendasException(SistVendasException.Causa.PRODUTO_INEXISTENTE);
            ItemVenda itemVenda = new ItemVenda(itensVenda.size() + 1, produto.getCodigo(), item.getQuantidade(),
                    produto.getPreco());
            itensVenda.add(itemVenda);
        }
        return itensVenda;
    }
}
