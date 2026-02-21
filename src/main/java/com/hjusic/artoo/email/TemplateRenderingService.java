package com.hjusic.artoo.email;

import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;
import org.springframework.stereotype.Service;

@Service
public class TemplateRenderingService {

  private final TemplateEngine templateEngine;

  public TemplateRenderingService(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  public String render(String template, Object model) {
    StringOutput output = new StringOutput();
    templateEngine.render(template, model, output);
    return output.toString();
  }
}
