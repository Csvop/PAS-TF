package com.bcopstein.Interface.DTO;

public class ItemVendaDTO implements Comparable<ItemVendaDTO>{
    private Long id;
    private int nro;
    private Long codigoProduto;
    private int quantidade;
    private double valorVendido;

    public ItemVendaDTO(int nroItem,Long codigoProduto, int quantidade, double valorVendido) {
        this.nro = nroItem;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.valorVendido = valorVendido;
    }

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
    public int compareTo(ItemVendaDTO o) {
        return (int) (this.id - o.id);
    }

    @Override
    public String toString() {
        return "ItemVenda [codigoProduto=" + codigoProduto + ", id=" + id + ", nro=" + nro + ", quantidade="
                + quantidade + ", valorVendido=" + valorVendido + "]";
    }

    
}
