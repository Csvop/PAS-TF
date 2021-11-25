package com.bcopstein.Aplicacao.CasosDeUso;

import java.util.Collection;

import com.bcopstein.Negocio.Entidades.ItemEstoque.ItemEstoque;
import com.bcopstein.Negocio.Servicos.ServicoDeEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaixaDeProdutoUC {
    ServicoDeEstoque servicoDeEstoque;

    @Autowired
    public BaixaDeProdutoUC(ServicoDeEstoque servicoDeEstoque) {
        this.servicoDeEstoque = servicoDeEstoque;
    }    

    public boolean execute(Collection<ItemEstoque> itens) {
        return servicoDeEstoque.baixa(itens);
    }
}
