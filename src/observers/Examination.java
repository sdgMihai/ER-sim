/**
 * Examination
 * <p>
 * 18-Dec-18
 *
 * @author Gheoace Mihai
 */

package observers;

import entities.doctors.BaseDoctor;
import entities.patients.Patient;
import entities.enums.IllnessType;
import entities.enums.InvestigationResult;
import entities.patients.PatientComparator;
import main.ER;

import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

public final class Examination implements Observer {
    /**
     * Apllies examination update for one round.
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        ER er = ER.getInstance();

        Collections.sort(er.getExamination(), new PatientComparator());

        while (!er.getExamination().isEmpty()) {
            // get current patient
            Patient cPatient = er.getExamination().remove(0);
            // get doctor according to patient's need
            BaseDoctor doctor  = getDoctor0(cPatient);

            // no doctors available
            if (doctor == null) {
                cPatient.sentToOtherHospital();
                continue;
            }

            if (cPatient.getInvestigationResult() == InvestigationResult.NOT_DIAGNOSED) {
                // remove doctor and add him to the end of the queue
                updateExaminationQueue(doctor);
                // severity <= maxForTreatment -> patient sent home
                if (cPatient.getState().getSeverity() <= doctor.getMaxForTreatment()) {
                    cPatient.setInvestigationResult(InvestigationResult.SEND_HOME);
                    showExaminationResult(doctor, cPatient);
                } else {  // examine patient
                    er.getInvestigation().add(cPatient);
                    cPatient.setInvestigationResult(InvestigationResult.NOT_DIAGNOSED);
                    showExaminationResult(doctor, cPatient);
                }
            } else {
                // set number of rounds
                doctor.investigate(cPatient);
                // remove doctor and add him to the end of the queue
                updateExaminationQueue(doctor);
                showExaminationResult(doctor, cPatient);
            }
        }
    }

    public void showExaminationResult(BaseDoctor doctor, Patient patient) {
        switch (patient.getInvestigationResult()) {
            case HOSPITALIZE:
                doctor.hospitalize(patient);
                break;
            case OPERATE:
                doctor.operate(patient);
                break;
            case SEND_HOME:
                doctor.sendHome(patient);
                break;
            case NOT_DIAGNOSED:
                patient.examine();
                break;
            default:
                System.out.println("error in investigation@examination");
        }
    }

    public BaseDoctor getDoctor1(Patient patient) {
        ER er = ER.getInstance();
        return er.getExaminationDoctors().get(patient.getState().getIllnessName()).get(0);
    }

    public BaseDoctor getDoctor0(Patient patient) {
        ER er = ER.getInstance();
        // operation not necessary
        if (patient.getInvestigationResult() != InvestigationResult.OPERATE) {
            return getDoctor1(patient);
        }
        // operation necessary
        for (BaseDoctor doctor
                : er.getExaminationDoctors().get(patient.getState().getIllnessName())) {
            if (doctor.getIsSurgeon()) {
                return doctor;
            }
        }
        // default; it shouldn't happen so we return null
        return null;
    }

    private void updateExaminationQueue(BaseDoctor doctor) {
        switch (doctor.getType()) {
            case CARDIOLOGIST:
                updateExQueueSingleDisease(IllnessType.HEART_DISEASE, doctor);
                updateExQueueSingleDisease(IllnessType.HEART_ATTACK, doctor);
                break;
            case ER_PHYSICIAN:
                updateExQueueSingleDisease(IllnessType.ALLERGIC_REACTION, doctor);
                updateExQueueSingleDisease(IllnessType.BROKEN_BONES, doctor);
                updateExQueueSingleDisease(IllnessType.BURNS, doctor);
                updateExQueueSingleDisease(IllnessType.CAR_ACCIDENT, doctor);
                updateExQueueSingleDisease(IllnessType.CUTS, doctor);
                updateExQueueSingleDisease(IllnessType.HIGH_FEVER, doctor);
                updateExQueueSingleDisease(IllnessType.SPORT_INJURIES, doctor);
                break;
            case GASTROENTEROLOGIST:
                updateExQueueSingleDisease(IllnessType.ABDOMINAL_PAIN, doctor);
                updateExQueueSingleDisease(IllnessType.ALLERGIC_REACTION, doctor);
                updateExQueueSingleDisease(IllnessType.FOOD_POISONING, doctor);
                break;
            case GENERAL_SURGEON:
                updateExQueueSingleDisease(IllnessType.ABDOMINAL_PAIN, doctor);
                updateExQueueSingleDisease(IllnessType.BURNS, doctor);
                updateExQueueSingleDisease(IllnessType.CAR_ACCIDENT, doctor);
                updateExQueueSingleDisease(IllnessType.CUTS, doctor);
                updateExQueueSingleDisease(IllnessType.SPORT_INJURIES, doctor);
                break;
            case INTERNIST:
                updateExQueueSingleDisease(IllnessType.ABDOMINAL_PAIN, doctor);
                updateExQueueSingleDisease(IllnessType.ALLERGIC_REACTION, doctor);
                updateExQueueSingleDisease(IllnessType.FOOD_POISONING, doctor);
                updateExQueueSingleDisease(IllnessType.HEART_DISEASE, doctor);
                updateExQueueSingleDisease(IllnessType.HIGH_FEVER, doctor);
                updateExQueueSingleDisease(IllnessType.PNEUMONIA, doctor);
                break;
            case NEUROLOGIST:
                updateExQueueSingleDisease(IllnessType.STROKE, doctor);
            default:
                System.out.println("Error updating examination queue");
        }
    }

    private void updateExQueueSingleDisease(IllnessType illnessType, BaseDoctor doctor) {
        ER er = ER.getInstance();
        er.getExaminationDoctors().get(illnessType).remove(doctor);
        er.getExaminationDoctors().get(illnessType).add(doctor);
    }
}
