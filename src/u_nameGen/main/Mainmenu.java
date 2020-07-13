package u_nameGen.main;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import u_nameGen.main.gui.MainmenuGUI;
import u_nameGen.util.externalAccess.SendEmail;

public class Mainmenu {

	public static void main(String[] args) {
		// SendEmail s =new SendEmail();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						for (LookAndFeelInfo info : UIManager
								.getInstalledLookAndFeels()) {
							if ("Nimbus".equals(info.getName())) {
								UIManager.setLookAndFeel(info.getClassName());
								break;
							}
						}
					} catch (Exception e) {
						UIManager.setLookAndFeel(UIManager
								.getSystemLookAndFeelClassName()); // If Nimbus
																	// is not
																	// available,
																	// you can
																	// set the
																	// GUI to
																	// another
																	// look and
																	// feel.
					}

					MainmenuGUI window = new MainmenuGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
