package gui;

/**
 * This defines a relationship with another profile.
 * @author Romina Sharifpour
 */

public class Relation {
	protected String _name;
	protected String _relationType;

	public Relation(String name, String relationType) {
		_name = name;
		_relationType = relationType;
	}

	public String getName() {
		return _name;
	}

	public String getRelationType() {
		return _relationType;
	}
	
	@Override
	public String toString(){
		return _name + " - " + _relationType;
	}
}
