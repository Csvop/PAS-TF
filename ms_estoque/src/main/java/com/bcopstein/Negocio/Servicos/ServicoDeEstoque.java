package com.bcopstein.Negocio.Servicos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bcopstein.Negocio.Entidades.ItemEstoque.ItemEstoque;
import com.bcopstein.Negocio.Exception.SistVendasException;
import com.bcopstein.Negocio.Repositorio.IEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicoDeEstoque {
    private IEstoque estoque;

    @Autowired
    public ServicoDeEstoque(IEstoque estoque) {
        this.estoque = estoque;
    }

    public boolean baixa(Collection<ItemEstoque> itens) {
        Collection<ItemEstoque> itensRollback = estoque.todos();
        Collection<ItemEstoque> itensBD = estoque.todos();
        Collection<ItemEstoque> validos = new ArrayList<>();
        boolean precisaRollback = false;

        for (ItemEstoque itemBD : itensBD) {
            for(ItemEstoque item : itens) {
                if(item.getNroItem() == itemBD.getNroItem()
                && item.getCodigoProduto() == itemBD.getCodigoProduto()) {
                    validos.add(itemBD);
                }
            }
        }

        if(validos.size() == itens.size()) {
            
            //Verificação do Baixa
            for (ItemEstoque itemBD : itensBD) {
                for(ItemEstoque item : itens) {
                    if(item.getNroItem() == itemBD.getNroItem()
                    && item.getCodigoProduto() == itemBD.getCodigoProduto()) {
                        itemBD.saida(item.getQuantidade());
                        estoque.atualiza(itemBD);
                    }
                }
            }

            // Vereificação do Rollback
            for (ItemEstoque itemBD : itensBD) {
                for(ItemEstoque item : itens) {
                    try {
                        if(itemBD.getCodigoProduto() == item.getCodigoProduto()) {
                            itemBD.saida(item.getQuantidade());
                        }
                    } catch (Exception e) {
                        precisaRollback = true;
                    }
                }
            }

            if(precisaRollback) {
                rollback(itensRollback);
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    public void rollback(Collection<ItemEstoque> itens) {
        itens.forEach((item) -> {
            estoque.atualiza(item);
        });
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

                long maior = 0;
                for(ItemEstoque itemEstoque : itensEstoque) {
                    if(itemEstoque.getNroItem() > maior) {
                        maior = itemEstoque.getNroItem();
                    }
                }

                estoque.cadastra(new ItemEstoque(maior+1, item.getCodigoProduto(), item.getQuantidade()));
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

    public boolean disponivelEmEstoque(Long codProd, int quantidade) {
        Collection<ItemEstoque> itens = estoque.todos();
        boolean res = false;

        for(ItemEstoque itemEstoque : itens) {
            if(itemEstoque.getCodigoProduto() == codProd) {
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
