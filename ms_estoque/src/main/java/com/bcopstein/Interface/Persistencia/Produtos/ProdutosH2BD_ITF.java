package com.bcopstein.Interface.Persistencia.Produtos;

import java.util.List;

import com.bcopstein.Negocio.Entidades.Produto.Produto;

import org.springframework.data.repository.CrudRepository;

public interface ProdutosH2BD_ITF extends CrudRepository<Produto,Long> {
    List<Produto> findByCodigo(Long codProd);
    List<Produto> findAll();
}

