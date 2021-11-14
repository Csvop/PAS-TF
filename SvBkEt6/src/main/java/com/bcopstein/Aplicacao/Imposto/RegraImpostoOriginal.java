package com.bcopstein.Aplicacao.Imposto;

import java.util.List;

import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Servicos.IRegraImposto;

public class RegraImpostoOriginal implements IRegraImposto {
    @Override
    public double calcular(List<ItemVenda> itens) {
        double soma = itens.stream().mapToDouble(it->it.getValorVendido()*it.getQuantidade()).sum();
        return soma * 0.1;
    }
}
