package by.htp.speq.view.menu;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import by.htp.speq.entity.Client;
import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.logic.ConsoleStationLogicImpl;
import by.htp.speq.logic.StationLogic;
import by.htp.speq.station.AvailableEquipment;
import by.htp.speq.station.RentedEquipment;

public class InputClientIdMenuAction extends BaseMenuAction {
	
	private StationLogic logic;	
	Equipment equipment;
	
	{
		logic = new ConsoleStationLogicImpl();
	}
	
	public InputClientIdMenuAction(Equipment equipment) {
		this.equipment = equipment;
	}

	@Override
	protected void printText() {
		System.out.println("Enter id of Client:");
	}

	@Override
	protected void handleUserInput(int userInput) {
		//TODO: validate counters
		try {
			List<Equipment> availableEquipment = logic.readAvailableEquipment().getAvailableEquipment();
			List<RentUnit> units = logic.readRentedEquipment().getUnits();
			availableEquipment.remove(equipment);
			RentUnit rentUnit = new RentUnit(equipment, new Date(), new Client(userInput));
			units.add(rentUnit);
			logic.writeAvailableEquipment(new AvailableEquipment(availableEquipment));
			logic.writeRentedEquipment(new RentedEquipment(units));
			new MainMenuAction().perform();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
