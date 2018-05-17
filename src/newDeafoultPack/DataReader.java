package newDeafoultPack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;



public class DataReader {
	
	private BufferedReader breader;
	private ArrayList<Profile> profiles;
	
	
	public void setBreader(BufferedReader breader) {
		this.breader = breader;
	}

	public ArrayList<Profile> loadAdults() {
		
		try {
			setBreader(new BufferedReader(new FileReader("data/people.txt")));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		ArrayList<Profile> adults = new ArrayList<Profile>();
		
		try {
			String line = breader.readLine();
			while (line != null) {
				
				if (line.startsWith("#")) {  						//Skipping the comment lines
					line = breader.readLine();
					continue;
				}
				
				String[] tokens = line.split("\\|");
				String name = tokens[0];
				String surname = tokens[1];
				String imagePath = tokens[2];
				String status = tokens[3];
				String sex = tokens[4];
				String ageT = tokens[5];
				int age = Integer.parseInt(ageT);
				String state = tokens[6];
				
				if (age > 16) {
					adults.add(new Adult(name, surname, imagePath, status, sex, age, state));
				}
				
				line = breader.readLine();
				
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return adults;
		
		
	}
	
public ArrayList<Profile> loadChildren() {
		
		try {
			setBreader(new BufferedReader(new FileReader("data/people.txt")));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
		
		ArrayList<Profile> children = new ArrayList<Profile>();
		
		try {
			String line = breader.readLine();
			while (line != null) {
				
				if (line.startsWith("#")) {  						//Skipping the comment lines
					line = breader.readLine();
					continue;
				}
				
				String[] tokens = line.split("\\|");
				String name = tokens[0];
				String surname = tokens[1];
				String imagePath = tokens[2];
				String status = tokens[3];
				String sex = tokens[4];
				String ageT = tokens[5];
				int age = Integer.parseInt(ageT);
				String state = tokens[6];		
				
				if  (age > 2 && age < 17) {
					children.add(new Child(name, surname, imagePath, status, sex, age, state));
				}
				
				
				line = breader.readLine();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return children;
		
		
	}

public ArrayList<Profile> loadKids() {
	
	try {
		setBreader(new BufferedReader(new FileReader("data/people.txt")));
	} 
	catch (FileNotFoundException e) {
		e.printStackTrace();
		return null;
	}
	
	ArrayList<Profile> kids = new ArrayList<Profile>();
	
	try {
		String line = breader.readLine();
		while (line != null) {
			
			if (line.startsWith("#")) {  						//Skipping the comment lines
				line = breader.readLine();
				continue;
			}
			
			String[] tokens = line.split("\\|");
			String name = tokens[0];
			String surname = tokens[1];
			String imagePath = tokens[2];
			String status = tokens[3];
			String sex = tokens[4];
			String ageT = tokens[5];
			int age = Integer.parseInt(ageT);
			String state = tokens[6];		
			
			if  (age < 3) {
				kids.add(new YoungChild(name, surname, imagePath, status, sex, age, state));
			}
			
			
			line = breader.readLine();
			
		}
	} catch (IOException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
	
	return kids;
	
	
}

public ArrayList<Profile> loadAllProfiles() {
	
	try {
		setBreader(new BufferedReader(new FileReader("data/people.txt")));
	} 
	catch (FileNotFoundException e) {
		e.printStackTrace();
		return null;
	}
	
	ArrayList<Profile> allProfiles = new ArrayList<Profile>();
	
	try {
		String line = breader.readLine();
		while (line != null) {
			
			if (line.startsWith("#")) {  						//Skipping the comment lines
				line = breader.readLine();
				continue;
			}
			
			String[] tokens = line.split("\\|");
			String name = tokens[0];
			String surname = tokens[1];
			String imagePath = tokens[2];
			String status = tokens[3];
			String sex = tokens[4];
			String ageT = tokens[5];
			int age = Integer.parseInt(ageT);
			String state = tokens[6];		
			
			
				allProfiles.add(new Profile(name, surname, imagePath, status, sex, age, state));
			
			
			
			line = breader.readLine();
			
		}
	} catch (IOException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
	
	return allProfiles;
	
	
}

public ArrayList<Profile> getAllProfiles() {
	profiles = null;
	for (Profile a : loadAdults()) {
		profiles.add(a);
	}
	for (Profile c : loadChildren()) {
		profiles.add(c);
	}
	for (Profile k : loadKids()) {
		profiles.add(k);
	}
	return profiles;
}

public Profile searchProfile (String name) {
	Profile searchingProfile = null;
	for (Profile p : getAllProfiles()) {
		if (p.get_name().equals(name)) {
			searchingProfile = p;
		}
	}
	return searchingProfile;
}


	
	public static void main(String[] args) {
		DataReader reader = new DataReader();
		for (Profile a : reader.loadAllProfiles()) {
			System.out.println(a);
		}
		
	}
	
	

	
	

}
