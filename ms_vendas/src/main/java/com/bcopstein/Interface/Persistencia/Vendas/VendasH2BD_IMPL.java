package com.bcopstein.Interface.Persistencia.Vendas;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.bcopstein.Negocio.Entidades.Venda.Venda;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IVendas;

import org.springframework.stereotype.Component;

@Component
public class VendasH2BD_IMPL implements IVendas {
    private VendasH2BD_ITF vendasBD;

    public VendasH2BD_IMPL(VendasH2BD_ITF vendasBD) {
        this.vendasBD = vendasBD;
    }

    @Override
    public void cadastra(Venda venda) {
        vendasBD.save(venda);
    }

    @Override
    public Collection<Venda> todos() {
        return vendasBD.findAll();
    }

    @Override
    public void carrega() {
        // Não faz sentido no momento
    }

    @Override
    public void persiste() {
        // Não faz sentido no momento
    }

    static SistVendasException makeSVendaInexistenteException() { 
        return new SistVendasException(SistVendasException.Causa.VENDA_INEXISTENTE);
    }

    @Override
    public Venda recupera(Long chave) {
        return vendasBD.findById(chave)
               .orElseThrow(VendasImpl::makeSVendaInexistenteException);
    }

    @Override
    public boolean existente(Long chave) {
      return vendasBD.existsById(chave);
    }

    @Override
    public Collection<Venda> pesquisa(Predicate<Venda> pred) {
        return vendasBD.findAll()
        .stream()
        .filter(pred)
        .collect(Collectors.toList());
    }

    @Override
    public void atualiza(Venda elemento) {
        throw new IllegalStateException("Operacao nao permitida");
    }

    @Override
    public void remove(Long chave) {
        vendasBD.deleteById(chave);;
    }
}
