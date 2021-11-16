package com.bcopstein.Negocio.Servicos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bcopstein.Negocio.Entidades.ItemEstoque.ItemEstoque;
import com.bcopstein.Negocio.Entidades.Venda.ItemVenda;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IEstoque;
import com.bcopstein.Negocio.Repositorio.IProdutos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicoDeEstoque {
    private IProdutos produtos;
    private IEstoque estoque;

    @Autowired
    public ServicoDeEstoque(IProdutos produtos, IEstoque estoque) {
        this.produtos = produtos;
        this.estoque = estoque;
    }

    public boolean baixa(Collection<ItemEstoque> itens) {
        Collection<ItemEstoque> validos = new ArrayList<>();

        disponiveis().forEach((item) -> {
            if(item.disponivel(item.getQuantidade())) {
                validos.add(item);
            }
        });

        if(itens.size() == validos.size()) {

            validos.forEach((item) -> {
                int qnt = item.getQuantidade();
                item.saida(qnt);
                
                //Atualiza o item
                estoque.atualiza(item);
            });

            return true;
        } else {
            return false;
        }
    }

    public void cadastra(Long codigoProduto, int quantidade) {
        if (quantidade < 0) {
            throw new SistVendasException(SistVendasException.Causa.QUANTIDADE_INVALIDA);
        }
        if (!produtos.existente(codigoProduto)) {
            throw new SistVendasException(SistVendasException.Causa.PRODUTO_INEXISTENTE);
        }
        if (estoque.existente(codigoProduto)) {
            throw new SistVendasException(SistVendasException.Causa.CODIGO_DUPLICADO);
        }
        ItemEstoque it = new ItemEstoque(codigoProduto, quantidade);
        estoque.cadastra(it);
    }

    public void entradaDeProduto(int codigoProduto, int qtdade) {
        Collection<ItemEstoque> itens = estoque.pesquisa(ie -> ie.getCodigoProduto() == codigoProduto);
        if (itens.size() == 0) {
            throw new SistVendasException(SistVendasException.Causa.PRODUTO_NAO_CADASTRADO_ESTOQUE);
        } else {
            ItemEstoque it = (ItemEstoque) itens.toArray()[0];
            it.entrada(qtdade);
        }
    }

    public void chegadaDeProdutos(List<ItemEstoque> itens) {
        itens.forEach((item) -> {
            Collection<ItemEstoque> itensEstoque = estoque.todos();
            boolean existe = false;
            for(ItemEstoque itemEstoque : itensEstoque) {
                if(itemEstoque.getNroItem() == item.getNroItem() && itemEstoque.getCodigoProduto() == item.getCodigoProduto()) {
                    System.out.println("\nAtualizando a quantidade no estoque:\nQuantidade Antes -> "+itemEstoque.getQuantidade());

                    itemEstoque.entrada(item.getQuantidade());
                    existe = true;

                    //Atualiza o item
                    estoque.atualiza(itemEstoque);

                    System.out.println("Quantidade Depois -> "+itemEstoque.getQuantidade());
                }
            }
            if(!existe) {
                System.out.println("\n\nCadastrando novo produto!");
                cadastra(item.getCodigoProduto(), item.getQuantidade());
            }
        });
    } 

    public void saidaDeProduto(Long codigoProduto, int qtdade) {
        Collection<ItemEstoque> itens = estoque.pesquisa(ie -> ie.getCodigoProduto() == codigoProduto);
        if (itens.size() == 0) {
            throw new SistVendasException(SistVendasException.Causa.PRODUTO_NAO_CADASTRADO_ESTOQUE);
        } else {
            ItemEstoque it = (ItemEstoque) itens.toArray()[0];
            it.saida(qtdade);
        }
    }

    public void saidaDeProdutos(List<ItemVenda> itens){
        itens.forEach(it->saidaDeProduto(it.getCodigoProduto(),it.getQuantidade()));
    }

    public ItemEstoque recupera(Long codigoProduto) {
        Collection<ItemEstoque> itens = estoque.pesquisa(ie -> ie.getCodigoProduto() == codigoProduto);
        if (itens.size() == 0) {
            throw new SistVendasException(SistVendasException.Causa.PRODUTO_NAO_CADASTRADO_ESTOQUE);
        }
        return (ItemEstoque) itens.toArray()[0];
    }

    public boolean disponivel(Long codProd, int quantidade) {
        ItemEstoque it = recupera(codProd);
        return it.disponivel(quantidade);
    }

    public boolean disponivelEmEstoque(Long nroItem, Long codProd, int quantidade) {
        Collection<ItemEstoque> itens = estoque.todos();
        boolean res = false;

        for(ItemEstoque itemEstoque : itens) {
            if(itemEstoque.getNroItem() == nroItem && itemEstoque.getCodigoProduto() == codProd) {
                res = itemEstoque.disponivel(quantidade);
            }
        }

        return res;
    }
   
    public Collection<ItemEstoque> disponiveis() {
        Collection<ItemEstoque> itens = estoque.todos();
        
        itens.removeIf(i -> i.getQuantidade() <= 0);
        
        return itens;
    }

    // DEBUG // DEBUG // DEBUG // DEBUG // DEBUG // DEBUG
    public Collection<ItemEstoque> listaTodos() {
        return estoque.todos();
    }
    // DEBUG // DEBUG // DEBUG // DEBUG // DEBUG // DEBUG
}
