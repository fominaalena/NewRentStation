package by.htp.speq.view.menu;

public class MainMenuAction extends BaseMenuAction {

	@Override
	protected void printText() {
		System.out.println("1. View available equipment");
		System.out.println("2. View rented items");
		System.out.println("3. Close application");
	}

	@Override
	protected void handleUserInput(int userInput) {
		switch (userInput) {
		case 1:
			new AvailableEquipmentMenuAction().perform();
			break;
		case 2:
			new RentedEquipmentMenuAction().perform();
			break;
		case 3:
			new CloseApplicationMenuAction().perform();
			break;
		default:
			break;
		}
	}

}
