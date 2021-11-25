package com.bcopstein.Interface.DTO;

public class ItemEstoqueDTO {
    private Long nroItem;
    private Long codigoProduto;
    private int quantidade;

    public ItemEstoqueDTO(Long nroItem, Long codigoProduto, int quantidade) {
        this.nroItem = nroItem;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public Long getNroItem() {
        return nroItem;
    }

    public int getQuantidade() {
        return quantidade;
    }
}