package src;

import java.util.ArrayList;
import java.util.List;

public class Library {
	private Catalogue catalogue;
    private List<Patron> patrons;
    // write your solution below

    public Library() {
        catalogue = new Catalogue(null);
        patrons = new ArrayList<Patron>();
    }

    public static void main(String[] args) {
        new Library().use();
    }

    //main menu
    private char mainMenu() {
        System.out.println("Welcome to the Library! Please make a selection from the menu:");
        System.out.println("1. Explore the catalogue.");
        System.out.println("2. View your patron record.");
        System.out.println("3. Show you favourite books.");
        System.out.println("4. Enter Admin Mode.");
        System.out.println("X. Exit the system.");
        System.out.print("Enter a choice: ");
        return readChar();
    }

    private void use() {
        char choice;
        while ((choice = mainMenu()) != 'X') {
            switch(choice) {
                case '1': catalogueUse(); break;
                case '2': patronRecord(); break;
                case '3': favoriteBook(); break;
                case '4': adminUse(); break;
                default: help("4", "X");
            }
        }
    }

    public Patron patron (int ID) {
        for (Patron patron : patrons)
            if (patron.contains(ID))
                return patron;
        return null;
    }

    private void patronRecord() {
        System.out.print("\nEnter a patron ID: ");
        int ID = readNumber();
        if (patron(ID) != null) {
            Patron patron = patron(ID);
            System.out.println(patron(ID));
            System.out.println("Books currently borrowed by " + patron.patronName() + ":");
            patron.printBorrow();
            System.out.println(patron.patronName() + "'s borrowing history:");
            patron.printHistory();
            System.out.println();
        }
        else 
            System.out.println("That patron does not exist.\n"); 
    }

    private void favoriteBook() {
        System.out.print("\nEnter a patron ID: ");
        int ID = readNumber();
        if (patron(ID) != null) {
            Patron patron = patron(ID);
            System.out.println(patron.patronName() + "'s favourite books are:");
            patron.favoriteBook();
        }
        System.out.println();
    }

    //end of main menu

	//catalogue menu
	private char catalogueMenu() {
        System.out.println("Welcome to the Catalogue! Please make a selection from the menu:");
        System.out.println("1. Display all books.");
        System.out.println("2. Display all available books.");
        System.out.println("3. Display all genres.");
        System.out.println("4. Display books in a genre.");
		System.out.println("5. Display all authors.");
		System.out.println("6. Display all books by an author.");
		System.out.println("7. Borrow a book.");
		System.out.println("8. Return a book.");
		System.out.println("9. Place a hold.");
		System.out.println("R. Return to previous menu.");
        System.out.print("Enter a choice: ");
		return readChar();		
	}

	public void catalogueUse() {
	    char choice;
        while ((choice = catalogueMenu()) != 'R') {
            switch(choice) {
				case '1': catalogue.displayBook(); break;
				case '2': catalogue.displayAvailableBook(); break;
				case '3': catalogue.displayGenres(); break;
                case '4': getGenre(); break;
				case '5': catalogue.displayAuthors(); break;
                case '6': getAuthor(); break;
                case '7': borrowBook(); break;
                case '8': returnBook(); break;
                case '9': placeHold(); break;
                default: help("9", "R");
			}
		}
    }

    private void getGenre() {
        System.out.print("\nEnter a genre: ");
        String genre = readName();
        System.out.println("The library has the following books in that genre:");
        catalogue.getGenre(genre);
        System.out.println();
    }

    private void getAuthor() {
        System.out.print("\nEnter the name of an author: ");
        String author = readName();
        System.out.println("The library has the following books by that author:");
        catalogue.getAuthor(author);
        System.out.println();
    }

    private void borrowBook() {
		System.out.print("\nEnter a valid patron ID: ");
		int ID = readNumber();
		if (patron(ID) != null) {
            Patron patron = patron(ID);
			System.out.print("Enter the title of the book you wish to borrow: ");
			String name = readName();
            Book book = catalogue.availableBooks(name);
			if (book != null) {
                if (book.emptyHolds()) {
                    patron.borrowBook(book);
                    catalogue.borrowBook(book);
                    System.out.println("Book loaned.\n");
                }
                else {
                    if (book.checkPatron(patron)) {
				        patron.borrowBook(book);
                        catalogue.borrowBook(book);
                        System.out.println("Book loaned.\n");
                    }
                    else
                        System.out.println("That book is not available or doesn't exist.\n");
                }
			}
            else
                System.out.println("That book is not available or doesn't exist.\n");
		}
    }
    
