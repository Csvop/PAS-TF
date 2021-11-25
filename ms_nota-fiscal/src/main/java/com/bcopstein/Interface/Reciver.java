package com.bcopstein.Interface;

import com.bcopstein.Aplicacao.CasosDeUso.RegistraVendaUC;
import com.bcopstein.Negocio.Entidades.Venda.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Reciver { 

    @Autowired
    RegistraVendaUC registraVendaUC;

    public void registraVenda(Venda venda) {
      registraVendaUC.execute(venda);
    }
}