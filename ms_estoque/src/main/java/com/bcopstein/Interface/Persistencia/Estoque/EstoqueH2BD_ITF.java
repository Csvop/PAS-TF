package com.bcopstein.Interface.Persistencia.Estoque;

import java.util.List;

import com.bcopstein.Negocio.Entidades.ItemEstoque.ItemEstoque;

import org.springframework.data.repository.CrudRepository;

public interface EstoqueH2BD_ITF extends CrudRepository<ItemEstoque, Long> {
    List<ItemEstoque> findByCodigoProduto(Long codProd);
    List<ItemEstoque> findAll();    
}
