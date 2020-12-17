package src;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private String title;
	private Author author;
	private Genre genre;
	private List<Patron> holds;
	// write your solution below

	public Book(String title, Author author, Genre genre) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		holds = new ArrayList<Patron>();
	}

	public boolean contains(String name) {
		if (this.title == name)
			return true;
		return false;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		String name = author.toString();
		return name;
	}

	public String getGenre() {
		String name = genre.toString();
		return name;
	}

	public boolean checkPatron(Patron patron) {
		if (holds.indexOf(patron) == 0) {
			holds.remove(patron);
			return true;
		}
		else 
			return false;
	}

	public boolean emptyHolds() {
		if (holds.size() == 0)
			return true;
		return false;
	}

	public boolean checkHolds(int ID) {
		for (Patron patron : holds)
			if (ID == (patron.getID()))
				return true;
		return false;
	}


	public void addHold(Patron patron) {
		holds.add(patron);
	}

	@Override
	public String toString() {
		return author + ", " + title;
	}
}