//package newDeafoultPack;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class Driver {
//	DataReader _reader = new DataReader();
//	ArrayList<Profile> _adults;
//	ArrayList<Profile> _children;
//	ArrayList<Profile> _kids;
//	ArrayList<Profile> _allProfiles;
//	private BufferedReader breader;
//
//	public void setBreader(BufferedReader breader) {
//		this.breader = breader;
//	}
//
//	public Driver(DataReader _reader) {
//		this._reader = _reader;
//		_adults = _reader.loadAdults();
//		_children = _reader.loadChildren();
//		_kids = _reader.loadKids();
//		_allProfiles = _reader.loadAllProfiles();
//	}
//
//	public DataReader get_reader() {
//		return _reader;
//	}
//
//	public void set_reader(DataReader _reader) {
//		this._reader = _reader;
//	}
//
//	public ArrayList<Profile> get_adults() {
//		return _adults;
//	}
//
//	public void set_adults(ArrayList<Profile> _adults) {
//		this._adults = _adults;
//	}
//
//	public ArrayList<Profile> get_children() {
//		return _children;
//	}
//
//	public void set_children(ArrayList<Profile> _children) {
//		this._children = _children;
//	}
//
//	public ArrayList<Profile> get_kids() {
//		return _kids;
//	}
//
//	public void set_kids(ArrayList<Profile> _kids) {
//		this._kids = _kids;
//	}
//
//	public ArrayList<Profile> get_allProfiles() {
//		return _allProfiles;
//	}
//
//	public void set_allProfiles(ArrayList<Profile> _allProfiles) {
//		this._allProfiles = _allProfiles;
//	}
//
//	public DataReader getReader() {
//		return _reader;
//	}
//
//	public void setReader(DataReader reader) {
//		this._reader = reader;
//	}
//
//	public ArrayList<Profile> getAdults() {
//		return _adults;
//	}
//
//	public void setAdults(ArrayList<Profile> adults) {
//		this._adults = adults;
//	}
//
//	public ArrayList<Profile> getChildren() {
//		return _children;
//	}
//
//	public void setChildren(ArrayList<Profile> children) {
//		this._children = children;
//	}
//
//	public ArrayList<Profile> getKids() {
//		return _kids;
//	}
//
//	public void setKids(ArrayList<Profile> kids) {
//		this._kids = kids;
//	}
//
//	public ArrayList<Profile> getFriends() {
//
//		try {
//			setBreader(new BufferedReader(new FileReader("data/relations.txt")));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//		ArrayList<Profile> friendlist = new ArrayList<>();
//
//		try {
//			String line = breader.readLine();
//			while (line != null) {
//
//				if (line.startsWith("#")) { // Skipping the comment lines
//					line = breader.readLine();
//					continue;
//				}
//
//				String[] tokens = line.split("\\|");
//				String name = tokens[0];
//				String surname = tokens[1];
//				String name2 = tokens[2];
//				String surname2 = tokens[3];
//				String relation = tokens[4];
//
//				for (Profile p : _reader.getAllProfiles()) {
//					if (p.get_name().equals(name)) {
//						p._friendlist.add(_reader.searchProfile(name2));
//					}
//				}
//
//				line = breader.readLine();
//
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
//		return friendlist;
//	}
//
//}
