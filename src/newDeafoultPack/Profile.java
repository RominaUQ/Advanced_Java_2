//package newDeafoultPack;
//
//import java.util.Collection;
//
//public class Profile {
//	
//	protected String _name;
//	protected String _surname;
//	protected String _imagePath;
//	protected String _sex;
//	protected String _state;
//	protected String _status;
//	protected int _age;
//	protected Collection <Profile> _friendlist;
//	
//	public Profile(String name, String surname, String imagePath, String status, String sex, int age, String state ) {
//		this._name = name;
//		this._surname = surname;
//		this._status = status;
//		this._age = age;
//		this._imagePath = imagePath;
//		this._sex = sex;
//		this._state = state;
//		
//	}
//
//	public Collection<Profile> get_friendlist() {
//		return _friendlist;
//	}
//
//	public void set_friendlist(Collection<Profile> _friendlist) {
//		this._friendlist = _friendlist;
//	}
//
//	public String get_name() {
//		return _name;
//	}
//
//	public void set_name(String _name) {
//		this._name = _name;
//	}
//
//	public String get_surname() {
//		return _surname;
//	}
//
//	public void set_surname(String _surname) {
//		this._surname = _surname;
//	}
//
//	public String get_imagePath() {
//		return _imagePath;
//	}
//
//	public void set_imagePath(String _imagePath) {
//		this._imagePath = _imagePath;
//	}
//
//	public String get_sex() {
//		return _sex;
//	}
//
//	public void set_sex(String _sex) {
//		this._sex = _sex;
//	}
//
//	public String get_state() {
//		return _state;
//	}
//
//	public void set_state(String _state) {
//		this._state = _state;
//	}
//
//	public String get_status() {
//		return _status;
//	}
//
//	public void set_status(String _status) {
//		this._status = _status;
//	}
//
//	public int get_age() {
//		return _age;
//	}
//
//	public void set_age(int _age) {
//		this._age = _age;
//	}
//	
//	
//	
//	@Override
//	public String toString() {
//		String profileString = "";
//		profileString += _name + " ";
//		profileString += _surname + " ";
//		profileString += _imagePath + " ";
//		profileString += _status + " ";
//		profileString += _sex + " ";
//		profileString += _age + " - ";
//		profileString += _state + " ";
//		
//		return profileString;
//	}
//
//}
