/**
 * TriageComparator
 * <p>
 * 20-Dec-18
 *
 * @author Gheoace Mihai
 */

package entities.patients;

import java.util.Comparator;

public final class TriageComparator implements Comparator<Patient> {

    @Override
    public int compare(Patient o1, Patient o2) {
        return o2.getState().getSeverity() - o1.getState().getSeverity();
    }
}
