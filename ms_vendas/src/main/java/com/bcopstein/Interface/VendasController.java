package com.bcopstein.Interface;

import com.bcopstein.Aplicacao.CasosDeUso.RegistraVendaUC;
import com.bcopstein.Negocio.Entidades.Venda.Venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class VendasController {
  private RegistraVendaUC registraVendaUC;

  @Autowired
  public VendasController(RegistraVendaUC registraVendaUC) {
    this.registraVendaUC = registraVendaUC;
  }

  //Registro de uma venda: recebe a descrição completa de uma venda e armazena em seu banco de dados local.
  //Deve usar comunicação assíncrona baseada em fila de mensagens.
  @PostMapping("/venda")
  public boolean registraVenda(@RequestBody Venda venda) {
    return registraVendaUC.execute(venda);
  }
}
