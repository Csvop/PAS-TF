package com.bcopstein.Aplicacao.CasosDeUso;

import com.bcopstein.Negocio.Entidades.Venda.Venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistraVendaUC {
    
    @Autowired
    public RegistraVendaUC() {
        
    }

    public boolean execute(Venda venda) {
        //Armazena a venda no banco de dados
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
