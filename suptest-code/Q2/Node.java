/**
 * Base class for node
 *
 * @param <T> data type
 */

public class Node<T extends Comparable<T>> {

	Colour colour;			// Node colour
	PersonName<T> key; // Node key, which is a PersonName object
	Object value; 	             // Node key, which is an object
	Node<T> parent; 		// Parent node
	Node<T> left, right; 	// Child nodes

	public Node(PersonName<T> key, Object value) {
		this.key  = key;
		this.value  = value;
		
		this.colour = Colour.RED; //property 3 (if a node is red, both children are black) may be violated if parent is red

		this.parent = null;

		// Initialise children leaf nodes
		this.left 			= new Node<T>();  //leaf node
		this.right 			= new Node<T>();  //leaf node
		this.left.parent 	= this; //reference to parent
		this.right.parent 	= this; //reference to parent
	}

	// Leaf node
	public Node() {
		this.key 	= null; //leaf nodes are null
		this.value 	= null; 
		this.colour = Colour.BLACK; //leaf nodes are always black
	}

	// The first names and last names will be sorted in alphabetical order
	// For a pair of PersonName p1, p2, we write p1 < p2, if
	//  (1)	p1.lastName alphabetically precedes p2.lastName, or
	//  (2)	p1.lastName = p2.lastName, and p1.firstName alphabetically precedes p2.firstName
	// For example, ("David", "Newton") < ("Chris", "Watson")  and  ("Chris", "Newton") < ("David", "Newton")
	public static class PersonName<T extends Comparable<T>>{
		public final T firstName;
		public final T lastName;
		public PersonName(T firstName, T lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
	}
}