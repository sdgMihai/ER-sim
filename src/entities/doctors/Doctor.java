/**
 * Doctor
 * <p>
 * 17-Dec-18
 *
 * @author Gheoace Mihai
 */

package entities.doctors;

import entities.patients.Patient;

public interface Doctor {
    void treat(Patient patient);

    void investigate(Patient patient);

    void hospitalize(Patient patient);

    void operate(Patient patient);

    void sendHome(Patient patient);
}
