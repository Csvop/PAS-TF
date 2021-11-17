package com.bcopstein.Aplicacao.CasosDeUso;

import com.bcopstein.Negocio.Servicos.ServicoDeEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaProdutoParaVendaUC {
    ServicoDeEstoque servicoDeEstoque;

    @Autowired
    public ValidaProdutoParaVendaUC(ServicoDeEstoque servicoDeEstoque) {
        this.servicoDeEstoque = servicoDeEstoque;
    }

    public boolean execute(long codigoProduto, int quantidade) {
        return servicoDeEstoque.disponivelEmEstoque(codigoProduto, quantidade);
    }
}