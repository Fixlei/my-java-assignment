package edu.ood.model;

public class Motorcycle extends Vehicle {
  public Motorcycle(String licensePlate) {
    super(licensePlate, VehicleSize.MOTORCYCLE);
  }

  public boolean canFitInSpot(ParkingSpot spot) {
    return true; // Can park in any spot
  }
}
