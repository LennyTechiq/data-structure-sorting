class Comparator {

	public boolean equal(int a, int b) {
		return (a == b);
	}

	public boolean lessThan(int a, int b) {
		return (a < b);
	}

	public boolean lessThanOrEqual(int a, int b) {
		return ((a == b) || (a < b));
	}

	public boolean greaterThan(int a, int b) {
		return (a > b);
	}

	public boolean greaterThanOrEqual(int a, int b) {
		return ((a > b) || (a == b));
	}
}
