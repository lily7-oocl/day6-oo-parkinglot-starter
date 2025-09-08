package com.afs.parkinglot;

public class Ticket {
    private boolean isUsed;
    public Ticket() {
        this.isUsed = false;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
