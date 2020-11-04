import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.*;

public class Patron {
	private int ID;
	private String name;
	private List<Book> currentlyBorrowed;
	private List<Book> borrowingHistory;
	// write your solution below

	public Patron(int ID, String name) {
		this.ID = ID;
		this.name = name;
		currentlyBorrowed = new ArrayList<Book>();
		borrowingHistory = new ArrayList<Book>();
	}

	public boolean contains(int ID) {
		if (this.ID == ID)
			return true;
		return false;
	}

	public String patronName() {
		return name;
	}

	public void borrowBook(Book book) {
		currentlyBorrowed.add(book);
		borrowingHistory.add(book);
	}

	public void returnBook(Book book) {
		currentlyBorrowed.remove(book);
	}

	public void printBorrow() {
		if (!currentlyBorrowed.isEmpty())
		for (Book book : currentlyBorrowed)
			System.out.println(book);
	}

	public void printHistory() {
		if (!borrowingHistory.isEmpty())
		for (Book book : borrowingHistory)
			System.out.println(book);
	}

	public Book ownedBooks(String name) {
		for (Book book : currentlyBorrowed)
            if (name.equals(book.getTitle()))
                return book;
        return null;
	}

	public ArrayList<Book> frequencyList(ArrayList<Book> history) {
		Map<Book, Integer> frequencyMap = new LinkedHashMap<>();
		for (int i = 0; i < history.size(); i++) {
			if (frequencyMap.containsKey(history.get(i)))
				frequencyMap.put(history.get(i), frequencyMap.get(history.get(i)) + 1);
			else 
				frequencyMap.put(history.get(i), 1);
		}
		ArrayList<Entry<Book, Integer>> frequencyEntry = new ArrayList<>(frequencyMap.entrySet());
		Collections.sort(frequencyEntry, new Comparator<Entry<Book, Integer>>() {
			@Override
			public int compare(Entry<Book, Integer> o1, Entry<Book, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		}
		);
		ArrayList<Book> favorite = new ArrayList<Book>();
		for (Entry<Book, Integer> entry : frequencyEntry) {
            int frequency = entry.getValue();
            while (frequency >= 1){
                favorite.add(entry.getKey());
                frequency--;
            }
		}
		return favorite;
	}

	public void favoriteBook() {
		ArrayList<Book> BorrowingHistory = new ArrayList<Book>(borrowingHistory);
		ArrayList<Book> frequency = frequencyList(BorrowingHistory);
		Set<Book> favoriteSet = new LinkedHashSet<Book>(frequency);
		ArrayList<Book> favorite = new ArrayList<Book>(favoriteSet);
		if (favorite.size() < 4) 
			for (Book book : favorite)
				System.out.println(book);
		else 
			for (int i = 0; i < 3; i++)
				System.out.println(favorite.get(i));
	}

	public int getID() {
		return ID;
	}

	@Override
	public String toString() {
		return ID + " " + name;
	}
}