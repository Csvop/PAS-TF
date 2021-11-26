package com.bcopstein.Aplicacao.Validacao;

import java.util.List;

import com.bcopstein.Interface.EstoqueProxy;
import com.bcopstein.Interface.DTO.ItemVendaDTO;
import com.bcopstein.Negocio.Entidades.Produto.Produto;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IProdutos;

public class ValidacaoForaHorarioComercial implements IRegraValidacao {
    @Override
    public void valida(IProdutos produtos, List<ItemVendaDTO> itens, EstoqueProxy estoqueProxy) throws SistVendasException {
        System.out.println("Fora horario comercial");
        if (itens.size()>5){
            throw new SistVendasException(SistVendasException.Causa.VENDA_COM_EXCESSO_DE_ITENS);
        }
        for (ItemVendaDTO iv : itens) {
            final int quantidade = iv.getQuantidade();

            if(!estoqueProxy.disponivel(iv.getCodigoProduto(), quantidade)) {
                throw new SistVendasException(SistVendasException.Causa.QUANTIDADE_INSUFICIENTE);
            }
            
            final Produto produto = produtos.recupera(iv.getCodigoProduto());
            if ((produto.getPreco() * iv.getQuantidade()) > 5000.0){
                throw new SistVendasException(SistVendasException.Causa.VENDA_COM_ITEM_MUITO_CARO);
            }
        }       
    }
}
