/**
 * DoctorSpecialisation
 * <p>
 * 17-Dec-18
 *
 * @author Gheoace Mihai
 */

package entities.enums;

public enum DoctorSpecialisation {
    CARDIOLOGIST("CARDIOLOGIST"),
    ER_PHYSICIAN("ER_PHYSICIAN"),
    GASTROENTEROLOGIST("GASTROENTEROLOGIST"),
    GENERAL_SURGEON("GENERAL_SURGEON"),
    INTERNIST("INTERNIST"),
    NEUROLOGIST("NEUROLOGIST");

    private String value;

    DoctorSpecialisation(String value) {
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
