package com.hjusic.artoo.config;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JteConfigTest {

  private final JteConfig jteConfig = new JteConfig();

  @Test
  void jteDevTemplateEngine_shouldCreateTemplateEngineWithDirectoryResolver() {
    // when
    TemplateEngine templateEngine = jteConfig.jteDevTemplateEngine();

    // then
    assertThat(templateEngine).isNotNull();
  }

  @Test
  void jteProdTemplateEngine_shouldCreatePrecompiledTemplateEngine() {
    // when
    TemplateEngine templateEngine = jteConfig.jteProdTemplateEngine();

    // then
    assertThat(templateEngine).isNotNull();
  }
}
