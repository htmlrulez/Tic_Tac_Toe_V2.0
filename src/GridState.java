enum GridState {
  EMPTY(" "),
  HUMAN("X"),
  COMPUTER("O");

  private final String symbol;

  GridState(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }
}
