package main;

import entities.doctors.BaseDoctor;
import entities.patients.Patient;

import java.util.ArrayList;

public final class MainRedistributable {
    private int simulationLength;
    private int nurses;
    private int investigators;
    private ArrayList<BaseDoctor> doctors;
    private ArrayList<Patient> patients;
    public int getSimulationLength() {
        return simulationLength;
    }

    public void setSimulationLength(int simulationLength) {
        this.simulationLength = simulationLength;
    }

    public int getNurses() {
        return nurses;
    }

    public void setNurses(int nurses) {
        this.nurses = nurses;
    }

    public int getInvestigators() {
        return investigators;
    }

    public void setInvestigators(int investigators) {
        this.investigators = investigators;
    }

    public ArrayList<BaseDoctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<BaseDoctor> doctors) {
        this.doctors = doctors;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setIncomingPatients(ArrayList<Patient> incomingPatients) {
        this.patients = incomingPatients;
    }
}
