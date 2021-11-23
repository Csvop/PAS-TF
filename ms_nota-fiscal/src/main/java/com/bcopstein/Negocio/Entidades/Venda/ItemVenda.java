package com.bcopstein.Negocio.Entidades.Venda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemVenda implements Comparable<ItemVenda>{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private int nro;
    private Long codigoProduto;
    private int quantidade;
    private double valorVendido;

    public ItemVenda(int nroItem,Long codigoProduto, int quantidade, double valorVendido) {
        this.nro = nroItem;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.valorVendido = valorVendido;
    }

    protected ItemVenda(){}

    public Long getId(){
        return id;
    }

    public int getNro() {
        return nro;
    }

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorVendido() {
        return valorVendido;
    }

    @Override
    public int compareTo(ItemVenda o) {
        return (int) (this.id - o.id);
    }

    @Override
    public String toString() {
        return "ItemVenda [codigoProduto=" + codigoProduto + ", id=" + id + ", nro=" + nro + ", quantidade="
                + quantidade + ", valorVendido=" + valorVendido + "]";
    }

    
}
