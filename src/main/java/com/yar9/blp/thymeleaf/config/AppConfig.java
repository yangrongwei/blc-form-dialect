package com.yar9.blp.thymeleaf.config;

import com.yar9.blp.thymeleaf.processor.Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class AppConfig {

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver)
    {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(new Dialect());
        return templateEngine;
    }
}
