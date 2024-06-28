class PQADT implements PQ {

	private int size;
	private Item header, trailer;
	Comparator relation = new Comparator();

	public PQADT () {

	}
	
	public boolean empty() {
		return size == 0;

	}

	public int size() {
		return size;
	}

	public Item searchItem(int key) {
		Item temp = header.getNext();
		while (temp != trailer) {
			if (relation.lessThan(temp.getKey(), key))
				temp = temp.getNext();
			else
				return temp.getPrev();
		}
		return temp.getPrev();
	}

	public int minItem() {
		return header.getNext().getData();
	}

	public int minKey() {
		return header.getNext().getKey();
	}

	public void insertItem(int newKey, int newData) {
		Item temp = searchItem(newKey);
		Item newItem = new Item(newKey, newData, temp.getNext(), temp);
		temp.getNext().setPrev(newItem);
		temp.setNext(newItem);
		size++;
	}

	public int removeItem() {
		int data;
		data = header.getNext().getData();
		size--;
		header.setNext(header.getNext().getNext());
		return data;
	}

	public void traverse() {
		System.out.println("Current priority queue: ");
		Item current = header.getNext();
		while (current != trailer) {
			current.displayItem();
			current = current.getNext();
		}
		System.out.println();
	}
}
