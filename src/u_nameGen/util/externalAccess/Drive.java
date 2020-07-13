package u_nameGen.util.externalAccess;

import java.util.List;
import java.util.ArrayList;

import u_nameGen.main.Student;

public class Drive {
	public boolean checkUserName(String username) {
		return true;
	}
	public boolean checkOrgName(String orgName) {
		return true;
	}
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		for (int x = 0; x < 1; x++) {
			students.add(new Student("Nkem", "Ogosi",
					"11ogosi_n@bexleygs.co.uk"));
		}
		return students;

	}

	public void sortToForm(List<Student> students) {

	}
}
