/*
 * William Weihnacht
 * 
 * UID: 114165770
 * 
 * Directory ID: wwhynot
 * 
 * Section: 0203
 * 
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment.
 * 
 */

package tree;

import java.util.List;

/*
 * This class represents the non-empty tree portion of a polymorphic tree
 * meaning it holds values and keys
 * 
 */

@SuppressWarnings("unchecked")
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	//make sure each node has a left and right reference as well 
	//as a key and value
	K curKey;
	V curValue;
	Tree<K, V> left = EmptyTree.getInstance();
	Tree<K, V> right = EmptyTree.getInstance();

	//a constructor for the class
	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
		this.curKey = key;
		this.curValue = value;
		this.left = left;
		this.right = right;
	}

	//adds a given key and the associated value to the tree
	public NonEmptyTree<K, V> addKeyWithValue(K keyToAdd, V valueToAdd) {

		//check for null objects
		if(keyToAdd == null || valueToAdd == null) {
			throw new NullPointerException();
		}

		//recursively use the binary search functionality of the tree to find
		//it efficiently using recursion
		if(curKey.compareTo(keyToAdd) == 0) {
			curValue = valueToAdd;
		} else if(keyToAdd.compareTo(curKey) > 0) {
			right = right.addKeyWithValue(keyToAdd, valueToAdd);
		} else if(keyToAdd.compareTo(curKey) < 0) {
			left = left.addKeyWithValue(keyToAdd, valueToAdd);
		}

		return this;

	}

	//find the size of the tree
	public int size() {
		return 1 + left.size() + right.size();
	}

	//returns a reference to the value associated with the given key
	public V lookup(K lookUpKey) {

		//check for null objects
		if(lookUpKey == null) {
			throw new NullPointerException();
		}

		//use the binary search tree element of the tree to find the right key
		if(lookUpKey.compareTo(curKey) < 0) {
			return left.lookup(lookUpKey);
		} else if(lookUpKey.compareTo(curKey) > 0) {
			return right.lookup(lookUpKey);
		} else {
			return curValue;
		}

	}

	//this method clears the given list and adds all elements between the 
	//root and NonEmptyList containing the key in the parameter to the list
	public void pathFromRoot(K key, List<K> list) {

		//check for null objects
		if(key == null || list == null) {
			throw new NullPointerException();
		}

		//calls a recursive method
		if(this.lookup(key) != null) {
			modifyList(key, list, true);
		}

	}

	//returns true if the tree it's called on and another tree have the same
	//keys
	public boolean haveSameKeys(Tree<K, V> otherTree) {

		//check for null objects
		if(otherTree == null) {
			throw new NullPointerException();
		}

		//call a recursive helper method
		if(this.size() == otherTree.size()) {
			return compareTrees(otherTree);
		}

		return false;
	}

	//find how many elements exist at a given level of the tree
	public int numElementsAtLevel(int level) {

		return 3; //I don't know

	}

	//find the max key in the tree
	public K max() throws EmptyTreeException {

		K maxKey = curKey;

		//keep going to the right until there's an empty tree then return
		try{
			maxKey = right.max();							
			return maxKey;
		} catch(EmptyTreeException e) {
			return maxKey;
		}


	}

	//find the minimum key in the tree
	public K min() throws EmptyTreeException {

		K minKey = curKey;

		//keep going to the left until there's an empty tree then return
		try{
			minKey = left.min();
			return minKey;
		} catch(EmptyTreeException e) {
			return minKey;
		}

	}

	//deletes a key and associated value from the tree
	public Tree<K, V> deleteKeyAndValue(K keyToDelete) {

		//check for null objects
		if(keyToDelete == null) {
			throw new NullPointerException();
		}

		//if this is the key to delete replace the objects values and key with
		//that of the smallest from the right subtree
		if(this.lookup(keyToDelete) != null) {
			if(keyToDelete.compareTo(curKey) == 0) {

				try{

					curValue = lookup(right.min());
					curKey = right.min();
					right = right.deleteKeyAndValue(curKey);

				} catch(EmptyTreeException e) {
					return left;
				}

			}
			//otherwise keep looking
			if(keyToDelete.compareTo(curKey) < 0) {
				left = left.deleteKeyAndValue(keyToDelete);
			} else if(keyToDelete.compareTo(curKey) > 0) {
				right = right.deleteKeyAndValue(keyToDelete);
			}
		}

		return this;
	}

	//returns a string representation of the object
	public String toString() {

		String returnString = new String("");

		returnString += left.toString() + " " + curKey + "/" + curValue + " " + 
				right.toString();

		returnString = returnString.trim();

		return returnString;
	}

	//a helper method for haveSameKeys
	public boolean compareTrees(Tree<K, V> otherTree) {

		//recursively see if the current key is in the other tree
		if(otherTree.lookup(this.curKey) != null) {
			return left.compareTrees(otherTree) && right.compareTrees(otherTree);
		}
		
		return false;
	}
	
	//a helper method for pathFromRoot
	public void modifyList(K key, List<K> list, boolean clear) {

		//if it's the first time through clear the list
		if(clear) {
			list.clear();
		}

		//look for the key, adding all elements to the list
		list.add(this.curKey);
		if(key.compareTo(this.curKey) < 0) {

			left.modifyList(key, list, false);

		} else if(key.compareTo(this.curKey) > 0) {

			right.modifyList(key, list, false);

		}


	}


}
