package de.airdevelopments.webuntisapi.resultobjects;

public enum SubstitutionType {
	
	CANCEL("Cancelled"), SUBST("Substitution"), ADD("Extra"), SHIFT("Shifted"), RMCHG("Room Changed"), FREE("Free"), UNKNOWN(""), INFO("Info");

	private String display;

	private SubstitutionType(String display)
	{
		this.display = display;
	}

	public String getDisplay()
	{
		return this.display;
	}

}
