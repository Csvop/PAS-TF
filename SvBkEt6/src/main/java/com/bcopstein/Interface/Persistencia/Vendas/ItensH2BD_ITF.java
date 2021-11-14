package com.bcopstein.Interface.Persistencia.Vendas;

import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;

import org.springframework.data.repository.CrudRepository;

public interface ItensH2BD_ITF extends CrudRepository<ItemVenda,Long> {
    
}
