package com.bcopstein.Aplicacao.CasosDeUso;

import com.bcopstein.Negocio.Entidades.ItemEstoque.ItemEstoque;
import com.bcopstein.Negocio.Servicos.ServicoDeEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PodeVenderUC {
    private ServicoDeEstoque servicoDeEstoque;

    @Autowired
    public PodeVenderUC(ServicoDeEstoque servicoDeEstoque) {
        this.servicoDeEstoque = servicoDeEstoque;
    }

    public boolean execute(final Integer codProd, final Integer qtdade) {
        ItemEstoque itemEstoque = servicoDeEstoque.recupera((long) codProd);
        if (itemEstoque == null) {
            return false;
        } else {
            return itemEstoque.disponivel(qtdade);
        }
    }

}
