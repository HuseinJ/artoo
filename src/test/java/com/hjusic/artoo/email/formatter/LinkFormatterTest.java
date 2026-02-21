package com.hjusic.artoo.email.formatter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LinkFormatterTest {

  @Test
  void linkify_shouldConvertHttpUrlToLink() {
    // given
    String text = "Visit http://example.com for more info";

    // when
    String result = LinkFormatter.linkify(text);

    // then
    assertThat(result).isEqualTo("Visit <a href=\"http://example.com\" style=\"color:#4f8ef7;\">http://example.com</a> for more info");
  }

  @Test
  void linkify_shouldConvertHttpsUrlToLink() {
    // given
    String text = "Visit https://example.com for more info";

    // when
    String result = LinkFormatter.linkify(text);

    // then
    assertThat(result).isEqualTo("Visit <a href=\"https://example.com\" style=\"color:#4f8ef7;\">https://example.com</a> for more info");
  }

  @Test
  void linkify_shouldConvertMultipleUrls() {
    // given
    String text = "Visit http://example.com and https://test.com";

    // when
    String result = LinkFormatter.linkify(text);

    // then
    assertThat(result).isEqualTo("Visit <a href=\"http://example.com\" style=\"color:#4f8ef7;\">http://example.com</a> and <a href=\"https://test.com\" style=\"color:#4f8ef7;\">https://test.com</a>");
  }

  @Test
  void linkify_shouldHandleUrlsWithQueryParameters() {
    // given
    String text = "Check https://example.com/path?param=value&other=123";

    // when
    String result = LinkFormatter.linkify(text);

    // then
    assertThat(result).isEqualTo("Check <a href=\"https://example.com/path?param=value&other=123\" style=\"color:#4f8ef7;\">https://example.com/path?param=value&other=123</a>");
  }

  @Test
  void linkify_shouldHandleUrlsWithFragment() {
    // given
    String text = "Go to https://example.com/page#section";

    // when
    String result = LinkFormatter.linkify(text);

    // then
    assertThat(result).isEqualTo("Go to <a href=\"https://example.com/page#section\" style=\"color:#4f8ef7;\">https://example.com/page#section</a>");
  }

  @Test
  void linkify_shouldReturnOriginalTextWhenNoUrls() {
    // given
    String text = "This is plain text without any URLs";

    // when
    String result = LinkFormatter.linkify(text);

    // then
    assertThat(result).isEqualTo(text);
  }

  @Test
  void linkify_shouldHandleEmptyString() {
    // given
    String text = "";

    // when
    String result = LinkFormatter.linkify(text);

    // then
    assertThat(result).isEmpty();
  }

  @Test
  void linkify_shouldHandleUrlWithPort() {
    // given
    String text = "Connect to http://localhost:8080/api";

    // when
    String result = LinkFormatter.linkify(text);

    // then
    assertThat(result).isEqualTo("Connect to <a href=\"http://localhost:8080/api\" style=\"color:#4f8ef7;\">http://localhost:8080/api</a>");
  }
}
