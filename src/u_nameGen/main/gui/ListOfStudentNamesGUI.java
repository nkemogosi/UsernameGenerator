package u_nameGen.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ScrollPaneConstants;

import u_nameGen.main.Student;

import java.awt.Rectangle;

public class ListOfStudentNamesGUI {
	List<Student> students;
	String[] studentList;
	private JFrame frame;
	ImageIcon img = new ImageIcon("res/icon.png");

	/**
	 * Create the application.
	 * 
	 * @param students
	 */
	public ListOfStudentNamesGUI(List<Student> students) {
		this.students = students;
		initialize(students);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<Student> students) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 446, 464);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(img.getImage());
		studentList = reAssignChoice();

		JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public int getSize() {
				return studentList.length;
			}

			public String getElementAt(int index) {
				return studentList[index];
			}
		});
		list.setBounds(0, 0, 430, 426);
		JScrollPane scrollpane;
		scrollpane = new JScrollPane(list);
		scrollpane.setBounds(new Rectangle(0, 0, 440, 436));
		scrollpane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// frame.getContentPane().add(list);
		list.setDragEnabled(false);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					new Userform(list.getSelectedValue(), list
							.getSelectedIndex());
				}

			}
		});
		frame.getContentPane().add(scrollpane, BorderLayout.CENTER);

		frame.setVisible(true);
	}

	String[] reAssignChoice() {
		String[] studentList = new String[students.size()];
		for (int i = 0; i < students.size(); i++) {
			studentList[i] = (students.get(i).getSName() + ", "
					+ students.get(i).getFName() + ", "
					+ students.get(i).getHouse() + ", " + students.get(i)
					.getUserName());
		}
		return studentList;
	}

	private class Userform {
		JFrame frame;

		private Userform(String student, int studentIndex) {
			createUserForm(student, studentIndex);
		}

		void createUserForm(String student, int studentIndex) {
			String s[] = student.split(",");

			frame = new JFrame("User Profile");
			frame.getContentPane().setBackground(Color.WHITE);
			frame.setBackground(Color.WHITE);
			frame.setSize(300, 300);
			// frame.setIconImage(img.getImage());
			frame.setLocationRelativeTo(null);
			// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			int yPos = 30;
			int xPos = 80;
			int xSize = 150;
			int ySize = 30;
			String[] labelText = { "Surname", "Forename", "House", "Username" };
			for (int x = 0; x < s.length - 1; x++) {
				JLabel jLabel = new JLabel(labelText[x]);
				jLabel.setBounds(xPos - 60, yPos, xSize, ySize);
				frame.getContentPane().add(jLabel);
				JTextField textField = new JTextField();
				textField.setBounds(xPos, yPos, xSize, ySize);
				textField.setText(s[x]);
				textField.setEditable(false);
				frame.getContentPane().add(textField);
				yPos += 40;
			}
			JLabel usernameLabel = new JLabel(labelText[3]);
			usernameLabel.setBounds(xPos - 60, yPos, xSize, ySize);
			frame.getContentPane().add(usernameLabel);
			JTextField userNameTextField = new JTextField();
			userNameTextField.setBounds(xPos, yPos, xSize, ySize);
			userNameTextField.setText(s[3]);
			frame.getContentPane().add(userNameTextField);
			String originalUserName = s[3];
			// int studentPos = getStudentPos(originalUserName);
			JButton saveChanges = new JButton("Save Changes");
			saveChanges.setBounds(xPos, yPos + 40, xSize, ySize);
			yPos += 40;
			frame.getContentPane().add(saveChanges);

			saveChanges.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					userNameTextField.setText(userNameTextField.getText()
							.trim().toLowerCase());
					int strLength = userNameTextField.getText().length();
					if (originalUserName.matches(userNameTextField.getText())) {
						frame.dispose();
					} else {
						int i = 0;
						boolean isMatching = false;
						while (!isMatching && ++i < students.size()) {
							if (userNameTextField.getText().matches(
									students.get(i).getUserName())) {
								isMatching = true;
							}

						}
						if (!isMatching && (strLength >= 10 && strLength <= 20)) {
							students.get(studentIndex).setUserName(
									userNameTextField.getText());
							studentList = reAssignChoice();
							frame.dispose();

						}

					}
				}
			});

			JButton btnReset = new JButton("Reset Button");
			btnReset.setBounds(xPos, yPos + 30, xSize, ySize);
			frame.getContentPane().add(btnReset);
			btnReset.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// students.get(studentPos).setUserName(originalUserName);
					userNameTextField.setText(originalUserName);

				}
			});
			frame.setVisible(true);
		}
		// int getStudentPos(String username) {
		// boolean found = false;
		// int index = 0;
		// while (!found && ++index < students.size()) {
		// if (students.get(index).getUserName().matches(username)) {
		// found = true;
		//
		// }
		// }
		// return index;
		// }
		//

	}
}
