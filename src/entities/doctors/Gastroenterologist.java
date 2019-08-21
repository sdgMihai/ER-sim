/**
 * Gastroenterologist
 * <p>
 * 24-Dec-18
 *
 * @author Gheoace Mihai
 */

package entities.doctors;

import entities.enums.DoctorFancyName;
import entities.patients.Patient;
import entities.enums.State;

public final class Gastroenterologist extends BaseDoctor {
    public Gastroenterologist() {

    }

    public Gastroenterologist(BaseDoctor baseDoctor) {
        super(baseDoctor);
    }

    /**
     * update doctors verdict for given patient.
     * @param patient
     */
    @Override
    public void treat(Patient patient) {
        super.treat(DoctorFancyName.GASTROENTEROLOGIST, patient);
    }

    /**
     * update patient status to hospitalized.
     * @param patient
     */
    @Override
    public void hospitalize(Patient patient) {
        er.getExamMsg().put(patient.getName(),
                patient.getName() + " is " + State.HOSPITALIZED_GASTRO.getValue()
        );
    }

    /**
     * update patient status to sent Home.
     * @param patient
     */
    @Override
    public void sendHome(Patient patient) {
        er.getExamMsg().put(patient.getName(),
                patient.getName() + " is " + State.HOME_GASTRO.getValue()
        );
    }

}
