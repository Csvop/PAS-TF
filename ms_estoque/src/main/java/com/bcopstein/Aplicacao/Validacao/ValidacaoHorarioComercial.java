package com.bcopstein.Aplicacao.Validacao;

import java.util.List;

import com.bcopstein.Negocio.Entidades.Produto.Produto;
import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IProdutos;
import com.bcopstein.Negocio.Servicos.ServicoDeEstoque;

public class ValidacaoHorarioComercial implements IRegraValidacao {

    @Override
    public void valida(IProdutos produtos, ServicoDeEstoque servicoDeEstoque, List<ItemVenda> itens) throws SistVendasException {
        System.out.println("Horario comercial");
        for (ItemVenda iv : itens) {
            final Produto produto = produtos.recupera(iv.getCodigoProduto());
            final int quantidade = iv.getQuantidade();
            if (!servicoDeEstoque.disponivel(produto.getCodigo(),quantidade)){
                throw new SistVendasException(SistVendasException.Causa.QUANTIDADE_INSUFICIENTE);
            }
        }
    }
}
