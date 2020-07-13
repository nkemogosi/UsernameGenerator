package u_nameGen.main.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.UIManager.*;

import u_nameGen.year12.gui.Year12Menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainmenuGUI {

	public JFrame frame;
	ImageIcon img = new ImageIcon("res/icon.png");

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public MainmenuGUI() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Main Menu");
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.setBounds(100, 100, 446, 283);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(img.getImage());
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();

				new UsernameGUI(Integer.parseInt(button.getText().substring(
						button.getText().length() - 2,
						button.getText().length())));
			}
		};

		JButton btnYear7 = new JButton("Year 07");
		btnYear7.addActionListener(buttonListener);
		btnYear7.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnYear7.setBounds(10, 11, 200, 44);
		frame.getContentPane().add(btnYear7);

		JButton btnYear8 = new JButton("Year 08");
		btnYear8.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnYear8.setBounds(220, 11, 200, 44);
		btnYear8.addActionListener(buttonListener);
		frame.getContentPane().add(btnYear8);

		JButton btnYear9 = new JButton("Year 09");
		btnYear9.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnYear9.setBounds(10, 66, 200, 44);
		btnYear9.addActionListener(buttonListener);
		frame.getContentPane().add(btnYear9);

		JButton btnYear10 = new JButton("Year 10");
		btnYear10.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnYear10.setBounds(220, 66, 200, 44);
		btnYear10.addActionListener(buttonListener);
		frame.getContentPane().add(btnYear10);

		JButton btnYear11 = new JButton("Year 11");
		btnYear11.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnYear11.setBounds(10, 121, 200, 44);
		btnYear11.addActionListener(buttonListener);
		frame.getContentPane().add(btnYear11);

		JButton btnYear12 = new JButton("Year 12");
		btnYear12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Year12Menu();
			}
		});
		btnYear12.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnYear12.setBounds(220, 121, 200, 44);
		frame.getContentPane().add(btnYear12);

		JButton btnYear13 = new JButton("Year 13");
		btnYear13.addActionListener(buttonListener);
		btnYear13.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnYear13.setBounds(10, 176, 200, 44);
		frame.getContentPane().add(btnYear13);

		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HelpMenuGUI();
			}
		});
		btnHelp.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHelp.setBounds(220, 176, 200, 44);
		frame.getContentPane().add(btnHelp);

	}
}
