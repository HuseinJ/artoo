package com.hjusic.artoo.config;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import java.nio.file.Path;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class JteConfig {

  @Bean
  @Profile("dev")
  public TemplateEngine jteDevTemplateEngine() {
    Path templatesDir = Path.of("src/main/jte");
    return TemplateEngine.create(
        new DirectoryCodeResolver(templatesDir),
        ContentType.Html
    );
  }

  @Bean
  @Profile("!dev")
  public TemplateEngine jteProdTemplateEngine() {
    return TemplateEngine.createPrecompiled(ContentType.Html);
  }
}
