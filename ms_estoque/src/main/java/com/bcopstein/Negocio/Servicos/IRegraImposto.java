package com.bcopstein.Negocio.Servicos;

import java.util.List;

import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;

public interface IRegraImposto {
    public double calcular(List<ItemVenda> itens);
}
