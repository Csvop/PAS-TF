package com.bcopstein.Aplicacao.CasosDeUso;

import com.bcopstein.Negocio.Entidades.Venda.Venda;
import com.bcopstein.Negocio.Servicos.ServicoDeVendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistraVendaUC {
    ServicoDeVendas servicoDeVendas;

    @Autowired
    public RegistraVendaUC(ServicoDeVendas servicoDeVendas) {
        this.servicoDeVendas = servicoDeVendas;
    }

    public boolean execute(Venda venda) {
        //Armazena a venda no banco de dados
        try {
            servicoDeVendas.cadastra(venda);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
