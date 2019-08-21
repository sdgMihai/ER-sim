/**
 * GeneralSurgeon
 * <p>
 * 24-Dec-18
 *
 * @author Gheoace Mihai
 */

package entities.doctors;

import entities.enums.DoctorFancyName;
import entities.patients.Patient;
import entities.enums.State;

public final class GeneralSurgeon extends BaseDoctor {
    public GeneralSurgeon(BaseDoctor baseDoctor) {
        super(baseDoctor);
    }

    /**
     * update doctors verdict for given patient.
     * @param patient
     */
    @Override
    public void treat(Patient patient) {
        super.treat(DoctorFancyName.GENERAL_SURGEON, patient);
    }

    /**
     * update patient status to hospitalized.
     * @param patient
     */
    @Override
    public void hospitalize(Patient patient) {
        er.getExamMsg().put(patient.getName(),
                patient.getName() + " is " + State.HOSPITALIZED_SURGEON.getValue()
        );
    }

    /**
     * update patient status to operated.
     * @param patient
     */
    @Override
    public void operate(Patient patient) {
        er.getExamMsg().put(patient.getName(),
                patient.getName() + " is " + State.OPERATED_SURGEON.getValue()
        );
    }

    /**
     * update patient status to sent home.
     * @param patient
     */
    @Override
    public void sendHome(Patient patient) {
        er.getExamMsg().put(patient.getName(),
                patient.getName() + " is " + State.HOME_SURGEON.getValue()
        );
    }

}