    private void returnBook() {
        System.out.print("\nEnter a valid patron ID: ");
        int ID = readNumber();
		if (patron(ID) != null) {
            Patron patron = patron(ID);
            System.out.println(patron.patronName() + " has the following books:");
            System.out.println("Books currently borrowed by " + patron.patronName() + ":");
            patron.printBorrow();
            System.out.print("Enter the title of the book you wish to return: ");
            String name = readName();
            Book book = patron.ownedBooks(name);
            if (book != null) {
                catalogue.returnBook(book);
                patron.returnBook(book);
                System.out.println(book.getTitle() + " has been returned.\n");
            }
        }
    }
    
    private void placeHold() {
        System.out.print("\nEnter a patron ID: ");
        int ID = readNumber();
        if (patron(ID) != null) {
            Patron patron = patron(ID);
            System.out.print("Enter book title: ");
            String name = readName();
            Book book = catalogue.availableBooks(name);
            if (book == null) {
                book = catalogue.loanedBooks(name);
                if (book != null) {
                    if (!book.checkHolds(ID)) {
                    book.addHold(patron);
                    System.out.println("Book held.\n");
                    }
                }
            }
            else {
                if (!book.checkHolds(ID)) {
                book.addHold(patron);
                System.out.println("Book held.\n");
                }
            }
        }
    }

    //end catalogue menu


    //administrator menu
    private char adminMenu() {
        System.out.println("Welcome to the administration menu:");
        System.out.println("1. Add a patron.");
        System.out.println("2. Remove a patron.");
        System.out.println("3. Add a book to the catalogue.");
        System.out.println("4. Remove a book from the catalogue.");
        System.out.println("R. Return to the previous menu.");
        System.out.print("Enter a choice: ");
        return readChar();
    }
    private void adminUse() {
        char choice;
        while ((choice = adminMenu()) != 'R') {
            switch(choice) {
                case '1': addPatron(); break;
                case '2': removePatron(); break;
                case '3': addBook(); break;
                case '4': removeBook(); break;
                default: help("4", "R");
            }
        }
    }

    private void addPatron() {
        System.out.println("\nAdding a new patron.");
        System.out.print("Enter a new ID: ");
        int ID = readNumber();
        System.out.print("Enter the patron's name: ");
        String name = readName();
        System.out.println("Patron added.\n");
        patrons.add(new Patron(ID, name));
    }

    private void removePatron() {
        System.out.println("\nRemoving a patron.");
        System.out.print("Enter a patron ID: ");
        int ID = readNumber();
        if (patron(ID) != null) {
            patrons.remove(patron(ID));
            System.out.println("Patron removed.\n");
        }
        else
            System.out.println("That patron does not exist.\n");
    }

    private void addBook() {
        System.out.println("\nAdding a new book.");
        System.out.print("Enter the title of the book: ");
        String title = readName();
        System.out.print("Enter the author's name: ");
        Author author = new Author(readName());
        System.out.print("Enter the genre: ");
        Genre genre = new Genre(readName());
        catalogue.addBook(title, author, genre);
        System.out.println("Added " + title + " to catalogue.\n");
    }

    private void removeBook() {
        System.out.println("\nRemoving a book.");
        System.out.print("Enter the title of the book: ");
        String name = readName();
        System.out.print("Enter the author's name: ");
        String authorName = readName();
        String title = catalogue.findTitle(name);
        String author = catalogue.findAuthor(authorName);
        if (title != null && author != null) {
            catalogue.removeBook(name, author);
            System.out.println(author + ", " + title + " removed from catalogue.\n");
        }
        else
            System.out.println("No such book found.\n");
    }
    //end administrator menu


    private void help(String number, String letter) {
        if (letter == "R")
            System.out.println("Please enter a number between 1 and " + number + " or press R to return to the previous menu.");
        else    
            System.out.println("Please enter a number between 1 and " + number + ", or press X to exit.");
    }

    private char readChar() {
        return In.nextChar();
    }

    private int readNumber() {
        return In.nextInt();
    }

    private String readName() {
        return In.nextLine();
    }
}