package gui;

/**
 * This class handles communication between the GUI and the database.
 * @author Romina Sharifpour
 */

import exceptions.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import SQL.CreateQueries;
import SQL.DeleteQuery;
import SQL.SearchQueries;
import SQL.ShowAllUsers;

/// all profiles are recorded in  a set
public class Driver {

	private Set<Profile> _profiles = new HashSet<>();
	private DataReader _reader = new DataReader();
	
	/// create a profile method
	public Boolean createProfile(String name, String status, String sex, int age, String state) throws Exception {
		return createProfile(name, status, sex, age, state, null, null);
	}

	public Boolean createProfile(String name, String status, String sex, int age, String state, String mumName,
			String dadName) throws Exception {
		if (age < 0 || age > 150) {
			throw new NoSuchAgeException("Trying to enter a person whose age is negative or over 150");
		}

		Profile profile = null;
		if (searchProfile(name) == null) {
			if (age > 16) {
				profile = new Adult(name, name + ".jpg", status, sex, age, status);
			} else {
				Adult mum = (Adult) searchProfile(mumName);
				Adult dad = (Adult) searchProfile(dadName);

				if (mum == null || dad == null) {
					throw new NoParentException(
							"A Child can only be one couple dependent, including not only one parent");
				}

				if (age > 2) {
					profile = new Child(name, name + ".jpg", status, sex, age, state, mum, dad);
				} else {
					profile = new YoungChild(name, name + ".jpg", status, sex, age, state, mum, dad);
				}
			}
			CreateQueries.createNewUser(name, status, sex, age, state);
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
			DeleteQuery.userDelete(name);
			_profiles.remove(profile);
			for (Profile otherProfile : profile.getfriendlist()) {
				if (otherProfile.getfriendlist().contains(profile)) {
					otherProfile.removeFriend(profile);
				}
			}
		}
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
		Profile profile = SearchQueries.userSearch(name);
		return profile;
	}

	/// Showing dependencies
	public Set<Profile> showRelatives(Profile profile) {
		return profile.getRelatives();
	}

	//// if two are connected
	public Boolean areProfilesConnected(Profile profile1, Profile profile2) {
		return profile1.getfriendlist().contains(profile2);
	}

	public Collection<Profile> listProfiles() throws Exception {
		return _reader.loadAllProfiles();
	}

	public String Displayfriendlist(String name) {
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
		return ShowAllUsers.userShowAll();
	}

	public Boolean createRelationship(String name1, String name2, String relationType) {
		try {
			validateRelationship(name1, name2, relationType);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		CreateQueries.addRelation(name1, name2, relationType);
		CreateQueries.addRelation(name2, name1, relationType);
		return true;
	}

	private void validateRelationship(String name1, String name2, String relationType) throws Exception {
		Profile profile1 = searchProfile(name1);
		Profile profile2 = searchProfile(name2);

		if (relationType.equals("Friend")) {
			if (profile1 instanceof YoungChild || profile2 instanceof YoungChild) {
				throw new TooYoungException("Trying to make friends with a young child");
			}

			if (profile1 instanceof Child || profile2 instanceof Child) {
				if (profile1 instanceof Adult || profile2 instanceof Adult) {
					throw new NotToBeFriendsException("Trying to make an adult and a child friends");
				}
				if (Math.abs(profile1.getage() - profile2.getage()) > 3) {
					throw new NotToBeFriendsException("Can't friend two children with an age gap larger than 3");
				}
			}
		}

		if (relationType.equals("Couple")) {
			if (!(profile1 instanceof Adult) || !(profile2 instanceof Adult)) {
				throw new NotToBeCoupledException("Trying to make a couple when at least one member is not an adult");
			}

			if (((Adult) profile1).getSpouse() != null || ((Adult) profile2).getSpouse() != null) {
				throw new NoAvailableException(
						"Trying to make two adults a couple and at least one of them is already connected with another adult as a couple");
			}
		}

		if (relationType.equals("Colleague")) {
			if (!(profile1 instanceof Adult) || !(profile2 instanceof Adult)) {
				throw new NotToBeColleaguesException("Trying to connect a child in a colleague relation");
			}
		}

		if (relationType.equals("Classmate")) {
			if (profile1 instanceof YoungChild || profile2 instanceof YoungChild) {
				throw new NotToBeClassmatesException("Trying to connect a young child in a classmate relation");
			}
		}
	}
}