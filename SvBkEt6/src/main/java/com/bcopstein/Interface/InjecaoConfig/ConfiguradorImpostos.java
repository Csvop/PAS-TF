package com.bcopstein.Interface.InjecaoConfig;

import com.bcopstein.Aplicacao.Imposto.RegraImpostoComprasGrandes;
import com.bcopstein.Aplicacao.Imposto.RegraImpostoOriginal;
import com.bcopstein.Negocio.Servicos.IRegraImposto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguradorImpostos{
    @Bean
    @ConditionalOnProperty(name = "regra.imposto", havingValue = "original", matchIfMissing = true)
    public IRegraImposto opcaoRegraClassica() {
        return new RegraImpostoOriginal();
    }
 
    @Bean
    @ConditionalOnProperty(name = "regra.imposto", havingValue = "grandes")
    public IRegraImposto opcaoRegraComprasGrandes() {
        return new RegraImpostoComprasGrandes();
    }
}
