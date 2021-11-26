package com.bcopstein.Interface;

import com.bcopstein.Aplicacao.CasosDeUso.RegistraVendaUC;
import com.bcopstein.Negocio.Entidades.Venda.Venda;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Reciver { 
    Gson gson = new Gson();

    @Autowired
    RegistraVendaUC registraVendaUC;

    public void registraVenda(String json) {
      Venda venda = gson.fromJson(json, Venda.class);
      registraVendaUC.execute(venda);
    }
}