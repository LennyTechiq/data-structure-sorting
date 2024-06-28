class Item {

	private int key, data;
	private Item next, prev;

	public Item() {
		key = 0;
		data = 0;
		next = null;
		prev = null;
	}

	public Item(int newKey, int newData, Item newNext, Item newPrev) {
		key = newKey;
		data = newData;
		next = newNext;
		prev = newPrev;
	}

	public int getKey() {
		return key;
	}

	public int getData() {
		return data;
	}

	public Item getNext() {
		return next;
	}

	public Item getPrev() {
		return prev;
	}

	public void setKey(int newKey) {
		key = newKey;
	}

	public void setData(int newData) {
		data = newData;
	}

	public void setNext(Item newNext) {
		next = newNext;
	}

	public void setPrev(Item newPrev) {
		prev = newPrev;
	}

	public void displayItem() {
		System.out.println("key: " + key + ";  data: " + data);
	}
}
