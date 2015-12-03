import java.util.Scanner;

/*
 *    An implementation of an AVL Tree.
 *    
 *    Jalal Khan
 *    Due: 12/3/15
 */

public class AvlTree<T extends Comparable<T>> {
	private Node root = null; // Pointer to the root of the AVL Tree

	// The following variables are used during a
	// restructuring of the tree.

	private Node p1 = null; // Pointer to the pivot node's parent.
	private Node p2 = null; // Pointer to the pivot node.
	private Node p3 = null; // Pointer to pivot's child along the search path.
	private Node p4 = null; // Pointer to pivot's grandchild along the search
							// path.

	// Constants to name the rotations.
	private final static int LL = 0; // Left-Left Rotation.
	private final static int LR = 1; // Left-Right Rotation.
	private final static int RR = 2; // Right-Right Rotation.
	private final static int RL = 3; // Right-Left Rotation.

	public AvlTree() {
		this.root = null; // Start out with an empty tree.
	}

	public void insert(T x) {
		avl_insert(x); // Insert into an AVL tree - restructure as needed.
	}

	private void avl_insert(T x) {
		int rotType; // Type of rotation needed to balance the tree.

		if (search(x)) // Only attach if the new data is not in the tree.
			return;

		attach(x); // Add the new node to the tree.
		p2 = pivot(x); // Find the pivot node;

		if (p2 == null) // If there's no pivot Node
			resetbalance(root, x); // reset bf's from root along the search
									// path.
		else if (addToShort(p2, x)) // Elseif (node added to pivot's shorter
									// subtree)
			resetbalance(p2, x); // reset bf's from pivot along the search path.
		else { // Else (node added to pivot's longer subtree)
			resetbalance(p2, x); // restructure the tree.

			setpointers(x); // Set pointers p1, p2, p3 , p4 in preparation
							// to perform a rotation and re-balance the tree.
			rotType = find_rotType(x); // Determine the kind of rotation to
										// perform.

			switch (rotType) {
			case LL:
				LL_rotation();
				break;
			case LR:
				LR_rotation();
				break;
			case RL:
				RL_rotation();
				break;
			case RR:
				RR_rotation();
				break;
			}
		}
	}

	//
	// find_rotType() - This function returns the type of rotation
	// (LL, LR, RR, RL) needed to maintain the balance
	// of a tree when a new node has been attached
	// that unbalances the tree.
	// p2 points to the pivot node upon entry to the function.
	//

	private int find_rotType(T x) {
		if (p4 == null)
			p4 = new Node();

		//check each possible relationship of p2, p3, and p4, making sure to check for
		//null pointers in every step since we aren't guaranteed the pointers will exist 
		if (p2.lchild != null && p2.lchild.equals(p3) && p3.lchild != null && p3.lchild.equals(p4)) {
			return LL;
		} else if (p2.lchild != null && p2.lchild.equals(p3) && p3.rchild != null && p3.rchild.equals(p4)) {
			return LR;
		} else if (p2.rchild != null && p2.rchild.equals(p3) && p3.lchild != null && p3.lchild.equals(p4)) {
			return RL;
		} else {
			return RR;
		}
	}

	//
	// resetbalance() - Reset the balance factors when a new node with
	// data value x is attached to the avl tree. Only
	// those nodes along the search path starting from
	// the node addressed by the parameter p need to have
	// their balance factors set.
	//

	private void resetbalance(Node p, T x) {
		
		// if the new node was added to the right and the balance factor of this
		// node was already greater than -1, decrease it and move down the tree
		if (p.bf >= 0 && x.compareTo(p.data) > 0) {
			p.bf--;
			resetbalance(p.rchild, x);
		}
		
		//otherwise, if the balance factor was less than 1 and the new node was
		//added to the left, increase it and move down the tree
		else if (p.bf <= 0 && x.compareTo(p.data) < 0) {
			p.bf++;
			resetbalance(p.lchild, x);
		} 
		
		//check if we actually got to the node we were supposed to
		//there might be a node with 0 bf in between, so keep traversing
		//until we reach x
		else if(x.compareTo(p.data) > 0)
			resetbalance(p.rchild, x);
		
		else if(x.compareTo(p.data) < 0)
			resetbalance(p.lchild, x);
		
		//we're there! set node with value x to balance factor 0
		else
			p.bf = 0;
	}

