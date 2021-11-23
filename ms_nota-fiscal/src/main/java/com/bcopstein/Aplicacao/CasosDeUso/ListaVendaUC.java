package com.bcopstein.Aplicacao.CasosDeUso;

import java.util.Collection;

import com.bcopstein.Negocio.Entidades.Venda.Venda;
import com.bcopstein.Negocio.Servicos.ServicoDeVendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListaVendaUC {
    ServicoDeVendas servicoDeVendas;

    @Autowired
    public ListaVendaUC(ServicoDeVendas servicoDeVendas){
        this.servicoDeVendas = servicoDeVendas;
    }

    public Collection<Venda> execute(){
        return servicoDeVendas.listaVendas();
    }
}
