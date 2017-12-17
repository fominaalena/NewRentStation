package by.htp.speq.view.menu;

import java.io.FileNotFoundException;
import java.util.List;

import by.htp.speq.entity.RentUnit;
import by.htp.speq.logic.ConsoleStationLogicImpl;
import by.htp.speq.logic.StationLogic;
import by.htp.speq.station.RentedEquipment;

public class RentedEquipmentMenuAction extends BaseMenuAction {

	private StationLogic logic;
	{
		logic = new ConsoleStationLogicImpl();
	}

	@Override
	protected void printText() {
		RentedEquipment rentedEquipment;
		try {
			rentedEquipment = logic.readRentedEquipment();
			List<RentUnit> units = rentedEquipment.getUnits();
			for (RentUnit unit : units) {
				System.out.println(unit);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Press ZERO to return");
	}

	@Override
	protected void handleUserInput(int userInput) {
		new MainMenuAction().perform();
	}

}
