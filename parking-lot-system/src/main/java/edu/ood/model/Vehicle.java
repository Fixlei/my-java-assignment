package edu.ood.model;

import java.util.Objects;

/**
 * Abstract base class for all vehicle types.
 * Car, Bus, and Motorcycle inherit from this class and each implement
 * their own canFitInSpot() logic based on size constraints.
 *
 * FIX: Added equals() and hashCode() based on license plate.
 *      Level.removeVehicle() relies on .equals() to locate a parked vehicle,
 *      but the original code never overrode it — meaning it compared by
 *      reference identity, which would silently fail.
 */
public abstract class Vehicle {

  private final String licensePlate;
  private final int spotsNeeded;
  private final VehicleSize size;

  public Vehicle(String licensePlate, VehicleSize size) {
    this.licensePlate = Objects.requireNonNull(licensePlate, "licensePlate must not be null");
    this.size = Objects.requireNonNull(size, "size must not be null");
    this.spotsNeeded = (size == VehicleSize.LARGE) ? 5 : 1;
  }

  public int getSpotsNeeded() {
    return spotsNeeded;
  }

  public VehicleSize getSize() {
    return size;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  /**
   * Determines whether this vehicle can physically fit in the given spot.
   * Subclasses define the size-matching rules.
   */
  public abstract boolean canFitInSpot(ParkingSpot spot);

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Vehicle that)) return false;
    return licensePlate.equals(that.licensePlate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(licensePlate);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + licensePlate + "]";
  }
}