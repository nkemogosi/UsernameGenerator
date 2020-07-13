package u_nameGen.year12.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import u_nameGen.main.UsernameGen;
import u_nameGen.main.gui.UsernameGUI;
import u_nameGen.util.exceptions.IdenticalFormNameException;
import u_nameGen.util.exceptions.IncompleteFormException;
import u_nameGen.util.operatingDialogs.Open;

public class ExternalsMenu {

	private JFrame frame;
	ImageIcon img = new ImageIcon("res/icon.png");
	private JTable table;
	int formPos;

	/**
	 * Launch the application.
	 */

	public ExternalsMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Year 12 Externals");
		frame.setBounds(100, 100, 423, 268);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(img.getImage());
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		table = new JTable();

		table.setModel(new DefaultTableModel(new String[][] { { null },
				{ null }, { null }, { null }, { null }, { null }, { null },
				{ null }, { null }, { null }, { null }, { null }, },
				new String[] { "Form Groups" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		frame.getContentPane().add(table, BorderLayout.CENTER);
		table.setToolTipText("Enter the initials of the 12 form groups in year 12. Don't include numbers");
		String[] forms = new String[table.getRowCount()];
		JButton btnGenerateUsers = new JButton("Generate User(s)");
		btnGenerateUsers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.isEditing())
					table.getCellEditor().stopCellEditing();
				while (formPos < table.getRowCount()
						&& table.getValueAt(formPos, 0).toString()
								.matches("^[a-zA-Z]{1,3}$")) {
					forms[formPos] = table.getValueAt(formPos, 0).toString()
							.toUpperCase().trim();

					formPos++;
				}
				if (formPos != table.getRowCount()) {
					try {
						throw new IncompleteFormException();
					} catch (IncompleteFormException ei) {

					}
				} else {
					boolean valid = true;
					for (int i = 0; i < forms.length; i++) {
						for (int j = i + 1; j < forms.length; j++) {
							if (forms[i].equals(forms[j])) {
								valid = false;

							}
						}
					}
					if (valid) {

						try {
							String year = String.valueOf(Calendar.getInstance()
									.get(Calendar.YEAR) - 5);
							year = year.substring(year.length() - 2,
									year.length());
							new UsernameGUI(new UsernameGen()
									.createExternalUsernames(forms,
											new Open().getAbsolutePath(), year,
											12), 12);
						} catch (IOException e1) {

						}
					} else {
						try {

							throw new IdenticalFormNameException();
						} catch (IdenticalFormNameException ei) {

						}
					}

				}
				formPos = 0;
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		scrollPane.setVisible(true);
		frame.getContentPane().add(btnGenerateUsers, BorderLayout.SOUTH);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
