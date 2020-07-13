package u_nameGen.util.exceptions;

import javax.swing.JOptionPane;

public class IncompleteFormException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1495441368892764492L;

	public IncompleteFormException() {
		JOptionPane.showMessageDialog(null,
				"There are some unfilled students in this form ", "Error :  "
						+ "IncompleteFormException",
				JOptionPane.WARNING_MESSAGE);
	}

}
