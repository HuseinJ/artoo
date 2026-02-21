package com.hjusic.artoo.email;

import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TemplateRenderingServiceTest {

  @Mock
  private TemplateEngine templateEngine;

  @InjectMocks
  private TemplateRenderingService templateRenderingService;

  @Test
  void render_shouldCallTemplateEngineAndReturnOutput() {
    // given
    String templateName = "test.jte";
    Object model = new Object();

    doAnswer(invocation -> {
      StringOutput output = invocation.getArgument(2);
      output.writeContent("<html>Rendered</html>");
      return null;
    }).when(templateEngine).render(eq(templateName), eq(model), any(StringOutput.class));

    // when
    String result = templateRenderingService.render(templateName, model);

    // then
    verify(templateEngine).render(eq(templateName), eq(model), any(StringOutput.class));
    assertThat(result).isEqualTo("<html>Rendered</html>");
  }

  @Test
  void render_shouldReturnEmptyStringWhenNoContent() {
    // given
    String templateName = "empty.jte";
    Object model = new Object();

    doNothing().when(templateEngine).render(eq(templateName), eq(model), any(StringOutput.class));

    // when
    String result = templateRenderingService.render(templateName, model);

    // then
    verify(templateEngine).render(eq(templateName), eq(model), any(StringOutput.class));
    assertThat(result).isEmpty();
  }
}
