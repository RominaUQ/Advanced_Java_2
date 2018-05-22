package gui;

import exceptions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
////Author: Aleksey Savran
import java.util.HashSet;
import java.util.Set;

import SQL.CreateQueries;
import SQL.ShowAllUsers;

/// all profiles are recorded in  a set
public class Driver {

	private Set<Profile> _profiles = new HashSet<>();
	DataReader _reader;
	ArrayList<Profile> _adults;
	ArrayList<Profile> _children;
	ArrayList<Profile> _kids;
	ArrayList<Profile> _allProfiles;
	private BufferedReader breader;

	public void setBreader(BufferedReader breader) {
		this.breader = breader;
	}

	public Driver(DataReader _reader) {
		try {
			this._reader = _reader;
			_adults = _reader.loadAdults();
			_children = _reader.loadChildren();
			_kids = _reader.loadKids();

			_allProfiles = _reader.loadAllProfiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	//// add some object for building the network
	// public Driver() {
	// try {
	// Adult prof1 = new Adult("Romina Sharif", "Working at Deloitte", 21);
	// Adult prof2 = new Adult("Nicholas Brown", "Working at RMIT", 35);
	// Adult prof3 = new Adult("John Smith", "<3", 29);
	// Adult prof4 = new Adult("Lisa Chan", "nurse at royal hospital", 26);
	// Adult prof5 = new Adult("Lola Gray", "nurse at royal hospital", 24);
	//
	// prof1.marry(prof2);
	// prof2.marry(prof1);
	// prof4.marry(prof3);
	// prof3.marry(prof4);
	//
	// Child child1 = new Child("Honey Brown", "Hi!I am baby", 13, prof1, prof2);
	// Child child2 = new Child("Sugar Brown", "Weeee", 6, prof1, prof2);
	// Child child3 = new Child("Rose Daw", "Hi!I am baby", 15, prof3, prof4);
	// Child YoungChild1 = new YoungChild("Bee Smith", "AWwwww", 2, prof4, prof3);
	//
	// AddFriend(prof1, prof2);
	// AddFriend(prof3, prof1);
	// AddFriend(prof4, prof5);
	// AddFriend(prof3, prof4);
	// AddFriend(child1, child3);
	//
	// _profiles.add(prof1);
	// _profiles.add(prof2);
	// _profiles.add(prof3);
	// _profiles.add(prof4);
	// _profiles.add(prof5);
	// _profiles.add(child1);
	// _profiles.add(child2);
	// _profiles.add(child3);
	// _profiles.add(YoungChild1);

	// } catch (Exception ex) {
	// System.out.println(ex.toString());
	// }

	// }

	/// create a profile method
	public Boolean createProfile(String name, String status, int age) throws Exception {
		return createProfile(name, status, age, null, null);
	}

	public Boolean createProfile(String name, String status, int age, Adult mum, Adult dad) throws Exception { // modify
																												// later
		Profile profile = null;
		if (searchProfile(name) == null) {
			if (age > 16) {
				profile = new Adult(name, "data/" + name + ".jpg", status, "", age, status);
			} else if (age <= 16 && age > 2) {
				profile = new Child(name, "data/" + name + ".jpg", status, status, age, status, mum, dad);
			} else {
				profile = new YoungChild(name, "data/" + name + ".jpg", status, status, age, status, mum, dad);
			}
			CreateQueries.createNewUser(name, status, "m", age, "Student"); 
			_profiles.add(profile);
			return true;
		}
		return false;
	}

	public void DeleteProfile(String name) throws Exception {
		Profile profile = searchProfile(name);
		if (profile.isParent()) {
			throw new NoParentException("Parent profile can not be deleted, because it has a connected child");
		} else {
			_profiles.remove(profile);
			for (Profile otherProfile : profile.getfriendlist()) {
				if (otherProfile.getfriendlist().contains(profile)) {
					otherProfile.removeFriend(profile);
				}
			}
		}
	}

	public Boolean createChild(String name, String status, int age, Adult parent1, Adult parent2) throws Exception { // modify
																														// later
		if (searchProfile(name) == null) {
			Profile profile = new Child(name, "data/Frodo.jpg", status, status, age, status, parent1, parent2);
			_profiles.add(profile);
			return true;
		}
		return false;
	}

	/// connect two profiles
	public Boolean AddFriend(Profile profile1, Profile profile2) throws Exception {
		Boolean success = profile1.addfriend(profile2, false);
		if (!success) {
			return false;
		}
		success = profile2.addfriend(profile1, false);
		return success;
	}

	//// disconnect two profiles
	public void removeFriend(Profile profile1, Profile profile2) {
		profile1.removeFriend(profile2);
		profile2.removeFriend(profile1);
	}

	/// look up a profile
	public Profile searchProfile(String name) {
		for (Profile p : _profiles) {
			if (p.getname().equals(name)) {
				return p;
			}
		}
		return null;
	}

	/// Showing dependencies
	public Set<Profile> showRelatives(Profile profile) {
		return profile.getRelatives();
	}

	//// if two are connected
	public Boolean areProfilesConnected(Profile profile1, Profile profile2) {
		return profile1.getfriendlist().contains(profile2);
	}

	public Set<Profile> listMembers() {
		return _profiles;
	}

	public Collection<Profile> listProfiles() throws Exception {
		return _reader.loadAllProfiles();
	}

	//// displaying profile
	public String DiplayProfile(String name) {
		Profile profile = searchProfile(name);
		return profile.toString();
	}
	///// display the friend list of a profile

	public String Diplayfriendlist(String name) {
		Profile profile = searchProfile(name);
		Set<Profile> _friendlist = profile.getfriendlist();

		if (_friendlist.isEmpty()) {
			return "";
		} else {
			String friends = "\r\nFriend list: \r\n************ \r\n";

			for (Profile p : _friendlist) {
				friends += p.getname();
				friends += "\r\n";
			}
			return friends;
		}
	}

	public Set<Profile> getAllProfiles() {
		return _profiles;
	}

	public DataReader get_reader() {
		return _reader;
	}

	public void set_reader(DataReader _reader) {
		this._reader = _reader;
	}

	public ArrayList<Profile> get_adults() {
		return _adults;
	}

	public void set_adults(ArrayList<Profile> _adults) {
		this._adults = _adults;
	}

	public ArrayList<Profile> get_children() {
		return _children;
	}

	public void set_children(ArrayList<Profile> _children) {
		this._children = _children;
	}

	public ArrayList<Profile> get_kids() {
		return _kids;
	}

	public void set_kids(ArrayList<Profile> _kids) {
		this._kids = _kids;
	}

	public ArrayList<Profile> get_allProfiles() {
		return _allProfiles;
	}

	public void set_allProfiles(ArrayList<Profile> _allProfiles) {
		this._allProfiles = _allProfiles;
	}

	public DataReader getReader() {
		return _reader;
	}

	public void setReader(DataReader reader) {
		this._reader = reader;
	}

	public ArrayList<Profile> getAdults() {
		return _adults;
	}

	public void setAdults(ArrayList<Profile> adults) {
		this._adults = adults;
	}

	public ArrayList<Profile> getChildren() {
		return _children;
	}

	public void setChildren(ArrayList<Profile> children) {
		this._children = children;
	}

	public ArrayList<Profile> getKids() {
		return _kids;
	}

	public void setKids(ArrayList<Profile> kids) {
		this._kids = kids;
	}

	public ArrayList<Profile> getFriends() throws Exception {

		try {
			setBreader(new BufferedReader(new FileReader("data/relations.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		ArrayList<Profile> friendlist = new ArrayList<>();

		try {
			String line = breader.readLine();
			while (line != null) {

				if (line.startsWith("#")) { // Skipping the comment lines
					line = breader.readLine();
					continue;
				}

				String[] tokens = line.split("\\|");
				String name = tokens[0];
				String name2 = tokens[1];
				String relation = tokens[2];
				
				if (relation == "Friends") {
					for (Profile p : _reader.getAllProfiles()) {
						if (p.getname().equals(name)) {
							p._friendlist.add(_reader.searchProfile(name2));
						}
					}
				}

				line = breader.readLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return friendlist;
	}

}
