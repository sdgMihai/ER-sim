package developer;

import entities.enums.IllnessType;
import entities.enums.Urgency;

import java.util.HashMap;
import java.util.Map;


/**
 * Estimates the urgency based on the patient's illness and how severe the illness is manifested.
 */
public final class UrgencyEstimator {

    private static UrgencyEstimator instance;
    private Map<Urgency, HashMap<IllnessType, Integer>> algorithm;

    private UrgencyEstimator() {
        algorithm = new HashMap<Urgency, HashMap<IllnessType, Integer>>() {
            {
                put(Urgency.IMMEDIATE,
                        new HashMap<IllnessType, Integer>() {
                            {
                                put(IllnessType.ABDOMINAL_PAIN,
                                        Immediate.ABDOMINAL_PAIN.getValue());
                                put(IllnessType.ALLERGIC_REACTION,
                                        Immediate.ALLERGIC_REACTION.getValue());
                                put(IllnessType.BROKEN_BONES,
                                        Immediate.BROKEN_BONES.getValue());
                                put(IllnessType.BURNS,
                                        Immediate.BURNS.getValue());
                                put(IllnessType.CAR_ACCIDENT,
                                        Immediate.CAR_ACCIDENT.getValue());
                                put(IllnessType.CUTS,
                                        Immediate.CUTS.getValue());
                                put(IllnessType.FOOD_POISONING,
                                        Immediate.FOOD_POISONING.getValue());
                                put(IllnessType.HEART_ATTACK,
                                        Immediate.HEART_ATTACK.getValue());
                                put(IllnessType.HEART_DISEASE,
                                        Immediate.HEART_DISEASE.getValue());
                                put(IllnessType.HIGH_FEVER,
                                        Immediate.HIGH_FEVER.getValue());
                                put(IllnessType.PNEUMONIA,
                                        Immediate.PNEUMONIA.getValue());
                                put(IllnessType.SPORT_INJURIES,
                                        Immediate.SPORT_INJURIES.getValue());
                                put(IllnessType.STROKE,
                                        Immediate.STROKE.getValue());

                            }
                        });

                put(Urgency.URGENT,
                        new HashMap<IllnessType, Integer>() {
                            {
                                put(IllnessType.ABDOMINAL_PAIN,
                                        Urgent.ABDOMINAL_PAIN.getValue());
                                put(IllnessType.ALLERGIC_REACTION,
                                        Urgent.ALLERGIC_REACTION.getValue());
                                put(IllnessType.BROKEN_BONES,
                                        Urgent.BROKEN_BONES.getValue());
                                put(IllnessType.BURNS,
                                        Urgent.BURNS.getValue());
                                put(IllnessType.CAR_ACCIDENT,
                                        Urgent.CAR_ACCIDENT.getValue());
                                put(IllnessType.CUTS,
                                        Urgent.CUTS.getValue());
                                put(IllnessType.HEART_ATTACK,
                                        Urgent.HEART_ATTACK.getValue());
                                put(IllnessType.FOOD_POISONING,
                                        Urgent.FOOD_POISONING.getValue());
                                put(IllnessType.HEART_DISEASE,
                                        Urgent.HEART_DISEASE.getValue());
                                put(IllnessType.HIGH_FEVER,
                                        Urgent.HIGH_FEVER.getValue());
                                put(IllnessType.PNEUMONIA,
                                        Urgent.PNEUMONIA.getValue());
                                put(IllnessType.SPORT_INJURIES,
                                        Urgent.SPORT_INJURIES.getValue());
                                put(IllnessType.STROKE,
                                        Urgent.STROKE.getValue());
                            }
                        });

                put(Urgency.LESS_URGENT,
                        new HashMap<IllnessType, Integer>() {
                            {
                                put(IllnessType.ABDOMINAL_PAIN,
                                        LessUrgent.ABDOMINAL_PAIN.getValue());
                                put(IllnessType.ALLERGIC_REACTION,
                                        LessUrgent.ALLERGIC_REACTION.getValue());
                                put(IllnessType.BROKEN_BONES,
                                        LessUrgent.BROKEN_BONES.getValue());
                                put(IllnessType.BURNS,
                                        LessUrgent.BURNS.getValue());
                                put(IllnessType.CAR_ACCIDENT,
                                        LessUrgent.CAR_ACCIDENT.getValue());
                                put(IllnessType.CUTS,
                                        LessUrgent.CUTS.getValue());
                                put(IllnessType.FOOD_POISONING,
                                        LessUrgent.FOOD_POISONING.getValue());
                                put(IllnessType.HEART_ATTACK,
                                        LessUrgent.HEART_ATTACK.getValue());
                                put(IllnessType.HEART_DISEASE,
                                        LessUrgent.HEART_DISEASE.getValue());
                                put(IllnessType.HIGH_FEVER,
                                        LessUrgent.HIGH_FEVER.getValue());
                                put(IllnessType.PNEUMONIA,
                                        LessUrgent.PNEUMONIA.getValue());
                                put(IllnessType.SPORT_INJURIES,
                                        LessUrgent.SPORT_INJURIES.getValue());
                                put(IllnessType.STROKE,
                                        LessUrgent.STROKE.getValue());
                            }

                        });

            }
        };
    }

    public static UrgencyEstimator getInstance() {
        if (instance == null) {
            instance = new UrgencyEstimator();
        }
        return instance;
    }

    //called by doctors and nurses
    public Urgency estimateUrgency(IllnessType illnessType, int severity) {

        if (severity >= algorithm.get(Urgency.IMMEDIATE).get(illnessType)) {
            return Urgency.IMMEDIATE;
        }
        if (severity >= algorithm.get(Urgency.URGENT).get(illnessType)) {
            return Urgency.URGENT;
        }
        if (severity >= algorithm.get(Urgency.LESS_URGENT).get(illnessType)) {
            return Urgency.LESS_URGENT;
        }
        return Urgency.NON_URGENT;
    }
}

enum Immediate {
    ABDOMINAL_PAIN(60),
    ALLERGIC_REACTION(50),
    BROKEN_BONES(80),
    BURNS(40),
    CAR_ACCIDENT(30),
    CUTS(50),
    FOOD_POISONING(50),
    HEART_ATTACK(0),
    HEART_DISEASE(40),
    HIGH_FEVER(70),
    PNEUMONIA(80),
    SPORT_INJURIES(70),
    STROKE(0);

    private Integer value;

    Immediate(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

enum Urgent {
    ABDOMINAL_PAIN(40),
    ALLERGIC_REACTION(30),
    BROKEN_BONES(50),
    BURNS(20),
    CAR_ACCIDENT(20),
    CUTS(30),
    HEART_ATTACK(0),
    FOOD_POISONING(20),
    HEART_DISEASE(20),
    HIGH_FEVER(40),
    PNEUMONIA(50),
    SPORT_INJURIES(50),
    STROKE(0);

    private Integer value;

    Urgent(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

enum LessUrgent {
    ABDOMINAL_PAIN(10),
    ALLERGIC_REACTION(10),
    BROKEN_BONES(20),
    BURNS(10),
    CAR_ACCIDENT(10),
    CUTS(10),
    FOOD_POISONING(0),
    HEART_ATTACK(0),
    HEART_DISEASE(10),
    HIGH_FEVER(0),
    PNEUMONIA(10),
    SPORT_INJURIES(20),
    STROKE(0);

    private Integer value;

    LessUrgent(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
