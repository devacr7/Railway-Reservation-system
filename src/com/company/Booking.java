package com.company;

import java.util.*;

public class Booking {

    static int confirmedNumber = 0;
    static Map<Integer, Ticket> confirmTicket = new TreeMap<>();
    static Queue<Ticket> RAC = new LinkedList<>();
    static Queue<Ticket> waitingList = new LinkedList<>();
    static Map<Integer, String> berthMap = new HashMap<>();



    public void bookTicket(Ticket ticket) {

        if(confirmTicket.size() < 7) {
            String prefBerth = ticket.getBerth_pref();
            int prefBerthSeat = getPrefBerth(prefBerth);
            if (prefBerthSeat != -1) {
                ticket.setBookedStatus("Confirmed");
                ticket.setTicket_id(prefBerthSeat);
                ticket.setBerth_allocated(prefBerth);
                confirmTicket.put(prefBerthSeat, ticket);
            }
            else {
                int i=1;
                while(berthMap.get(i) == null) {
                    i++;
                }
                ticket.setBookedStatus("Confirmed");
                ticket.setTicket_id(i);
                ticket.setBerth_allocated(berthMap.get(i));
                confirmTicket.put(i, ticket);
                berthMap.put(i, null);
                confirmedNumber++;
                System.out.println("Confirm ticket booked");
            }
        }
        else if(RAC.size() < 2) {
            ticket.setBookedStatus("RAC");
            RAC.add(ticket);
            System.out.println("RAC ticket is booked");
        }
        else if(waitingList.size() < 1) {
            ticket.setBookedStatus("Waiting");
            waitingList.add(ticket);
            System.out.println("Waiting list ticket is booked");
        }
        else {
            System.out.println("Sorry all tickets are booked");
        }
    }

    public void cancelTicket(Ticket ticket) {
        if(RAC.size() > 0 && ticket.getBookedStatus().equals("Confirmed")) {
            for (Integer i : confirmTicket.keySet()) {
                if (confirmTicket.get(i).getPassengerId() == ticket.getPassengerId()) {
                    System.out.println(RAC.peek().getPassengerId());
                    RAC.peek().setTicket_id(confirmTicket.get(i).getTicket_id());
                    RAC.peek().setBerth_allocated(confirmTicket.get(i).getBerth_allocated());
                    RAC.peek().setBookedStatus("Confirmed");
                    confirmTicket.put(i, RAC.peek());
                    RAC.poll();
                }
            }
        }
        else if(RAC.size() == 0 && ticket.getBookedStatus().equals("Confirmed")) {
            for (Integer i : new HashMap<>(confirmTicket).keySet()) {
                if (confirmTicket.get(i).getPassengerId() == ticket.getPassengerId()) {
                    confirmTicket.remove(i);
                    berthMap.put(ticket.getTicket_id(), ticket.getBerth_allocated());
                }
            }
        }
        else if(waitingList.size() > 0 && ticket.getBookedStatus().equals("RAC")) {
            RAC.remove(ticket);
            Ticket t = waitingList.poll();
            t.setBookedStatus("RAC");
            RAC.add(t);
        }
        else if(waitingList.size() == 0 && ticket.getBookedStatus().equals("RAC")) {
            RAC.remove(ticket);
        }
        else if(ticket.getBookedStatus().equals("Waiting")) {
            waitingList.remove(ticket);
        }
    }

    public void printAllTickets() {
        System.out.println("Confirmed Tickets");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for(Integer i : confirmTicket.keySet()) {
            System.out.println("Passenger Id : "+confirmTicket.get(i).getPassengerId()+" Seat No : "+i+" Name : "+confirmTicket.get(i).getName()+" Age : "+confirmTicket.get(i).getAge()+" Berth Preference : "+confirmTicket.get(i).getBerth_pref()+" Booking Status : "+confirmTicket.get(i).getBookedStatus()+" Berth Allocated : "+confirmTicket.get(i).getBerth_allocated());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("RAC Tickets");
        for(Ticket t : RAC) {
            System.out.println("Passenger Id : "+t.getPassengerId()+" Name : "+t.getName()+" Age : "+t.getAge()+" Berth Preference : "+t.getBerth_pref()+" Booking Status : "+t.getBookedStatus());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Waiting List Tickets");
        for(Ticket t : waitingList) {
            System.out.println("Passenger Id : "+t.getPassengerId()+" Name : "+t.getName()+" Age : "+t.getAge()+" Berth Preference : "+t.getBerth_pref()+" Booking Status : "+t.getBookedStatus());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void printAvailableTickets() {
        int lower = 0, middle = 0, upper = 0, sideUpper = 0;
        for(Integer i : berthMap.keySet()) {
            if(berthMap.get(i) != null) {
                if(berthMap.get(i).equals("LB"))
                    lower++;
                else if(berthMap.get(i).equals("MB"))
                    middle++;
                else if(berthMap.get(i).equals("UB"))
                    upper++;
                else if(berthMap.get(i).equals("SU"))
                    sideUpper++;
            }
        }
        System.out.println("Available Tickets : ");
        System.out.println("Lower : "+lower+"\nMiddle : "+middle+"\nUpper : "+upper+"\nSide Upper : "+sideUpper);
    }

    public static void loadBerthMap() {
        int seatNo = 1;
        String str[] = {"LB", "MB", "UB", "LB", "MB", "UB", "SU"};
        for(int i=0;i<7;i++) {
            berthMap.put(i+1, str[i%7]);
        }
    }

    public int getPrefBerth(String prefBerth) {
        for(int i=1;i<=7;i++) {
            if(berthMap.get(i) != null && berthMap.get(i).equals(prefBerth)) {
                berthMap.put(i, null);
                return i;
            }
        }
        return -1;
    }
}
