package by.htp.speq.entity;

import java.util.Date;

public class RentUnit {

	private Equipment equipment;
	private Date rentDate;
	private Client client;
	
	public RentUnit() {
				
	}

	public RentUnit(Equipment equipment, Date rentDate, Client client) {
		super();
		this.equipment = equipment;
		this.rentDate = rentDate;
		this.client = client;
	}

	@Override
	public String toString() {
		return "RentUnit [equipment=" + equipment + ", rentDate=" + rentDate + ", client=" + client + "]";
	}

	
}
