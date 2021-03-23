package edu.csulb.semantics.project2;

public class Victim {

	private String caseIndividualId, caseVehicleId, victimStatus, roleType, seatingPosition, ejection, licenseStateCode,
			sex, transportMethod, safetyEquipment, injuryDesc, injuryLoc, injurySeverity;
	private int age;

	// == Constructor ==//
	private Victim(String ID) {
		this.caseVehicleId = ID;
	}

	// == == Creation == ==//
	public static Victim create(String ID) {
		return new Victim(ID);
	}

	// == == Method to build a Vehicle object == ==//
	public Victim hasCaseIndividualId(String caseId) {
		this.caseIndividualId = caseId;
		return this;
	}

	public Victim hasVictimStatus(String status) {
		this.victimStatus = status;
		return this;
	}

	public Victim hasRoleType(String role) {
		this.roleType = role;
		return this;
	}

	public Victim hasSeatingPosition(String seating) {
		this.seatingPosition = seating;
		return this;
	}

	public Victim hasEjection(String eject) {
		this.ejection = eject;
		return this;
	}

	public Victim hasLicenseStateCode(String licCode) {
		this.licenseStateCode = licCode;
		return this;
	}

	public Victim hasSex(String sex) {
		this.sex = sex;
		return this;
	}

	public Victim hasTransportMethod(String transport) {
		this.transportMethod = transport;
		return this;
	}

	public Victim hasInjuryDesc(String injurydesc) {
		this.injuryDesc = injurydesc;
		return this;
	}

	public Victim hasInjuryLoc(String injuryLoc) {
		this.injuryLoc = injuryLoc;
		return this;
	}

	public Victim hasInjurySeverity(String severity) {
		this.injurySeverity = severity;
		return this;
	}

	public Victim hasAge(int age) {
		this.age = age;
		return this;
	}

	// getters
	public String getCaseIndividualId() {
		return caseIndividualId;
	}

	public String getCaseVehicleId() {
		return caseVehicleId;
	}

	public String getVictimStatus() {
		return victimStatus;
	}

	public String getRoleType() {
		return roleType;
	}

	public String getSeatingPosition() {
		return seatingPosition;
	}

	public String getEjection() {
		return ejection;
	}

	public String getLicenseStateCode() {
		return licenseStateCode;
	}

	public String getSex() {
		return sex;
	}

	public String getTransportMethod() {
		return transportMethod;
	}

	public String getSafetyEquipment() {
		return safetyEquipment;
	}

	public String getInjuryDesc() {
		return injuryDesc;
	}

	public String getInjuryLoc() {
		return injuryLoc;
	}

	public String getInjurySeverity() {
		return injurySeverity;
	}

	public int getAge() {
		return age;
	}

}
