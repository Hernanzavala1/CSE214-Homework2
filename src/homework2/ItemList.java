package homework2;

/**
 * 
 * @author Hernan Zavala Yanes
 *  Professor: Ahmad Esmaili
 *   This class serves as a
 *         list that contains ItemInfoNodes which in term could represent items
 *         in an inventory of a store.
 */
public class ItemList {
	public ItemInfoNode head;
	public ItemInfoNode tail;

	public ItemList() {

	}

	/**
	 * This method inserts a Item into the list with correct parameters and at the
	 * correct position.
	 * 
	 * @param name
	 *            String that holds the name of the item
	 * @param rfidTag
	 *            String that holds the id number of that item
	 * @param price
	 *            The price of the item
	 * @param initPosition
	 *            The initial position where the item is placed.
	 * 
	 *            The time complexity of this method is O(n) since we have to search
	 *            through the whole list and compare the rfigtag numbers with each
	 *            node in order to find the right place to add the item.
	 */
	public void insertInfo(String name, String rfidTag, double price, String initPosition) {
		ItemInfo newInfo = new ItemInfo(name, price, rfidTag, initPosition);
		ItemInfoNode newNode = new ItemInfoNode();
		newNode.setItemInfo(newInfo);

		if (head == null) {
			head = newNode;
			tail = newNode;
			return;
		}
		ItemInfoNode temp = head;
		/*
		 * a negative integer, zero, or a positive integer as the specified String is
		 * greater than, equal to, or less than this String, ignoring case
		 * considerations
		 * 
		 */

		if (temp.getItemInfo().getRfidTagNumber().compareToIgnoreCase(rfidTag) > 0) {
			newNode.setNext(temp);
			temp.setPrev(newNode);
			head = newNode;

			return;
		}
		if (tail.getItemInfo().getRfidTagNumber().compareToIgnoreCase(rfidTag) < 0) {
			newNode.setNext(tail.getNext());
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
			return;

		} else {

			while (temp != tail) {
				if (temp.getItemInfo().getRfidTagNumber().compareToIgnoreCase(rfidTag) <= 0) {
					break;
				}
				temp = temp.getNext();
			}

			newNode.setNext(temp.getNext());
			temp.getNext().setPrev(newNode);
			temp.setNext(newNode);
			newNode.setPrev(temp);
		}

	}

	/**
	 * This method moves an item from a original position to a different position as
	 * long as the new position is valid and its found at the source position.
	 * 
	 * @param rfidTag
	 *            String that holds the id of the item in order to find it in the
	 *            list.
	 * @param source
	 *            String that holds the original location of the item.
	 * @param dest'
	 *            String that holds the new location for the item
	 * @return returns true if the item was found at the original position, false
	 *         otherwise. The time complexity of this is O(n) since we must traverse
	 *         the list looking for the specified item. The traversal of the list is
	 *         related to the number of items.
	 */
	public boolean moveItem(String rfidTag, String source, String dest) {
		ItemInfoNode temp = head;
		boolean answer = false;
		while (temp.getNext() != null) {
			if (temp.getItemInfo().getRfidTagNumber().equals(rfidTag)) {
				break;
			}
			temp = temp.getNext();
		}
		if (temp.getItemInfo().getOriginalLocation().equalsIgnoreCase(source)) {
			answer = true;
			temp.getItemInfo().setCurrentLocation(dest);
			System.out.println(temp.getItemInfo().getRfidTagNumber());
			System.out.println(temp.getItemInfo().getCurrentLocation());

		}
		return answer;

	}

	/**
	 * This method traverses the list looking for nodes that have a current location
	 * as "out" and it removes it from the list The time complexity is O(n) since we
	 * must check every single node in the list in order to make sure everything
	 * that is sold is out of the inventory.
	 */
	public void removeAllPurchased() {
		ItemList removedList = new ItemList();
		ItemInfoNode temp = head;
		// Reminder: chek if only one node otherwise NULL POINTER!!!
		while (temp != null) {
			if (temp.getItemInfo().getCurrentLocation().equalsIgnoreCase("OUT")) {
				if (temp == head && temp == tail) {
					removedList.insertInfo(temp.getItemInfo().getName(), temp.getItemInfo().getRfidTagNumber(),
							temp.getItemInfo().getPrice(), temp.getItemInfo().getCurrentLocation());
					head = null;
					tail = null;
					temp = head;
					printRemovedList(removedList);
					return;
				} else if (temp == head) {
					removedList.insertInfo(temp.getItemInfo().getName(), temp.getItemInfo().getRfidTagNumber(),
							temp.getItemInfo().getPrice(), temp.getItemInfo().getCurrentLocation());
					head = head.getNext();
					head.setPrev(null);

				} else if (temp == tail) {
					removedList.insertInfo(temp.getItemInfo().getName(), temp.getItemInfo().getRfidTagNumber(),
							temp.getItemInfo().getPrice(), temp.getItemInfo().getCurrentLocation());
					tail.getPrev().setNext(null);
					tail = tail.getPrev();

				} else {
					temp.getPrev().setNext(temp.getNext());
					temp.getNext().setPrev(temp.getPrev());

				}
			}
			temp = temp.getNext();
		}
		printRemovedList(removedList);
	}

