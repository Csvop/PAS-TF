package com.bcopstein.Negocio.Entidades.Produto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {
  @Id
  private Long codigo;
  private String descricao;
  private double preco;

  protected Produto(){}
  
  public Produto(Long codigo, String descricao, double preco) {
    this.codigo = codigo;
    this.descricao = descricao;
    this.preco = preco;
  }

  public long getCodigo() {
    return codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  public double getPreco() {
    return preco;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }

  @Override
  public String toString() {
    return "Produto [codigo=" + codigo + ", descricao=" + descricao +
           ", preco=" + preco + "]";
  }
}
