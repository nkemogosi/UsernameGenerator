package u_nameGen.main.gui;

import java.awt.EventQueue;
import java.awt.Image;


import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JEditorPane;

public class HelpMenuGUI {
	String[] helpList;
	int x;
	private JFrame frame;
	Image i;
	JEditorPane editorPane = new JEditorPane();
	JScrollPane j = new JScrollPane(editorPane);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpMenuGUI window = new HelpMenuGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HelpMenuGUI() {
		initialize();
		// java.net.URL he =
		try {
			setPicture(x);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Help Menu");
		frame.setBounds(100, 100, 411, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton = new JButton("←");
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (x > 0)
					x -= 1;
				try {
					setPicture(x);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("→");
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (x < helpList.length - 1) {

					x += 1;
					try {
						setPicture(x);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		panel.add(btnNewButton_1);
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		frame.getContentPane().add(j, BorderLayout.CENTER);

		helpList = getHelpList();
	}

	private String[] getHelpList() {
		File file = new File("res/help");
		String[] helpList = file.list();
		for (int i = 0; i < helpList.length; i++) {
			helpList[i] = file.getAbsolutePath() + File.separator + helpList[i];
		}
		// TODO Auto-generated method stub
		return helpList;

	}

	void setPictures(int i) {
		try {
			editorPane.setPage(helpList[i]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void setPicture(int i) throws IOException {
		String helpContent = "";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					helpList[i]));
			String l = "";
			while ((l = reader.readLine()) != null) {
				helpContent += l;

			}

			reader.close();
			editorPane.setText(helpContent);
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		}

	}

}
