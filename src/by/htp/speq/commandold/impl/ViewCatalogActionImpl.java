package by.htp.speq.commandold.impl;

import java.io.FileNotFoundException;
import java.util.List;

import by.htp.speq.commandold.StationAction;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.logic.ConsoleStationLogicImpl;
//import by.htp.speq.logic.FileStationLogicImpl;
import by.htp.speq.logic.StationLogic;
import by.htp.speq.station.RentedEquipment;

public class ViewCatalogActionImpl implements StationAction {

	private StationLogic logic;

	{
		logic = new ConsoleStationLogicImpl();
		// logic = new FileStationLogicImpl();
		// logic = new DataBaseStationLogicImpl();
	}

	@Override
	public void performAction() throws FileNotFoundException {
		RentedEquipment catalog = logic.readRentedEquipment();
		List<RentUnit> units = catalog.getUnits();
		for (RentUnit unit : units) {
			System.out.println(unit);
		}
	}
}
