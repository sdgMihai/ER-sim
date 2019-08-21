/**
 * ER
 * <p>
 * 18-Dec-18
 *
 * @author Gheoace Mihai
 */

package main;

import entities.Nurse;
import entities.doctors.BaseDoctor;
import entities.doctors.Cardiologist;
import entities.doctors.ErPhysician;
import entities.doctors.Gastroenterologist;
import entities.doctors.GeneralSurgeon;
import entities.doctors.Internist;
import entities.doctors.Neurologist;
import entities.patients.Patient;
import entities.enums.IllnessType;
import observers.Examination;
import observers.Investigation;
import observers.Treatment;
import observers.Triage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

public final class ER extends Observable {
    private ArrayList<Patient> triage;
    private ArrayList<Patient> examination;
    private Map<IllnessType, ArrayList<BaseDoctor>> examinationDoctors;
    private ArrayList<Patient> investigation;
    private ArrayList<BaseDoctor> doctors;
    private ArrayList<Patient>  patients;
    private int nrNurses;
    private ArrayList<Nurse> nurses;
    private ArrayList<Patient> hospitalizedPatients;
    private int investigators;
    // round message
    private TreeMap<String, String> examMsg;
    // nurses message
    private Map<Patient, String> nursesMsg;
    // doctor message
    private HashMap<Integer, HashMap<String, String>> doctorsMsg;

    private static ER er = null;

    public static ER getInstance() {
        if (er == null) {
            er = new ER();
        }
        return er;
    }

