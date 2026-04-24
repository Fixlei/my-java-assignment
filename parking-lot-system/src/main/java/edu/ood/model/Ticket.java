package edu.ood.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/**
 * Parking ticket. Records entry time and computes duration on exit.
 *
 * FIX 1: Replaced legacy java.util.Date with java.time.Instant.
 * FIX 2: getDurationSeconds() now throws a clear exception when exitTime
 *         has not been set, instead of a cryptic NullPointerException.
 * FIX 3: Added getVehicle() — PaymentService and RateCalculator need
 *         the vehicle reference to determine pricing.
 */
public class Ticket {

  private final Vehicle vehicle;
  private final Instant issueTime;
  private Instant exitTime;

  public Ticket(Vehicle vehicle) {
    this.vehicle = Objects.requireNonNull(vehicle, "vehicle must not be null");
    this.issueTime = Instant.now();
  }

  /** Stamps the exit time as "now". Call this when the vehicle leaves. */
  public void markExit() {
    this.exitTime = Instant.now();
  }

  /** Sets a specific exit time (useful for testing). */
  public void setExitTime(Instant exitTime) {
    this.exitTime = Objects.requireNonNull(exitTime, "exitTime must not be null");
  }

  /**
   * Returns parking duration in seconds.
   *
   * @throws IllegalStateException if exit time has not been set yet
   */
  public long getDurationSeconds() {
    if (exitTime == null) {
      throw new IllegalStateException("Exit time has not been set. Call markExit() first.");
    }
    return Duration.between(issueTime, exitTime).getSeconds();
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public Instant getIssueTime() {
    return issueTime;
  }

  public Instant getExitTime() {
    return exitTime;
  }

  @Override
  public String toString() {
    return String.format("Ticket[%s, issued=%s]", vehicle, issueTime);
  }
}