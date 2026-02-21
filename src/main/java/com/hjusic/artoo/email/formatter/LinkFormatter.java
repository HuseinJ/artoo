package com.hjusic.artoo.email.formatter;

import java.util.regex.Pattern;

public class LinkFormatter {

  private static final Pattern URL_PATTERN = Pattern.compile(
      "(https?://[\\w\\-._~:/?#\\[\\]@!$&'()*+,;=%]+)",
      Pattern.CASE_INSENSITIVE
  );

  public static String linkify(String text) {
    return URL_PATTERN.matcher(text)
        .replaceAll(match -> "<a href=\"" + match.group(1) + "\" style=\"color:#4f8ef7;\">" + match.group(1) + "</a>");
  }

}
