package edu.csulb.semantics.project2.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.UnionClass;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.vocabulary.XSD;

import edu.csulb.semantics.project2.CSVParse;
import edu.csulb.semantics.project2.Crash;
import edu.csulb.semantics.project2.Vehicle;
import edu.csulb.semantics.project2.Victim;

/**
 * @author krutikapathak
 *
 */
public class Model {

	public enum Class_Name {
		Vehicle, Victim, Crash;
	}

	public enum Prop_Name {
		isOccupiedBy, isOccupantOf, involves, isInvolvedIn, hasYear, hasBodyType, hasFuelType, hasVehicleYear,
		hasEngineCapacity, hasVehicleMake, hasVin, hasCharacteristics, hasRegistrationClass, hasOccupants,
		hasPriorAction, safetyEquipmentInPlace, hasCaseIndividualId, hasCaseVehicleId, hasEventType,
		hasContributingFactor, hasContributingFactor1, hasContributingFactor2, hasContributingFactorDesc,
		hasContributingFactorDesc1, hasContributingFactorDesc2, hasVictimStatus, hasSeatingPosition, hasRoleType,
		hasSex, hasAge, hasEjection, hasInjuryLoc, hasInjuryDesc, hasInjurySeverity, hasTransportMethod,
		hasLicenseStateCode;
	}

	private static final String ns = "https://www.data.gov#";
	private static final String fileName = "Crashes.owl";

	private OntModel model;

	private Map<String, Vehicle> vehicles = CSVParse.getVehicle();
	private Map<String, Victim> victim = CSVParse.getVictim();
	private Map<String, Crash> crash = CSVParse.getCrash();

	// create ontology classes
	protected void addClasses() {
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF); // rule-based reasoner with OWL
																					// rules
		model.setNsPrefix("dt", ns);

		OntClass vehicle = model.createClass(ns + Class_Name.Vehicle);
		OntClass victim = model.createClass(ns + Class_Name.Victim);
		UnionClass crash = model.createUnionClass(ns + Class_Name.Crash,
				model.createList(new RDFNode[] { victim, vehicle }));

