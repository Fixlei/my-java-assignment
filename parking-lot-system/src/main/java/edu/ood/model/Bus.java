package edu.ood.model;

/**
 * A bus requires 5 consecutive LARGE spots in the same row.
 */
public class Bus extends Vehicle {

  public Bus(String licensePlate) {
    super(licensePlate, VehicleSize.LARGE);
  }

  @Override
  public boolean canFitInSpot(ParkingSpot spot) {
    return spot.getSpotSize() == VehicleSize.LARGE;
  }
}