	/**
	 * This method prints a list of all of the nodes that are found at a certain
	 * position.
	 * 
	 * @param location
	 *            String that holds the position that we must search for in the
	 *            list. The time complexity is O(n) since we have to check which
	 *            nodes in the list belong to that specific location if the current
	 *            location matches the entered location then it is added to the list
	 *            and printed.
	 */
	public void printByLocation(String location) {
		ItemInfoNode temp = head;
		ItemList list = new ItemList();

		while (temp != null) {
			if (temp.getItemInfo().getCurrentLocation().equals(location)) {
				list.insertInfo(temp.getItemInfo().getName(), temp.getItemInfo().getRfidTagNumber(),
						temp.getItemInfo().getPrice(), temp.getItemInfo().getOriginalLocation());
				temp = temp.getNext();
			} else {
				temp = temp.getNext();
			}
		}
		System.out.println("Items at " + location);
		printRemovedList(list);
	}

	/**
	 * This method checks out the items found in the entered cart location and
	 * returns a total for the purchase.
	 * 
	 * @param cartNumber
	 *            String that holds the locationn of the cart that we must search
	 *            for.
	 * @return returns the total for the purchase of the items in the specified
	 *         cart. time complexity of O(n) since we must find all of the nodes in
	 *         the list that belong to the entered cart.
	 */
	public double checkOut(String cartNumber) {
		double total = 0;
		ItemList list = new ItemList();
		ItemInfoNode temp = head;
		while (temp != null) {
			if (temp.getItemInfo().getCurrentLocation().equals(cartNumber)) {
				total += temp.getItemInfo().getPrice();
				temp.getItemInfo().setCurrentLocation("out");
				list.insertInfo(temp.getItemInfo().getName(), temp.getItemInfo().getRfidTagNumber(),
						temp.getItemInfo().getPrice(), temp.getItemInfo().getOriginalLocation());
			} else {
				temp = temp.getNext();
			}
		}
		System.out.println("The items sold were: ");
		printRemovedList(list);
		return total;

	}

	/**
	 * This method moves all of the misplaced items back to their original position.
	 * it searches through the whole list and checks every node if they are at their
	 * original positions if not then they are move back Time complexity of O(n)
	 */
	public void cleanStore() {
		ItemInfoNode temp = head;
		ItemList list = new ItemList();
		while (temp != null) {
			if (temp.getItemInfo().getCurrentLocation().equalsIgnoreCase("out")
					|| temp.getItemInfo().getCurrentLocation().charAt(0) == 'C') {
				temp = temp.getNext();
			} else if (temp.getItemInfo().getCurrentLocation() != temp.getItemInfo().getOriginalLocation()) {
				list.insertInfo(temp.getItemInfo().getName(), temp.getItemInfo().getRfidTagNumber(),
						temp.getItemInfo().getPrice(), temp.getItemInfo().getOriginalLocation());
				temp.getItemInfo().setCurrentLocation(temp.getItemInfo().getOriginalLocation());
				temp = temp.getNext();
			} else {
				temp = temp.getNext();
			}
		}
		printRemovedList(list);
	}

	/**
	 * This method simply prints out a nicely formatted table of a list that is
	 * passed as a parameter.
	 * 
	 * @param list
	 *            it takes in a list
	 */
	public void printRemovedList(ItemList list) {
		ItemInfoNode temp = list.head;
		System.out.printf("%-20s%-16s%-22s%-22s%-18s\n", "Item Name", "RFID", "Original location", "current Location",
				"Price");
		System.out.println("-------------------------------------------------------------------------------------");
		while (temp != null) {
			String name = temp.getItemInfo().getName();
			String rfidTag = temp.getItemInfo().getRfidTagNumber();
			String originalLocation = temp.getItemInfo().getOriginalLocation();
			String current = temp.getItemInfo().getCurrentLocation();
			double price = temp.getItemInfo().getPrice();
			System.out.printf(" %-18s%-18s%-23s%-21s%-18s\n", name, rfidTag, originalLocation, current, price);
			temp = temp.getNext();
		}
		System.out.println();
	}

	/**
	 * this method prints out the list of items currently in the store time
	 * complexity of O(n)
	 * 
	 */
	public void printAll() {
		ItemInfoNode temp = head;
		System.out.printf("%-20s%-16s%-22s%-22s%-18s\n", "Item Name", "RFID", "Original location", "current Location",
				"Price");
		System.out.println("-------------------------------------------------------------------------------------");
		while (temp != null) {
			String name = temp.getItemInfo().getName();
			String rfidTag = temp.getItemInfo().getRfidTagNumber();
			String originalLocation = temp.getItemInfo().getOriginalLocation();
			String current = temp.getItemInfo().getCurrentLocation();
			double price = temp.getItemInfo().getPrice();
			System.out.printf(" %-18s%-18s%-23s%-21s%-18s\n", name, rfidTag, originalLocation, current, price);
			temp = temp.getNext();
		}
	}

}
