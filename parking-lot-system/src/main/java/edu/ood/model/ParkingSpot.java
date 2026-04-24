package edu.ood.model;

import edu.ood.park.Level;

/**
 * Represents a single parking spot within a Level.
 * <p>
 * isOccupied() had an empty method body — would not compile.
 * Added getVehicle() — Level.removeVehicle() needs it to locate
 * which spot holds a given vehicle.
 * parkVehicle() now throws if the spot cannot fit the vehicle,
 * rather than silently doing nothing.
 */
public class ParkingSpot {

  private Vehicle vehicle;
  private final VehicleSize spotSize;
  private final int row;
  private final int spotNumber;
  private final Level level;

  public ParkingSpot(Level level, int row, int spotNumber, VehicleSize spotSize) {
    this.level = level;
    this.row = row;
    this.spotNumber = spotNumber;
    this.spotSize = spotSize;
    this.vehicle = null;
  }

  public boolean isAvailable() {
    return vehicle == null;
  }

  // Original method body was empty (compilation error)
  public boolean isOccupied() {
    return vehicle != null;
  }

  public boolean canFitVehicle(Vehicle vehicle) {
    return isAvailable() && vehicle.canFitInSpot(this);
  }

  public void parkVehicle(Vehicle vehicle) {
    if (!canFitVehicle(vehicle)) {
      throw new IllegalStateException(
          "Cannot park " + vehicle + " in spot " + spotNumber);
    }
    this.vehicle = vehicle;
  }

  public void removeVehicle() {
    this.vehicle = null;
  }

  // Added — Level.removeVehicle() depends on this method
  public Vehicle getVehicle() {
    return vehicle;
  }

  public VehicleSize getSpotSize() {
    return spotSize;
  }

  public int getRow() {
    return row;
  }

  public int getSpotNumber() {
    return spotNumber;
  }

  public Level getLevel() {
    return level;
  }

  @Override
  public String toString() {
    return String.format("Spot[L%d-R%d-#%d %s %s]",
        level.getLevelNumber(), row, spotNumber, spotSize,
        isOccupied() ? "OCCUPIED" : "AVAILABLE");
  }
}