
public class YoungChild extends Dependent {

	public YoungChild(String firstname, String famname, String status, int age , Profile MumParent, Profile DadParent) {
		super(firstname, famname, status, age, MumParent, DadParent);
	}
	 
	public Boolean addfriend (Profile profile, Boolean isRelative) {
		if (isRelative && this.getage()<=2)  {
			_friendlist.add(profile);
			return true;
		} 
		else
			System.out.println("Younger than two year old, can not have add any friend");
		return false;
	}
	
}