package com.bcopstein.Interface;

import java.util.Collection;

import com.bcopstein.Negocio.Entidades.Venda.Venda;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="nota-fiscal")
public interface NotaFiscalProxy {

    @GetMapping("/nota-fiscal/lista")
    public Collection<Venda> listaVendas();

    @PostMapping("/nota-fiscal/venda")
    public boolean registraVenda(@RequestBody Venda venda);
}
