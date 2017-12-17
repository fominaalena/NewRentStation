package by.htp.speq.view.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class BaseMenuAction {

	protected abstract void printText();

	
	protected abstract void handleUserInput(int userInput);

	public BaseMenuAction() {
	}
	
	public void perform() {
		printText();
		int userInput = -1;
		try {
			userInput = readUserInput();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		handleUserInput(userInput);
	}

	public static int readUserInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		return convertInputString(input);
	}
	
	private static int convertInputString(String input) {
		int value = Integer.parseInt(input);
		return value;
	}
}
