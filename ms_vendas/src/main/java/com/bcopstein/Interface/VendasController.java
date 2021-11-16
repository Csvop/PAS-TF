package com.bcopstein.Interface;

import com.bcopstein.Negocio.Entidades.Venda.Venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class VendasController {

  @Autowired
  public VendasController() {
    
  }

  //Registro de uma venda: recebe a descrição completa de uma venda e armazena em seu banco de dados local.
  //Deve usar comunicação assíncrona baseada em fila de mensagens.
  @PostMapping("/venda")
  public boolean registraVenda(@RequestParam Venda venda) {
    return false;
  }
}
