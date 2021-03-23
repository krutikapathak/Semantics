package edu.csulb.semantics.project2;

public class Crash {
	private int crashYear;
	private String caseVehicleId, eventType, priorAction, contributingFactor1, contributingFactor1Desc,
			contributingFactor2, contributingFactor2Desc;

	// == Constructor ==//
	private Crash(String ID) {
		this.caseVehicleId = ID;
	}

	// == == Creation == ==//
	public static Crash create(String ID) {
		return new Crash(ID);
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

	public String getPriorAction() {
		return priorAction;
	}

	public String getContributingFactor1() {
		return contributingFactor1;
	}

	public String getContributingFactor1Desc() {
		return contributingFactor1Desc;
	}

	public String getContributingFactor2() {
		return contributingFactor2;
	}

	public String getContributingFactor2Desc() {
		return contributingFactor2Desc;
	}

	public String getEventType() {
		return eventType;
	}
}
