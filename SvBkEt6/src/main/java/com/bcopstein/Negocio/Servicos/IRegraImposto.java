package com.bcopstein.Negocio.Servicos;

import java.util.List;

import com.bcopstein.Interface.DTO.ItemVendaDTO;

public interface IRegraImposto {
    public double calcular(List<ItemVendaDTO> itens);
}
