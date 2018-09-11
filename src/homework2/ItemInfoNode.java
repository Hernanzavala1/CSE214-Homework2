package homework2;
/** 
 * This class creates nodes where information of an item is stored 
 * and this nodes are placed in a doubly linked list to form a data structure that constains several 
 * items of a store and manage them.
 * 
 * @author Hernan Zavala Yanes
 *
 */
public class ItemInfoNode {
	private ItemInfo itemInfo;
	private ItemInfoNode next;
	private ItemInfoNode prev;

	public ItemInfoNode() {
		itemInfo = null;
		next = null;
		prev = null;
	}

	public ItemInfo getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(ItemInfo itemInfo) {
		this.itemInfo = itemInfo;
	}

	public ItemInfoNode getNext() {
		return next;
	}

	public void setNext(ItemInfoNode next) {
		this.next = next;
	}

	public ItemInfoNode getPrev() {
		return prev;
	}

	public void setPrev(ItemInfoNode prev) {
		this.prev = prev;
	}

}
