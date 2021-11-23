package com.bcopstein.Interface;

import java.util.Collection;

import com.bcopstein.Aplicacao.CasosDeUso.ListaVendaUC;
import com.bcopstein.Aplicacao.CasosDeUso.RegistraVendaUC;
import com.bcopstein.Negocio.Entidades.Venda.Venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notafiscal")
public class VendasController {
  private RegistraVendaUC registraVendaUC;
  private ListaVendaUC listaVendaUC;

  @Autowired
  public VendasController(RegistraVendaUC registraVendaUC, ListaVendaUC listaVendaUC) {
    this.registraVendaUC = registraVendaUC;
    this.listaVendaUC = listaVendaUC;
  }

  //Registro de uma venda: recebe a descrição completa de uma venda e armazena em seu banco de dados local.
  //Deve usar comunicação assíncrona baseada em fila de mensagens.
  @PostMapping("/venda")
  public boolean registraVenda(@RequestBody Venda venda) {
    return registraVendaUC.execute(venda);
  }

  //Lista de vendas: responde com a relação total das vendas efetuadas até agora.
  //Deve ser usada comunicação síncrona através de um “controller”.
  @GetMapping("/lista")
  public Collection<Venda> listaVendas() {
    return listaVendaUC.execute();
  }
}
