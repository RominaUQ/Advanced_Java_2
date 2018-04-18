////Author: Aleksey Savran

import java.util.HashSet;
import java.util.Set;

////Dependent class inherits the profile and is used specifically for child objects

public class Dependent extends Profile {
	Profile _parent1;
	Profile _parent2;
	int age;
	Set<Dependent> _classmate = new HashSet<>();
	
	public Dependent(String firstname, String famname, String status, int age, Profile MumParent, Profile DadParent) {
		super(firstname, famname, status, age);
		this._parent1 = MumParent;
		this._parent2 = DadParent;

		this._parent1.addfriend(this, true);
		this._parent2.addfriend(this, true);
	}

	//// overriding addfreind method from profile class in here
	@Override
	public Boolean addfriend(Profile profile, Boolean isRelative) {
		if (isRelative) {
			_friendlist.add(profile);
			return true;
		}

		//if (profile.getage() <= 2 && this.getage() <= 2) {
			//System.out.println("Younger than two year old, can not have any friend");
			//return false;
		//}

		/// to maintain the age difference condition

		int agediff = Math.abs(this.getage() - profile.getage());
		if (profile.getage() < 16 && agediff > 3) {
			_friendlist.add(profile);
		} else {
			System.out.println("You are not allowed to add a friend more than 3 years older than yourself");
			return false;
		}
		return true;
	}

	/// a set to maintain the list of parents of a dependent

	public Set<Profile> getRelatives() {
		Set<Profile> parents = new HashSet<>();
		parents.add(_parent1);
		parents.add(_parent2);
		return parents;
	}

	public String getMumname() {
		return _parent1.getname();

	}

	public String getMumfamname() {
		return _parent1.getsurname();
	}

	public String getDadname() {
		return _parent2.getname();

	}

	public String getDadfamname() {
		return _parent2.getsurname();
	}

	public void setclassmate(Set<Dependent> classmate) {
		_classmate = classmate;
	}
	
	
	
	public void addClassmate (Dependent Dependent1, Dependent Dependent2) {
		if (Dependent1.getstatus()== Dependent2.getstatus()) {
		_classmate.add(Dependent1);
		_classmate.add(Dependent2);
		} 
	}	
		public Set<Dependent> getclassmate() {
			return _classmate;
		
		}
	}







