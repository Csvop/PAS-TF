package com.bcopstein.Interface.Persistencia.Estoque;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.bcopstein.Negocio.Entidades.ItemEstoque.ItemEstoque;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstoqueH2BD_IMPL implements IEstoque {
    private EstoqueH2BD_ITF estoque;

    @Autowired
    public EstoqueH2BD_IMPL(EstoqueH2BD_ITF estoque) {
        this.estoque = estoque;
    }

    @Override
    public void carrega() {
        // Não faz sentido na implementação em BD
    }

    @Override
    public void persiste() {
        // Não faz sentido na implementação em BD
    }

    @Override
    public void cadastra(ItemEstoque item) {
        // Garante que produto existe no repositório
        if (estoque.findByCodigoProduto(item.getCodigoProduto()).size() != 0) { 
            throw new SistVendasException(SistVendasException.Causa.CODIGO_DUPLICADO); 
        } 
        
        // Insere produto no estoque
        estoque.save(item); 
    }

    @Override
    public ItemEstoque recupera(Long codProd) {
        List<ItemEstoque> resp =  estoque.findByCodigoProduto(codProd);
        if (resp.size() == 0){
            return null;
        }
        return resp.get(0);
    }

    @Override
    public Collection<ItemEstoque> todos() {
        return estoque.findAll();
    }

    @Override
    public boolean existente(Long chave) {
        return recupera(chave) != null;
    }

    @Override
    public Collection<ItemEstoque> pesquisa(Predicate<ItemEstoque> pred) {
        return estoque.findAll()
                         .stream()
                         .filter(pred)
                         .collect(Collectors.toList());
    }

    @Override
    public void atualiza(ItemEstoque elemento) {
        estoque.save(elemento);
    }

    @Override
    public void remove(Long chave) {
        ItemEstoque tmp = recupera(chave);
        estoque.delete(tmp);
    }
}
