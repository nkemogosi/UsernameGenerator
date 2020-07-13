package u_nameGen.util.externalAccess;

import java.util.Calendar;
import java.util.List;

import u_nameGen.main.Student;

public class GamAccess {
	Drive d;
	String yearofAddition = String.valueOf(Calendar.getInstance().get(
			Calendar.YEAR) - 5);

	public GamAccess() {
		d = new Drive();
	}

	public String moveUsers(List<Student> students, String oldForm,
			String[] newForms) {

		String info = "";
		String desc = "\"/Students/" + yearofAddition + " Entry";
		for (String form : newForms) {
			if (d.checkOrgName("/" + desc + "/" + form)) {
				info += "gam create org " + form + " parent " + desc + "\"\n";
			}

		}
		for (Student s : students) {
			if (!s.getForm().toLowerCase().equals("leaving")
					&& check(newForms, s.getForm())) {
				info += "gam update user " + s.getEmail() + " org " + desc
						+ "/" + s.getForm() + "\"\n";
			}

		}

		return info;
	}

	private boolean check(String[] newForms, String sForm) {
		boolean valid = false;
		for (String form : newForms) {
			if (form.equals(sForm.toUpperCase())) {
				valid = true;
			}

		}
		return valid;

	}
}
