package com.bcopstein.Negocio.Entidades.ItemEstoque;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bcopstein.Negocio.Exception.SistVendasException;

@Entity
public class ItemEstoque {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long nroItem;
    private Long codigoProduto;
    private int quantidade;

    public ItemEstoque(Long codigoProduto, int quantidade) {
        if (quantidade < 0) {
            throw new SistVendasException(SistVendasException.Causa.QUANTIDADE_INVALIDA);
        } else {
            this.codigoProduto = codigoProduto;
            this.quantidade = quantidade;
        }
    }

    // Para uso da JPA
    protected ItemEstoque(){}
    
    public ItemEstoque(Long nroItem,Long codigoProduto, int quantidade) {
        this.nroItem = nroItem;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }


    public Long getNroItem() {
        return nroItem;
    }

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean disponivel(int quantidade) {
        return this.quantidade >= quantidade;
    }

    public void entrada(int quantidade) {
        if (quantidade <= 0) {
            throw new SistVendasException(SistVendasException.Causa.QUANTIDADE_INVALIDA);
        } else {
            this.quantidade += quantidade;
        }
    }

    public void saida(int quantidade) {
        if (quantidade <= 0) {
            throw new SistVendasException(SistVendasException.Causa.QUANTIDADE_INVALIDA);
        } else {
            if (this.quantidade - quantidade < 0) {
                throw new SistVendasException(SistVendasException.Causa.QUANTIDADE_INSUFICIENTE);
            } else {
                this.quantidade -= quantidade;
            }
        }
    }
}
