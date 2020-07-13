package u_nameGen.year12.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Year12Menu {

	private JFrame frame;
	ImageIcon img = new ImageIcon("res/icon.png");

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Year12Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 278, 253);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnGenerateUsernames = new JButton("Internals");
		btnGenerateUsernames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InternalsGUI();
			}
		});
		btnGenerateUsernames.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnGenerateUsernames.setBounds(10, 11, 242, 90);
		frame.getContentPane().add(btnGenerateUsernames);

		JButton btnAssignToForm = new JButton("Externals");
		btnAssignToForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ExternalsMenu();
			}
		});
		btnAssignToForm.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAssignToForm.setBounds(10, 112, 242, 90);
		frame.getContentPane().add(btnAssignToForm);

		frame.setVisible(true);
	}
}
