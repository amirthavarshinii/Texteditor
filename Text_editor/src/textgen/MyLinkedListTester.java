/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 * This  is a tester class, whenever you store any class name with end name such as Test or tester it might function as follows. You should have included junit
 *that is junit tests library. You should include methods like assert equals and all.
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;// creating objects of the linked list class
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before// this should be declared before everything so that this method will be updated everytime before each test. 
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds"); // if the exception is not catched then this method will be executes , that is the test will show a failure with a particular 
			//message.
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));// message , the expected value , the actual value
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		try {
			emptyList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		try {
			emptyList.remove(1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
			assertEquals("Check first", (Integer)42, list1.get(2));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Check size", 3, list1.size);
	}

	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		list1.add(1, 55);
		assertEquals("Check addatindex", (Integer)55, list1.get(1));
		assertEquals("Check size", 4, list1.size);

		try {
			emptyList.add(-1, 0); // checking lower bounds
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		try {
			emptyList.add(1, 1);// checking upper bounds 
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		try {
			emptyList.add(0, null);/// also checking if you can add a null element 
			fail("Adding null element");
		}
		catch (NullPointerException e) {
		}
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		assertEquals("Check set", "B", shortList.set(1, "c"));
		assertEquals("Check set", "c", shortList.get(1));
		assertEquals("Check size", 2, shortList.size);

		//test empty list, get should throw an exception
		try {
			emptyList.set(-1, 0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		//test empty list, get should throw an exception
		try {
			emptyList.set(1, 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}

		try {
			shortList.set(1, null);
			fail("Setting null element");
		}
		catch (NullPointerException e) {
			
		}
	}
	
	
	// TODO: Optionally add more test methods.
	
}
