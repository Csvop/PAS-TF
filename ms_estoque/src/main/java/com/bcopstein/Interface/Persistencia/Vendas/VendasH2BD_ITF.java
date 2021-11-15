package com.bcopstein.Interface.Persistencia.Vendas;

import java.util.List;

import com.bcopstein.Negocio.Entidades.Venda.Venda;

import org.springframework.data.repository.CrudRepository;

public interface VendasH2BD_ITF extends CrudRepository<Venda,Long>{
    List<Venda> findAll();
}

