package edu.statemachine;

import java.util.List;

public class VendingMachine {
  List<Product> products;

  public VendingMachine(List<Product> products) {
    this.products = products;
  }

  State currentState;
  Integer currentBalance;

  public void insertCoin() {

  }

  public void dispense() {

  }

  public void restock() {
  }

}
