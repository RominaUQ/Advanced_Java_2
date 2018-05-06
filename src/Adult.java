import java.util.HashSet;
import exceptions.*;
import java.util.Set;

public class Adult extends Profile {

	Adult Spouse;
	Set<Adult> _colleague = new HashSet<>();

	public Adult(String name, String status, int age) {
		super(name, status, age);
	}

	protected Set<Child> _dependents = new HashSet<>();

	public Boolean addfriend(Profile profile, Boolean isRelative) throws Exception {
		if (isRelative || !(profile instanceof Child)) {
			System.out.println("Adding friend " + profile.getname());
			_friendlist.add(profile);
		} else {
			throw new NotToBeFriendsException("An Adult is not allowed to add a Child");

		}
		return true;
	}

	public void marry(Adult Adult) throws Exception {
		if (Adult.getSpouse() != null || Spouse != null) {
			throw new NoAvailableException("This person is already married to someone else and is unavailble");
		} else {
			Spouse = Adult;
		}
	}

	public Boolean isMarried() {
		return Spouse != null;
	}

	public Adult getSpouse() {
		return Spouse;
	}

	public Set<Profile> getRelatives() {
		Set<Profile> dependents = new HashSet<>();
		for (Profile friend : _friendlist) {
			if (friend instanceof Child) {
				dependents.add((Child) friend);
			}
		}
		return dependents;
	}

	public void setColleague(Set<Adult> colleague) {
		_colleague = colleague;
	}

	public void addColleague(Adult adult) {
		if (this.getstatus() == adult.getstatus() && !this._colleague.contains(adult)) {
			_colleague.add(adult);
			adult.addColleague(this);
		}
	}

}
