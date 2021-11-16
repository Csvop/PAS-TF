package com.bcopstein.Interface;

import java.util.Collection;
import java.util.List;

import com.bcopstein.Aplicacao.CasosDeUso.ChegadaDeProdutosUC;
import com.bcopstein.Aplicacao.CasosDeUso.ListaTodosItemsUC;
import com.bcopstein.Negocio.Entidades.ItemEstoque.ItemEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
  // DEBUG
  ListaTodosItemsUC listaTodosItemsUC;
  // DEBUG

  ChegadaDeProdutosUC chegadaDeProdutosUC;

  @Autowired
  public EstoqueController(ChegadaDeProdutosUC chegadaDeProdutosUC, ListaTodosItemsUC listaTodosItemsUC) {
    this.chegadaDeProdutosUC = chegadaDeProdutosUC;
    this.listaTodosItemsUC = listaTodosItemsUC;
  }

  //Por motivos de debug
  // DEBUG// DEBUG // DEBUG// DEBUG // DEBUG// DEBUG // DEBUG
  // DEBUG// DEBUG // DEBUG// DEBUG // DEBUG// DEBUG // DEBUG
  @GetMapping("/todos")
  public Collection<ItemEstoque> todos() {
    return listaTodosItemsUC.execute();
  }
  // DEBUG// DEBUG // DEBUG // DEBUG // DEBUG// DEBUG // DEBUG
  // DEBUG// DEBUG // DEBUG// DEBUG // DEBUG// DEBUG // DEBUG

  @PostMapping("/chegada")
  public boolean chegadaDeProdutos(@RequestBody List<ItemEstoque> itens) {
    	return chegadaDeProdutosUC.execute(itens);
  }
}
