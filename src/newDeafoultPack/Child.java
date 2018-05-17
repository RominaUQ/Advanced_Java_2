package newDeafoultPack;

import java.util.HashSet;
import java.util.Set;

public class Child extends Profile {
	Adult _parent1;
	Adult _parent2;
	Set<Child> _classmate = new HashSet<>();
	
	public Child(String name, String surname, String imagePath, String status, String sex, Integer age, String state, Adult MumParent, Adult DadParent) throws Exception {
		super(name, surname, imagePath, status, sex, age, state);
		this._parent1 = MumParent;
		this._parent2 = DadParent;
	}
	
	public Child(String name, String surname, String imagePath, String status, String sex, Integer age, String state) {
		super(name, surname, imagePath, status, sex, age, state);
	}

}
