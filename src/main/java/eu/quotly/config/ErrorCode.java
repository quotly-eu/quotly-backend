package eu.quotly.config;

public enum ErrorCode {
  USER_NOT_FOUND(40010L),
  QUOTE_NOT_FOUND(40020L),
  COMMENT_NOT_FOUND(40030L),
  REACTION_NOT_FOUND(40040L);

  private final Long code;

  ErrorCode(Long code) {
    this.code = code;
  }

  public Long getValue() {
    return code;
  }
}
