package com.bcopstein.Aplicacao.Validacao;

import java.util.List;

import com.bcopstein.Interface.EstoqueProxy;
import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IProdutos;

public interface IRegraValidacao {
    public void valida(IProdutos produtos, List<ItemVenda> itens, EstoqueProxy estoqueProxy) throws SistVendasException;
}
