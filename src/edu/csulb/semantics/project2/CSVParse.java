package edu.csulb.semantics.project2;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

/**
 * @author krutikapathak
 *
 */
public class CSVParse {
	public static Map<String, Vehicle> vehicles = new HashMap<>(); // String = VehicleCaseID
	public static Map<String, Victim> victim = new HashMap<>(); // String = IndividualCaseID
	public static Map<String, Crash> crash = new HashMap<>(); // String = crashID

	static {
		try {
			parseVehicleCSV();
			parseIndividualCSV();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// == == CSV parsing == ==//
	private static void parseVehicleCSV() throws IOException, CsvValidationException {
		final String vehicleFileName = "./Motor_Vehicle_Crashes_-_Vehicle_Information__Three_Year_Window.csv";
		Vehicle vehicle;
		Crash crashes;
		Reader reader = Files.newBufferedReader(Paths.get(vehicleFileName));
		CSVReader openCsvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

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

			// Creating crashes object
			crashes = getCrash("2018" + caseVehicleId).hasCrashYear(Integer.parseInt(crashYear)).hasPriorAction(priorAction)
					.hasContributingFactor1(contributingFactor1).hasContributingFactor1Desc(contributingFactor1Desc)
					.hasContributingFactor2(contributingFactor2).hasContributingFactor2Desc(contributingFactor2Desc)
					.hasEventType(eventType).hasVehicle(vehicle);

			// Adding vehicle to the hashmap of vehicles.
			vehicles.put(caseVehicleId, vehicle);
			// Adding crashes to the hashmap of crash. Prefix 2018 to differentiate crash with vehicle
			crash.put("2018" + caseVehicleId, crashes);
		}
		openCsvReader.close();
		reader.close();
	}

	// Reading file Individual info on vehicle crashes
	private static void parseIndividualCSV() throws IOException, CsvValidationException {
		final String individualCrashFileName = "./Motor_Vehicle_Crashes_-_Individual_Information__Three_Year_Window.csv";
		Reader reader = Files.newBufferedReader(Paths.get(individualCrashFileName));
		CSVReader openCsvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

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

			if (vehicles.containsKey(caseVehicleId)) {

				// create victim object
				Victim victimInfo = getVictim(caseIndividualId).hasCaseVehicleId(caseVehicleId)
						.hasSafetyEquipment(safetyEquipment).hasVictimStatus(victimStatus).hasRoleType(roleType)
						.hasSeatingPosition(seatingPosition).hasEjection(ejection).hasLicenseStateCode(licenseStateCode)
						.hasSex(sex).hasTransportMethod(transportMethod).hasInjuryDesc(injuryDesc)
						.hasInjuryLoc(injuryLoc).hasInjurySeverity(injurySeverity).hasAge(Integer.parseInt(age));
				
				// Adding victim info to victim hashmap
				victim.put(caseIndividualId, victimInfo);
				// Create crash with victim or Update existing crash object to add victim info
				Crash crashNew = getCrash("2018" + caseVehicleId).hasVictim(victimInfo)
						.hasCrashId("2018" + caseVehicleId);
				crash.put(caseIndividualId + caseVehicleId, crashNew);
			}
		}
		openCsvReader.close();
		reader.close();
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
