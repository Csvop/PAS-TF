package com.bcopstein.Interface;

import java.util.Collection;

import com.bcopstein.Aplicacao.CasosDeUso.ListarProdutosUC;
import com.bcopstein.Aplicacao.CasosDeUso.VerificaQuantidadeUC;
import com.bcopstein.Negocio.Entidades.Produto.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
  private ListarProdutosUC listarProdutosUC;
  private VerificaQuantidadeUC verificaQuantidadeUC;

  @Autowired
  public EstoqueController(ListarProdutosUC listarProdutosUC, VerificaQuantidadeUC verificaQuantidadeUC) {
    this.listarProdutosUC = listarProdutosUC;
    this.verificaQuantidadeUC = verificaQuantidadeUC;
  }

  @GetMapping("/produtos")
  @CrossOrigin(origins = "*")
  public Collection<Produto> listaProdutos() {
    return listarProdutosUC.execute();
  }

  //Deve retornar se existe a quantidade demandada do produto em estoque
  //True == Valido / False == Invalido
  @PostMapping("/produto")
  @CrossOrigin(origins = "*")
  public boolean existeQuantidadeDeProduto(@RequestBody long codigo, @RequestBody int quantidade) {
    return verificaQuantidadeUC.verificaQuantidade(codigo, quantidade);
  }
}
