import java.util.ArrayList;
import java.util.List;

public class Catalogue {
	private Library library;
	private List<Book> booksOnShelf;
	private List<Book> booksOnLoan;
	private List<Genre> genres;
	private List<Author> authors;
		// write your solution below

	public Catalogue(Library library) {
		library = this.library;
		booksOnShelf = new ArrayList<Book>();
		booksOnLoan = new ArrayList<Book>();
		genres = new ArrayList<Genre>();
		authors = new ArrayList<Author>();
	}

	public void displayBook() {
		System.out.println("");
		System.out.println("The Library has the following books:");
		for (Book book : booksOnShelf)
			System.out.println(book);
		for (Book book : booksOnLoan)
			System.out.println(book);
		System.out.println("");
	}

	public void displayAvailableBook() {
		System.out.println("\nThe following books are on the shelf:");
		for (Book book : booksOnShelf)
			System.out.println(book);
		System.out.println("");
	}

	public void displayGenres() {
		System.out.println("\nThe Library has books in the following genres:");
		for (Genre genre : genres)
			System.out.println(genre.toString().toLowerCase());
		System.out.println();
	}

	public void getGenre(String genre) {
		for (Book book : booksOnShelf)
			if ((genre.toLowerCase()).equals(book.getGenre().toLowerCase()))
				System.out.println(book);
	}

	public void getAuthor(String author) {
		for (Book book : booksOnShelf)
			if ((author.toLowerCase()).equals(book.getAuthor().toLowerCase()))
				System.out.println(book);
	}

	public void displayAuthors() {
		System.out.println("\nThe following authors have books in this Library:");
		for (Author author : authors)
			System.out.println(author);
		System.out.println();
	}

	public Book availableBooks(String name) {
        for (Book book : booksOnShelf)
            if (name.equals(book.getTitle()))
                return book;
        return null;
	}

	public Book loanedBooks(String name) {
		for (Book book : booksOnLoan)
			if (name.equals(book.getTitle()))
                return book;
        return null;
	}
	
	public String findAuthor(String name) {
		for (Book book : booksOnShelf)
			if (name.equals(book.getAuthor()))  
				return name;
		return null;
	}

	public String findTitle(String name) {
		for (Book book : booksOnShelf)
			if (name.equals(book.getTitle()))  
				return name;
		return null;
	}

	public void removeBook(String title, String author) {
		for (Book book : booksOnShelf) {
			if ((title.equals(book.getTitle()) && (author.equals(book.getAuthor()))))
				booksOnShelf.remove(book);
				break;
		}
	}

	public void addBook(String title, Author author, Genre genre) {
		booksOnShelf.add(new Book(title, author, genre));
		if (!authors.toString().contains(author.toString()))
			authors.add(author);
		if (!genres.toString().contains(genre.toString()))
			genres.add(genre);
	}

	public void borrowBook(Book book) {
		booksOnShelf.remove(book);
		booksOnLoan.add(book);
	}

	public void returnBook(Book book) {
		booksOnShelf.add(book);
		booksOnLoan.remove(book);
	}
}