package com.bcopstein.Interface;

import java.util.Collection;

import com.bcopstein.Interface.DTO.ItemEstoqueDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="estoque")
public interface EstoqueProxy {
    
    @GetMapping("/estoque/verificaDisponivel/cod/{codigoProduto}/qnt/{quantidade}")
    public boolean disponivel(
        @PathVariable long codigoProduto, 
        @PathVariable int quantidade
    );
    
    @PostMapping("/estoque/baixa")
    public boolean saidaDeProdutos(
        @RequestBody Collection<ItemEstoqueDTO> produtos
    );
}
