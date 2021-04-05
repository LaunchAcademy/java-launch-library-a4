import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
  public static final String CONTRIBUTE_TEXT = "Add a Book";
  public static final String CHECKOUT_TEXT = "Check out a Book";
  public static final String RETURN_TEXT = "Return a Book";
  public static final String QUIT_TEXT = "Quit";

  public enum MenuOption {

    a(CONTRIBUTE_TEXT),
    c(CHECKOUT_TEXT),
    r(RETURN_TEXT),
    q(QUIT_TEXT);

    private String optionText;

    MenuOption(String optionText) {
      this.optionText = optionText;
    }

    public String toString() {
      return this.name() + ") " + this.optionText + "\n";
    }
  }

  public String toString() {
    String output = "MAIN MENU\n\n";
    for(MenuOption option : MenuOption.values()) {
      output += option.toString();
    }
    return output;
  }

  public static List<Book> getInitialBooksFromLibrary() {
    List<Book> books = new ArrayList<Book>();
    books.add(new Book("The Little Prince"));
    books.add(new Book("Tale of Two Cities"));
    return books;
  }

  public static void promptUntilQuit() {
    // welcome the user to the library
    System.out.println("Welcome to the Library!");
    // list out their options
    MainMenu menu = new MainMenu();

    List<Book> booksInLibrary = MainMenu.getInitialBooksFromLibrary();

    // prompt the user to make a selection
    MenuOption selectedOption = null;
    do {
      System.out.println(menu.toString());
      Scanner scanner = new Scanner(System.in);
      try {
        selectedOption = MenuOption.valueOf(scanner.next());
      }
      catch(IllegalArgumentException exception) {
        System.out.println("That isn't a valid menu option. Please try again");
      }

      if(selectedOption == MenuOption.c) {
        CheckoutMenu checkoutMenu = new CheckoutMenu(booksInLibrary);
        checkoutMenu.promptUntilDone();
      }

    } while (selectedOption != MenuOption.q);

    System.out.println("Thanks for stopping by!");

    // check for bad input - if they don't make a valid selection, prompt them again
    // loop until the quit
  }
}
