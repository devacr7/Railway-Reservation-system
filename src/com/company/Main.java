package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        Map<Integer, Ticket> passengerIdToTicket = new HashMap<>();
        System.out.println("Welcome to IRCTC");
        Booking.loadBerthMap();
        String choice;
        do {
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("Press \n 1. Booking \n 2. Cancel \n 3. Print all booked tickets \n 4. Print Available tickets \n Type exit to stop the Application");
            System.out.println("--------------------------------------------------------------------------------------------");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("Enter passenger name : ");
                    String name = sc.next();
                    System.out.println("Enter passenger age : ");
                    int age = sc.nextInt();
                    System.out.println("Enter passenger birth preference (LB, MB, UB, SU) : ");
                    String birth_pref = sc.next();
                    Ticket ticket = new Ticket(name, age, birth_pref);
                    passengerIdToTicket.put(ticket.getPassengerId(), ticket);
                    Booking ticketBooking = new Booking();
                    ticketBooking.bookTicket(ticket);
                    break;
                case "2":
                    System.out.println("Enter the Passenger id : ");
                    int id = sc.nextInt();
                    Booking cancelBooking = new Booking();
                    cancelBooking.cancelTicket(passengerIdToTicket.get(id));
                    break;
                case "3":
                    Booking bookedTickets = new Booking();
                    bookedTickets.printAllTickets();
                    break;
                case "4":
                    Booking availableTickets = new Booking();
                    availableTickets.printAvailableTickets();
                    break;
            }
        }while (!choice.equals("exit"));
    }
}
