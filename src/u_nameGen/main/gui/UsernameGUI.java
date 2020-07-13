package u_nameGen.main.gui;

import java.awt.Choice;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.EtchedBorder;

import java.awt.SystemColor;

import javax.swing.JButton;

import java.awt.Label;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import u_nameGen.main.Student;
import u_nameGen.main.UsernameGen;
import u_nameGen.util.externalAccess.SendEmail;
import u_nameGen.util.externalAccess.SendEmailGUI;
import u_nameGen.util.operatingDialogs.Open;
import u_nameGen.util.operatingDialogs.Save;
public class UsernameGUI {
	List<Student> students = new ArrayList<>();
	private JFrame frame;
	private JTextField pathField;
	Calendar now = Calendar.getInstance(); // Gets the current date and time
	String yearofAddition = String.valueOf(now.get(Calendar.YEAR));
	Choice selection = new Choice();
	ImageIcon img = new ImageIcon("res/icon.png");
	String[] forms = new String[12];
	int yearGroup;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */

	public UsernameGUI(int parseInt) {
		yearGroup = parseInt;
		initialize();
		
	}

	public UsernameGUI(List<Student> externalUsernames, int yearGroup) {
		this.yearGroup = yearGroup;
		students = externalUsernames;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		yearofAddition = String
				.valueOf((Integer.parseInt(yearofAddition) - (yearGroup - 7)));
		yearofAddition = yearofAddition.substring(yearofAddition.length() - 2);
		frame = new JFrame("Generate and Save Usernames - Year " + yearGroup);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setSize(624, 278);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(img.getImage());

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(-15, 89, 633, 161);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		pathField = new JTextField();
		pathField.setText("N:\\IB\\Computer Science\\test1.csv");
		pathField.setBounds(95, 20, 386, 31);
		panel.add(pathField);
		pathField.setColumns(10);

		JLabel statusLabel = new JLabel("");
		statusLabel.setBounds(20, 127, 200, 14);
		panel.add(statusLabel);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (pathField.getText().equals("")) {
					pathField.setText(new Open().getAbsolutePath());
				}
				if (!pathField.getText().equals("")) {
					try {
						students = new UsernameGen().start(pathField.getText(),
								yearofAddition, yearGroup);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				if (!students.isEmpty()) {
					statusLabel.setText("Usernames have been generated");
					pathField.setText("");
				} else {
					statusLabel.setText("There is an error in the path file");
				}

			}

		});
		if (!students.isEmpty()) {
			btnGenerate.setEnabled(false);
		}
		btnGenerate.setBounds(221, 90, 89, 31);
		panel.add(btnGenerate);

		Label label = new Label("File Path");
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setBounds(28, 20, 62, 31);
		panel.add(label);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!students.isEmpty()) {
						new Save().start(students);
						statusLabel.setText("Usernames have been saved");
					} else {

					}

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		btnSave.setBounds(320, 90, 89, 31);
		panel.add(btnSave);

		JButton btnEmail = new JButton("Send Email(s)");
		btnEmail.setBounds(419, 90, 89, 31);
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!students.isEmpty()) {
					new SendEmailGUI(students);
				}

			}
		});
		panel.add(btnEmail);
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});
		btnCancel.setBounds(518, 90, 89, 31);
		panel.add(btnCancel);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pathField.setText(new Open().getAbsolutePath());

			}
		});
		btnBrowse.setBounds(518, 20, 89, 31);
		panel.add(btnBrowse);

		JLabel titleLabel = new JLabel("Generate and Save Usernames - Year "
				+ yearGroup);
		titleLabel.setFont(new Font(null, 1, 18));

		titleLabel.setBounds(6, 0, 300, 50);
		frame.getContentPane().add(titleLabel);

		JLabel textLabel = new JLabel("");
		textLabel.setVerticalAlignment(SwingConstants.TOP);
		textLabel
				.setText(convertToMultiline("Create gmail and system usernames and accounts for \nyear "
						+ yearGroup
						+ " students using desired file and save it as .csv files"));
		textLabel.setFont(new Font(null, 0, 11));

		textLabel.setBounds(6, 40, 300, 100);
		frame.getContentPane().add(textLabel);

		JButton btnPreview = new JButton("Preview");
		btnPreview.setBounds(122, 90, 89, 31);
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!students.isEmpty()) {

					new ListOfStudentNamesGUI(students);

				}

			}

		});
		panel.add(btnPreview);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	public String convertToMultiline(String orig) {
		return "<html>" + orig.replaceAll("\n", "<br>");
	}

}
