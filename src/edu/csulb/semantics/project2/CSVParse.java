package edu.csulb.semantics.project2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class CSVParse {
	public static Map<String, Vehicle> vehicles = new HashMap<>(); // String = VehicleCaseID
	public static Map<String, Victim> victim = new HashMap<>(); // String = VehicleCaseID
	public static Map<String, Crash> crash = new HashMap<>(); // String = VehicleCaseID

	static {
		try {
			parseVehicleCSV();
			parseIndividualCSV();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// == Private methods ==//
	// == == CSVs == ==//
	private static void parseVehicleCSV() throws IOException, CsvValidationException {
//		final String vehicleFileName = "Motor_Vehicle_Crashes_-_Vehicle_Information__Three_Year_Window.csv";
		final String vehicleFileName = "vehicle.csv";
		Vehicle vehicle;
		Crash crashes;
		CSVReader openCsvReader = new CSVReaderBuilder(new InputStreamReader(
				Objects.requireNonNull(CSVParse.class.getClassLoader().getResourceAsStream(vehicleFileName))))
						.withSkipLines(1).build();

		String[] data;
		while ((data = openCsvReader.readNext()) != null) {

			String crashYear = data[0];
			String caseVehicleId = data[1];
			String vehicleBodyType = data[2];
			String registrationClass = data[3];
			String priorAction = data[4];
			String axleType = data[5];
			String fuelType = data[7];
			String vehicleYear = data[8];
			String registrationState = data[9];
			String occupants = data[10];
			String engineCylinder = data[11];
			String vehicleMake = data[12];
			String contributingFactor1 = data[13];
			String contributingFactor1Desc = data[14];
			String contributingFactor2 = data[15];
			String contributingFactor2Desc = data[16];
			String eventType = data[17];
			String vin = data[18];

			// Creating vehicle object
			vehicle = getVehicle(caseVehicleId).hasBodyType(vehicleBodyType).hasOccupants(Integer.parseInt(occupants))
					.hasVehicleYear(Integer.parseInt(vehicleYear)).hasRegistrationClass(registrationClass)
					.hasAxleType(axleType).hasFuelType(fuelType).hasRegistrationState(registrationState)
					.hasEngineCylinder(Integer.parseInt(engineCylinder)).hasVehicleMake(vehicleMake).hasVin(vin);

			crashes = getCrash(caseVehicleId).hasCrashYear(Integer.parseInt(crashYear)).hasPriorAction(priorAction)
					.hasContributingFactor1(contributingFactor1).hasContributingFactor1Desc(contributingFactor1Desc)
					.hasContributingFactor2(contributingFactor2).hasContributingFactor2Desc(contributingFactor2Desc)
					.hasEventType(eventType);

			// Adding vehicle to the hashmap of vehicles
			vehicles.put(caseVehicleId, vehicle);
			crash.put(caseVehicleId, crashes);
		}
	}

	// Reading file Individual info on vehicle crashes
	private static void parseIndividualCSV() throws IOException, CsvValidationException {
//		final String individualCrashFileName = "Motor_Vehicle_Crashes_-_Individual_Information__Three_Year_Window.csv";
		final String individualCrashFileName = "individual.csv";
		CSVReader openCsvReader = new CSVReaderBuilder(new InputStreamReader(
				Objects.requireNonNull(CSVParse.class.getClassLoader().getResourceAsStream(individualCrashFileName))))
						.withSkipLines(1).build();

		String[] data;
		while ((data = openCsvReader.readNext()) != null) {

			String caseIndividualId = data[1];
			String caseVehicleId = data[2];
			String victimStatus = data[3];
			String roleType = data[4];
			String seatingPosition = data[5];
			String ejection = data[6];
			String licenseStateCode = data[7];
			String sex = data[8];
			String transportMethod = data[9];
			String safetyEquipment = data[10];
			String injuryDesc = data[11];
			String injuryLoc = data[12];
			String injurySeverity = data[13];
			String age = data[14];

			// Check if the caseVehicleId already exists in the map, modify the object by
			// adding safetyEquipment
			Vehicle safetyEquip = getVehicle(caseVehicleId).hasSafetyEquipment(safetyEquipment);
			vehicles.put(caseVehicleId, safetyEquip);

			// create individual object
			Victim individualInfo = getVictim(caseVehicleId).hasCaseIndividualId(caseIndividualId)
					.hasVictimStatus(victimStatus).hasRoleType(roleType).hasSeatingPosition(seatingPosition)
					.hasEjection(ejection).hasLicenseStateCode(licenseStateCode).hasSex(sex)
					.hasTransportMethod(transportMethod).hasInjuryDesc(injuryDesc).hasInjuryLoc(injuryLoc)
					.hasInjurySeverity(injurySeverity).hasAge(Integer.parseInt(age));

			victim.put(caseVehicleId, individualInfo);
		}
	}

	private static Vehicle getVehicle(String ID) {
		return vehicles.containsKey(ID) ? vehicles.get(ID) : Vehicle.create(ID);
	}

	private static Crash getCrash(String ID) {
		return crash.containsKey(ID) ? crash.get(ID) : Crash.create(ID);
	}

	private static Victim getVictim(String ID) {
		return victim.containsKey(ID) ? victim.get(ID) : Victim.create(ID);
	}

	public static Map<String, Vehicle> getVehicle() {
		return vehicles;
	}

	public static Map<String, Crash> getCrash() {
		return crash;
	}

	public static Map<String, Victim> getVictim() {
		return victim;
	}
}
