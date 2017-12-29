package by.htp.speq.view.menu;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.logic.ConsoleStationLogicImpl;
import by.htp.speq.logic.StationLogic;
import by.htp.speq.station.AvailableEquipment;
import by.htp.speq.station.RentedEquipment;

public class RentedEquipmentMenuAction extends BaseMenuAction {

	public static final int MAX_RENT_TIME = 24;
	
	@Override
	protected void printText() {
		System.out.println("Press ZERO to return");
		System.out.println("Press equipment number to return it:");
		try {
			List<RentUnit> units = logic.readRentedEquipment().getUnits();
			for (int i = 0; i < units.size(); i++) {
				System.out.println(i + 1 + ". " + units.get(i));
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
				List<Equipment> availableEquipment = logic.readAvailableEquipment().getAvailableEquipment();
				List<RentUnit> units = logic.readRentedEquipment().getUnits();
				RentUnit currentItem = units.get(userInput - 1);
				Date returnDate = new Date();
				long diffInHours = Math.floorDiv((returnDate.getTime() - currentItem.getRentDate().getTime()), (1000 * 60 * 60));
				double income = Math.min(diffInHours, MAX_RENT_TIME) * currentItem.getEquipment().getHourRate();
				double fine = Math.abs(diffInHours - MAX_RENT_TIME) * 1.1 * currentItem.getEquipment().getHourRate();
				logic.addFines(fine);
				logic.addIncome(income);
				units.remove(userInput - 1);
				availableEquipment.add(currentItem.getEquipment());
				logic.writeAvailableEquipment(new AvailableEquipment(availableEquipment));
				logic.writeRentedEquipment(new RentedEquipment(units));
				new MainMenuAction().perform();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
