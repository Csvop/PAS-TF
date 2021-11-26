package com.bcopstein.Interface.DTO.Imposto;

import java.util.List;

import com.bcopstein.Interface.DTO.ItemVendaDTO;
import com.bcopstein.Negocio.Servicos.IRegraImposto;

public class RegraImpostoComprasGrandes implements IRegraImposto {
    @Override
    public double calcular(List<ItemVendaDTO> itens) {
        // Ordena a lista em ordem crescente do custo dos itens
        itens.sort((it1,it2)->
          (int)((it1.getValorVendido()*it1.getQuantidade()) - (it2.getValorVendido()*it2.getQuantidade())));
        // Calcula 10% para os 3 itens mais caros (se houverem)
        int qtdade = Math.min(3,itens.size());
        double imposto = 0.0;
        for(int i=0;i<qtdade;i++){
            imposto += (itens.get(i).getValorVendido()*itens.get(i).getQuantidade()*0.1);
        }
        // Calcula o imposto sobre os demais itens se houverem
        for(int i=qtdade;i<itens.size();i++){
            imposto += (itens.get(i).getValorVendido()*itens.get(i).getQuantidade()*0.05);
        }
        return imposto;
    }
}
