/**
 * AbstractDoctor
 * <p>
 * 30-Dec-18
 *
 * @author Gheoace Mihai
 */

package entities.doctors;

import entities.enums.DoctorSpecialisation;
import entities.patients.Patient;
import main.ER;

import java.util.ArrayList;

public abstract class AbstractDoctor implements Doctor {
    protected ER er = ER.getInstance();
    protected DoctorSpecialisation specialisation;
    protected boolean isSurgeon;
    protected int maxForTreatment;
    protected double c1;
    protected double c2;
    protected static final int TREAT_REDUCER = 22;
    protected int id;
    protected ArrayList<Patient> patients = new ArrayList<>();

    /**
     * Return doctor's patients.
     * @return patients
     */
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    /**
     * Set's doctors patients.
     * @param patients list of patients
     */
    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    /**
     * Adds one patient to doctors list of patients.
     * @param patient to be added
     */
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     * Returns doctors specialisation.
     * @return doc specialisation
     */
    public DoctorSpecialisation getType() {
        return specialisation;
    }

    /**
     * Set's doctors specialisation.
     * @param type string representing doctors specialisation
     */
    public void setType(String type) {
        //this.specialisation.setValue(type);
        if (type.equals("CARDIOLOGIST")) {
            specialisation = DoctorSpecialisation.CARDIOLOGIST;
            final double c1Cardio = 0.4;
            final double c2Cardio = 0.1;
            c1 = c1Cardio;
            c2 = c2Cardio;
        } else if (type.equals("ER_PHYSICIAN")) {
            specialisation = DoctorSpecialisation.ER_PHYSICIAN;
            final double c1ErPhy = 0.1;
            final double c2ErPhy = 0.3;
            c1 = c1ErPhy;
            c2 = c2ErPhy;
        } else if (type.equals("GASTROENTEROLOGIST")) {
            specialisation = DoctorSpecialisation.GASTROENTEROLOGIST;
            final double c1Gastro = 0.5;
            final double c2Gastro = 0;
            c1 = c1Gastro;
            c2 = c2Gastro;
        } else if (type.equals("GENERAL_SURGEON")) {
            specialisation = DoctorSpecialisation.GENERAL_SURGEON;
            isSurgeon = true;
            final double c1Surgeon = 0.2;
            final double c2Surgeon = 0.2;
            c1 = c1Surgeon;
            c2 = c2Surgeon;
        } else if (type.equals("INTERNIST")) {
            specialisation = DoctorSpecialisation.INTERNIST;
            final double c1Intern = 0.01;
            final double c2Intern = 0;
            c1 = c1Intern;
            c2 = c2Intern;
        } else if (type.equals("NEUROLOGIST")) {
            specialisation = DoctorSpecialisation.NEUROLOGIST;
            final double c1Neuro = 0.5;
            final double c2Neuro = 0.1;
            c1 = c1Neuro;
            c2 = c2Neuro;
        } else {
            specialisation = null;
        }
    }

    /**
     * Return true if is surgeon.
     * @return boolean true for surgeon
     */
    public boolean getIsSurgeon() {
        return isSurgeon;
    }

    /**
     *  Set if doctor is surgeon to true, else false.
     * @param surgeon
     */
    public void setIsSurgeon(boolean surgeon) {
        isSurgeon = surgeon;
    }

    /**
     * Return the maximum value of severity for which patient
     * is sent home for treatment.
     * @return
     */
    public int getMaxForTreatment() {
        return maxForTreatment;
    }

    /**
     * Set maximum value of severity for which doctor send
     * patient home for treatment.
     * @param maxForTreatment
     */
    public void setMaxForTreatment(int maxForTreatment) {
        this.maxForTreatment = maxForTreatment;
    }

    /**
     * Get treatment reducer.
     * @return
     */
    public int getT() {
        return TREAT_REDUCER;
    }

    /**
     * Getter id.
     * @return the doctor's id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id.
     * @param id patient's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get doctor's specialisation.
     * @return
     */
    public DoctorSpecialisation getSpecialisation() {
        return specialisation;
    }
}
