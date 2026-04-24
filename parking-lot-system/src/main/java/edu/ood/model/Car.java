package edu.ood.model;

public class Car extends Vehicle {
  public Car(String licensePlate) {
    super(licensePlate, VehicleSize.COMPACT);
  }

  public boolean canFitInSpot(ParkingSpot spot) {
    return spot.getSpotSize() == VehicleSize.COMPACT || spot.getSpotSize() == VehicleSize.LARGE;
  }
}
