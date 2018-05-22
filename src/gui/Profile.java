package gui;

////Author: Romina Sharifpour
//package assignment2;

import java.util.HashSet;
import java.util.Set;

import SQL.SearchQueries;

///Profile class represent the profile of all adults and superclass for other profile types such as dependents
public abstract class Profile {
	protected String _name;
	protected String _imagePath;
	protected String _sex;
	protected String _state;
	protected String _status;
	protected int _age;

	protected Set<Profile> _friendlist = new HashSet<>();

	public Profile(String name, String imagePath, String status, String sex, int age, String state) {
		this._name = name;
		this._status = status;
		this._age = age;
		this._imagePath = imagePath;
		this._sex = sex;
		this._state = state;
	}

	//// getters and setters
	public String get_imagePath() {
		return _imagePath;
	}

	public String get_sex() {
		return _sex;
	}

	public String get_state() {
		return _state;
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

	public Set<Relation> getRelations() {
		return SearchQueries.getRelationsForUser(_name);
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
		profileString += _imagePath + " ";
		profileString += _status + " ";
		profileString += _sex + " ";
		profileString += _age + " - ";
		profileString += _state + " ";

		return profileString;
	}

	public String getRelationship(Profile secondProfile) {
		Set<Relation> relations = SearchQueries.getRelationsForUser(this.getname());

		for (Relation relation : relations) {
			if (relation.getName().equals(secondProfile.getname())) {
				return relation.getRelationType();
			}
		}

		for (Relation relation : relations) {
			Set<Relation> friendsRelations = SearchQueries.getRelationsForUser(relation.getName());
			for (Relation friendsRelation : friendsRelations) {
				if (friendsRelation.getName().equals(this.getname())) {
					return "Friend of Friend";
				}
			}
		}

		return "Not connected";
	}
}