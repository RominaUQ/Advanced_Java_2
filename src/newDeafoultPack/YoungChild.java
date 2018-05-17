package newDeafoultPack;

public class YoungChild extends Child {

	public YoungChild(String name, String surname, String imagePath, String status, String sex, Integer age,
			String state, Adult MumParent, Adult DadParent) throws Exception {
		super(name, surname, imagePath, status, sex, age, state, MumParent, DadParent);
	}
	
	public YoungChild(String name, String surname, String imagePath, String status, String sex, Integer age, String state) {
		super(name, surname, imagePath, status, sex, age, state);
	}
	
	

}
