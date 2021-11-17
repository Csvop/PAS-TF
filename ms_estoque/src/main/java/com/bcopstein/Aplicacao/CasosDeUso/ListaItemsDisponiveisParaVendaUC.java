package com.bcopstein.Aplicacao.CasosDeUso;

import java.util.Collection;

import com.bcopstein.Negocio.Entidades.Produto.Produto;
import com.bcopstein.Negocio.Servicos.ServicoDeEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListaItemsDisponiveisParaVendaUC {
    ServicoDeEstoque servicoDeEstoque;
    
    @Autowired
    public ListaItemsDisponiveisParaVendaUC(ServicoDeEstoque servicoDeEstoque) {
        this.servicoDeEstoque = servicoDeEstoque;
    }

    public Collection<Produto> execute() {
        return servicoDeEstoque.disponiveis();
    }
}
