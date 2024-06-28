import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SortingMain {

	static int rComparisons = 0;
	static int rExchanges = 0;

	public static void main(String[] Args) throws IOException {

		Scanner scan = new Scanner(System.in);

		// user gives the text file name they want to use
		System.out.println("Please input the exact text file you would like to use:");
		String userinput = scan.next();

		File inputFile = new File(userinput);

		if (!inputFile.exists() || !inputFile.canRead()) {
			System.out.println("File not found or cannot be read. Please check the file name and try again.");
			scan.close();
			return;
		}

		try {
			Scanner fileScanner = new Scanner(inputFile);
			int counter = 0;
			int[] temparray = new int[2000];
			while (fileScanner.hasNextInt()) {
				temparray[counter] = fileScanner.nextInt();
				counter++;
			}
			fileScanner.close();

			// transfer the selected text document to a proper sized array for sorting
			int[] testarray = new int[counter];
			for (int i = 0; i < testarray.length; i++) {
				testarray[i] = temparray[i];
			}

			// Asks user to select which sorting algorithm they want, then run that algorithm
			System.out.println("Please input a number associated with the sorting method you would like to use: \n Insertion Sort - 1 \n Selection Sort - 2 \n Bubble Sort - 3 \n Merge Sort - 4 \n Radix Sort - 5 \n Heap Sort - 6");
			int sortnum = scan.nextInt();
			switch (sortnum) {
				case 1:
					insertionSort(testarray);
					break;
				case 2:
					selectionSort(testarray);
					break;
				case 3:
					bubbleSort(testarray);
					break;
				case 4:
					mergeSort(testarray);
					System.out.println("Merge Sort: Number of Exchanges: " + rExchanges);
					System.out.println("Merge Sort: Number of Comparisons: " + rComparisons);
					break;
				case 5:
					radixSort(testarray);
					break;
				case 6:
					rComparisons = 0; // Reset for heap sort
					rExchanges = 0;   // Reset for heap sort
					heapSort(testarray);
					System.out.println("Heap Sort: Number of Exchanges: " + rExchanges);
					System.out.println("Heap Sort: Number of Comparisons: " + rComparisons);
					break;
				default:
					System.out.println("Invalid selection. Please run the program again and select a valid sorting method.");
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred while reading the file. Please try again.");
		} finally {
			scan.close();
		}
	}

	// Sorting methods (insertionSort, selectionSort, bubbleSort, mergeSort, radixSort) remain unchanged
	// ...

	public static void insertionSort(int[] InputArray) {
		int ArrayLength = InputArray.length;
		int comparisons = 0;
		int exchanges = 0;

		for (int i = 1; i < ArrayLength; i++) {
			int temp = InputArray[i];
			int j = i - 1;

			while (j >= 0 && InputArray[j] > temp) {
				exchanges++;
				comparisons++;
				InputArray[j + 1] = InputArray[j];
				j--;
			}
			InputArray[j + 1] = temp;
		}
		System.out.println("Insertion Sort: Number of Exchanges: " + exchanges);
		System.out.println("Insertion Sort: Number of Comparisons: " + comparisons);
	}

	public static void selectionSort(int[] inputArray) {
		int temp, min, exchanges = 0, comparisons = 0;
		int arrayLength = inputArray.length;
		for (int pass = 0; pass != arrayLength - 1; pass++) {
			min = pass;
			for (int i = pass + 1; i != arrayLength; i++) {
				comparisons++;
				if (inputArray[i] < inputArray[min])
					min = i;
			}
			temp = inputArray[min];
			inputArray[min] = inputArray[pass];
			inputArray[pass] = temp;
			exchanges++;
		}
		System.out.println("Selection Sort: Number of Exchanges: " + exchanges);
		System.out.println("Selection Sort: Number of Comparisons: " + comparisons);
	}

	public static void bubbleSort(int[] inputArray) {
		int comparisons = 0;
		int exchanges = 0;

		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 0; i < inputArray.length - 1; i++) {
				if (inputArray[i] > inputArray[i + 1]) {
					swapped = true;
					int temp = inputArray[i];
					inputArray[i] = inputArray[i + 1];
					inputArray[i + 1] = temp;
					exchanges++;
				}
				comparisons++;
			}
		}
		System.out.println("Bubble Sort: Number of Exchanges: " + exchanges);
		System.out.println("Bubble Sort: Number of Comparisons: " + comparisons);
	}

	public static void mergeSort(int[] InputArray) {
		int ArrayLength = InputArray.length;
		int MidIndex = ArrayLength / 2;
		int[] LeftHalf = new int[MidIndex];
		int[] RightHalf = new int[ArrayLength - MidIndex];
		if (ArrayLength < 2) {
			return;
		}
		for (int i = 0; i < MidIndex; i++) {
			LeftHalf[i] = InputArray[i];
			rExchanges++;
		}

		for (int i = MidIndex; i < ArrayLength; i++) {
			RightHalf[i - MidIndex] = InputArray[i];
			rExchanges++;
		}

		mergeSort(LeftHalf);
		mergeSort(RightHalf);
		Merge(InputArray, LeftHalf, RightHalf);
	}

	public static void Merge(int[] InputArray, int[] LeftHalf, int[] RightHalf) {
		int i = 0, j = 0, k = 0;
		int LeftSize = LeftHalf.length;
		int RightSize = RightHalf.length;

		while (i < LeftSize && j < RightSize) {
			if (LeftHalf[i] <= RightHalf[j]) {
				InputArray[k] = LeftHalf[i];
				i++;
			} else {
				InputArray[k] = RightHalf[j];
				j++;
			}
			k++;
			rComparisons++;
			rExchanges++;
		}
		while (i < LeftSize) {
			InputArray[k] = LeftHalf[i];
			i++;
			k++;
			rExchanges++;
		}
		while (j < RightSize) {
			InputArray[k] = RightHalf[j];
			j++;
			k++;
			rExchanges++;
		}
	}

	public static void radixSort(int[] InputArray) {
		int comparisons = 0;
		int exchanges = 0;
		QueueADT masterList = new QueueADT(InputArray.length);
		QueueADT[] subList = new QueueADT[10];
		// puts the data from the selected file into a master queue
		for (int i = 0; i < InputArray.length; i++) {
			masterList.enqueue(InputArray[i]);
		}

		int digit = 1;
		while (digit <= 1000) {
			// initialize the 10 sublist queues and put them into the queue array
			for (int k = 0; k < 10; k++) {
				subList[k] = new QueueADT(InputArray.length);
			}
			// grabs elements from master queue and looks at what the element is at that digit level with getpile method, then puts it into correct sub-queue
			while (!masterList.empty()) {
				int temp = masterList.dequeue();
				int pile = getPile(temp, digit);
				subList[pile].enqueue(temp);
				exchanges++;
			}
			// reinitialize master queue
			masterList = new QueueADT(InputArray.length);
			// put elements from sub-queues back into master queue
			for (int j = 0; j < 10; j++) {
				while (!subList[j].empty()) {
					masterList.enqueue(subList[j].dequeue());
					exchanges++;
				}
			}
			digit = digit * 10;
		}
		// puts the queue back into the original input array
		for (int h = 0; h < InputArray.length; h++) {
			InputArray[h] = masterList.dequeue();
		}

		System.out.println("Radix Sort: Number of Exchanges: " + exchanges);
		System.out.println("Radix Sort: Number of Comparisons: " + comparisons);
	}

	static int getPile(int number, int digit) {
		return (number % (10 * digit) / digit);
	}

	public static void heapSort(int[] inputArray) {
		int n = inputArray.length;

		// Build the heap using bottom-up insertion
		for (int i = 1; i < n; i++) {
			int current = i;
			while (current > 0) {
				int parent = (current - 1) / 2;
				if (inputArray[current] > inputArray[parent]) {
					int temp = inputArray[current];
					inputArray[current] = inputArray[parent];
					inputArray[parent] = temp;
					rExchanges++;
					current = parent;
				} else {
					break;
				}
				rComparisons++;
			}
		}

		// Extract elements from the heap
		for (int i = n - 1; i > 0; i--) {
			int temp = inputArray[0];
			inputArray[0] = inputArray[i];
			inputArray[i] = temp;
			rExchanges++;

			int current = 0;
			while (true) {
				int leftChild = 2 * current + 1;
				int rightChild = 2 * current + 2;
				int largest = current;

				if (leftChild < i && inputArray[leftChild] > inputArray[largest]) {
					largest = leftChild;
				}
				if (rightChild < i && inputArray[rightChild] > inputArray[largest]) {
					largest = rightChild;
				}
				if (largest == current) {
					break;
				}

				temp = inputArray[current];
				inputArray[current] = inputArray[largest];
				inputArray[largest] = temp;
				current = largest;
				rExchanges++;
				rComparisons++;
			}
		}
	}
}
