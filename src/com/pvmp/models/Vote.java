package com.pvmp.models;

public class Vote {
	private Boolean value;
	private Deputy deputy;
	private Proposition proposition;
	
	public Vote () {
		this.value = null;
		this.deputy = null;
		this.proposition = null;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	public Deputy getDeputy() {
		return deputy;
	}

	public void setDeputy(Deputy deputy) {
		this.deputy = deputy;
	}

	public Proposition getProposition() {
		return proposition;
	}

	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}
}
