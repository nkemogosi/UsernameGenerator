package u_nameGen.year12.gui;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.JPanel;

import u_nameGen.util.exceptions.IllegalFormNameException;
import u_nameGen.util.externalAccess.Drive;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InternalsGUI {

	private JFrame frame;
	private JTable table;
	private JPanel panel;
	ImageIcon img = new ImageIcon("res/icon.png");

	public InternalsGUI() {
		initialize();
		// Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK,
		// true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Year 12 Internals");
		frame.setBounds(100, 100, 506, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(img.getImage());
		String[] columNames = { "Old Form", "New Form 1", "New Form 2",
				"Move Students " };
		Object[][] data = new Object[][] { { null, null, null, "GO" },
				{ null, null, null, "GO" }, { null, null, null, "GO" },
				{ null, null, null, "GO" }, { null, null, null, "GO" },
				{ null, null, null, "GO" }, };

		panel = new JPanel();
		panel.setBounds(0, 0, 490, 284);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		table = new JTable(data, columNames);
		for (int x = 0; x < 4; x++) {
			table.getColumnModel().getColumn(x).setPreferredWidth(85);
		}
		table.setRowHeight(44);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		table.getColumn(columNames[3]).setCellRenderer(new ButtonRenderer());
		table.getColumn(columNames[3]).setCellEditor(
				new ButtonEditor(new JCheckBox()));
		panel.add(table, BorderLayout.CENTER);
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		frame.setVisible(true);
	}
}

class ButtonRenderer extends JButton implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonRenderer() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}

class ButtonEditor extends DefaultCellEditor {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	protected JButton button;

	private String label;

	private boolean isPushed;
	private int selectedRow;
	private JTable table;

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
			selectedRow = row;
			this.table = table;
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {

			try {
				String regex = "^[a-zA-Z]{1,3}$";
				String oldForm = (String) table.getModel().getValueAt(
						selectedRow, 0);
				String form1 = (String) table.getModel().getValueAt(
						selectedRow, 1);
				String form2 = (String) table.getModel().getValueAt(
						selectedRow, 2);

				form1 = form1.toUpperCase().trim();
				form2 = form2.toUpperCase().trim();
				oldForm = oldForm.toUpperCase();
				if (!form1.matches(regex) || !form2.matches(regex)
						|| !oldForm.matches(regex)
						|| form1.matches("^" + form2 + "$")) {

					throw new IllegalFormNameException();

				} else {

					new FormAssignment(oldForm, new String[] { form1, form2 },
							new Drive().getStudents());
				}

			} catch (Exception e) {
				try {
					throw new IllegalFormNameException();
				} catch (IllegalFormNameException e1) {
					// TODO Auto-generated catch block

				}
			}

		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
