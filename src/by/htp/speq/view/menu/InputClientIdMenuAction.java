package by.htp.speq.view.menu;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import by.htp.speq.entity.Client;
import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.Equipment.Type;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.logic.ConsoleStationLogicImpl;
import by.htp.speq.logic.StationLogic;
import by.htp.speq.station.AvailableEquipment;
import by.htp.speq.station.RentedEquipment;

public class InputClientIdMenuAction extends BaseMenuAction {

	Equipment equipment;

	public InputClientIdMenuAction(Equipment equipment) {
		this.equipment = equipment;
	}

	@Override
	protected void printText() {
		System.out.println("Enter id of Client:");
	}

	@Override
	protected void handleUserInput(int userInput) {
		try {
			List<Equipment> availableEquipment = logic.readAvailableEquipment().getAvailableEquipment();
			List<RentUnit> units = logic.readRentedEquipment().getUnits();
			if (equipment.getType() == Type.OUTFIT && canRentMoreItems(userInput, units) || equipment.getType() == Type.ACCESSORY) {
					availableEquipment.remove(equipment);
					RentUnit rentUnit = new RentUnit(equipment, new Date(), new Client(userInput));
					units.add(rentUnit);
					logic.writeAvailableEquipment(new AvailableEquipment(availableEquipment));
					logic.writeRentedEquipment(new RentedEquipment(units));
				}
			new MainMenuAction().perform();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean canRentMoreItems(int clientId, List<RentUnit> units) {
		int count = 1;
		for (RentUnit rentUnit : units) {
			if (rentUnit.getEquipment().getType() == Type.OUTFIT) {
				if (rentUnit.getClient().id == clientId) {
					count++;
				}
				if (count > 3) {
					System.out.println("You can't get more than 3 items");
					System.out.println("----------");
					return false;
				}
			}
		}
		return true;
	}
}