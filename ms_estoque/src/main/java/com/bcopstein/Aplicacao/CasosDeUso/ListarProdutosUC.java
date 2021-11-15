package com.bcopstein.Aplicacao.CasosDeUso;

import java.util.Collection;

import com.bcopstein.Negocio.Entidades.Produto.Produto;
import com.bcopstein.Negocio.Repositorio.IProdutos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListarProdutosUC {
    private IProdutos produtos;

    @Autowired
    public ListarProdutosUC(IProdutos produtos) {
        this.produtos = produtos;
    }
    
    public Collection<Produto> execute(){
        return produtos.todos();
    }   
}
