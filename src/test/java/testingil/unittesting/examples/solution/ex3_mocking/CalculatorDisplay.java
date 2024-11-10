package testingil.unittesting.examples.solution.ex3_mocking;

public class CalculatorDisplay {
	private ExternalDisplay externalDisplay;
	String display = "";
	int lastArgument = 0;
	int result = 0;
	Boolean newArgument = false;
	Boolean shouldReset = true;

	OperationType lastOperation;
	public boolean hasDisplayConnected;

	public CalculatorDisplay(ExternalDisplay externalDisplay){
		this.externalDisplay = externalDisplay;
		hasDisplayConnected = false;
	}

	public void press(String key) {
		if (key.equals("+")) {
			lastOperation = OperationType.Plus;
			lastArgument = Integer.parseInt(display);
			newArgument = true;
		} else {
			if (key.equals("/")) {
				lastOperation = OperationType.Div;
				lastArgument = Integer.parseInt(display);
				newArgument = true;
			} else if (key.equals("=")) {
				int currentArgument = Integer.parseInt(display);
				if (lastOperation == OperationType.Plus) {
					display = Integer.toString(lastArgument + currentArgument);
				}
				if (lastOperation == OperationType.Div && currentArgument == 0) {
					display = "Division By Zero Error";
				}
				shouldReset = true;
			} else {
				if (shouldReset) {
					display = "";
					shouldReset = false;
				}
				if (newArgument) {
					display = "";
					newArgument = false;
				}
				display += key;
			}
		}
		if (externalDisplay.isOn()) {
			try {
				externalDisplay.show(getDisplay());
			} catch (Exception e) {
				display = "E";
			}
		}
		else
			this.hasDisplayConnected = false;
	}

	public String getDisplay() {
		if (display.equals(""))
			return "0";
		if (display.length() > 5)
			return "E";
		return display;
	}

}