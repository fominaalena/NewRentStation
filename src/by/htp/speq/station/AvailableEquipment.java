package by.htp.speq.station;

import java.util.ArrayList;
import java.util.List;

import by.htp.speq.entity.Equipment;

public class AvailableEquipment {

	private List<Equipment> availableEquipment = new ArrayList<Equipment>();
	
	public AvailableEquipment() {
		
	}
	
	public AvailableEquipment(List<Equipment> availableEquipment) {
		super();
		this.availableEquipment = availableEquipment;
	}

	public void addEquipment(Equipment equipment) {
		availableEquipment.add(equipment);
	}

	public List<Equipment> getAvailableEquipment() {
		return availableEquipment;
	}	
	
	public void removeEquipment(Equipment equipment) {
		availableEquipment.remove(equipment);
	}

	public void setAvailableEquipment(List<Equipment> availableEquipment) {
		this.availableEquipment = availableEquipment;
	}
	
}
