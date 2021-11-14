package com.bcopstein.Interface.Persistencia.Vendas;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.bcopstein.Negocio.Entidades.Venda.Venda;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IVendas;

//import org.springframework.stereotype.Component;

//@Component
public class VendasImpl implements IVendas {
    private static Long nroVenda = 1L;
    private List<Venda> vendas;

    public VendasImpl() {
        vendas = new LinkedList<>();
        carrega();
    }

    @Override
    public void cadastra(Venda venda) {
        if (venda.getCodigo() == -1) {
            venda.setCodigo(nroVenda);
            nroVenda++;
        }
        vendas.add(venda);
    }

    @Override
    public Collection<Venda> todos() {
        return vendas;
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
        return vendas
          .stream()
          .filter(v->v.getCodigo() == chave)
          .findAny()
          .orElseThrow(VendasImpl::makeSVendaInexistenteException);
    }

    @Override
    public boolean existente(Long chave) {
      return vendas
        .stream()
        .anyMatch(v->v.getCodigo() == chave);
    }

    @Override
    public Collection<Venda> pesquisa(Predicate<Venda> pred) {
        return vendas
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
        Venda venda = recupera(chave);
        vendas.remove(venda);
    }
}
