package com.bcopstein.Interface.DTO.Imposto;

import java.util.List;

import com.bcopstein.Interface.DTO.ItemVendaDTO;
import com.bcopstein.Negocio.Servicos.IRegraImposto;

public class RegraImpostoOriginal implements IRegraImposto {
    @Override
    public double calcular(List<ItemVendaDTO> itens) {
        double soma = itens.stream().mapToDouble(it->it.getValorVendido()*it.getQuantidade()).sum();
        return soma * 0.1;
    }
}
