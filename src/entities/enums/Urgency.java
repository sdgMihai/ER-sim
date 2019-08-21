package entities.enums;

/**
 * IMMEDIATE > URGENT > LESS_URGENT > NON_URGENT.
 * NON_URGENT means it will not enter the emergency flows
 *
 * [Part of the homework's skeleton]
 */
public enum Urgency {
    NON_URGENT,
    LESS_URGENT,
    URGENT,
    IMMEDIATE,
    NOT_DETERMINED;

}
