
////Author: Romina Sharifpour
//package assignment2;

import java.util.HashSet;
import java.util.Set;

///Profile class represent the profile of all adults and superclass for other profile types such as dependents
public abstract class Profile {
	protected String _name;
	protected String _surname;
	protected String _status;
	protected int _age;
	protected Set<Profile> _friendlist = new HashSet<>();

	public Profile(String firstname, String famname, String status, int age) {
		this._name = firstname;
		this._surname = famname;
		this._status = status;
		this._age = age;
	}

	//// getters and setters

	/// this is to represent Adult profiles (overloading)
	public Profile(String firstname, String famname) {
		this._name = firstname;
		this._surname = famname;
	}

	public void setFriendlist(Set<Profile> friendlist) {
		_friendlist = friendlist;
	}

	public void setname(String firstname) {
		_name = firstname;
	}

	public String getname() {
		return _name;
	}

	public void setsurname(String familyname) {
		_surname = familyname;
	}

	public String getsurname() {
		return _surname;
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

	public void removefriend(Profile profile) {
		if (_friendlist.contains(profile)) {
			_friendlist.remove(profile);
		}
	}

	@Override
	public String toString() {
		String profileString = "";
		profileString += _name + " ";
		profileString += _surname + " - ";
		profileString += _age + " - ";
		profileString += _status;
		return profileString;
	}
}
