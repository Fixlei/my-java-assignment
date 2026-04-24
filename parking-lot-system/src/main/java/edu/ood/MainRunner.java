package edu.ood;

import edu.ood.model.*;
import edu.ood.park.ParkingLot;
import edu.ood.service.PaymentService;

/**
 * Demonstrates the full Park → Pay → Leave workflow
 * for three vehicle types: Car, Motorcycle, and Bus.
 */
public class MainRunner {

  public static void main(String[] args) throws InterruptedException {
    // Initialize: 3 levels, 20 spots per level
    ParkingLot lot = new ParkingLot(3, 20);
    PaymentService paymentService = new PaymentService();

    System.out.println("Available spots: " + lot.getAvailableSpots());
    System.out.println();

    // ── Scenario 1: Park a car ──
    Vehicle myCar = new Car("BOS-1234");
    System.out.println("Parking " + myCar + " ...");

    if (lot.parkVehicle(myCar)) {
      Ticket carTicket = new Ticket(myCar);
      System.out.println("Parked! Available spots: " + lot.getAvailableSpots());

      // Simulate staying for 2 seconds
      Thread.sleep(2000);

      // Pay and leave
      paymentService.processPayment(carTicket);
      lot.removeVehicle(myCar);
      System.out.println("Car left. Available spots: " + lot.getAvailableSpots());
    } else {
      System.out.println("No spots available for car!");
    }

    System.out.println();

    // ── Scenario 2: Park a motorcycle ──
    Vehicle myBike = new Motorcycle("MOTO-5678");
    System.out.println("Parking " + myBike + " ...");

    if (lot.parkVehicle(myBike)) {
      Ticket bikeTicket = new Ticket(myBike);
      System.out.println("Parked! Available spots: " + lot.getAvailableSpots());

      Thread.sleep(1000);

      paymentService.processPayment(bikeTicket);
      lot.removeVehicle(myBike);
      System.out.println("Motorcycle left. Available spots: " + lot.getAvailableSpots());
    }

    System.out.println();

    // ── Scenario 3: Park a bus (needs 5 consecutive LARGE spots) ──
    Vehicle myBus = new Bus("BUS-9999");
    System.out.println("Parking " + myBus + " ...");

    if (lot.parkVehicle(myBus)) {
      Ticket busTicket = new Ticket(myBus);
      System.out.println("Parked! Available spots: " + lot.getAvailableSpots());

      Thread.sleep(1000);

      paymentService.processPayment(busTicket);
      lot.removeVehicle(myBus);
      System.out.println("Bus left. Available spots: " + lot.getAvailableSpots());
    } else {
      System.out.println("No spots available for bus!");
    }
  }
}