package by.htp.speq.view.menu;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import by.htp.speq.entity.RentUnit;

public class CloseApplicationMenuAction extends BaseMenuAction {

	public CloseApplicationMenuAction() {
		
	}

	@Override
	protected void printText() {
		System.out.println("Прибыль составила:" + Math.ceil(logic.income()));
		System.out.println("Сумма штрафов составила:" + Math.ceil(logic.fine()));
		try {
			List<RentUnit> units = logic.readRentedEquipment().getUnits();
			for( RentUnit rentUnit: units) {
				Date returnDate = new Date();
				long diffInHours = Math.floorDiv((returnDate.getTime() - rentUnit.getRentDate().getTime()), (1000 * 60 * 60));
				if (diffInHours >24) {
					System.out.println(rentUnit);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void handleUserInput(int userInput) {

	}

}
