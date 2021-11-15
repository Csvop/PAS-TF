package com.bcopstein.Interface;

import java.util.Collection;
import java.util.List;

import com.bcopstein.Aplicacao.CasosDeUso.CalculaSubtotalUC;
import com.bcopstein.Aplicacao.CasosDeUso.ConfirmaVendaUC;
import com.bcopstein.Aplicacao.CasosDeUso.ListaVendasEfetuadasUC;
import com.bcopstein.Aplicacao.CasosDeUso.ListarProdutosUC;
import com.bcopstein.Aplicacao.CasosDeUso.PodeVenderUC;
import com.bcopstein.Interface.DTO.ItemCarrinhoDTO;
import com.bcopstein.Interface.DTO.MapeadorItemCarrinho;
import com.bcopstein.Negocio.Entidades.Produto.Produto;
import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Entidades.Venda.Venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class VendasController {
  private MapeadorItemCarrinho mapeadorIC;
  private ListarProdutosUC listarProdutosUC;
  private PodeVenderUC podeVenderUC;
  private CalculaSubtotalUC calculaSubtotalUC;
  private ListaVendasEfetuadasUC listaVendasEfetuadasUC;
  private ConfirmaVendaUC confirmaVendaUC;

  @Autowired
  public VendasController(ListarProdutosUC listarProdutosUC,
                          PodeVenderUC podeVenderUC,
                          CalculaSubtotalUC calculaSubtotalUC,
                          ListaVendasEfetuadasUC listaVendasEfetuadasUC,
                          ConfirmaVendaUC confirmaVendaUC,
                          MapeadorItemCarrinho mapeadorIC) {
    this.mapeadorIC = mapeadorIC;
    this.listarProdutosUC = listarProdutosUC;
    this.podeVenderUC = podeVenderUC;
    this.calculaSubtotalUC = calculaSubtotalUC;
    this.listaVendasEfetuadasUC = listaVendasEfetuadasUC;
    this.confirmaVendaUC = confirmaVendaUC;
  }

  @GetMapping("/produtos")
  @CrossOrigin(origins = "*")
  public Collection<Produto> listaProdutos() {
    return listarProdutosUC.execute();
  }
  
  @GetMapping("/autorizacao")
  @CrossOrigin(origins = "*")
  public boolean podeVender(@RequestParam final Integer codProd,
                            @RequestParam final Integer qtdade) {
      return podeVenderUC.execute(codProd, qtdade);
  }

  @PostMapping("/confirmacao")
  @CrossOrigin(origins = "*")
  public boolean confirmaVenda(@RequestBody final ItemCarrinhoDTO[] itens) {
    List<ItemVenda> itensVenda = mapeadorIC.ItemCarrinhoToItemvenda(itens);
    return confirmaVendaUC.execute(itensVenda);
  }

  @GetMapping("/historico")
  @CrossOrigin(origins = "*")
  public Collection<Venda> vendasEfetuadas() {
    Collection<Venda> aux =  listaVendasEfetuadasUC.execute();
    return aux;
  }

  @PostMapping("/subtotal")
  @CrossOrigin(origins = "*")
  public Integer[] calculaSubtotal(@RequestBody final ItemCarrinhoDTO[] itens) {
    List<ItemVenda> itensVenda = mapeadorIC.ItemCarrinhoToItemvenda(itens);
    return calculaSubtotalUC.execute(itensVenda);
  }
}
