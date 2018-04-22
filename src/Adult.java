import java.util.HashSet;
import java.util.Set;

public class Adult extends Profile {

	public Adult (String firstname, String famname, String status, int age) {
		super(firstname, famname, status, age);
	}
	
 protected Set<Child> _dependents = new HashSet<>();
 
 public Boolean addfriend(Profile profile, Boolean isRelative) {
		if (isRelative || !(profile instanceof Child)) {
			_friendlist.add(profile);
		} else {
			System.out.println("An Adult is not allowed to add a Child");
			return false;
		}
		return true;
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


}
