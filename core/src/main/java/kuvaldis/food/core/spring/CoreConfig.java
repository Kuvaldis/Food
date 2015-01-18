package kuvaldis.food.core.spring;

import kuvaldis.food.fatsecret.config.FatSecretConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "kuvaldis.food.core")
@Import(FatSecretConfig.class)
public class CoreConfig {

}
