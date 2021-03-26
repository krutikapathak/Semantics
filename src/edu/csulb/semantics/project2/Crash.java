package edu.csulb.semantics.project2;

import java.util.ArrayList;
import java.util.List;

import com.google.common.net.UrlEscapers;

/**
 * @author krutikapathak
 *
 */
public class Crash {
	private int crashYear;
	private String crashId, eventType, priorAction, contributingFactor1, contributingFactor1Desc, contributingFactor2,
			contributingFactor2Desc;
	private Vehicle vehicle;
	private List<Victim> victims;

	// == Constructor ==//
	private Crash(String ID) {
		victims = new ArrayList<>();
		this.crashId = ID;
	}
	
	// getters for URI
	public String getCrashIdAsURI() {
		return UrlEscapers.urlFragmentEscaper().escape(crashId);
	}

	public String getVehicleIDAsURI() {
		return UrlEscapers.urlFragmentEscaper().escape(vehicle.getCaseVehicleId());
	}

	public String getVictimIDAsURI(int i) {
		return this.victims.get(i) != null ? UrlEscapers.urlFragmentEscaper().escape(this.victims.get(i).getCaseIndividualId()) : "";
	}

	// == == Creation == ==//
	public static Crash create(String ID) {
		return new Crash(ID);
	}
	
	// == == Method to build a Crash object == ==//
	public Crash hasCrashId(String id) {
		this.crashId = id;
		return this;
	}

	public Crash hasPriorAction(String action) {
		this.priorAction = action;
		return this;
	}

	public Crash hasContributingFactor1(String factor1) {
		this.contributingFactor1 = factor1;
		return this;
	}

	public Crash hasContributingFactor1Desc(String factor1des) {
		this.contributingFactor1Desc = factor1des;
		return this;
	}

	public Crash hasContributingFactor2(String factor2) {
		this.contributingFactor2 = factor2;
		return this;
	}

	public Crash hasContributingFactor2Desc(String factor2des) {
		this.contributingFactor2Desc = factor2des;
		return this;
	}

	public Crash hasEventType(String event) {
		this.eventType = event;
		return this;
	}

	public Crash hasCrashYear(int cyear) {
		this.crashYear = cyear;
		return this;
	}

	public Crash hasVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		return this;
	}

	public Crash hasVictim(Victim victim) {
		this.victims.add(victim);
		return this;
	}
	
	// getters
	public String getPriorAction() {
		return this.priorAction != null ? UrlEscapers.urlFragmentEscaper().escape(this.priorAction) : "";
	}

	public String getContributingFactor1() {
		return this.contributingFactor1 != null ? UrlEscapers.urlFragmentEscaper().escape(this.contributingFactor1) : "";
	}

	public String getContributingFactor1Desc() {
		return this.contributingFactor1Desc != null ? UrlEscapers.urlFragmentEscaper().escape(this.contributingFactor1Desc) : "";
	}

	public String getContributingFactor2() {
		return this.contributingFactor2 != null ? UrlEscapers.urlFragmentEscaper().escape(this.contributingFactor2) : "";
	}

	public String getContributingFactor2Desc() {
		return this.contributingFactor2Desc != null ? UrlEscapers.urlFragmentEscaper().escape(this.contributingFactor2Desc) : "";
	}
	
	public List<Victim> getVictims() {
		return this.victims;
	}

	public String getEventType() {
		return this.eventType != null ? UrlEscapers.urlFragmentEscaper().escape(this.eventType) : "";
	}

}
