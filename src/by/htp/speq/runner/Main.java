package by.htp.speq.runner;

import java.io.IOException;

import by.htp.speq.commandold.ActionHelper;
import by.htp.speq.commandold.StationAction;
import by.htp.speq.view.ConsoleMenu;
import by.htp.speq.view.menu.MainMenuAction;

public class Main {

	public static void main(String[] args) throws IOException {
		
//		ConsoleMenu.printMenu();
//		int input = ConsoleMenu.readUserInput();
//		
//		StationAction action = ActionHelper.defineAction(input);
//		action.performAction();
		new MainMenuAction().perform();
	}

}
