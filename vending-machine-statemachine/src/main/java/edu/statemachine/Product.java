package edu.statemachine;

public class Product {
  String name;
  Double price;
  Integer quantity;;

  public Product(String name, Double price, Integer quantity) {
    this.name = name;
    this.price = price;
  }

  public Product(String name, Double price) {
    this.name = name;
    this.price = price;
  }
}
