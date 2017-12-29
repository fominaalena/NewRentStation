package by.htp.speq.view.menu;

import java.io.FileNotFoundException;
import java.util.List;

import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.logic.ConsoleStationLogicImpl;
import by.htp.speq.logic.StationLogic;
import by.htp.speq.station.AvailableEquipment;
import by.htp.speq.station.RentedEquipment;

public class AvailableEquipmentMenuAction extends BaseMenuAction {
	
	public AvailableEquipmentMenuAction() {
	}

	@Override
	protected void printText() {
		System.out.println("Press ZERO to return");
		System.out.println("Press equipment number to rent it:");
		try {
			List<Equipment> availableEquipment = logic.readAvailableEquipment().getAvailableEquipment();
			for (int i = 0; i < availableEquipment.size(); i++) {
				System.out.println(i + 1 + ". " + availableEquipment.get(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void handleUserInput(int userInput) {
		if (userInput == 0) {
			new MainMenuAction().perform();
		} else {
			try {
				new InputClientIdMenuAction(logic.readAvailableEquipment().getAvailableEquipment().get(userInput - 1)).perform();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
