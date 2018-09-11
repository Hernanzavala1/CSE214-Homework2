package homework2;

/**
 * 
 * @author Hernan Zavala Yanes This class takes in information necessary for an
 *         item. which is later placed in a list.
 */
public class ItemInfo {
	private String name;
	private double price;
	private String rfidTagNumber;
	private String originalLocation;
	private String currentLocation;

	public ItemInfo(String name, double price, String rfidTagNumber, String originalLocation) {
		super();
		this.name = name;
		if (price > 0) {
			this.price = price;
		}
		this.rfidTagNumber = rfidTagNumber;

		this.originalLocation = originalLocation;
		this.currentLocation = originalLocation;
	}

	/**
	 * 
	 * @return returns the name of an item
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            sets the name of an item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return returns the price of an item
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price
	 *            sets the price of an item
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 
	 * @return returns the rfidtag number of an item
	 */
	public String getRfidTagNumber() {
		return rfidTagNumber;
	}

	/**
	 * 
	 * @param rfidTagNumber
	 *            sets the rfidtag number of an item
	 */
	public void setRfidTagNumber(String rfidTagNumber) {
		this.rfidTagNumber = rfidTagNumber;
	}

	/**
	 * 
	 * @return returns the original location of an item
	 */
	public String getOriginalLocation() {
		return originalLocation;
	}

	/**
	 * 
	 * @param originalLocation
	 *            sets the original location of an item as well as the current
	 *            location
	 */
	public void setOriginalLocation(String originalLocation) {
		this.originalLocation = originalLocation;
		this.currentLocation = originalLocation;
	}

	/**
	 * 
	 * @return returns the current location of an item
	 */
	public String getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * sets the current location of an item
	 * 
	 * @param currentLocation
	 */
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

}
