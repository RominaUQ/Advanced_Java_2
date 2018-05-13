
////Author: Romina Sharifpour
//package assignment2;

import java.util.HashSet;
import java.util.Set;

///Profile class represent the profile of all adults and superclass for other profile types such as dependents
public abstract class Profile {
	protected String _name;
	protected String _status;
	protected int _age;
	protected Set<Profile> _friendlist = new HashSet<>();

	public Profile(String name, String status, int age) {
		this._name = name;
		this._status = status;
		this._age = age;
	}

	//// getters and setters

	/// this is to represent Adult profiles (overloading)
	public Profile(String name) {
		this._name = name;
	}

	public void setFriendlist(Set<Profile> friendlist) {
		_friendlist = friendlist;
	}

	public void setname(String name) {
		_name = name;
	}

	public String getname() {
		return _name;
	}

	public String getstatus() {
		return _status;
	}

	public int getage() {
		return _age;
	}

	/// we'll use a set to keep the track of friendlists
	public Set<Profile> getfriendlist() {
		return _friendlist;
	}

	/// add friend method, and to avoid child and adult connecting
	public abstract Boolean addfriend(Profile profile, Boolean isRelative) throws Exception;

	/// dependent and parent relationships, we're using a set to maintain the
	/// dependents of a profile
	public abstract Set<Profile> getRelatives();

	public boolean isParent() {
		return false;
	}

	public void removeFriend(Profile profile) {
		if (_friendlist.contains(profile)) {
			_friendlist.remove(profile);
		}
	}

	@Override
	public String toString() {
		String profileString = "";
		profileString += _name + " ";
		profileString += _age + " - ";
		profileString += _status;
		return profileString;
	}

	public String getRelationship(Profile secondProfile) {
		boolean isInFriendList = _friendlist.contains(secondProfile);
		if (!isInFriendList) {
			return "No connection";
		}

		boolean isRelative = getRelatives().contains(secondProfile);
		if (isRelative) {
			if (secondProfile instanceof Adult) {
				if (!(this instanceof Adult)) {
					return this.getname() + " is child of " + secondProfile.getname();
				}
				if (((Adult) secondProfile).getSpouse().getname().equals(this.getname())) {
					return this.getname() + " is married to " + secondProfile.getname();
				}
			}
			if (!(secondProfile instanceof Adult) && this.getRelatives().contains(secondProfile)) {
				return this.getname() + " is parent of " + secondProfile.getname();
			}
		}

		if (secondProfile.getstatus().equals(this.getstatus())) {
			return (secondProfile instanceof Adult) ? "Colleagues" : "Classmates";
		}

		return "Friends";
	}
}
