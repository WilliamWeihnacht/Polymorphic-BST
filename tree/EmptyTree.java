package tree;

import java.util.List;

/*
 * This class represents the spaces in a tree that would be null but since
 * it's polymorphic instead of being null a left or right reference will
 * point to this tree instead which contains the proper methods to manifest
 * itself as such. Also it is a singleton class so there is only one instance
 */


@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	//create the single instance of this class
	private static EmptyTree unique = new EmptyTree();

	//to ensure that this is a singleton class
	private EmptyTree() {
	}

	//returns a reference to the single instance of this class
	public static EmptyTree getInstance() {
		return unique;
	}
	
	//by the principals of a BST if this was called the key must not exist yet
	//and should be added through this call
	public NonEmptyTree<K, V> addKeyWithValue(K keyToAdd, V valueToAdd) {
		return new NonEmptyTree(keyToAdd, valueToAdd, this, this);
	}

	//an empty tree has a size of 0
	public int size() {
		return 0;
	}

	//an empty tree has no keys
	public V lookup(K lookUpKey) {
		return null;
	}

	//should do nothing
	public void pathFromRoot(K key, List<K> list) {
	}

	//returns weather the trees are equal
	public boolean haveSameKeys(Tree<K, V> otherTree) {
		return this.equals(otherTree);
	}
	
	//an empty tree has no elements
	public int numElementsAtLevel(int level) {
		return 0;
	}

	//notifies method that it has reached the end
	public K max() throws EmptyTreeException {
		throw new EmptyTreeException();
	}
	
	//notifies method that it has reached the end
	public K min() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	//shouldn't reach this
	public Tree<K, V> deleteKeyAndValue(K keyToDelete) {
		return null;
	}

	//an empty tree contains nothing so it will return nothing to print
	public String toString() {
		return "";
	}

	//shouldn't reach here
	public void modifyList(K key, List<K> list, boolean clear) {
		return;
	}

	//two empty trees are equal
	public boolean compareTrees(Tree<K, V> otherTree) {
		return true;
	}
	
}
