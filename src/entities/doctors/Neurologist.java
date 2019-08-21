/**
 * Neurologist
 * <p>
 * 02-Jan-19
 *
 * @author Gheoace Mihai
 */

package entities.doctors;

import entities.enums.DoctorFancyName;
import entities.enums.State;
import entities.patients.Patient;

public final class Neurologist extends BaseDoctor {
    public Neurologist(BaseDoctor baseDoctor) {
        super(baseDoctor);
    }

    /**
     * update doctors verdict for given patient.
     * @param patient
     */
    @Override
    public void treat(Patient patient) {
        super.treat(DoctorFancyName.NEUROLOGIST, patient);
    }

    /**
     * update patient status to hospitalized.
     * @param patient
     */
    @Override
    public void hospitalize(Patient patient) {
        er.getExamMsg().put(patient.getName(),
                patient.getName() + " is " + State.HOSPITALIZED_NEURO.getValue()
        );
    }

    /**
     * update patient status to operated.
     * @param patient
     */
    @Override
    public void operate(Patient patient) {
        er.getExamMsg().put(patient.getName(),
                patient.getName() + " is " + State.OPERATED_NEURO.getValue()
        );
    }

    /**
     * update patient status to sent home.
     * @param patient
     */
    @Override
    public void sendHome(Patient patient) {
        er.getExamMsg().put(patient.getName(),
                patient.getName() + " is " + State.HOME_NEURO.getValue()

        );
    }
}
