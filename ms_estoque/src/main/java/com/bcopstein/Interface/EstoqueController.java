package com.bcopstein.Interface;

import java.util.Collection;
import java.util.List;

import com.bcopstein.Aplicacao.CasosDeUso.ChegadaDeProdutosUC;
import com.bcopstein.Aplicacao.CasosDeUso.ListaItemsDisponiveisParaVendaUC;
import com.bcopstein.Aplicacao.CasosDeUso.ListaTodosItemsUC;
import com.bcopstein.Aplicacao.CasosDeUso.ValidaProdutoParaVendaUC;
import com.bcopstein.Negocio.Entidades.ItemEstoque.ItemEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  ValidaProdutoParaVendaUC validaProdutoParaVendaUC;

  @Autowired
  public EstoqueController(ChegadaDeProdutosUC chegadaDeProdutosUC, 
  ListaTodosItemsUC listaTodosItemsUC, ListaItemsDisponiveisParaVendaUC listaItemsDisponiveisParaVendaUC,
  ValidaProdutoParaVendaUC validaProdutoParaVendaUC) {
    this.chegadaDeProdutosUC = chegadaDeProdutosUC;
    this.listaTodosItemsUC = listaTodosItemsUC;
    this.listaItemsDisponiveisParaVendaUC = listaItemsDisponiveisParaVendaUC;
    this.validaProdutoParaVendaUC = validaProdutoParaVendaUC;
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

  // Produtos em estoque:
  // Deve retornar se existe a quantidade demandada de um produto no estoque.
  // True == existe a quantidade demandada no estoque.
  // False == não existe a quantidade demandada no estoque.
  @GetMapping("/verificaDisponivel/nro/{nroItem}/cod/{codigoProduto}/qnt/{quantidade}")
  public boolean produtoDisponivel(@PathVariable long nroItem,
  @PathVariable long codigoProduto, @PathVariable int quantidade) {
    return validaProdutoParaVendaUC.execute(nroItem, codigoProduto, quantidade);
  }
}