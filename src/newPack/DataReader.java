package newPack;

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
	
	public void setBreader(BufferedReader breader) {
		this.breader = breader;
	}

	public Collection<Adult> loadProfile() {
		
		try {
			setBreader(new BufferedReader(new FileReader("data/people.txt")));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		ArrayList<Adult> adults = new ArrayList<Adult>();
		ArrayList<Child> children = new ArrayList<Child>();
		ArrayList<YoungChild> kids = new ArrayList<YoungChild>();
		ArrayList<Profile> allProfiles = new ArrayList<>();
		
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
				
				if  (age > 2 && age < 17) {
					children.add(new Child(name, surname, imagePath, status, sex, age, state));
				}
				
				if (age < 3) {
					kids.add(new YoungChild(name, surname, imagePath, status, sex, age, state));
				}
				
				line = breader.readLine();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return adults;
		
		
	}
	
	public static void main(String[] args) {
		DataReader reader = new DataReader();
		reader.loadProfile();
		
	}

	
	

}
