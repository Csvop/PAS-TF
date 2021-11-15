package com.bcopstein.Aplicacao.Validacao;

import java.util.List;

import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IProdutos;
import com.bcopstein.Negocio.Servicos.ServicoDeEstoque;

public interface IRegraValidacao {
    public void valida(IProdutos produtos, ServicoDeEstoque servicoEstoque, List<ItemVenda> itens) throws SistVendasException;
}
