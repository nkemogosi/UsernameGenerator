package u_nameGen.year12.gui;

import javax.swing.JFrame;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import u_nameGen.main.Student;
import u_nameGen.util.exceptions.IncompleteFormException;
import u_nameGen.util.externalAccess.GamAccess;
import u_nameGen.util.operatingDialogs.Save;

public class FormAssignment {

	private JFrame frame;
	private JTable table;
	ImageIcon img = new ImageIcon("res/icon.png");
	int studentPos = 0;
	String gamScript;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { FormAssignment window = new
	 * FormAssignment(); window.frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the application.
	 * 
	 * @param form2
	 * @param form1
	 * @param students
	 */
	public FormAssignment(String oldForm, String[] forms, List<Student> students) {
		initialize(oldForm, forms, students);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String oldForm, String[] forms,
			List<Student> students) {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setIconImage(img.getImage());
		String[] header = { "Student", "Choice" };
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(header);
		table = new JTable();
		table.setModel(dtm);
		for (Student s : students) {
			dtm.addRow(new Object[] {
					s.getFName() + " " + s.getSName() + "," + s.getUserName(),
					new String() });

		}
		table.setBounds(0, 0, 434, 262);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		scrollPane.setVisible(true);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem(forms[0]);
		comboBox.addItem(forms[1]);
		comboBox.addItem("Leaving");

		TableColumn d1 = new TableColumn();
		d1 = table.getColumnModel().getColumn(1);
		d1.setCellEditor(new DefaultCellEditor(comboBox));
		JButton submitBtn = new JButton("Submit");

		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.isEditing())
					table.getCellEditor().stopCellEditing();
				while (studentPos < students.size()
						&& !table.getValueAt(studentPos, 1).toString()
								.matches("^$")) {
					students.get(studentPos).setForm(
							(String) table.getValueAt(studentPos, 1));
					studentPos++;
				}
				if (studentPos != students.size()) {
					try {
						throw new IncompleteFormException();

					} catch (IncompleteFormException ei) {

					}
				} else {
					GamAccess gam = new GamAccess();
					gamScript = gam.moveUsers(students, oldForm, forms);
					int n = JOptionPane.showConfirmDialog(frame,
							"Would you like to preview the script?",
							"Script Preview", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						frame.dispose();
						frame = new JFrame();
						frame.setBounds(100, 100, 450, 300);
						frame.setVisible(true);
						JTextArea prevText = new JTextArea(gamScript);
						prevText.setText(gamScript);
						frame.getContentPane().setLayout(new BorderLayout());
						frame.getContentPane().add(prevText,
								BorderLayout.CENTER);
						for (ActionListener al : submitBtn.getActionListeners()) {
							submitBtn.removeActionListener(al);
						}
						submitBtn.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								gamScript = prevText.getText().trim();

							}
						});
						frame.getContentPane().add(submitBtn,
								BorderLayout.SOUTH);
					}
					new Save().writeBatch(gamScript, 12);

				}
				studentPos = 0;
			}
		});
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().add(submitBtn, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
}
