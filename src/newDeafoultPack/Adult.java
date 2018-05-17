package newDeafoultPack;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import exceptions.NoAvailableException;
import exceptions.NotToBeFriendsException;

public class Adult extends Profile {
	
	DataReader reader;
	
	Adult Spouse;
	
	Collection <Adult> _colleague;
	
	protected Collection <Child> _dependents;

	public Adult(String name, String surname, String imagePath, String status, String sex, int age, String state) {
		super(name, surname, imagePath, status, sex, age, state);
		
	}
	
	public Boolean addfriend(Profile profile, Boolean isRelative) throws Exception {
		if (isRelative || !(profile instanceof Child)) {
			System.out.println("Adding friend " + profile.get_name());
			_friendlist.add(profile);
		} else {
			throw new NotToBeFriendsException("An Adult is not allowed to add a Child");

		}
		return true;
	}

	public boolean isParent() {
		return getRelatives().size() > 0;
	}

	public void marry(Adult adult) throws Exception {
		if ((adult.getSpouse() != null || Spouse != null) && !adult.getSpouse().get_name().equals(this.get_name())) {
			throw new NoAvailableException("This person is already married to someone else and is unavailble");
		} else {
			Spouse = adult;
		}
	}

	public Boolean isMarried() {
		return Spouse != null;
	}

	public Adult getSpouse() {
		return Spouse;
	}

	public Collection<Profile> getRelatives() {
		Collection<Profile> dependents = new HashSet<>();
		for (Profile friend : _friendlist) {
			if (friend instanceof Child || friend instanceof YoungChild) {
				dependents.add(friend);
			}
		}
		return dependents;
	}

	public void setColleague(Collection<Adult> colleague) {
		_colleague = colleague;
	}

	public void addColleague(Adult adult) {
		if (this.get_status() == adult.get_status() && !this._colleague.contains(adult)) {
			_colleague.add(adult);
			adult.addColleague(this);
		}
	}

}
