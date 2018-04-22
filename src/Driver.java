
////Author: Aleksey Savran
import java.math.*;
import java.util.HashSet;
import java.util.Set;

/// all profiles are recorded in  a set
public class Driver {

	private Set<Profile> _profiles = new HashSet<>();

	//// add some object for building the network
	public Driver() {
		Profile prof1 = new Adult("Romina", "Sharif", "Working at Deloitte", 21);
		Profile prof2 = new Adult("Nicholas", "Brown", "Working at RMIT", 35);
		Profile prof3 = new Adult("John", "Smith", "<3", 29);
		Profile prof4 = new Adult("Lisa", "Chan", "nurse at royal hospital", 26);
		Child child = new Child("Honey", "Brown", "Hi!I am baby", 13, prof1, prof2);
		Child dependent2 = new Child("Sugar", "Brown", "Weeee", 6, prof1, prof2);
		Child YoungChild1 = new YoungChild("Bee", "Smith", "AWwwww", 2, prof4, prof3);

		AddFriend(prof1, prof2);
		AddFriend(prof3, prof1);
		AddFriend(child, dependent2);

		_profiles.add(prof1);
		_profiles.add(prof2);
		_profiles.add(prof3);
		_profiles.add(prof4);
		_profiles.add(child);
		_profiles.add(dependent2);
		_profiles.add(YoungChild1);
	}

	/// create a profile method
	public Boolean createProfile(String firstname, String famname, String status, int age) {
		return createProfile(firstname, famname, status, age, null, null);
	}

	public Boolean createProfile(String firstname, String famname, String status, int age, Adult mum, Adult dad) {
		Profile profile = null;
		if (searchProfile(firstname, famname) == null) {
			if (age > 16) {
				profile = new Adult(firstname, famname, status, age);
			} else if (age <= 16 && age > 2) {
				profile = new Child(firstname, famname, status, age, mum, dad);
			} else {
				profile = new YoungChild(firstname, famname, status, age, mum, dad);
			}
			_profiles.add(profile);
			return true;
		}
		return false;
	}

	public Boolean createDependent(String firstname, String famname, String status, int age, Profile parent1,
			Profile parent2) {
		if (searchProfile(firstname, famname) == null) {
			Profile profile = new Child(firstname, famname, status, age, parent1, parent2);
			_profiles.add(profile);
			return true;
		}
		return false;
	}

	/// connect two profiles
	public Boolean AddFriend(Profile profile1, Profile profile2) {
		Boolean success = profile1.addfriend(profile2, false);
		if (!success) {
			return false;
		}
		success = profile2.addfriend(profile1, false);
		return success;
	}

	//// disconnect two profiles
	public void removeFriend(Profile profile1, Profile profile2) {
		profile1.removefriend(profile2);
		profile2.removefriend(profile1);
	}

	/// look up a profile
	public Profile searchProfile(String name, String surname) {
		for (Profile p : _profiles) {
			if (p.getname().equals(name) && p.getsurname().equals(surname)) {
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

	//// displaying profile
	public String DiplayProfile(String name, String surname) {
		Profile profile = searchProfile(name, surname);
		return profile.toString();
	}
	///// display the friend list of a profile

	public String Diplayfriendlist(String name, String surname) {
		Profile profile = searchProfile(name, surname);
		Set<Profile> _friendlist = profile.getfriendlist();

		if (_friendlist.isEmpty()) {
			return "";
		} else {
			String friends = "\r\nFriend list: \r\n************ \r\n";

			for (Profile p : _friendlist) {
				friends += p.getname();
				friends += " ";
				friends += p.getsurname();
				friends += "\r\n";
			}
			return friends;
		}
	}
}
