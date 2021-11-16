package com.bcopstein.Aplicacao.CasosDeUso;

import java.util.List;

import com.bcopstein.Negocio.Entidades.ItemEstoque.ItemEstoque;
import com.bcopstein.Negocio.Servicos.ServicoDeEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChegadaDeProdutosUC {
    ServicoDeEstoque servicoDeEstoque;

    @Autowired
    public ChegadaDeProdutosUC(ServicoDeEstoque servicoDeEstoque) {
        this.servicoDeEstoque = servicoDeEstoque;
    }

    public boolean execute(List<ItemEstoque> itens) {
        try {
            servicoDeEstoque.chegadaDeProdutos(itens);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
