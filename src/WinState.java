enum WinState {
  NO_WIN(""),
  HUMAN_WIN("Human WINS - Game Over!"),
  COMPUTER_WIN("Computer WINS - Game Over!"),
  TIE("GAME IS TIE - Game Over!");

  private final String message;

  WinState(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
