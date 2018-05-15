import exceptions.*;
////Author: Aleksey Savran
import java.util.HashSet;
import java.util.Set;

/// all profiles are recorded in  a set
public class Driver {

	private Set<Profile> _profiles = new HashSet<>();

	//// add some object for building the network
	public Driver() {
		try {
			Adult prof1 = new Adult("Romina Sharif", "Working at Deloitte", 21);
			Adult prof2 = new Adult("Nicholas Brown", "Working at RMIT", 35);
			Adult prof3 = new Adult("John Smith", "<3", 29);
			Adult prof4 = new Adult("Lisa Chan", "nurse at royal hospital", 26);
			Adult prof5 = new Adult("Lola Gray", "nurse at royal hospital", 24);

			prof1.marry(prof2);
			prof2.marry(prof1);
			prof4.marry(prof3);
			prof3.marry(prof4);

			Child child1 = new Child("Honey Brown", "Hi!I am baby", 13, prof1, prof2);
			Child child2 = new Child("Sugar Brown", "Weeee", 6, prof1, prof2);
			Child child3 = new Child("Rose Daw", "Hi!I am baby", 15, prof3, prof4);
			Child YoungChild1 = new YoungChild("Bee Smith", "AWwwww", 2, prof4, prof3);

			AddFriend(prof1, prof2);
			AddFriend(prof3, prof2);
			AddFriend(prof3, prof4);
			AddFriend(prof4, prof5);
			AddFriend(child1, child3);

			_profiles.add(prof1);
			_profiles.add(prof2);
			_profiles.add(prof3);
			_profiles.add(prof4);
			_profiles.add(prof5);
			_profiles.add(child1);
			_profiles.add(child2);
			_profiles.add(child3);
			_profiles.add(YoungChild1);

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}

	/// create a profile method
	public Boolean createProfile(String name, String status, int age) throws Exception {
		return createProfile(name, status, age, null, null);
	}

	public Boolean createProfile(String name, String status, int age, Adult mum, Adult dad) throws Exception {
		Profile profile = null;
		if (searchProfile(name) == null) {
			if (age > 16) {
				profile = new Adult(name, status, age);
			} else if (age <= 16 && age > 2) {
				profile = new Child(name, status, age, mum, dad);
			} else {
				profile = new YoungChild(name, status, age, mum, dad);
			}
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
	
	public Boolean createChild(String name, String status, int age, Adult parent1, Adult parent2) throws Exception {
		if (searchProfile(name) == null) {
			Profile profile = new Child(name, status, age, parent1, parent2);
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
}
