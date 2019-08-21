/**
 * DoctorFancyName
 * <p>
 * 01-Jan-19
 *
 * @author Gheoace Mihai
 */

package entities.enums;

public enum DoctorFancyName {
    CARDIOLOGIST("Cardiologist"),
    ER_PHYSICIAN("ERPhysician"),
    GASTROENTEROLOGIST("Gastroenterologist"),
    GENERAL_SURGEON("General Surgeon"),
    INTERNIST("Internist"),
    NEUROLOGIST("Neurologist");

    private String value;

    DoctorFancyName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
