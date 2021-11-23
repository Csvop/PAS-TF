package com.bcopstein.Negocio.Servicos;

import java.util.Collection;
import java.util.List;

import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Entidades.Venda.Venda;
import com.bcopstein.Negocio.Repositorio.IVendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicoDeVendas {
    private IVendas vendas;

    @Autowired
    public ServicoDeVendas(IVendas vendas) {
        this.vendas = vendas;
    }

    public void cadastra(Venda venda){
        vendas.cadastra(venda);
    }

    public Integer calculaSubtotal(List<ItemVenda> itens) {
        return (int) (itens.stream().mapToDouble(it -> it.getValorVendido() * it.getQuantidade()).sum());
    }

    public Collection<Venda> listaVendas() {
        return vendas.todos();
    }
}
