package com.bcopstein.Aplicacao.CasosDeUso;

import com.bcopstein.Negocio.Repositorio.IProdutos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificaQuantidadeUC {
    private IProdutos produtos;

    @Autowired
    public VerificaQuantidadeUC(IProdutos produtos) {
        this.produtos = produtos;
    }

    public boolean verificaQuantidade(long codigo, int quantidade) {
        return false;
    }
}
