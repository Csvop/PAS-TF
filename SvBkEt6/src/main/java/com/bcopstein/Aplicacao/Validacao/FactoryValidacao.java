package com.bcopstein.Aplicacao.Validacao;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

@Component
public class FactoryValidacao {
    public IRegraValidacao getRegraValidacao(){
        if (LocalTime.parse("08:00").isAfter(LocalTime.now()) &&
            LocalTime.parse("18:00").isBefore(LocalTime.now())){
                return new ValidacaoHorarioComercial();
        }else{
            return new ValidacaoHorarioComercial();
        }
    } 
}
