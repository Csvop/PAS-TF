package com.bcopstein.Negocio.Servicos;

import java.util.List;

import com.bcopstein.Interface.DTO.ItemVendaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicoDeVendas {
    private IRegraImposto regraImposto;

    @Autowired
    public ServicoDeVendas(IRegraImposto regraImposto) {
        this.regraImposto = regraImposto;
    }

    public Integer calculaSubtotal(List<ItemVendaDTO> itens) {
        return (int) (itens.stream().mapToDouble(it -> it.getValorVendido() * it.getQuantidade()).sum());
    }

    public Integer calculaImpostos(List<ItemVendaDTO> itens) {
        return (int) regraImposto.calcular(itens);
    }

    public Integer calculaPrecoFinal(List<ItemVendaDTO> itens) {
        return calculaSubtotal(itens) + calculaImpostos(itens);
    }

    public Integer[] todosValores(List<ItemVendaDTO> itens) {
        Integer[] valores = new Integer[3];
        valores[0] = calculaSubtotal(itens);
        valores[1] = calculaImpostos(itens);
        valores[2] = calculaPrecoFinal(itens);
        return valores;
    }
}
