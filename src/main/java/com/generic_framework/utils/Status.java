package com.generic_framework.utils;

public enum Status {
    In_Meeting("In a meeting"),
    Commuting("Commuting"),
    Out_Sick("Out sick"),
    Vacationing("Vacationing"),
    Working_Remotely("Working remotely"),
    Custom_Status("none");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
