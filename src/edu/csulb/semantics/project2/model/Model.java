package edu.csulb.semantics.project2.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.XSD;

import edu.csulb.semantics.project2.CSVParse;
import edu.csulb.semantics.project2.Crash;
import edu.csulb.semantics.project2.Vehicle;
import edu.csulb.semantics.project2.Victim;

public class Model {

	private static final String ns = "https://www.data.gov#";
	private static final String fileName = "Crashes.owl";

	private OntModel model;

	private Map<String, Vehicle> vehicles = CSVParse.getVehicle();
	private Map<String, Victim> victim = CSVParse.getVictim();
	private Map<String, Crash> crash = CSVParse.getCrash();

	private Model() {
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);
		addClasses();
		addDataProperties();
	}

	private void addClasses() {
		OntClass vehicle = model.createClass(ns + "Vehicle");
		OntClass victim = model.createClass(ns + "Victim");
		OntClass crash = model.createClass(ns + "Crash");
	}

	private void addDataProperties() {
		ObjectProperty hasYear = model.createObjectProperty(ns + "hasYear");
		hasYear.addComment("Year of Crash", "EN");
		hasYear.addDomain(model.getOntClass(ns + "crash"));
		hasYear.addRange(XSD.positiveInteger);
		ObjectProperty isYearOf = model.createObjectProperty(ns + "isYearOf");
		isYearOf.addInverseOf(hasYear);

		ObjectProperty hasBodyType = model.createObjectProperty(ns + "hasBodyType");
		hasBodyType.addComment("Vehicle Body type", "EN");
		hasBodyType.addDomain(model.getOntClass(ns + "vehicle"));
		hasBodyType.addRange(XSD.xstring);
		ObjectProperty isBodyTypeOf = model.createObjectProperty(ns + "isBodyTypeOf");
		isBodyTypeOf.addInverseOf(hasBodyType);

		ObjectProperty hasRegistrationClass = model.createObjectProperty(ns + "hasRegistrationClass");
		hasRegistrationClass.addComment("Vehicle Registration Class, for ex: commercial, truck, etc.", "EN");
		hasRegistrationClass.addDomain(model.getOntClass(ns + "vehicle"));
		hasRegistrationClass.addRange(XSD.xstring);
		ObjectProperty isRegistrationClassOf = model.createObjectProperty(ns + "isRegistrationClassOf");
		isRegistrationClassOf.addInverseOf(hasRegistrationClass);

		ObjectProperty hasOccupants = model.createObjectProperty(ns + "hasOccupants");
		hasOccupants.addComment("Number of occupants in the vehicle", "EN");
		hasOccupants.addDomain(model.getOntClass(ns + "vehicle"));
		hasOccupants.addRange(XSD.integer);
		ObjectProperty areOccupantsOf = model.createObjectProperty(ns + "areOccupantsOf");
		areOccupantsOf.addInverseOf(hasOccupants);

		ObjectProperty hasFuelType = model.createObjectProperty(ns + "hasFuelType");
		hasFuelType.addComment("Fuel type of vehicle- gas, diesel etc.", "EN");
		hasFuelType.addDomain(model.getOntClass(ns + "vehicle"));
		hasFuelType.addRange(XSD.xstring);
		ObjectProperty isFuelTypeOf = model.createObjectProperty(ns + "isFuelTypeOf");
		isFuelTypeOf.addInverseOf(hasFuelType);

		ObjectProperty hasLicenseState = model.createObjectProperty(ns + "hasLicenseState");
		hasLicenseState.addComment("License state code for a vehicle", "EN");
		hasLicenseState.addDomain(model.getOntClass(ns + "vehicle"));
		hasLicenseState.addRange(XSD.xstring);
		ObjectProperty isLicenseStateOf = model.createObjectProperty(ns + "isLicenseStateOf");
		isLicenseStateOf.addInverseOf(hasLicenseState);

		ObjectProperty hasVehicleYear = model.createObjectProperty(ns + "hasVehicleYear");
		hasVehicleYear.addComment("Year of Vehicle", "EN");
		hasVehicleYear.addDomain(model.getOntClass(ns + "vehicle"));
		hasVehicleYear.addRange(XSD.nonNegativeInteger);
		ObjectProperty isVehicleYearOf = model.createObjectProperty(ns + "isVehicleYearOf");
		isVehicleYearOf.addInverseOf(hasVehicleYear);

		ObjectProperty hasPriorAction = model.createObjectProperty(ns + "hasPriorAction");
		hasPriorAction.addComment("Action prior to accident", "EN");
		hasPriorAction.addDomain(model.getOntClass(ns + "crash"));
		hasPriorAction.addRange(XSD.xstring);
		ObjectProperty isPriorActionOf = model.createObjectProperty(ns + "isPriorActionOf");
		isPriorActionOf.addInverseOf(hasPriorAction);

		ObjectProperty hasEngineCapacity = model.createObjectProperty(ns + "hasEngineCylinder");
		hasEngineCapacity.addComment("Engine capacity of vehicle", "EN");
		hasEngineCapacity.addDomain(model.getOntClass(ns + "vehicle"));
		hasEngineCapacity.addRange(XSD.integer);
		ObjectProperty isEngineCapacityOf = model.createObjectProperty(ns + "isEngineCapacityOf");
		isEngineCapacityOf.addInverseOf(hasEngineCapacity);

		ObjectProperty hasVehicleMake = model.createObjectProperty(ns + "hasVehicleMake");
		hasVehicleMake.addComment("Vehicle make", "EN");
		hasVehicleMake.addDomain(model.getOntClass(ns + "vehicle"));
		hasVehicleMake.addRange(XSD.xstring);
		ObjectProperty isVehicleMakeOf = model.createObjectProperty(ns + "isVehicleMakeOf");
		isVehicleMakeOf.addInverseOf(hasVehicleMake);

		ObjectProperty hasVin = model.createObjectProperty(ns + "hasVin");
		hasVin.addComment("Vehicle VIN number", "EN");
		hasVin.addDomain(model.getOntClass(ns + "vehicle"));
		hasVin.addRange(XSD.xstring);
		ObjectProperty isVinOf = model.createObjectProperty(ns + "isVinOf");
		isVinOf.addInverseOf(hasVin);

		ObjectProperty hasSafetyEquipment = model.createObjectProperty(ns + "hasSafetyEquipment");
		hasSafetyEquipment.addComment("Safety equipment of vehicle", "EN");
		hasSafetyEquipment.addDomain(model.getOntClass(ns + "vehicle"));
		hasSafetyEquipment.addRange(XSD.xstring);
		ObjectProperty isSafetyEquipmentOf = model.createObjectProperty(ns + "isSafetyEquipmentOf");
		isSafetyEquipmentOf.addInverseOf(hasSafetyEquipment);

		ObjectProperty hasCaseIndividualId = model.createObjectProperty(ns + "hasCaseIndividualId");
		hasCaseIndividualId.addComment("Case individual ID", "EN");
		hasCaseIndividualId.addDomain(model.getOntClass(ns + "crash"));
		hasCaseIndividualId.addRange(XSD.xstring);
		ObjectProperty isCaseIndividualIdOf = model.createObjectProperty(ns + "isCaseIndividualIdOf");
		isCaseIndividualIdOf.addInverseOf(hasCaseIndividualId);

		ObjectProperty hasCaseVehicleId = model.createObjectProperty(ns + "hasCaseVehicleId");
		hasCaseVehicleId.addComment("Case vehicle ID", "EN");
		hasCaseVehicleId.addDomain(model.getOntClass(ns + "crash"));
		hasCaseVehicleId.addRange(XSD.xstring);
		ObjectProperty isCaseVehicleIdOf = model.createObjectProperty(ns + "isCaseVehicleIdOf");
		isCaseVehicleIdOf.addInverseOf(hasCaseVehicleId);

		ObjectProperty hasEventType = model.createObjectProperty(ns + "hasEventType");
		hasEventType.addComment("Event lead to accident such as animal collision", "EN");
		hasEventType.addDomain(model.getOntClass(ns + "crash"));
		hasEventType.addRange(XSD.xstring);
		ObjectProperty isEventTypeOf = model.createObjectProperty(ns + "isEventTypeOf");
		isEventTypeOf.addInverseOf(hasEventType);

		ObjectProperty hasContributingFactor = model.createObjectProperty(ns + "hasContributingFactor");
		hasContributingFactor.addComment("Contributing factors to accident", "EN");
		hasContributingFactor.addDomain(model.getOntClass(ns + "crash"));
		hasContributingFactor.addRange(XSD.xstring);
		ObjectProperty hasContributingFactor1 = model.createObjectProperty(ns + "hasContributingFactor1");
		ObjectProperty hasContributingFactor2 = model.createObjectProperty(ns + "hasContributingFactor2");
		ObjectProperty isContributingFactorOf = model.createObjectProperty(ns + "isContributingFactorOf");
		hasContributingFactor.addSubProperty(hasContributingFactor1);
		hasContributingFactor.addSubProperty(hasContributingFactor2);
		isContributingFactorOf.addInverseOf(hasContributingFactor);

		ObjectProperty hasContributingFactorDesc = model.createObjectProperty(ns + "hasContributingFactorDesc");
		hasContributingFactorDesc.addComment("Contributing factors description of accident", "EN");
		hasContributingFactorDesc.addDomain(model.getOntClass(ns + "crash"));
		hasContributingFactorDesc.addRange(XSD.xstring);
		ObjectProperty hasContributingFactorDesc1 = model.createObjectProperty(ns + "hasContributingFactorDesc1");
		ObjectProperty hasContributingFactorDesc2 = model.createObjectProperty(ns + "hasContributingFactorDesc2");
		ObjectProperty isContributingFactorDescOf = model.createObjectProperty(ns + "isContributingFactorDescOf");
		hasContributingFactorDesc.addSubProperty(hasContributingFactorDesc1);
		hasContributingFactorDesc.addSubProperty(hasContributingFactorDesc2);
		isContributingFactorDescOf.addInverseOf(hasContributingFactorDesc);

		ObjectProperty hasVictimStatus = model.createObjectProperty(ns + "hasVictimStatus");
		hasVictimStatus.addComment("Status of victim after the crash", "EN");
		hasVictimStatus.addDomain(model.getOntClass(ns + "victim"));
		hasVictimStatus.addRange(XSD.xstring);
		ObjectProperty isVictimStatusOf = model.createObjectProperty(ns + "isVictimStatusOf");
		isVictimStatusOf.addInverseOf(hasVictimStatus);

		ObjectProperty hasSeatingPosition = model.createObjectProperty(ns + "hasSeatingPosition");
		hasSeatingPosition.addComment("Seating position of the victim during crash", "EN");
		hasSeatingPosition.addDomain(model.getOntClass(ns + "victim"));
		hasSeatingPosition.addRange(XSD.xstring);
		ObjectProperty isSeatingPositionOf = model.createObjectProperty(ns + "isSeatingPositionOf");
		isSeatingPositionOf.addInverseOf(hasSeatingPosition);

		ObjectProperty hasRoleType = model.createObjectProperty(ns + "hasRoleType");
		hasRoleType.addComment("Role Type of the victim like passenger of a vehicle", "EN");
		hasRoleType.addDomain(model.getOntClass(ns + "victim"));
		hasRoleType.addRange(XSD.xstring);
		ObjectProperty isRoleTypeOf = model.createObjectProperty(ns + "isRoleTypeOf");
		isRoleTypeOf.addInverseOf(hasRoleType);

		ObjectProperty hasGender = model.createObjectProperty(ns + "hasGender");
		hasGender.addComment("Gender of the Victim", "EN");
		hasGender.addDomain(model.getOntClass(ns + "victim"));
		hasGender.addRange(XSD.xstring);
		ObjectProperty isGenderOf = model.createObjectProperty(ns + "isGenderOf");
		isGenderOf.addInverseOf(hasGender);

		ObjectProperty hasAge = model.createObjectProperty(ns + "hasAge");
		hasAge.addComment("Age of the victim", "EN");
		hasAge.addDomain(model.getOntClass(ns + "victim"));
		hasAge.addRange(XSD.nonNegativeInteger);
		ObjectProperty isAgeOf = model.createObjectProperty(ns + "isAgeOf");
		isAgeOf.addInverseOf(hasAge);

		ObjectProperty hasEjection = model.createObjectProperty(ns + "hasEjection");
		hasEjection.addComment("Ejection status of the victim", "EN");
		hasEjection.addDomain(model.getOntClass(ns + "victim"));
		hasEjection.addRange(XSD.xstring);
		ObjectProperty isEjectionOf = model.createObjectProperty(ns + "isEjectionOf");
		isEjectionOf.addInverseOf(hasEjection);

		ObjectProperty hasInjuryArea = model.createObjectProperty(ns + "hasInjuryArea");
		hasInjuryArea.addComment("Area of injury sustained by the victim", "EN");
		hasInjuryArea.addDomain(model.getOntClass(ns + "victim"));
		hasInjuryArea.addRange(XSD.xstring);
		ObjectProperty isInjuryAreaOf = model.createObjectProperty(ns + "isInjuryAreaOf");
		isInjuryAreaOf.addInverseOf(hasInjuryArea);

		ObjectProperty hasInjuryDesc = model.createObjectProperty(ns + "hasInjuryDesc");
		hasInjuryDesc.addComment("Description of injury sustained by the victim", "EN");
		hasInjuryDesc.addDomain(model.getOntClass(ns + "victim"));
		hasInjuryDesc.addRange(XSD.xstring);
		ObjectProperty isInjuryDescOf = model.createObjectProperty(ns + "isInjuryDescOf");
		isInjuryDescOf.addInverseOf(hasInjuryDesc);

		ObjectProperty hasInjurySeverity = model.createObjectProperty(ns + "hasInjurySeverity");
		hasInjurySeverity.addComment("Severity of injury sustained by the victim", "EN");
		hasInjurySeverity.addDomain(model.getOntClass(ns + "victim"));
		hasInjurySeverity.addRange(XSD.xstring);
		ObjectProperty isInjurySeverityOf = model.createObjectProperty(ns + "isInjurySeverityOf");
		isInjurySeverityOf.addInverseOf(hasInjurySeverity);

		ObjectProperty hasTransportMethod = model.createObjectProperty(ns + "hasTransportMethod");
		hasTransportMethod.addComment("Method of transporting the victim from the site of crash", "EN");
		hasTransportMethod.addDomain(model.getOntClass(ns + "victim"));
		hasTransportMethod.addRange(XSD.xstring);
		ObjectProperty isTransportMethodOf = model.createObjectProperty(ns + "isTransportMethodOf");
		isTransportMethodOf.addInverseOf(hasTransportMethod);

	}

	private void addInstances() {
		addVehicleInstances();
		addIndividualInstances();
		addCrashInstances();
	}

	private void addVehicleInstances() {
		List<Vehicle> vehicle = vehicles.values().stream().collect(Collectors.toList());
		int size = vehicle.size();
		OntClass vehi = model.getOntClass(ns + "vehicle");
		
		for (Vehicle v : vehicle) {
			Individual vehicleInstance = vehi.createIndividual(ns + v.getIDAsURI());
//			model.add(vehicleInstance, hasID, h.getID());
//			model.add(vehicleInstance, hasBodyType, v.getVehicleBodytype())

		}

	}

	private void addIndividualInstances() {

	}

	private void addCrashInstances() {

	}
}
