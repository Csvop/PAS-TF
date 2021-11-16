package com.bcopstein.Interface;

import java.util.Collection;
import java.util.List;

import com.bcopstein.Aplicacao.CasosDeUso.ChegadaDeProdutosUC;
import com.bcopstein.Aplicacao.CasosDeUso.ListaItemsDisponiveisParaVendaUC;
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
  ListaItemsDisponiveisParaVendaUC listaItemsDisponiveisParaVendaUC;

  @Autowired
  public EstoqueController(ChegadaDeProdutosUC chegadaDeProdutosUC, ListaTodosItemsUC listaTodosItemsUC, ListaItemsDisponiveisParaVendaUC listaItemsDisponiveisParaVendaUC) {
    this.chegadaDeProdutosUC = chegadaDeProdutosUC;
    this.listaTodosItemsUC = listaTodosItemsUC;
    this.listaItemsDisponiveisParaVendaUC = listaItemsDisponiveisParaVendaUC;
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

  // Chegada de itens no estoque: 
  // Usada para informar a chegada de novos itens no estoque.
  @PostMapping("/chegada")
  public boolean chegadaDeProdutos(@RequestBody List<ItemEstoque> itens) {
    	return chegadaDeProdutosUC.execute(itens);
  }

  // Produtos vendidos:
  // Deve retornar à relação de produtos disponíveis para venda.
  @GetMapping("/disponiveis")
  public Collection<ItemEstoque> disponiveisParaVenda() {
    return listaItemsDisponiveisParaVendaUC.execute();
  }
}
