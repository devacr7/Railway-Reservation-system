package com.company;

public class Ticket {

    private int passengerId;
    private int ticket_id;
    private String bookedStatus;
    private String name;
    private int age;
    private String berth_pref;
    private String berth_allocated;

    private static int count = 1;

    public Ticket(String name, int age, String berth_pref) {
        this.passengerId = count++;
        this.name = name;
        this.age = age;
        this.berth_pref = berth_pref;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getBookedStatus() {
        return bookedStatus;
    }

    public void setBookedStatus(String bookedStatus) {
        this.bookedStatus = bookedStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBerth_pref() {
        return berth_pref;
    }

    public void setBerth_pref(String birth_pref) {
        this.berth_pref = birth_pref;
    }

    public String getBerth_allocated() {
        return berth_allocated;
    }

    public void setBerth_allocated(String birth_allocated) {
        this.berth_allocated = birth_allocated;
    }
}
