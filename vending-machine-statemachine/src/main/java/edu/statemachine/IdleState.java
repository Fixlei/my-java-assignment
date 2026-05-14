package edu.statemachine;

public class IdleState implements State{
  @Override
  public void insertCoin() {

  }

  @Override
  public void selectProduct() {

  }

  @Override
  public void dispense() {
throw new IllegalStateException("insert your coin ..");
  }

  @Override
  public void refund() {
throw new IllegalStateException("no refund");
  }


}