		vehicle.addComment("Vehicle class having vehicle related data involved in crash", "EN");
		victim.addComment("Information related to the Individuals involved in the crash  ", "EN");
		crash.addComment("Union of Vehicle and Victim data. Provides full information regarding a crash", "EN");
	}

	protected void addDataAndObjectProperties() {
		// create object properties
		ObjectProperty isOccupiedBy = model.createObjectProperty(ns + Prop_Name.isOccupiedBy);
		isOccupiedBy.setRange(model.getOntClass(ns + Class_Name.Vehicle));
		isOccupiedBy.setDomain(model.getOntClass(ns + Class_Name.Victim));
		isOccupiedBy.addComment("Vehicle is occupied by individuals/occupants during crash.", "EN");
		ObjectProperty isOccupantOf = model.createObjectProperty(ns + Prop_Name.isOccupantOf);
		isOccupantOf.addInverseOf(isOccupiedBy);
		isOccupantOf.addComment("Occupants of vehicle", "EN");

		ObjectProperty involves = model.createObjectProperty(ns + Prop_Name.involves);
		involves.setRange(model.getOntClass(ns + Class_Name.Vehicle));
		involves.setDomain(model.getOntClass(ns + Class_Name.Crash));
		involves.addComment("Crash involves vehicle", "EN");
		ObjectProperty isInvolvedIn = model.createObjectProperty(ns + Prop_Name.isInvolvedIn);
		involves.addInverseOf(isInvolvedIn);
		isInvolvedIn.addComment("Vehicle is involved in crash.", "EN");

		// create data properties
		DatatypeProperty hasYear = model.createDatatypeProperty(ns + Prop_Name.hasYear);
		hasYear.addComment("Year of Crash", "EN");
		hasYear.addDomain(model.getOntClass(ns + Class_Name.Crash));
		hasYear.addRange(XSD.positiveInteger);

		DatatypeProperty hasBodyType = model.createDatatypeProperty(ns + Prop_Name.hasBodyType);

		DatatypeProperty hasFuelType = model.createDatatypeProperty(ns + Prop_Name.hasFuelType);

		DatatypeProperty hasVehicleYear = model.createDatatypeProperty(ns + Prop_Name.hasVehicleYear);

		DatatypeProperty hasEngineCapacity = model.createDatatypeProperty(ns + Prop_Name.hasEngineCapacity);

		DatatypeProperty hasVehicleMake = model.createDatatypeProperty(ns + Prop_Name.hasVehicleMake);

		DatatypeProperty hasVin = model.createDatatypeProperty(ns + Prop_Name.hasVin);

		DatatypeProperty hasCharacteristics = model.createDatatypeProperty(ns + Prop_Name.hasCharacteristics);
		hasCharacteristics.addComment("Vehicle Characteristics", "EN");
		hasCharacteristics.addDomain(model.getOntClass(ns + Class_Name.Vehicle));
		hasCharacteristics.addRange(XSD.xstring);
		hasCharacteristics.addSubProperty(hasBodyType);
		hasCharacteristics.addSubProperty(hasEngineCapacity);
		hasCharacteristics.addSubProperty(hasFuelType);
		hasCharacteristics.addSubProperty(hasVehicleMake);
		hasCharacteristics.addSubProperty(hasVehicleYear);
		hasCharacteristics.addSubProperty(hasVin);

		DatatypeProperty hasRegistrationClass = model.createDatatypeProperty(ns + Prop_Name.hasRegistrationClass);
		hasRegistrationClass.addComment("Vehicle Registration Class, for ex: commercial, truck, etc.", "EN");
		hasRegistrationClass.addDomain(model.getOntClass(ns + Class_Name.Vehicle));
		hasRegistrationClass.addRange(XSD.xstring);

		DatatypeProperty hasOccupants = model.createDatatypeProperty(ns + Prop_Name.hasOccupants);
		hasOccupants.addComment("Number of occupants in the vehicle", "EN");
		hasOccupants.addDomain(model.getOntClass(ns + Class_Name.Vehicle));
		hasOccupants.addRange(XSD.integer);

		DatatypeProperty hasLicenseStateCode = model.createDatatypeProperty(ns + Prop_Name.hasLicenseStateCode);
		hasLicenseStateCode.addComment("License state code for a vehicle", "EN");
		hasLicenseStateCode.addDomain(model.getOntClass(ns + Class_Name.Vehicle));
		hasLicenseStateCode.addRange(XSD.xstring);

		DatatypeProperty hasPriorAction = model.createDatatypeProperty(ns + Prop_Name.hasPriorAction);
		hasPriorAction.addComment("Action prior to accident", "EN");
		hasPriorAction.addDomain(model.getOntClass(ns + Class_Name.Crash));
		hasPriorAction.addRange(XSD.xstring);

		DatatypeProperty safetyEquipmentInPlace = model.createDatatypeProperty(ns + Prop_Name.safetyEquipmentInPlace);
		safetyEquipmentInPlace.addComment("Safety equipment of vehicle", "EN");
		safetyEquipmentInPlace.addDomain(model.getOntClass(ns + Class_Name.Victim));
		safetyEquipmentInPlace.addRange(XSD.xstring);

		DatatypeProperty hasCaseIndividualId = model.createDatatypeProperty(ns + Prop_Name.hasCaseIndividualId);
		hasCaseIndividualId.addComment("Case individual ID", "EN");
		hasCaseIndividualId.addDomain(model.getOntClass(ns + Class_Name.Crash));
		hasCaseIndividualId.addRange(XSD.xstring);

		DatatypeProperty hasCaseVehicleId = model.createDatatypeProperty(ns + Prop_Name.hasCaseVehicleId);
		hasCaseVehicleId.addComment("Case vehicle ID", "EN");
		hasCaseVehicleId.addDomain(model.getOntClass(ns + Class_Name.Crash));
		hasCaseVehicleId.addRange(XSD.xstring);

		DatatypeProperty hasEventType = model.createDatatypeProperty(ns + Prop_Name.hasEventType);
		hasEventType.addComment("Event lead to accident such as animal collision", "EN");
		hasEventType.addDomain(model.getOntClass(ns + Class_Name.Crash));
		hasEventType.addRange(XSD.xstring);

		DatatypeProperty hasContributingFactor = model.createDatatypeProperty(ns + Prop_Name.hasContributingFactor);
		hasContributingFactor.addComment("Contributing factors to accident", "EN");
		hasContributingFactor.addDomain(model.getOntClass(ns + Class_Name.Crash));
		hasContributingFactor.addRange(XSD.xstring);
		DatatypeProperty hasContributingFactor1 = model.createDatatypeProperty(ns + Prop_Name.hasContributingFactor1);
		DatatypeProperty hasContributingFactor2 = model.createDatatypeProperty(ns + Prop_Name.hasContributingFactor2);
		hasContributingFactor.addSubProperty(hasContributingFactor1);
		hasContributingFactor.addSubProperty(hasContributingFactor2);

		DatatypeProperty hasContributingFactorDesc = model
				.createDatatypeProperty(ns + Prop_Name.hasContributingFactorDesc);
		hasContributingFactorDesc.addComment("Contributing factors description of accident", "EN");
		hasContributingFactorDesc.addDomain(model.getOntClass(ns + Class_Name.Crash));
		hasContributingFactorDesc.addRange(XSD.xstring);
		DatatypeProperty hasContributingFactorDesc1 = model
				.createDatatypeProperty(ns + Prop_Name.hasContributingFactorDesc1);
		DatatypeProperty hasContributingFactorDesc2 = model
				.createDatatypeProperty(ns + Prop_Name.hasContributingFactorDesc2);
		hasContributingFactorDesc.addSubProperty(hasContributingFactorDesc1);
		hasContributingFactorDesc.addSubProperty(hasContributingFactorDesc2);

		DatatypeProperty hasVictimStatus = model.createDatatypeProperty(ns + Prop_Name.hasVictimStatus);
		hasVictimStatus.addComment("Status of victim after the crash", "EN");
		hasVictimStatus.addDomain(model.getOntClass(ns + Class_Name.Victim));
		hasVictimStatus.addRange(XSD.xstring);

		DatatypeProperty hasSeatingPosition = model.createDatatypeProperty(ns + Prop_Name.hasSeatingPosition);
		hasSeatingPosition.addComment("Seating position of the victim during crash", "EN");
		hasSeatingPosition.addDomain(model.getOntClass(ns + Class_Name.Victim));
		hasSeatingPosition.addRange(XSD.xstring);

		DatatypeProperty hasRoleType = model.createDatatypeProperty(ns + Prop_Name.hasRoleType);
		hasRoleType.addComment("Role Type of the victim like passenger of a vehicle", "EN");
		hasRoleType.addDomain(model.getOntClass(ns + Class_Name.Victim));
		hasRoleType.addRange(XSD.xstring);

		DatatypeProperty hasSex = model.createDatatypeProperty(ns + Prop_Name.hasSex);
		hasSex.addComment("Gender of the Victim", "EN");
		hasSex.addDomain(model.getOntClass(ns + Class_Name.Victim));
		hasSex.addRange(XSD.xstring);

		DatatypeProperty hasAge = model.createDatatypeProperty(ns + Prop_Name.hasAge);
		hasAge.addComment("Age of the victim", "EN");
		hasAge.addDomain(model.getOntClass(ns + Class_Name.Victim));
		hasAge.addRange(XSD.nonNegativeInteger);

		DatatypeProperty hasEjection = model.createDatatypeProperty(ns + Prop_Name.hasEjection);
		hasEjection.addComment("Ejection status of the victim", "EN");
		hasEjection.addDomain(model.getOntClass(ns + Class_Name.Victim));
		hasEjection.addRange(XSD.xstring);

		DatatypeProperty hasInjuryLoc = model.createDatatypeProperty(ns + Prop_Name.hasInjuryLoc);
		hasInjuryLoc.addComment("Area of injury sustained by the victim", "EN");
		hasInjuryLoc.addDomain(model.getOntClass(ns + Class_Name.Victim));
		hasInjuryLoc.addRange(XSD.xstring);

		DatatypeProperty hasInjuryDesc = model.createDatatypeProperty(ns + Prop_Name.hasInjuryDesc);
		hasInjuryDesc.addComment("Description of injury sustained by the victim", "EN");
		hasInjuryDesc.addDomain(model.getOntClass(ns + Class_Name.Victim));
		hasInjuryDesc.addRange(XSD.xstring);

		DatatypeProperty hasInjurySeverity = model.createDatatypeProperty(ns + Prop_Name.hasInjurySeverity);
		hasInjurySeverity.addComment("Severity of injury sustained by the victim", "EN");
		hasInjurySeverity.addDomain(model.getOntClass(ns + Class_Name.Victim));
		hasInjurySeverity.addRange(XSD.xstring);

		DatatypeProperty hasTransportMethod = model.createDatatypeProperty(ns + Prop_Name.hasTransportMethod);
		hasTransportMethod.addComment("Method of transporting the victim from the site of crash", "EN");
		hasTransportMethod.addDomain(model.getOntClass(ns + Class_Name.Victim));
		hasTransportMethod.addRange(XSD.xstring);

	}

	// create instances
	protected void addInstances() {
		addCrashInstances();
		addVehicleInstances();
		addIndividualInstances();
	}

	private void addVehicleInstances() {
		List<Vehicle> vehicleDetails = vehicles.values().stream().collect(Collectors.toList());
		int size = vehicleDetails.size();
		OntClass vehicle = model.getOntClass(ns + Class_Name.Vehicle);
		OntClass victim = model.getUnionClass(ns + Class_Name.Victim);

		for (Vehicle v : vehicleDetails) {
			Individual vehicleInstance = vehicle.createIndividual(ns + v.getCaseVehicleId());
			model.add(vehicleInstance, model.getProperty(ns + Prop_Name.hasCaseVehicleId), v.getCaseVehicleId());
			model.add(vehicleInstance, model.getProperty(ns + Prop_Name.hasBodyType), v.getVehicleBodytype());
			model.add(vehicleInstance, model.getProperty(ns + Prop_Name.hasOccupants),
					String.valueOf(v.getOccupants()));
			model.add(vehicleInstance, model.getProperty(ns + Prop_Name.hasVehicleYear),
					String.valueOf(v.getVehicleYear()));
			model.add(vehicleInstance, model.getProperty(ns + Prop_Name.hasEngineCapacity),
					String.valueOf(v.getEngineCylinder()));
			model.add(vehicleInstance, model.getProperty(ns + Prop_Name.hasRegistrationClass),
					v.getRegistrationClass());
			model.add(vehicleInstance, model.getProperty(ns + Prop_Name.hasFuelType), v.getFuelType());
			model.add(vehicleInstance, model.getProperty(ns + Prop_Name.hasVehicleMake), v.getVehicleMake());
			model.add(vehicleInstance, model.getProperty(ns + Prop_Name.hasVin), v.getVin());
		}

	}

	private void addIndividualInstances() {
		List<Victim> victimDetails = victim.values().stream().collect(Collectors.toList());
		int size = victimDetails.size();
		OntClass victim = model.getOntClass(ns + Class_Name.Victim);

		for (Victim v : victimDetails) {
			Individual victimInstance = victim.createIndividual(ns + v.getCaseIndividualId());
			model.add(victimInstance, model.getProperty(ns + Prop_Name.hasVictimStatus), v.getVictimStatus());
			model.add(victimInstance, model.getProperty(ns + Prop_Name.hasRoleType), v.getRoleType());
			model.add(victimInstance, model.getProperty(ns + Prop_Name.hasSeatingPosition), v.getSeatingPosition());
			model.add(victimInstance, model.getProperty(ns + Prop_Name.hasEjection), v.getEjection());
			model.add(victimInstance, model.getProperty(ns + Prop_Name.hasLicenseStateCode), v.getLicenseStateCode());
			model.add(victimInstance, model.getProperty(ns + Prop_Name.hasSex), v.getSex());
			model.add(victimInstance, model.getProperty(ns + Prop_Name.hasTransportMethod), v.getTransportMethod());
			model.add(victimInstance, model.getProperty(ns + Prop_Name.hasInjuryDesc), v.getInjuryDesc());
			model.add(victimInstance, model.getProperty(ns + Prop_Name.hasInjuryLoc), v.getInjuryLoc());
			model.add(victimInstance, model.getProperty(ns + Prop_Name.hasInjurySeverity), v.getInjurySeverity());
			model.add(victimInstance, model.getProperty(ns + Prop_Name.hasAge), String.valueOf(v.getAge()));
		}
	}

	private void addCrashInstances() {
		List<Crash> crashDetails = crash.values().stream().collect(Collectors.toList());
		int size = crashDetails.size();
		OntClass crash = model.getUnionClass(ns + Class_Name.Crash);
		OntClass vehicle = model.getOntClass(ns + Class_Name.Vehicle);
		OntClass victim = model.getOntClass(ns + Class_Name.Victim);

		for (Crash c : crashDetails) {
			Individual crashInstance = crash.createIndividual(ns + c.getCrashIdAsURI());

			model.add(crashInstance, model.getProperty(ns + Prop_Name.hasEventType), c.getEventType());
			model.add(crashInstance, model.getProperty(ns + Prop_Name.hasPriorAction), c.getPriorAction());
			model.add(crashInstance, model.getProperty(ns + Prop_Name.hasContributingFactor1),
					c.getContributingFactor1());
			model.add(crashInstance, model.getProperty(ns + Prop_Name.hasContributingFactorDesc1),
					c.getContributingFactor1Desc());
			model.add(crashInstance, model.getProperty(ns + Prop_Name.hasContributingFactor2),
					c.getContributingFactor2());
			model.add(crashInstance, model.getProperty(ns + Prop_Name.hasContributingFactorDesc2),
					c.getContributingFactor2Desc());

			try {
				Individual vehicleInstance = vehicle.createIndividual(ns + c.getVehicleIDAsURI());
				model.add(crashInstance, model.getProperty(ns + Prop_Name.involves), vehicleInstance);
				int index = 0;
				for (Victim myVictim : c.getVictims()) {
					Individual victimInstance = victim.createIndividual(ns + c.getVictimIDAsURI(index++));
					if (!victimInstance.getURI().endsWith("#")) {
						model.add(vehicleInstance, model.getProperty(ns + Prop_Name.isOccupiedBy), victimInstance);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("crash ID as URI: " + c.getCrashIdAsURI());
			}
		}

	}

	public void writeModelToFile() throws IOException {
		FileWriter fw = new FileWriter(fileName);
		model.write(fw, "RDF/XML-ABBREV");
		fw.close();
	}
}
