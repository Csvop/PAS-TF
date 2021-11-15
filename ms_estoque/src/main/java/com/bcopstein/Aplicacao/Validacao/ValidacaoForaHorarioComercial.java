package com.bcopstein.Aplicacao.Validacao;

import java.util.List;

import com.bcopstein.Negocio.Entidades.Produto.Produto;
import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IProdutos;
import com.bcopstein.Negocio.Servicos.ServicoDeEstoque;

public class ValidacaoForaHorarioComercial implements IRegraValidacao {
    @Override
    public void valida(IProdutos produtos, ServicoDeEstoque servicoDeEstoque, List<ItemVenda> itens) throws SistVendasException {
        System.out.println("Fora horario comercial");
        if (itens.size()>5){
            throw new SistVendasException(SistVendasException.Causa.VENDA_COM_EXCESSO_DE_ITENS);
        }
        for (ItemVenda iv : itens) {
            final int quantidade = iv.getQuantidade();
            if (!servicoDeEstoque.disponivel(iv.getCodigoProduto(), quantidade)){
                throw new SistVendasException(SistVendasException.Causa.QUANTIDADE_INSUFICIENTE);
            }
            final Produto produto = produtos.recupera(iv.getCodigoProduto());
            if ((produto.getPreco() * iv.getQuantidade()) > 5000.0){
                throw new SistVendasException(SistVendasException.Causa.VENDA_COM_ITEM_MUITO_CARO);
            }
        }       
    }
}
