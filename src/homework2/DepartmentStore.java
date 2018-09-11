package homework2;

import java.util.Scanner;
/**
 * 
 * @author Hernan Zavala Yanes
 * Professor: Ahmad Esmaili
 * Stony Brook ID:112408132
 * This class serves the sole purpose of communicating with the user 
 * in order to work with the methods available in other classes
 * From this class you will be able to manage an inventory by adding 
 * items, moving, and deleting an item from the inventory.
 *
 */

public class DepartmentStore {

	/**
	 *The main method is used to communicate with the user and get all of the inputs.
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ItemList storeItems = new ItemList();

		System.out.println("Welcome!");
		do {
			System.out.println();
			System.out.println("C - Clean Store");
			System.out.println("I - Insert an item into the list");
			System.out.println("L - List by location");
			System.out.println("M - Move an item in the store");
			System.out.println("O - Checkout");
			System.out.println("P - Print all items in store");
			System.out.println("U - Update inventory system");
			System.out.println("Q - Exit the program.");

			System.out.print("Please select an option: \n");
			Scanner inp = new Scanner(System.in);
			String userInp = inp.nextLine().toUpperCase();
			char menuOption = userInp.charAt(0);

			switch (menuOption) {
			case 'C':
				System.out.println("The following items have been moved back to original position:");
				storeItems.cleanStore();
				break;
			case 'I':
				System.out.print("Enter the name: ");
				String name = inp.nextLine();
				System.out.print("Enter the RFIDTAG:");
				String rfidTag = inp.nextLine().toUpperCase();
				System.out.print("Enter the original location:");
				String originalLocation = inp.nextLine().toUpperCase();
				System.out.print("Enter the price:");
				double price = inp.nextDouble();
				try {
					if (correctNumber(rfidTag) && correctOriginalLocation(originalLocation)) {
						storeItems.insertInfo(name, rfidTag, price, originalLocation);
					} else {
						throw new Exception();
					}
				} catch (Exception e) {
					System.out.println("The input is not correct try again!");
				}
				break;
			case 'L':
				System.out.print("enter the location:");
				String location = inp.nextLine().toUpperCase();
				try {
					if (correctCurrentLocation(location)) {
						storeItems.printByLocation(location);
					} else {
						throw new WrongCurrentException();
					}
				} catch (WrongCurrentException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 'M':
				System.out.print("Enter the RFIDTAG:");
				String number = inp.nextLine();
				System.out.print("Enter the original location: ");
				String oldLocation = inp.nextLine().toUpperCase();
				System.out.print("Enter the new location:");
				// && correctCurrentLocation(newLocation)
				String newLocation = inp.nextLine().toUpperCase().trim();
				try {
					if (correctNumber(number) && correctOriginalLocation(oldLocation)
							&& correctCurrentLocation(newLocation)) {
						storeItems.moveItem(number, oldLocation, newLocation);
					} else {
						throw new Exception();
					}
				} catch (Exception e) {
					System.out.println("Wrong information entered!");
				}
				break;
			case 'O':
				System.out.print("enter the cart number:");
				String cartNumber = inp.nextLine().toUpperCase().trim();
				try {
					if (checkCart(cartNumber)) {
						System.out.println("The total was: " + storeItems.checkOut(cartNumber));
					} else {
						throw new WrongCartException();
					}
				} catch (WrongCartException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'P':
				storeItems.printAll();
				break;
			case 'U':
				System.out.println("The following have been removed from the system: ");
				try {
				storeItems.removeAllPurchased();
				}catch(NullPointerException e) {
					System.out.println("opps something went wrong!");
				}
				break;
			case 'Q':
				System.out.println("Good bye!");
				System.exit(-1);
			}

		} while (true);
	}
	
	
	/**
	 * This method verifies if a current location entered is valid or not.
	 * @param newLocation 
	 * String that holds the current location
	 * @return
	 * returns true if it meets all of the criteria that is checked through out the method, false otherwise.
	 */
	private static boolean correctCurrentLocation(String newLocation) {
		// TODO Auto-generated method stub
		newLocation.trim();
		boolean answer = false;
		char Char = newLocation.charAt(0);
		switch (Char) {
		case 'S':
			if (correctOriginalLocation(newLocation)) {
				answer = true;
			} else {
				answer = false;
			}
			break;
		case 'C':
			if (checkCart(newLocation)) {
				answer = true;
			} else {
				answer = false;
			}
			break;
		case 'O':
			if (newLocation.equalsIgnoreCase("out")) {
				answer = true;
			} else {
				answer = false;
			}
			break;

		}
		return answer;
	}

	/**
	 * This method checks if a cart location is valid or not.
	 * @param cart
	 * String that holds the cart location
	 * @return
	 * returns true if the cart location meets the criteria, false otherwise.
	 */
	public static boolean checkCart(String cart) {

		String secondPart = cart.substring(1);

		if (cart.length() != 4 || cart.charAt(0) != 'C') {
			return false;
		}
		for (int i = 0; i < secondPart.length(); i++) {
			if (!Character.isDigit(secondPart.charAt(i))) {
				return false;
			}

		}
		return true;
	}

	/**
	 * This method checks if an original location entered is valid. 
	 * @param originalLocation
	 * String that holds the original location to be checked.
	 * @return
	 * returns true if the location entered meets the criteria, false otherwise.
	 */
	private static boolean correctOriginalLocation(String originalLocation) {
		// TODO Auto-generated method stub
		if (originalLocation.length() != 6) {
			return false;
		}
		if (originalLocation.charAt(0) != 'S') {
			return false;
		}
		String secondPart = originalLocation.substring(1);
		for (int i = 0; i < secondPart.length(); i++) {
			if (Character.isDigit(secondPart.charAt(i)) == false) {
				return false;
			}

		}
		return true;
	}

	/**
	 * This method checks if a rfigtag number is valid.
	 * @param rfidTag
	 * String that holds the number to be checked.
	 * @return
	 * returns true if the number meets all of the requirements, false otherwise.
	 */
	private static boolean correctNumber(String rfidTag) {
		// TODO Auto-generated method stub
		if (rfidTag.length() != 9) {
			return false;
		}
		for (int i = 0; i < rfidTag.length(); i++) {
			char char1 = rfidTag.charAt(i);

			if (!Character.isDigit(char1) && char1 > 'F') {
				return false;
			}
		}
		return true;

	}

}
