package edu.ood.service;

import edu.ood.model.VehicleSize;

/**
 * Rate calculator — computes parking fees based on vehicle size and duration.
 *
 * This was originally an empty class. Now implements tiered pricing
 * with a minimum 1-hour charge and ceiling-to-next-hour billing.
 *
 * Can be extended into a Strategy pattern (e.g. peak-hour rates,
 * member discounts) by extracting an interface.
 */
public class RateCalculator {

  // Hourly rates in USD
  private static final double MOTORCYCLE_RATE = 1.0;
  private static final double CAR_RATE        = 2.0;
  private static final double BUS_RATE        = 5.0;

  // Minimum billable duration (seconds) — anything under 1 hour is charged as 1 hour
  private static final long MIN_BILLABLE_SECONDS = 3600;

  /**
   * Calculates the parking fee.
   *
   * @param size            vehicle size (determines hourly rate)
   * @param durationSeconds how long the vehicle stayed, in seconds
   * @return fee in USD
   */
  public double calculate(VehicleSize size, long durationSeconds) {
    if (durationSeconds < 0) {
      throw new IllegalArgumentException("Duration cannot be negative");
    }

    double hourlyRate = switch (size) {
      case MOTORCYCLE -> MOTORCYCLE_RATE;
      case COMPACT    -> CAR_RATE;
      case LARGE      -> BUS_RATE;
    };

    // Round up to the next full hour, with a 1-hour minimum
    long billableSeconds = Math.max(durationSeconds, MIN_BILLABLE_SECONDS);
    double hours = Math.ceil(billableSeconds / 3600.0);

    return hours * hourlyRate;
  }
}