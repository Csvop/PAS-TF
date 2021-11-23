package com.bcopstein.Interface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="estoque", url="http://localhost:8000")
public interface EstoqueProxy {
    
    @GetMapping("/estoque/verificaDisponivel/cod/{codigoProduto}/qnt/{quantidade}")
    public boolean disponivel(
        @PathVariable long codigoProduto, 
        @PathVariable int quantidade);
    
}
