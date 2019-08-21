/**
 * BaseDoctor
 * <p>
 * 17-Dec-18
 *
 * @author Gheoace Mihai
 */

package entities.doctors;

import developer.Tester;
import entities.enums.DoctorFancyName;
import entities.enums.State;
import entities.patients.Patient;
import entities.enums.InvestigationResult;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.StrictMath.max;

public class BaseDoctor extends AbstractDoctor {
    public BaseDoctor() {

    }

    public BaseDoctor(BaseDoctor baseDoctor) {
        this.specialisation = baseDoctor.specialisation;
        this.isSurgeon = baseDoctor.isSurgeon;
        this.maxForTreatment = baseDoctor.maxForTreatment;
        this.c1 = baseDoctor.c1;
        this.c2 = baseDoctor.c2;
        this.patients = (ArrayList<Patient>) baseDoctor.patients.clone();
    }

    /**
     * This function is designed for overwritting, defined in doctor interface.
     * In case of using, which is not it's purpose, displays an error msg
     * at std output.
     * @param patient
     */
    @Override
    public void treat(Patient patient) {
        System.out.println(
                patient.getName() + "error treat "
        );
    }

    /**
     * This function is the  encapsulation of all
     * void treat(Patient patient) logic in children.
     * It updates the doctors msg for given patient.
     * Patient must be hospitalized by this doctor,otherwise behaviour is unknown.
     * It must be called from children function void treat(Patient patient)
     * with coresp fancyName and given patient.
     * @param fancyName
     * @param patient
     */
    protected void treat(DoctorFancyName fancyName, Patient patient) {
        // get patient pos in patientsList
        // where patients List is doctor's message list
        HashMap<String, String> patientsList =
                er.getDoctorsMsg().get(id);
         if (patient.getRounds() <= 0 || patient.getState().getSeverity() <= 0) {
            // patient is in patientList and is ready
            // change patient status to sent home
            patientsList.put(patient.getName(),
                            fancyName.toString() + " sent " + patient.getName() + " home"
            );
            er.getExamMsg().put(patient.getName(),
                    patient.getName() + " is " + State.HOME_DONE_TREATMENT.getValue()
            );
            // set patient's result
            patient.setInvestigationResult(InvestigationResult.SEND_HOME);
        } else {
            patientsList.put(patient.getName(),
                            fancyName.toString() + " says that " + patient.getName()
                                    + " should remain in hospital"
            );
        }
    }

    /**
     * It shouldn't be overwritten.
     * This method is the general way of investigation for all doctors categories.
     * @param patient
     */
    @Override
    public void investigate(Patient patient) {
        final double magicNumber = 0.5;
        if (patient.getInvestigationResult() == InvestigationResult.OPERATE) {
            // S -= S * C2
            double res = patient.getState().getSeverity()
                    - patient.getState().getSeverity() * c2;

            if (res - Math.floor(res) <= magicNumber) {
                res = Math.floor(res);
            } else {
                res = Math.floor(res) + 1;
            }
            patient.getState().setSeverity(
                    (int) Math.round(res)
            );
        }
        final int defaultRounds = 3;
        if (patient.getInvestigationResult() == InvestigationResult.HOSPITALIZE
                || patient.getInvestigationResult() == InvestigationResult.OPERATE) {
            double res = Math.round(c1
                            * patient.getState().getSeverity());
            if (res - Math.floor(res) <= magicNumber) {
                res = Math.floor(res);
            } else {
                res = Math.floor(res) + 1;
            }
            patient.setRounds((int) max(res, defaultRounds));
            this.addPatient(patient);
            patient.setDoctor(this);
            er.addHospitalizedPatient(patient);
        }
    }

    /**
     * This implementation is for error handling.
     * All children should implement a personal version.
     * @param patient
     */
    @Override
    public void hospitalize(Patient patient) {
        System.out.println("abstract lord is hospitalized");
    }

    /**
     * This implementation is for error handling.
     * All children should implement a personal version.
     * @param patient
     */
    @Override
    public void operate(Patient patient) {
        System.out.println("abstract lord is operated");
    }

    /**
     * aThis implementation is for error handling.
     * All children should implement a personal version.
     * @param patient
     */
    @Override
    public void sendHome(Patient patient) {
        System.out.printf("abstract lord is sent home by unsafe operation");
    }

    /**
     * Logic for throwing patient.
     * @param patient
     */
    public void sendHomeAfterTreatment(Patient patient) {
        patients.remove(patient);
        patient.setDoctor(null);
        er.getHospitalizedPatients().remove(patient);
        er.getNursesMsg().remove(patient);
    }

    /**
     * Print doctors fields.
     */
    public void print() {
        Tester.printDoctor(this);
    }

    /**
     * It shouldn't be overwritten. Returns specialisation to string.
     * @return
     */
    @Override
    public String toString() {
        return specialisation.getValue();
    }
}
