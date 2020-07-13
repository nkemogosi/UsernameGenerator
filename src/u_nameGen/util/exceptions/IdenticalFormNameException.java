package u_nameGen.util.exceptions;

import javax.swing.JOptionPane;

public class IdenticalFormNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1495441368892764492L;

	public IdenticalFormNameException() {
		JOptionPane.showMessageDialog(null,
				"There areform names you have provided that are identical ",
				"Error :  " + "IdenticalFormNameException",
				JOptionPane.WARNING_MESSAGE);
	}

}
