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
    private IRegraImposto regraImposto;
    private IVendas vendas;

    @Autowired
    public ServicoDeVendas(IRegraImposto regraImposto,IVendas vendas) {
        this.regraImposto = regraImposto;
        this.vendas = vendas;
    }

    public void cadastra(Venda venda){
        vendas.cadastra(venda);
    }

    public Collection<Venda> todas(){
        return vendas.todos();
    }

    public Integer calculaSubtotal(List<ItemVenda> itens) {
        return (int) (itens.stream().mapToDouble(it -> it.getValorVendido() * it.getQuantidade()).sum());
    }

    public Integer calculaImpostos(List<ItemVenda> itens) {
        return (int) regraImposto.calcular(itens);
    }

    public Integer calculaPrecoFinal(List<ItemVenda> itens) {
        return calculaSubtotal(itens) + calculaImpostos(itens);
    }

    public Integer[] todosValores(List<ItemVenda> itens) {
        Integer[] valores = new Integer[3];
        valores[0] = calculaSubtotal(itens);
        valores[1] = calculaImpostos(itens);
        valores[2] = calculaPrecoFinal(itens);
        return valores;
    }
}
