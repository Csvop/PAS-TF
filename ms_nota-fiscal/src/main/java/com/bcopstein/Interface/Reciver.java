package com.bcopstein.Interface;

import com.bcopstein.Aplicacao.CasosDeUso.RegistraVendaUC;
import com.bcopstein.Negocio.Entidades.Venda.Venda;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Reciver { 
    @Autowired
    RegistraVendaUC registraVendaUC;

    public void registraVenda(String json) {
      //Gson gson = new Gson();
      //Venda venda = gson.fromJson(json, Venda.class);
      //registraVendaUC.execute(venda);
      System.out.println("\n\n\n\n\n\n\n");
      System.out.println(json);
      System.out.println("\n\n\n\n\n\n\n");
    }
}