	//
	// setpointers() - Upon entry, p2 points to the pivot node associated
	// with the attaching of a new node with data value x.
	// Instance variable p1, p2, p4 are set as follows:
	//
	// p1 = parent of the pivot, else null.
	// p3 = child of pivot along search path.
	// p4 = grandchild of the pivot along search path.
	//

	private void setpointers(T x) {
		
		//follow the search path to find where p3 and p4 are
		p3 = x.compareTo(p2.data) > 0 ? p2.rchild : p2.lchild;
		p4 = x.compareTo(p3.data) > 0 ? p3.rchild : p3.lchild;

		//to find p1, we have to search from the top of the tree
		//start at the root since we don't know where the pivot is in the tree
		Node temp = this.root;
		Node follow = null; // use this to store p1

		//traverse the tree
		while (!temp.equals(p2)) {
			follow = temp;

			if (p2.data.compareTo(temp.data) > 0)
				temp = temp.rchild;
			else
				temp = temp.lchild;

		}

		//if there is no p1, this will be null
		p1 = follow;
	}

	//
	// addToShort() - Determines, by looking at balance factors, if
	// the new node with data value x was added to
	// the subtree of the pivot node with the smaller
	// height. If so, the function returns true,
	// otherwise it returns false.
	//

	private boolean addToShort(Node p2, T x) {
		if (p2.data.compareTo(x) < 0 && p2.bf == 1) // New node added on right
			return true; // tree but left is longer.
		else if (p2.data.compareTo(x) > 0 && p2.bf == -1) // New node added on
															// left
			return true; // tree but right is longer.
		else // ... New node must have been added to
			return false; // the longer subtree of the pivot.
	}

	//
	// pivot() - Return a pointer to the pivot node associated with the newly
	// inserted
	// node with data value x. The pivot is the node on the search path,
	// with nonzero balance factor, closest to the new node.
	//

	private Node pivot(T x) {
		Node p; // A pointer used to traverse down the tree.

		this.p2 = null; // Return null if there is no pivot node.

		if (root != null) { // Start the search from the top of the tree.
			p = root;
			while (p.data.compareTo(x) != 0) { // Not at the leaf yet.
				if (p.bf != 0) { // Found a potential pivot further down 
					p2 = p; // the tree, update p2.
				}

				if (p.data.compareTo(x) < 0) // ... p follows the search path
					p = p.rchild; // down to the newly inserted
				else // node.
					p = p.lchild;
			}
		}

		return p2;
	}

	//
	// LL_rotation() - rebalance the tree by performing
	// an LL rotation of the nodes.
	//

	private void LL_rotation() {
		System.out.println("Doing LL rotation");

		//move the appropriate nodes to a new home while
		//maintaining their subtrees
		p2.lchild = p3.rchild;
		p3.rchild = p2;
		
		//there are now 2 situations that can occur, p2 is at the
		//root of the tree (and p1 is null), or it is not at the root
		//and p1 exists
		if(p1 == null) {
			
			//reset the bf to 0 since it was just rotated
			root.bf = 0;
			root = p3;
			resetbalance(root, root.data);
		}
		else {
			
			//determine where p2 is, and reset the tree
			//accordingly
			if(p1.lchild.equals(p2))
				p1.lchild = p3;
			else
				p1.rchild = p3;
			
			resetbalance(p3, p2.data);
		}
	}

	//
	// LR_rotation() - rebalance the tree by performing
	// an LL rotation of the nodes.
	//

	private void LR_rotation() {
		System.out.println("Doing LR rotation");

		//move the nodes to where they belong
		p2.lchild = p4.rchild;
		p3.rchild = p4.lchild;
		p4.rchild = p2;
		p4.lchild = p3;
		
		//since there is a fork in the tree where
		//we rotated the nodes, we need to reset the balance
		//along 2 paths
		if(p1 == null) {
			root = p4;
			resetbalance(root, p2.data);
			resetbalance(root, p3.data);
		}
		
		//if there is a p1, then we need to examine its
		//relationship to p2 and rebalance accordingly
		else {
			if(p1.rchild.equals(p2))
				p1.rchild = p4;
			else
				p1.lchild = p4;
			
			resetbalance(p4, p3.data);
			resetbalance(p4, p2.data);
		}
	}

