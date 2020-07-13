package u_nameGen.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import u_nameGen.util.externalAccess.Drive;

public class UsernameGen {
	List<Student> students = new ArrayList<>();
	Drive drive;
	String[] tempHouse = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L" };

	public UsernameGen() {

	}

	public List<Student> start(String path, String yearofAddition, int yearGroup)
			throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String l = reader.readLine();
			while ((l = reader.readLine()) != null) {
				String[] newLine = l.split(",");

				students.add(new Student(newLine[0], newLine[1], newLine[2],
						yearofAddition, yearGroup));

			}
			reader.close();
			students = sort(students);

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		}
		createUserNames();
		return students;
	}

	public List<Student> sort(List<Student> students) {
		for (int x = 0; x < students.size(); x++) {
			for (int y = 1; y < (students.size() - x); y++) {
				String s1 = students.get(y - 1).getSName()
						+ students.get(y - 1).getFName();
				String s2 = students.get(y).getSName()
						+ students.get(y).getFName();

				if (s1.compareToIgnoreCase(s2) > 0) {
					Student s = students.get(y - 1);
					students.set((y - 1), students.get(y));
					students.set(y, s);
				}
			}
		}

		return students;

	}

	private String removeSpaces(String word) {
		String noSpace = "";
		for (char c : word.toCharArray()) {

			if (Character.isLetter(c)) {
				noSpace += c;
			}
		}
		return formatWord(noSpace);

	}

	private String formatWord(String word) {
		return word.toLowerCase().trim();
	}

	public void generateUsername(Student student, String letters) {
		String sName = removeSpaces(student.getSName());
		if (sName.length() > 15) {
			sName = sName.substring(0, 14);
		}
		student.setUserName(student.getYearOfAddition() + removeSpaces(sName)
				+ "_" + letters);
	}

	private void checkForDuplicates(int pos) {
		Student currentStudent = students.get(pos);
		Student lastStudent;
		String suffix = formatWord(currentStudent.getFName().substring(0, 1));
		if (pos != 0) {
			lastStudent = students.get(pos - 1);
			if (lastStudent.getFName().charAt(0) == (currentStudent.getFName()
					.charAt(0))) {

				if (lastStudent.getSName().matches(currentStudent.getSName())) {
					int number = 0;
					int lastPos = pos - 1;
					if (!lastStudent.getHouse().matches(
							currentStudent.getHouse())) {

						while (!(students.get(lastPos).getHouse()
								.matches(currentStudent.getHouse()))
								&& (students.get(lastPos).getSName()
										.matches(currentStudent.getSName()))
								&& (lastPos != 0)) {
							lastPos--;
						}

					}
					lastStudent = students.get(lastPos);
					// String userName = lastStudent.getUserName();
					if (lastStudent.getHouse().matches(
							currentStudent.getHouse())) {
						number = getNumber(lastStudent.getUserName());
					} else {
						number = 0;
					}
					suffix += formatWord(currentStudent.getHouse().substring(0,
							1));

					if (number > 0) {
						suffix += number;
					}
				}

			}
		}
		generateUsername(currentStudent, suffix);
	}

	int getNumber(String userName) {
		int number = 0;
		if (Character.isDigit(userName.charAt((userName.length() - 1)))) {
			number = Integer
					.parseInt(userName.substring(userName.length() - 1));
		}
		return number + 1;
	}

	public List<Student> createExternalUsernames(String[] forms, String path,
			String yearOfAddition, int yearGroup) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String l = "";
			while ((l = reader.readLine()) != null) {
				String[] newLine = l.split(",");

				if (!(newLine[2].toLowerCase().equals("House".toLowerCase()))) {
					students.add(createStudent(forms, newLine[0], newLine[1],
							newLine[2].toLowerCase(), yearOfAddition, yearGroup));
				}

			}
			reader.close();
			students = sort(students);

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		}
		try {
			drive = new Drive();
			createUserNames();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return students;
	}

	private Student createStudent(String[] forms, String sName, String fName,
			String form, String year, int yearGroup) {
		Student s = null;

		for (int i = 0; i < forms.length; i++) {
			if (form.equals(forms[i].toLowerCase())) {
				s = new Student(sName, fName, forms[i], year, yearGroup);
				s.setForm(forms[i]);
			}
		}

		return s;
	}

	public void createUserNames() {
		for (int i = 0; i < students.size(); i++) {
			if (i == 4) {
				i = 4;
			}
			checkForDuplicates(i);
			if (drive != null) {
				while (!drive.checkUserName(students.get(i).getUserName())) {

				}
			}

		}
	}
}
