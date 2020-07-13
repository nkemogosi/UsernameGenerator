package u_nameGen.util.exceptions;

import javax.swing.JOptionPane;

public class IllegalFormNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1495441368892764492L;

	public IllegalFormNameException() {
		JOptionPane
				.showMessageDialog(
						null,
						"The form(s) name you have provided does not match the form name standard ",
						"Error :  " + "IllegalFormNameException",
						JOptionPane.WARNING_MESSAGE);
	}

}
