package com.bcopstein.Aplicacao.CasosDeUso;

import com.bcopstein.Negocio.Servicos.ServicoDeVendas;
import com.bcopstein.Negocio.Entidades.Venda.Venda;

import org.springframework.stereotype.Component;
import java.util.Collection;

@Component
public class ListaVendasEfetuadasUC {
    private ServicoDeVendas servicoDeVendas;

    public ListaVendasEfetuadasUC(ServicoDeVendas servicoDeVendas) {
        this.servicoDeVendas = servicoDeVendas;
    }

    public Collection<Venda> execute() {
        return servicoDeVendas.todas();
      }
    
}
