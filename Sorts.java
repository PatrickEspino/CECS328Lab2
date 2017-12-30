/**
 * Name: Patrick Espino
 * ID: 014254979
 * Lab: 2
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Sorts {
	public static void main(String[] args)
	{
		//Initializes array lists
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> qs = new ArrayList<Integer>();
		ArrayList<Integer> is = new ArrayList<Integer>();
		
		//Initializes timers for quicksort and insertionsort
		long total = 0;
		long start = 0;
		long end = 0;
		long avgQS = 0;
		long insertionStart = 0;
		long insertionEnd = 0;
		long insertionTotal = 0;
		long avgIS = 0;
		
		System.out.println("Enter a positive integer");
		Scanner read = new Scanner(System.in);
		Random random = new Random();
		int n = read.nextInt();
		
		for(int i=0; i<n; i++)
		{
			arr.add(random.nextInt(7000 + 1 + 7000) - 7000);
		}

		System.out.print("Unsorted: ");
		for(int i = 0; i < arr.size(); i++)
		{
			System.out.print(arr.get(i) + " ");
		}
		System.out.println("");
		
		//Quick sort and insertion sort
		qs = quickSort(arr);
		is = insertionSort(arr);
		
		//Print sorted array
		System.out.print("Sorted(QuickSort): ");
		for(int i = 0; i < qs.size(); i++)
		{
			System.out.print(qs.get(i) + " ");
		}
		
		System.out.println("");
		System.out.print("Sorted(InsertionSort): ");
		for(int i = 0; i < is.size(); i++)
		{
			System.out.print(is.get(i) + " ");
		}
		System.out.println("");
		
		//Run the quick sort 100 times for n = 10000
		int num = 10000;
		int cycles = 100;
		int counter = 0;
		while(counter < cycles)
		{
			ArrayList<Integer> qsArr = new ArrayList<Integer>();
			for(int i = 0; i < num; i++)
			{
				qsArr.add(random.nextInt(7000 + 1 + 7000) - 7000);
			}
			Median(qsArr,0,qsArr.size() - 1);

			start = System.nanoTime();
			qs = quickSort(qsArr);
			end = System.nanoTime();
			total = total + (end - start);
			counter++;
		}
		
		avgQS = total / cycles;
		System.out.println("Average Run Time for QuickSort: " + avgQS + " nanoseconds");

		int counter2 = 0;
		while(counter2 < cycles)
		{
			ArrayList<Integer> isArr = new ArrayList<Integer>();
			for(int i = 0; i < num; i++)
			{
				isArr.add(random.nextInt(7000 + 1 + 7000) - 7000);
			}
			Median(isArr,0,isArr.size() - 1);
			insertionStart = System.nanoTime();
			insertionSort(isArr);
			insertionEnd = System.nanoTime();
			insertionTotal = insertionTotal + (insertionEnd - insertionStart);
			counter2++;	
		}

		avgIS = insertionTotal / cycles;
		System.out.println("Average Run Time for InsertionSort: " + avgIS + " nanoseconds");
		
		double ips = ((n*n) / (double)(insertionTotal / 1000000000));
		
		System.out.println("How many instructions per second(using insertion sort): " + ips);
	}

	public static ArrayList<Integer> insertionSort(ArrayList<Integer> arr)
	{
		for(int i = 1; i < arr.size(); i++)
		{
			for(int j = i; j > 0; j--)
			{
				//swaps if next element is bigger
				if(arr.get(j) < arr.get(j-1))
				{
					Collections.swap(arr, j, j-1);
				}
			}
		}
		return arr;
	}
	
	
	public static ArrayList<Integer> quickSort(ArrayList<Integer> arr)
	{
		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		ArrayList<Integer> sorted = new ArrayList<Integer>();

		//Base Case
		if(arr.size() <= 3)
		{
			return insertionSort(arr);
		}
		//Puts numbers greater than the pivot to the right, and numbers less than to the right
		else
		{
			for(int i = 0; i < arr.size()-1; i++)
			{
				if(arr.get(i) >= arr.get(arr.size() - 1))
				{
					right.add(arr.get(i));
				}
				else if (arr.get(i) < arr.get(arr.size() - 1))
				{
					left.add(arr.get(i));
				}
			}
		}
		//Combines the left subarray, the pivot, and right subarray
		sorted.addAll(quickSort(left));
		sorted.add(arr.get(arr.size() - 1));
		sorted.addAll(quickSort(right));

		return sorted;
		
	}
	
	public static int Median(ArrayList<Integer> arr, int left, int right)
	{
		//initializes the first, the middle, and last element in the array
		int first = left;
		int middle = (right + left) / 2;
		int last = right;
		
		//puts those elements into a separate arraylist
		ArrayList<Integer> med = new ArrayList<Integer>();
		med.add(arr.get(left));
		med.add(arr.get(middle));
		med.add(arr.get(right));
		
		//sorts the 3 elements in the list
		Collections.sort(med);

		//if the middle element in the array list = the first element in the original array
		//swap the first and last element
		if(med.get(1) == arr.get(0))
		{
			Collections.swap(arr,first,last);
		}
		//if the middle element in the array list = middle element in the original array
		//swap the middle and last element
		if(med.get(1) == arr.get(arr.size() / 2))
		{
			Collections.swap(arr,middle,last);
		}
		
		return med.get(1);
		
	}
	
}
	