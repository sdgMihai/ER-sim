/**
 * PatientComparator
 * <p>
 * 18-Dec-18
 *
 * @author Gheoace Mihai
 */

package entities.patients;

import java.util.Comparator;

public final class PatientComparator implements Comparator<Patient> {
    @Override
    public int compare(Patient first, Patient second) {
        if (first == null) {
            return 1;
        }
        if (second == null) {
            return -1;
        }
        if (first.getUrgency() == second.getUrgency()) {
            int res = second.getState().getSeverity() - first.getState().getSeverity();
            if (res != 0) {
                return res;
            } else {
                return second.getName().compareTo(first.getName());
            }
        } else {
            return second.getUrgency()
                    .compareTo(first.getUrgency());
        }
    }
}
