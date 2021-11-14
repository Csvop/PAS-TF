package com.bcopstein.Negocio.Entidades.Venda;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Venda {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long codigo;
  private LocalDate data;

  @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
  private List<ItemVenda> itens;
  private double subtotal;
  private double impostos;
  private double valorFinal;
  private boolean fechada;

  public Venda() {
    //this.codigo = -1L;
    this.data = LocalDate.now();
    this.itens = new LinkedList<>();
    this.subtotal = 0.0;
    this.impostos = 0.0;
    this.valorFinal = 0.0;
    this.fechada = false;
  }

  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public LocalDate getData() {
    return data;
  }

  public List<ItemVenda> getItens(){
    return itens;
  }

  public double getSubtotal() {
    return subtotal;
  }

  public double getImpostos() {
    return impostos;
  }

  public double getValorFinal() {
    return valorFinal;
  }

  public boolean isFechada() {
    return fechada;
  }
 
  public boolean addItens(List<ItemVenda> itens) {
    if (!fechada) {
      this.itens.addAll(itens); // Aqui estava o bug. Faltava o this  
      return true;
    }
    return false;
  }

  public void fechaVenda(double subtotal, double impostos, double valorFinal) {
    fechada = true;
    this.subtotal = subtotal;
    this.impostos = impostos;
    this.valorFinal = valorFinal;
  }

  @Override
  public String toString() {
    return "Venda [codigo=" + codigo + ", data=" + data + ", fechada=" + fechada + ", impostos=" + impostos + ", itens="
        + itens.toString() + ", subtotal=" + subtotal + ", valorFinal=" + valorFinal + "]";
  }
  
}
