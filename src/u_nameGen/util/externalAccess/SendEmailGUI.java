package u_nameGen.util.externalAccess;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import u_nameGen.main.Student;
import u_nameGen.util.exceptions.IncompleteFormException;

public class SendEmailGUI {

    private JFrame frame;


    public SendEmailGUI(List<Student>students) {
        initialize(students);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(List<Student>students) {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        boolean valid = false;
        String s = null;
        JTable table;
        String[] forms;
        String[] emails;
        while (!valid) {
            s = (String) JOptionPane.showInputDialog(frame,
                "Choose the number of forms:\n", "Number of Form Groups",
                JOptionPane.QUESTION_MESSAGE, null, null, 6);
            if (s.matches("^[1-9]\\d{0,1}$") && Integer.parseInt(s) <= 12) {
                valid = true;
            }
        }
        if (!valid) {
            frame.dispose();
        } else {
            String[] columnNames = {
                "Teachers email address",
                "Form group"
            };
            
            table = new JTable(new DefaultTableModel(columnNames,
                Integer.parseInt(s)));
        	table.setToolTipText("Don't include numbers only the letters in the form name");
            for (int x = 0; x < 2; x++) {
                table.getColumnModel().getColumn(x)
                    .setPreferredWidth((frame.getHeight() / 2));

            }
            forms = new String[Integer.parseInt(s)];
            emails = new String[Integer.parseInt(s)];
            frame.getContentPane().add(table, BorderLayout.CENTER);
            frame.setVisible(true);
            frame.getContentPane().add(table.getTableHeader(),
                BorderLayout.NORTH);
            JButton btnSendEmails = new JButton("Send Emails");
            btnSendEmails.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                	if (table.isEditing())
                	    table.getCellEditor().stopCellEditing();
                    int formPos = 0;
                    while (formPos < table.getRowCount() &&
                        table.getValueAt(formPos, 1).toString()
									.matches("^[a-zA-Z]{1,3}$")
							&& table.getValueAt(formPos, 0)
                        .toString()
                        .matches(
                            "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})")) {
                        forms[formPos] = table.getValueAt(formPos, 1)
                            .toString().toUpperCase().trim();
                        emails[formPos] = table.getValueAt(formPos, 0)
                            .toString().trim();
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
                                if (forms[i].equals(forms[j]) || emails[i].equals(emails[j])) {
                                    valid = false;

                                }
                            }
                        }
                        if (valid) {
 new SendEmail(students,forms,emails);
                        }
                    }
                }
            });
            frame.getContentPane().add(btnSendEmails, BorderLayout.SOUTH);
        }

    }

}