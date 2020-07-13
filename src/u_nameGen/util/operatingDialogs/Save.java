package u_nameGen.util.operatingDialogs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import u_nameGen.main.Student;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Save {
	JFileChooser fc = new JFileChooser();

	public Save() {
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}

	public void start(List<Student> students) {

		if (fc.showSaveDialog(new JButton()) == JFileChooser.APPROVE_OPTION) {
			try {
				String filepathGmail = fc.getSelectedFile().getAbsolutePath()
						.toString()
						+ File.separator
						+ "year"
						+ students.get(0).getYearGroup()
						+ "SeptGmail"
						+ students.get(0).getYearOfAddition() + ".csv";
				writeGmail(students, new FileWriter(filepathGmail));
				String filepathSystem = fc.getSelectedFile().getAbsolutePath()
						.toString()
						+ File.separator
						+ "year"
						+ students.get(0).getYearGroup()
						+ "SeptSystem"
						+ students.get(0).getYearOfAddition() + ".csv";
				writeSystem(students, new FileWriter(filepathSystem));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void writeGmail(List<Student> students, FileWriter fW) {
		try {
			BufferedWriter bW = new BufferedWriter(fW);
			for (int i = 0; i < students.size(); i++) {
				Student s = students.get(i);

				String stuInfo = s.getSName() + "," + s.getFName() + ","
						+ s.getHouse() + "," + s.getUserName();
				bW.write(stuInfo);
				bW.newLine();

			}
			bW.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void writeSystem(List<Student> students, FileWriter fW) {
		try {
			BufferedWriter bW = new BufferedWriter(fW);
			for (int i = 0; i < students.size(); i++) {
				Student s = students.get(i);
				String stuInfo = "dsad user \"\"cn="
						+ s.getUserName()
						+ ",ou="
						+ s.getYearGroup()
						+ " - Year "
						+ students.get(0).getYearGroup()
						+ ","
						+ "ou=students,ou=users,ou=Bexley Grammar School,dc=bexleygs,dc=internal\"\" -fn \"\""
						+ s.getFName() + "\"\"  -ln \"\"" + s.getSName()
						+ "\"\" -display \"\"" + s.getUserName()
						+ "\"\" -UPN \"" + s.getUserName()
						+ "@bexleygs.internal\"\" -samID " + s.getUserName()
						+ " -pwd B3xleyGS";

				bW.write("\"" + stuInfo + "\"");
				bW.newLine();

			}
			bW.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeBatch(String fileStr, int year) {

		String filepathBatch = "res" + File.separator + "gamYear"
				+ String.valueOf(year) + "_"
				+ Calendar.getInstance().get(Calendar.YEAR) + ".bat";
		File f = new File(filepathBatch);
		filepathBatch = f.getAbsolutePath();
		try {
			BufferedWriter bW = new BufferedWriter(
					new FileWriter(filepathBatch));

			bW.write(fileStr);
			bW.close();

			String[] command = { "cmd.exe", "/C", "Start", filepathBatch };
			//Process p = Runtime.getRuntime().exec(command);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

// class createXMLFile {
// public createXMLFile(List<Student> students, String filepath)
// throws ParserConfigurationException {
// DocumentBuilderFactory docFactory = DocumentBuilderFactory
// .newInstance();
// DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
// Document doc = docBuilder.newDocument();
// Element rootElement = doc.createElement("students");
// doc.appendChild(rootElement);

// for (int i = 0; i < students.size(); i++) {
// Element student = doc.createElement("student");
// rootElement.appendChild(student);

// Attr attr = doc.createAttribute("id");
// attr.setValue(Integer.toString(i + 1));
// student.setAttributeNode(attr);

// Element firstname = doc.createElement("firstname");
// firstname.appendChild(doc
// .createTextNode(students.get(i).getFName()));
// student.appendChild(firstname);

// // lastname elements
// Element lastname = doc.createElement("lastname");
// lastname.appendChild(doc.createTextNode(students.get(i).getSName()));
// student.appendChild(lastname);

// // username elements
// Element username = doc.createElement("username");
// username.appendChild(doc.createTextNode(students.get(i)
// .getUserName()));
// student.appendChild(username);

// }

// try {
// TransformerFactory transformerFactory = TransformerFactory
// .newInstance();
// Transformer transformer = transformerFactory.newTransformer();
// DOMSource source = new DOMSource(doc);
// StreamResult result = new StreamResult(new File(filepath));
// // StreamResult result = new StreamResult(System.out);
// transformer.transform(source, result);
// } catch (TransformerConfigurationException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (TransformerException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }

// System.out.println("File saved!");

// }

