package u_nameGen.util.operatingDialogs;

import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Open {
	File file;

	public Open() {

		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(new JButton()) == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		}

	}

	public String getAbsolutePath() {
		try {
			return file.getAbsolutePath();
		} catch (Exception e) {

		}
		return null;

	}
}
