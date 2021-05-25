package com.jsoftware.capstone;

public class Medic {
    private String medicName, medicNumber, medicQualification, medicAddress, medicExperience;

    public Medic() {
    }

    public Medic(String medicName, String medicNumber, String medicQualification, String medicAddress, String medicExperience) {
        this.medicName = medicName;
        this.medicNumber = medicNumber;
        this.medicQualification = medicQualification;
        this.medicAddress = medicAddress;
        this.medicExperience = medicExperience;
    }

    public String getMedicName() {
        return medicName;
    }

    public void setMedicName(String medicName) {
        this.medicName = medicName;
    }

    public String getMedicNumber() {
        return medicNumber;
    }

    public void setMedicNumber(String medicNumber) {
        this.medicNumber = medicNumber;
    }

    public String getMedicQualification() {
        return medicQualification;
    }

    public void setMedicQualification(String medicQualification) {
        this.medicQualification = medicQualification;
    }

    public String getMedicAddress() {
        return medicAddress;
    }

    public void setMedicAddress(String medicAddress) {
        this.medicAddress = medicAddress;
    }

    public String getMedicExperience() {
        return medicExperience;
    }

    public void setMedicExperience(String medicExperience) {
        this.medicExperience = medicExperience;
    }
}
