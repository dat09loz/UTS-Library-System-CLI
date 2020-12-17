package src;

public class Author {
	private String name;
		// write your solution below

	public Author (String name) {
		this.name = name;
	}

	@Override
	public String toString () {
		return name;
	}
}