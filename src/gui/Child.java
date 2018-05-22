package gui;

/**
 * This is a Child class, extending the Profile abstract class.
 * @author Romina Sharifpour
 */

import java.util.HashSet;
import exceptions.*;
import java.util.Set;

////Dependent class inherits the profile and is used specifically for child objects

public class Child extends Profile {
	Adult _parent1;
	Adult _parent2;
	Set<Child> _classmate = new HashSet<>();

	public Child(String name, String imagePath, String status, String sex, Integer age, String state, Adult MumParent,
			Adult DadParent) throws Exception {
		super(name, imagePath, status, sex, age, state);

		if (MumParent != null && DadParent != null) {
			this._parent1 = MumParent;
			this._parent2 = DadParent;

			this.addfriend(DadParent, true);
			this.addfriend(MumParent, true);

			this._parent1.addfriend(this, true);
			this._parent2.addfriend(this, true);
		}
	}

	//// overriding addfreind method from profile class in here
	@Override
	public Boolean addfriend(Profile profile, Boolean isRelative) throws Exception {
		if (isRelative) {
			_friendlist.add(profile);
			return true;
		}

		/// to maintain the age difference condition
		int agediff = Math.abs(this.getage() - profile.getage());
		if (profile.getage() < 16 && agediff < 3) {
			_friendlist.add(profile);
		} else {
			throw new NotToBeFriendsException(
					"You are not allowed to add a friend more than 3 years older than yourself");
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

	public void setDad(Adult _parent1) {
		this._parent1 = _parent1;
	}

	public void setMum(Adult _parent2) {
		this._parent2 = _parent2;
	}
}
