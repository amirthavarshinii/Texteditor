package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode(null);
		tail = new LLNode(null);
		// you are making both head and tail point to each other since there is nothing between 
		//here we are using head and tail as sentinel nodes meaning there are dummy nodes
		head.next = tail;
		tail.prev = head;
			}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{   // Most often we forget to add these checks , these are the checks for corner cases which we should always remember to include 
		// what happens if user tries to add a null to the linkedlist.
		//now you have to throw a null pointer exception because you simply cannot add a null to list , it makes no sense.
	     	if (element == null) {
			throw new NullPointerException();
		    }
     
		LLNode newnode = new LLNode(element);
		// the order of these operations is so much important , you shouldn't lose the connection 
		  if(head.next == tail) {
			  head.next = newnode;
			  tail.prev = newnode;
			  newnode.prev = head;
			  newnode.next = tail;
		  } else {
			  newnode.prev = tail.prev; 
			  newnode.next = tail;
			  tail.prev.next = newnode;
			  tail.prev = newnode;
		  }
		size = size +1;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{ // now if you access the index which is outside the range of size then you have to throw indexoutofboundsexception
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		// TODO: Implement this method.
		int pos =0;
	   LLNode temp = head.next;
	   while(pos != index) {
		   pos = pos +1;
		   temp = temp.next;
	   }
	   return(E) temp.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}

		LLNode newnode = new LLNode(element);
		int pos =0;
		   LLNode temp = head.next;
		   while(pos != index) {
			   pos = pos +1;
			   temp = temp.next;
		   }
		   newnode.next = temp;
		   newnode.prev = temp.prev;
		   temp.prev.next = newnode;
		   temp.prev = newnode;
		   size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		int pos =0;
	    LLNode temp = head.next;
	    while(pos != index) {
			   pos = pos +1;
			   temp = temp.next;
		   }
	     Object o = temp.data;
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        size--;
		return (E) o;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}

		int pos =0;
		   LLNode temp = head.next;
		   while(pos != index) {
			   pos = pos +1;
			   temp = temp.next;
		   } 
		   Object o = temp.data;
		   temp.data = element;
		
		return (E) o; // if you havent included the casting to E , there will be a type mismatch , because you cannot return a object in the place of 
		//where it excepts a type E. ( the type of elements stored in the list).
	}   

	public void display() {
		LLNode temp = head;
		while(temp.next != null && temp.next.data != null) {
			temp = temp.next;
			System.out.println(temp.data);
		}
	}
}
class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}

/* in this class I also tried creating a main and then I tried to access the list in mylinkedlisttester class when i called the set up method using the object
 * of linkedtester class it showed me an error to surround it with try and catch blocks , because whichever method throws an exception , the method callind it 
 * should be able to handle that exception. 
 * Similary a method cannot be simply declared as throws INDEXOUTOFBOUNDSEXCEPTION.
 * Inside the method you should handle when it will throw that exception  that is like
 * public E get(index) throws INDEXOUTOFBOUNDEXCDPTION{
 * if(index > size)
 * throw indexouofboundexception;
 * }
 */
