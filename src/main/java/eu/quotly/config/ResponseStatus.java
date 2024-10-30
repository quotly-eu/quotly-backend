package eu.quotly.config;

@SuppressWarnings("unused")
public enum ResponseStatus {
  SUCCESS,
  ERROR,
  UNKNOWN;

  public String getName() {
    return this.name().toLowerCase();
  }
}
