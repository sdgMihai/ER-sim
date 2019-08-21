/**
 * Investigation
 * <p>
 * 18-Dec-18
 *
 * @author Gheoace Mihai
 */

package observers;

import entities.enums.State;
import entities.patients.Patient;
import entities.enums.InvestigationResult;
import entities.patients.PatientComparator;
import main.ER;

import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

public final class Investigation implements Observer {
    /**
     * Applies investigation update for one round.
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        ER er = ER.getInstance();
        final int c1 = 75;
        final int c2 = 40;

        Collections.sort(er.getInvestigation(), new PatientComparator());
        int cntInvestigations = 0;
        for (int i = 0; cntInvestigations < er.getInvestigators()
                && i < er.getInvestigation().size(); ++i) {
            Patient cPatient = er.getInvestigation().get(i);
            if (cPatient.getState().getSeverity() > c1) {
                cPatient.setInvestigationResult(InvestigationResult.OPERATE);
            } else if (cPatient.getState().getSeverity() > c2) {
                cPatient.setInvestigationResult(InvestigationResult.HOSPITALIZE);
            } else {
                cPatient.setInvestigationResult(InvestigationResult.SEND_HOME);
            }
            // prepare patient for next round to go to exam.
            er.getExamination().add(0, cPatient);
            er.getInvestigation().remove(cPatient);
            cPatient.examine();
            i--;
            cntInvestigations++;
        }

        // the rest of patients remain for the next round
        for (int i = 0; i < er.getInvestigation().size(); ++i) {
            String patientName = er.getInvestigation().get(i).getName();
            er.getExamMsg().put(patientName,
                    patientName + " is " + State.INVESTIGATIONSQUEUE.getValue()
            );
        }

    }
}
