package edu.ood.park;

import edu.ood.model.ParkingSpot;
import edu.ood.model.Vehicle;
import edu.ood.model.VehicleSize;

/**
 * A single floor in the parking lot, containing a mix of spot sizes.
 * <p>
 * The original constructor allocated the array but never instantiated
 * any ParkingSpot objects — every element was null, causing
 * NullPointerException on the very first iteration.
 * Added getLevelNumber() for external queries.
 * Spots are now distributed by size ratio (20% motorcycle,
 * 60% compact, 20% large) to simulate a realistic parking lot.
 * Added consecutive-spot logic for Bus parking (5 contiguous
 * LARGE spots in the same row).
 */
public class Level {

  private static final int SPOTS_PER_ROW = 10;

  private final int levelNumber;
  private final ParkingSpot[] spots;
  private int availableSpots;

  public Level(int levelNumber, int numSpots) {
    this.levelNumber = levelNumber;
    this.spots = new ParkingSpot[numSpots];
    this.availableSpots = numSpots;

    // Distribute spots: 20% MOTORCYCLE, 60% COMPACT, 20% LARGE
    for (int i = 0; i < numSpots; i++) {
      VehicleSize size;
      if (i < numSpots / 5) {
        size = VehicleSize.MOTORCYCLE;
      } else if (i < numSpots * 4 / 5) {
        size = VehicleSize.COMPACT;
      } else {
        size = VehicleSize.LARGE;
      }

      int row = i / SPOTS_PER_ROW;
      spots[i] = new ParkingSpot(this, row, i, size);
    }
  }

  /**
   * Attempts to park a vehicle on this level.
   * For multi-spot vehicles (e.g. Bus), finds consecutive same-row LARGE spots.
   */
  public boolean parkVehicle(Vehicle vehicle) {
    int spotsNeeded = vehicle.getSpotsNeeded();

    if (spotsNeeded == 1) {
      // Single-spot vehicle: find the first compatible available spot
      for (ParkingSpot spot : spots) {
        if (spot.canFitVehicle(vehicle)) {
          spot.parkVehicle(vehicle);
          availableSpots--;
          return true;
        }
      }
    } else {
      // Multi-spot vehicle (Bus): need consecutive LARGE spots in the same row
      for (int i = 0; i <= spots.length - spotsNeeded; i++) {
        if (canFitConsecutive(vehicle, i, spotsNeeded)) {
          for (int j = i; j < i + spotsNeeded; j++) {
            spots[j].parkVehicle(vehicle);
            availableSpots--;
          }
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if {@code count} spots starting at {@code startIndex} are all
   * in the same row and can each fit the given vehicle.
   */
  private boolean canFitConsecutive(Vehicle vehicle, int startIndex, int count) {
    int row = spots[startIndex].getRow();
    for (int i = startIndex; i < startIndex + count; i++) {
      if (spots[i].getRow() != row || !spots[i].canFitVehicle(vehicle)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Removes a vehicle from this level, freeing all spots it occupied.
   * A bus may occupy multiple spots — this method releases all of them.
   */
  public boolean removeVehicle(Vehicle vehicle) {
    boolean found = false;
    for (ParkingSpot spot : spots) {
      if (spot.isOccupied() && vehicle.equals(spot.getVehicle())) {
        spot.removeVehicle();
        availableSpots++;
        found = true;
      }
    }
    return found;
  }

  public int getAvailableSpots() {
    return availableSpots;
  }

  public int getLevelNumber() {
    return levelNumber;
  }

  public ParkingSpot[] getSpots() {
    return spots;
  }
}