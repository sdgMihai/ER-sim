/**
 * Triage
 * <p>
 * 18-Dec-18
 *
 * @author Gheoace Mihai
 */

package observers;

import developer.UrgencyEstimator;
import entities.enums.State;
import entities.patients.Patient;
import entities.patients.TriageComparator;
import main.ER;

import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

public final class Triage implements Observer {
    private static int round = 0;

    /**
     * Applies triage update for one round.
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        ER er = ER.getInstance();
        if (er.getPatients() != null) {
            // add patients to triage
            int i = 0;
            for (; i < er.getPatients().size(); ++i) {
                if (er.getPatients().get(i).getTime() == round) {
                    // add patient to triage
                    er.getTriage().add(er.getPatients().get(0));
                    // set Urgency
                    Patient cPatient = er.getTriage().get(er.getTriage().size() - 1);
                    cPatient.setUrgency(UrgencyEstimator.getInstance().estimateUrgency(
                            cPatient.getState().getIllnessName(),
                            cPatient.getState().getSeverity())
                    );
                    // remove current patient from patient list
                    // so that only the unhandled patient remain
                    er.getPatients().remove(i);
                    i--;
                }
            }
        }
        // update round
        round++;
        if (er.getTriage() == null || er.getTriage().isEmpty()) {
            return;
        }
        Collections.sort(er.getTriage(), new TriageComparator());
        // send to examination the most suffering according to nurses number
        for (int i = 0; i < er.getNrNurses(); ++i) {
            if (!er.getTriage().isEmpty()) {  // check if triage is empty
                er.getExamination().add(er.getTriage().get(0));
                er.getTriage().remove(0);
            } else {  // triage is empty
                break;
            }
        }
        for (int i = 0; i < er.getTriage().size(); ++i) {
            Patient patient = er.getTriage().get(i);
            er.getExamMsg().put(patient.getName(),
                    patient.getName() + " is " + State.TRIAGEQUEUE.getValue());
        }
    }
}
