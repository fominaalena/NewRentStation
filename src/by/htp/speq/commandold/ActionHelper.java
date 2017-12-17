package by.htp.speq.commandold;

import by.htp.speq.commandold.impl.DefaultActionImpl;
import by.htp.speq.commandold.impl.ViewCatalogActionImpl;

public class ActionHelper {

	public static StationAction defineAction(int input) {
		StationAction action = new DefaultActionImpl();
		switch(input) {
		case 1:
			action = new ViewCatalogActionImpl();
			break;
		}
		return action;
	}
}
