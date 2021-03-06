package com.bcopstein.Negocio.Exception;

public class SistVendasException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public enum Causa {
        CODIGO_DUPLICADO,
        PRODUTO_INEXISTENTE,
        QUANTIDADE_INVALIDA,
        QUANTIDADE_INSUFICIENTE,
        PRODUTO_NAO_CADASTRADO_ESTOQUE,
        VENDA_INEXISTENTE,
        VENDA_COM_EXCESSO_DE_ITENS,
        VENDA_COM_ITEM_MUITO_CARO
    };

    private Causa causa;

    public SistVendasException(Causa causa){
        super(causa.toString());
        this.causa = causa;
    }

    public Causa getCausa(){
        return causa;
    }
}
