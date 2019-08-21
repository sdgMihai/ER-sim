/**
 * Patient
 * <p>
 * 17-Dec-18
 *
 * @author Gheoace Mihai
 */

package entities.patients;

import developer.Tester;
import entities.doctors.BaseDoctor;
import entities.enums.IllnessType;
import entities.enums.InvestigationResult;
import entities.enums.State;
import entities.enums.Urgency;
import main.ER;

public final class Patient implements Comparable<Patient> {
    private int id;
    private String name;
    private int age;
    private int time;  // aka round
    private State state;
    private Urgency urgency;
    private int rounds;
    private BaseDoctor doctor;
    private InvestigationResult investigationResult
            = InvestigationResult.NOT_DIAGNOSED;
    public final class State {
        private IllnessType illnessName;
        private int severity;

        public State(IllnessType illnessName, int severity) {
            this.illnessName = illnessName;
            this.severity = severity;
        }

        public State(State state) {
            this.illnessName = state.getIllnessName();
            this.severity = state.getSeverity();
        }

        public State() {

        }

        public IllnessType getIllnessName() {
            return illnessName;
        }

        public void setIllnessName(IllnessType illnessName) {
            this.illnessName = illnessName;
        }

        public int getSeverity() {
            return severity;
        }

        public void setSeverity(int severity) {
            this.severity = severity;
        }
    }

    public void sentToOtherHospital() {
        ER er = ER.getInstance();
        er.getExamMsg().put(name,
                name + " is " + entities.enums.State.OTHERHOSPITAL.getValue()
        );
    }

    /**
     * It shouldn't be overwritten.
     * Updates patient status, setting it to " in examination queue".
     */
    public void examine() {
        ER er = ER.getInstance();
        er.getExamMsg().put(name,
                name + " is " + entities.enums.State.EXAMINATIONSQUEUE.getValue()
        );
    }

    @Override
    public int compareTo(Patient patient) {
        return this.getName().compareTo(patient.getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void print() {
        Tester.printPatient(this);
    }

    public InvestigationResult getInvestigationResult() {
        return investigationResult;
    }

    public void setInvestigationResult(InvestigationResult investigationResult) {
        this.investigationResult = investigationResult;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public BaseDoctor getDoctor() {
        return doctor;
    }

    public void setDoctor(BaseDoctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return name;
    }
}
