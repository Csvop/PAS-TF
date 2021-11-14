package com.bcopstein.Interface.Persistencia.Produtos;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.bcopstein.Negocio.Entidades.Produto.Produto;
import com.bcopstein.Negocio.Repositorio.IProdutos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutosH2BD_IMPL implements IProdutos {
    private ProdutosH2BD_ITF produtosBD;

    @Autowired
    public ProdutosH2BD_IMPL(ProdutosH2BD_ITF produtosBD){
        this.produtosBD = produtosBD;
    }

    @Override
    public void carrega() {
        // Não faz mais sentido !!
    }

    @Override
    public void persiste() {
        // Não faz mais sentido
    }

    @Override
    public void cadastra(Produto produto) {
        produtosBD.save(produto);

    }

    @Override
    public Produto recupera(Long chave) {
        //return produtosBD.findById(chave).orElse(null);
        return produtosBD.findByCodigo(chave).get(0);
    }

    @Override
    public Collection<Produto> todos() {
        return produtosBD.findAll();
    }

    @Override
    public boolean existente(Long chave) {
        return produtosBD.existsById(chave);
    }

    @Override
    public Collection<Produto> pesquisa(Predicate<Produto> pred) {
        return produtosBD.findAll()
                         .stream()
                         .filter(pred)
                         .collect(Collectors.toList());
    }

    @Override
    public void atualiza(Produto produto) {
        produtosBD.save(produto);
    }

    @Override
    public void remove(Long chave) {
        produtosBD.deleteById(chave);
    }
}
