package edu.ood.service;

import edu.ood.model.Ticket;

/**
 * Orchestrates the payment workflow: stamp exit → compute fee → print receipt.
 * <p>
 * The original code called getDuration() without ever setting exitTime,
 * which caused a NullPointerException. Now markExit() is called first.
 * Also delegates fee calculation to RateCalculator (separation of concerns).
 */
public class PaymentService {

  private final RateCalculator rateCalculator;

  public PaymentService() {
    this.rateCalculator = new RateCalculator();
  }

  /**
   * Constructor that accepts a custom RateCalculator (useful for testing).
   */
  public PaymentService(RateCalculator rateCalculator) {
    this.rateCalculator = rateCalculator;
  }

  /**
   * Processes the full payment flow for a parking ticket.
   *
   * @param ticket the parking ticket
   * @return the computed fee in USD
   */
  public double processPayment(Ticket ticket) {
    // FIX: Stamp exit time BEFORE computing duration to avoid NPE
    ticket.markExit();

    double fee = rateCalculator.calculate(
        ticket.getVehicle().getSize(),
        ticket.getDurationSeconds()
    );

    printReceipt(ticket, fee);
    return fee;
  }

  private void printReceipt(Ticket ticket, double fee) {
    System.out.println("════════════════════════════════════");
    System.out.println("         PARKING RECEIPT");
    System.out.println("════════════════════════════════════");
    System.out.printf("  Vehicle:  %s%n", ticket.getVehicle());
    System.out.printf("  Entry:    %s%n", ticket.getIssueTime());
    System.out.printf("  Exit:     %s%n", ticket.getExitTime());
    System.out.printf("  Duration: %d seconds%n", ticket.getDurationSeconds());
    System.out.printf("  Fee:      $%.2f%n", fee);
    System.out.println("════════════════════════════════════");
  }
}