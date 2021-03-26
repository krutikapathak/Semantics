package edu.csulb.semantics.project2;

/**
 * @author krutikapathak
 *
 */
public class Vehicle {

	private int occupants, vehicleYear, engineCylinder;
	private String caseVehicleId, vehicleBodytype, registrationClass, axleType, fuelType, registrationState,
			vehicleMake, vin;

	// == Constructor ==//
	private Vehicle(String ID) {
		this.caseVehicleId = ID;
	}

	// == == Creation == ==//
	public static Vehicle create(String ID) {
		return new Vehicle(ID);
	}

	// == == Method to build a Vehicle object == ==//
	public Vehicle hasBodyType(String bt) {
		this.vehicleBodytype = bt;
		return this;
	}

	public Vehicle hasOccupants(int b) {
		this.occupants = b;
		return this;
	}

	public Vehicle hasVehicleYear(int year) {
		this.vehicleYear = year;
		return this;
	}

	public Vehicle hasRegistrationClass(String regClass) {
		this.registrationClass = regClass;
		return this;
	}

	public Vehicle hasAxleType(String axle) {
		this.axleType = axle;
		return this;
	}

	public Vehicle hasFuelType(String fuelType) {
		this.fuelType = fuelType;
		return this;
	}

	public Vehicle hasRegistrationState(String state) {
		this.registrationState = state;
		return this;
	}

	public Vehicle hasEngineCylinder(int cylinder) {
		this.engineCylinder = cylinder;
		return this;
	}

	public Vehicle hasVehicleMake(String make) {
		this.vehicleMake = make;
		return this;
	}

	public Vehicle hasVin(String vin) {
		this.vin = vin;
		return this;
	}

	// getters
	public int getOccupants() {
		return occupants;
	}

	public int getVehicleYear() {
		return vehicleYear;
	}

	public String getCaseVehicleId() {
		return caseVehicleId;
	}

	public String getVehicleBodytype() {
		return vehicleBodytype == null ? "" : vehicleBodytype;
	}

	public String getRegistrationClass() {
		return registrationClass;
	}

	public String getAxleType() {
		return axleType;
	}

	public String getFuelType() {
		return fuelType;
	}

	public String getRegistrationState() {
		return registrationState;
	}

	public int getEngineCylinder() {
		return engineCylinder;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}

	public String getVin() {
		return vin;
	}
}