	//
	// RR_rotation() - rebalance the tree by performing
	// an LL rotation of the nodes.
	//

	private void RR_rotation() {
		System.out.println("Doing RR rotation");

		//mirror of LL
		p2.rchild = p3.lchild;
		p3.lchild = p2;
		
		if(p1 == null) { 
			root.bf = 0;
			root = p3;
			resetbalance(root, root.data);
		}
		else {
			if(p1.lchild.equals(p2))
				p1.lchild = p3;
			else
				p1.rchild = p3;
			
			resetbalance(p3, p2.data);
		}

		
	}

	//
	// RL_rotation() - rebalance the tree by performing
	// an LL rotation of the nodes.
	//

	private void RL_rotation() {
		System.out.println("Doing RL rotation");

		//mirror of LR
		p2.rchild = p4.lchild;
		p3.lchild = p4.rchild;
		p4.lchild = p2;
		p4.rchild = p3;
		
		if(p1 == null) {
			root = p4;
			resetbalance(root, p2.data);
			resetbalance(root, p3.data);
		}
		else {
			if(p1.rchild.equals(p2))
				p1.rchild = p4;
			else
				p1.lchild = p4;
			
			resetbalance(p4, p3.data);
			resetbalance(p4, p2.data);
		}
	}

	/*
	 * attach() - attach a node with data value x in the Binary Search Tree.
	 */

	public void attach(T x) {
		this.root = rattach(this.root, x);
	}

	/*
	 * rattach() - return a pointer to the root of a BST with data item x
	 * inserted. Do not insert duplicate data items.
	 */

	public Node rattach(Node root, T x) {
		if (root == null) // Base Step - Empty tree
			root = new Node(x, null, null, 0);
		else if (x.compareTo(root.data) < 0) // Smaller values go in
			root.lchild = rattach(root.lchild, x); // the left subtree,
		else if (x.compareTo(root.data) > 0) // larger values go in
			root.rchild = rattach(root.rchild, x); // the right subtree.

		return root;
	}

	public void printTree() {
		rPrintTree(root, 0);
	}

	/*
	 * rPrintTree() - the usual quick recursive method to print a tree.
	 */

	public void rPrintTree(Node r, int level) {

		if (r == null) // Empty tree, nothing to print.
			return;

		rPrintTree(r.rchild, level + 1); // Print the right subtree.

		for (int i = 0; i < level; i++)
			System.out.print("     ");

		System.out.println(r.data.toString() + "(" + r.bf + ")"); // Print the
																	// root.

		rPrintTree(r.lchild, level + 1); // Print the left subtree.
	}

	public boolean empty() {
		return this.root == null;
	}

	public boolean search(T x) {
		boolean here = false;
		Node ptr = this.root;

		while (ptr != null)
			if (ptr.data.compareTo(x) == 0)
				return true;
			else if (ptr.data.compareTo(x) < 0)
				ptr = ptr.rchild;
			else
				ptr = ptr.lchild;

		return false;
	}

	public static void main(String[] args) {
		AvlTree<Integer> t1 = new AvlTree<Integer>();

		Scanner keyb = new Scanner(System.in); // Scanner to read from keyboard.
		int value = 0; // Value to insert into tree.

		do {
			// Read a value to insert into the tree.

			System.out.print("Insert what integer (-1 to quit)? ");
			value = keyb.nextInt();

			if (value != -1) { // a value of -1 terminates the program.
				t1.insert(value); // Insert the value into the AVL tree.
				t1.printTree(); // Check if value was correctly inserted.
			}
		} while (value != -1);

		System.out.println("\nProgram terminating\n");
	}

	// inner class - a Node in the AVL tree.

	public class Node {
		T data; // The data item stored in the node.
		Node lchild; // Pointer to left child (left subtree).
		Node rchild; // Pointer to right child (right subtree).
		int bf; // The Balance Factor of the node.

		public Node() {
			this.data = null;
			this.lchild = null;
			this.rchild = null;
			this.bf = 0;
		}

		public Node(T data, Node lchild, Node rchild, int bf) {
			this.data = data;
			this.lchild = lchild;
			this.rchild = rchild;
			this.bf = bf;
		}
	}
}