    /**
     * Update world observers.
     */
    public void update() {
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Init world.
     * @param main
     */
    public void init(MainRedistributable main) {
        triage = new ArrayList<>();
        examination = new ArrayList<>();
        examinationDoctors = new HashMap<>();
        investigation = new ArrayList<>();

        specializeDoctors(main.getDoctors());

        patients = main.getPatients();
        nrNurses = main.getNurses();
        hospitalizedPatients = new ArrayList<>();

        investigators = main.getInvestigators();

        setNurses();
        setExaminationQueue();
        initDoctorsMsg();

        examMsg = new TreeMap<>();
        nursesMsg = new HashMap<>();
        this.addObserver(new Treatment());
        this.addObserver(new Investigation());
        this.addObserver(new Examination());
        this.addObserver(new Triage());
    }

    /**
     * Init nurses.
     */
    private void setNurses() {
        this.nurses = new ArrayList<>();
        for (int i = 0; i < nrNurses; ++i) {
            nurses.add(new Nurse());
        }
    }

    /**
     * Init doctors message list.
     */
    private void initDoctorsMsg() {
        doctorsMsg = new HashMap<>();
        for (BaseDoctor doctor : er.getDoctors()) {
            doctorsMsg.put(doctor.getId(),
                    new HashMap<>()
            );
        }
    }

    /**
     * This function specializes each doctor, by means of specialized children classes.
     * Also, it provides each doctor an id.
     * @param baseDoctors
     */
    private void specializeDoctors(ArrayList<BaseDoctor> baseDoctors) {
        this.doctors = new ArrayList<>();
        for (int i = 0; i < baseDoctors.size(); ++i) {
            switch (baseDoctors.get(i).getType()) {
                case NEUROLOGIST:
                    this.doctors.add(new Neurologist(baseDoctors.get(i)));
                    break;
                case INTERNIST:
                    this.doctors.add(new Internist(baseDoctors.get(i)));

                    break;
                case CARDIOLOGIST:
                    this.doctors.add(new Cardiologist(baseDoctors.get(i)));
                    break;
                case ER_PHYSICIAN:
                    this.doctors.add(new ErPhysician(baseDoctors.get(i)));
                    break;
                case GENERAL_SURGEON:
                    this.doctors.add(new GeneralSurgeon(baseDoctors.get(i)));
                    break;
                case GASTROENTEROLOGIST:
                    this.doctors.add(new Gastroenterologist(baseDoctors.get(i)));
                    break;
                    default:
                        this.doctors.add(baseDoctors.get(i));
            }
            doctors.get(i).setId(i);
        }
    }

    /**
     * Init examination queue list of mesages aka patients status holder for each round.
     */
    private void setExaminationQueue() {
        for (IllnessType illnessType : IllnessType.values()) {
            if (examinationDoctors.getOrDefault(illnessType, null) == null) {
                examinationDoctors.put(illnessType, new ArrayList<>());
            }
        }
        for (BaseDoctor doctor : doctors) {
            switch (doctor.getType()) {
                case CARDIOLOGIST:
                    examinationDoctors.get(IllnessType.HEART_ATTACK).add(doctor);
                    examinationDoctors.get(IllnessType.HEART_DISEASE).add(doctor);
                    break;
                case ER_PHYSICIAN:
                    examinationDoctors.get(IllnessType.ALLERGIC_REACTION).add(doctor);
                    examinationDoctors.get(IllnessType.BROKEN_BONES).add(doctor);
                    examinationDoctors.get(IllnessType.BURNS).add(doctor);
                    examinationDoctors.get(IllnessType.CAR_ACCIDENT).add(doctor);
                    examinationDoctors.get(IllnessType.CUTS).add(doctor);
                    examinationDoctors.get(IllnessType.HIGH_FEVER).add(doctor);
                    examinationDoctors.get(IllnessType.SPORT_INJURIES).add(doctor);
                    break;
                case INTERNIST:
                    examinationDoctors.get(IllnessType.ABDOMINAL_PAIN).add(doctor);
                    examinationDoctors.get(IllnessType.ALLERGIC_REACTION).add(doctor);
                    examinationDoctors.get(IllnessType.FOOD_POISONING).add(doctor);
                    examinationDoctors.get(IllnessType.HEART_DISEASE).add(doctor);
                    examinationDoctors.get(IllnessType.HIGH_FEVER).add(doctor);
                    examinationDoctors.get(IllnessType.PNEUMONIA).add(doctor);
                    break;
                case GASTROENTEROLOGIST:
                    examinationDoctors.get(IllnessType.ABDOMINAL_PAIN).add(doctor);
                    examinationDoctors.get(IllnessType.ALLERGIC_REACTION).add(doctor);
                    examinationDoctors.get(IllnessType.FOOD_POISONING).add(doctor);
                    break;
                case NEUROLOGIST:
                    examinationDoctors.get(IllnessType.STROKE).add(doctor);
                    break;
                case GENERAL_SURGEON:
                    examinationDoctors.get(IllnessType.ABDOMINAL_PAIN).add(doctor);
                    examinationDoctors.get(IllnessType.BURNS).add(doctor);
                    examinationDoctors.get(IllnessType.CAR_ACCIDENT).add(doctor);
                    examinationDoctors.get(IllnessType.CUTS).add(doctor);
                    examinationDoctors.get(IllnessType.SPORT_INJURIES).add(doctor);
                    break;
                default:
                    System.out.println("error filling examinationDoctors queue");
            }
        }
    }

    public ArrayList<BaseDoctor> getDoctors() {
        return doctors;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public int getNrNurses() {
        return nrNurses;
    }

    public ArrayList<Patient> getTriage() {
        return triage;
    }

    public Map<IllnessType, ArrayList<BaseDoctor>> getExaminationDoctors() {
        return examinationDoctors;
    }

    public void setExaminationDoctors(Map<IllnessType, ArrayList<BaseDoctor>> examinationDoctors) {
        this.examinationDoctors = examinationDoctors;
    }

    public ArrayList<Patient> getInvestigation() {
        return investigation;
    }

    public void setInvestigation(ArrayList<Patient> investigation) {
        this.investigation = investigation;
    }

    public int getInvestigators() {
        return investigators;
    }

    public void setInvestigators(int investigators) {
        this.investigators = investigators;
    }

    public TreeMap<String, String> getExamMsg() {
        return examMsg;
    }

    public void setExamMsg(TreeMap<String, String> examMsg) {
        this.examMsg = examMsg;
    }

    public ArrayList<Nurse> getNurses() {
        return nurses;
    }

    public HashMap<Patient, String> getNursesMsg() {
        return (HashMap<Patient, String>) nursesMsg;
    }


    public void addHospitalizedPatient(Patient patient) {
        hospitalizedPatients.add(patient);
    }

    public ArrayList<Patient> getHospitalizedPatients() {
        return hospitalizedPatients;
    }

    public HashMap<Integer, HashMap<String, String>> getDoctorsMsg() {
        return doctorsMsg;
    }

    public ArrayList<Patient> getExamination() {
        return examination;
    }

    public void setDoctorsMsg(HashMap<Integer, HashMap<String, String>> doctorsMsg) {
        this.doctorsMsg = doctorsMsg;
    }
}
