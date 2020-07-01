package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LocationOfMeasurementDefiningcode implements EnumValueSet {
    LEFTTHIGH("Left thigh", "The left thigh of the person", "local", "at0028"),

    FINGER("Finger", "A finger of the person", "local", "at1026"),

    RIGHTWRIST("Right wrist", "The right wrist of the person", "local", "at1020"),

    LEFTANKLE("Left ankle", "The left ankle of the person", "local", "at1032"),

    INTRAARTERIAL("Intra-arterial", "Blood pressure monitored via an intra-arterial line", "local", "at0032"),

    RIGHTTHIGH("Right thigh", "The right thigh of the person", "local", "at0027"),

    LEFTARM("Left arm", "The left arm of the person", "local", "at0026"),

    LEFTWRIST("Left wrist", "The left wrist of the person", "local", "at1021"),

    RIGHTANKLE("Right ankle", "The right ankle of the person", "local", "at1031"),

    RIGHTARM("Right arm", "The right arm of the person", "local", "at0025");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    LocationOfMeasurementDefiningcode(String value, String description, String terminologyId,
                                      String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTerminologyId() {
        return this.terminologyId;
    }

    public String getCode() {
        return this.code;
    }
}
