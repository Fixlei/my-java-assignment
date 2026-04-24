package edu.ood.park;

import edu.ood.model.Vehicle;

/**
 * Top-level parking lot facade. Manages multiple Levels and exposes
 * park / remove / availability operations.
 */
public class ParkingLot {

  private final Level[] levels;

  public ParkingLot(int numLevels, int spotsPerLevel) {
    levels = new Level[numLevels];
    for (int i = 0; i < numLevels; i++) {
      levels[i] = new Level(i, spotsPerLevel);
    }
  }

  /** Attempts to park the vehicle on the first level that has room. */
  public boolean parkVehicle(Vehicle vehicle) {
    for (Level level : levels) {
      if (level.parkVehicle(vehicle)) {
        return true;
      }
    }
    return false;
  }

  /** Removes the vehicle from whichever level it is parked on. */
  public boolean removeVehicle(Vehicle vehicle) {
    for (Level level : levels) {
      if (level.removeVehicle(vehicle)) {
        return true;
      }
    }
    return false;
  }

  /** Returns the total number of available spots across all levels. */
  public int getAvailableSpots() {
    int total = 0;
    for (Level level : levels) {
      total += level.getAvailableSpots();
    }
    return total;
  }

  public int getNumLevels() {
    return levels.length;
  }

  public Level getLevel(int index) {
    return levels[index];
  }
}