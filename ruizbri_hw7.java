/********
 * Brian Ruiz CPS 2231
 * Planning time: 1 hour
 * Coding time: 2 hours
 * Testing time: 1 hours
 * Bug fixing time: 6 hours
********/
package report;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ruizbri_hw7 {	
	
	public static void format() throws FileNotFoundException {
		Scanner file = new Scanner(new File("C:\\Users\\brian\\Documents\\hw7.txt")); // Scan file for formatting
		int numberOfChildren = file.nextInt(); // The first line in the text file is the total number of inputs
		System.out.println("The number of Children is " + numberOfChildren);
		System.out.printf("%10s %14s %9s %11s %19s \n", "Date Of Service", "Child Name", "Type", "Year", "Additional Info"); // Formats headings 
		ArrayList<Child> list1 = new ArrayList<Child>(); 

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		// Reading through file
		while (file.hasNext()) {
			String childName = file.next(); // Assigns names
		    int birthYear = file.nextInt(); // Assigns birth year
		    LocalDate dateOfService = LocalDate.parse(file.next(), formatter); // Assigns date of service to be compared with other dates of service
		    String type = file.next(); // Assigns type of child
		    Child c = null; // create new child object
			
		    // Makes new child object into their respective types
		    if (type.equals("newborn")) { 
				boolean isPreterm = file.nextBoolean();
		    	c = new Newborn(childName, birthYear, dateOfService, type, isPreterm);
		    }
		    else if (type.equals("infant")) {
				boolean canSit = file.nextBoolean();
		    	c = new Infant(childName, birthYear, dateOfService, type, canSit);
		    }
		    else if (type.equals("toddler")) {
				String mainActivity = file.next();
		    	c = new Toddler(childName, birthYear, dateOfService, type, mainActivity);
		    }
		    else if (type.equals("preschooler")) {
				int canCountTill = file.nextInt();
		    	c = new Preschooler(childName, birthYear, dateOfService, type, canCountTill);
		    }
		    else if (type.equals("schooler")) {
				boolean isWorking = file.nextBoolean();
		    	c = new Schooler(childName, birthYear, dateOfService, type, isWorking);
		    }
		    list1.add(c);
		}
		
		// Sorts and prints out each child object
		Collections.sort(list1, Child.ServiceDate);
		for (Child c: list1) {
			System.out.println(c);
		}		
	}
	
	public static void main(String[] arg) throws FileNotFoundException, InputMismatchException, NullPointerException {
		format();
	}
}

// Parent class
// Each class has a special attribute labeled "Additional Information"

class Child{
	private String childName;
	private int birthYear;
	private LocalDate dateOfService;
	private String type;
	
	public Child() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Child(String childName, int birthYear, LocalDate dateOfService, String type) {
		super();
		this.childName = childName;
		this.birthYear = birthYear;
		this.dateOfService = dateOfService;
		this.type = type;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public LocalDate getDateOfService() {
		return dateOfService;
	}
	public void setDateOfService(LocalDate dateOfService) {
		this.dateOfService = dateOfService;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return String.format("%10s %16s %14s %9s         ", getDateOfService(), getChildName(), getType(), getBirthYear());
	}
	// Compares dates of service and returns Child that has a earlier date first
	public static Comparator<Child> ServiceDate = new Comparator<Child>() {
		public int compare(Child x1, Child x2) {
			LocalDate dateOfService1 = x1.getDateOfService();
			LocalDate dateOfService2 = x2.getDateOfService();
			
			return dateOfService1.compareTo(dateOfService2);
		}
	};
}

class Newborn extends Child {
	private boolean isPreterm;
	
	public Newborn() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Newborn(String childName, int birthYear, LocalDate dateOfService, String type, boolean isPreterm) {
		super(childName, birthYear, dateOfService, type);
		this.isPreterm = isPreterm;
		// TODO Auto-generated constructor stub
	}
	public boolean isPreterm() {
		return isPreterm;
	}
	public void setPreterm(boolean isPreterm) {
		this.isPreterm = isPreterm;
	}
	public Newborn(boolean isPreterm) {
		super();
		this.isPreterm = isPreterm;
	}
	@Override
	public String toString() {
		return super.toString() + isPreterm();
	}
}

class Infant extends Child {
	private boolean canSit;
	
	public Infant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Infant(String childName, int birthYear, LocalDate dateOfService, String type, boolean canSit) {
		super(childName, birthYear, dateOfService, type);
		this.canSit = canSit;
		// TODO Auto-generated constructor stub
	}

	public boolean isCanSit() {
		return canSit;
	}

	public void setCanSit(boolean canSit) {
		this.canSit = canSit;
	}

	public Infant(boolean canSit) {
		super();
		this.canSit = canSit;
	}
	@Override
	public String toString() {
		return super.toString() + isCanSit();
	}
}

class Toddler extends Child {
	private String mainActivity;
	
	public Toddler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Toddler(String childName, int birthYear, LocalDate dateOfService, String type, String mainActivity) {
		super(childName, birthYear, dateOfService, type);
		this.mainActivity = mainActivity;
		// TODO Auto-generated constructor stub
	}
	public String getMainActivity() {
		return mainActivity;
	}

	public void setMainActivity(String mainActivity) {
		this.mainActivity = mainActivity;
	}

	public Toddler(String mainActivity) {
		super();
		this.mainActivity = mainActivity;
	}
	@Override
	public String toString() {
		return super.toString() + mainActivity;
	}

}

class Preschooler extends Child {
	private int canCountTill;
	
	public Preschooler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Preschooler(String childName, int birthYear, LocalDate dateOfService, String type, int canCountTill) {
		super(childName, birthYear, dateOfService, type);
		this.canCountTill = canCountTill;
		// TODO Auto-generated constructor stub
	}

	public int getCanCountTill() {
		return canCountTill;
	}

	public void setCanCountTill(int canCountTill) {
		this.canCountTill = canCountTill;
	}

	public Preschooler(int canCountTill) {
		super();
		this.canCountTill = canCountTill;
	}
	@Override
	public String toString() {
		return super.toString() + canCountTill;
	}
}

class Schooler extends Child {
	private boolean isWorking;
	
	public Schooler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Schooler(String childName, int birthYear, LocalDate dateOfService, String type, boolean isWorking) {
		super(childName, birthYear, dateOfService, type);
		this.isWorking = isWorking;
		// TODO Auto-generated constructor stub
	}

	public boolean isWorking() {
		return isWorking;
	}

	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}

	public Schooler(boolean isWorking) {
		super();
		this.isWorking = isWorking;
	}
	@Override
	public String toString() {
		return super.toString() + isWorking;
	}

}