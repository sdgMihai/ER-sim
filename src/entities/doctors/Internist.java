/**
 * Internist
 * <p>
 * 30-Dec-18
 *
 * @author Gheoace Mihai
 */

package entities.doctors;

import entities.enums.DoctorFancyName;
import entities.enums.State;
import entities.patients.Patient;

public final class Internist extends BaseDoctor {
    public Internist() {

    }

    public Internist(BaseDoctor baseDoctor) {
        super(baseDoctor);
    }

    /**
     * update doctors verdict for given patient.
     * @param patient
     */
    @Override
    public void treat(Patient patient) {
        super.treat(DoctorFancyName.INTERNIST, patient);
    }

    /**
     * update patient status to hospitalized.
     * @param patient
     */
    @Override
    public void hospitalize(Patient patient) {
        er.getExamMsg().put(patient.getName(),
                patient.getName() + " is " + State.HOSPITALIZED_INTERNIST.getValue()
        );
    }

    /**
     * update patient status to sent home.
     * @param patient
     */
    @Override
    public void sendHome(Patient patient) {
        er.getExamMsg().put(patient.getName(),
                patient.getName() + " is " + State.HOME_INTERNIST.getValue()
        );
    }
}
