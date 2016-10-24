package tests;

import org.junit.*;

import tree.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class StudentTests {

	//make a generic tree
	public Tree makeTree1() {

		Tree<Integer, Integer> tree = 
				new NonEmptyTree(5,7,EmptyTree.getInstance(),
						EmptyTree.getInstance());

		tree.addKeyWithValue(3, 4);
		tree.addKeyWithValue(7, 3);
		tree.addKeyWithValue(14, 2);

		return tree;
	}

	public Tree makeTree2() {

		Tree<Integer, Integer> tree = 
				new NonEmptyTree(14,2,EmptyTree.getInstance(),
						EmptyTree.getInstance());

		tree.addKeyWithValue(3, 4);
		tree.addKeyWithValue(5, 7);
		tree.addKeyWithValue(7, 3);

		return tree;

	}

	//test the size method
	@Test public void testStudent1() {

		Tree<Integer, Integer> tree = makeTree1();


		assertEquals(4, tree.size());
	}

	//test the lookup method
	@Test public void testStudent2() {

		Tree<Integer, Integer> tree = makeTree1();

		assertNull(tree.lookup(30));

	}

	//test pathFromRoot if the element isn't there
	@Test public void testStudent3() {

		Tree<Integer, Integer> tree = makeTree1();
		ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(10);
		list.add(5);
		list.add(9);

		tree.pathFromRoot(30, list);

		assertEquals(list.toString(), "[10, 5, 9]");
	}

	//test pathFromRoot if the element is the root
	@Test public void testStudent4() {

		Tree<Integer, Integer> tree = makeTree1();
		ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(10);
		list.add(5);
		list.add(9);

		tree.pathFromRoot(5, list);

		assertEquals(list.toString(), "[5]");
	}

	//test have same keys with different ordered trees
	@Test public void testStudent5() {

		Tree<Integer, Integer> tree1 = makeTree1();
		Tree<Integer, Integer> tree2 = makeTree2();

		assertTrue(tree1.haveSameKeys(tree2));
	}

	//test the max for a strange tree
	@Test public void testStudent6() {

		Tree<Integer, Integer> tree = 
				new NonEmptyTree(5,7,EmptyTree.getInstance(),
						EmptyTree.getInstance());

		tree.addKeyWithValue(4, 12);
		tree.addKeyWithValue(3, 3);
		tree.addKeyWithValue(1, 2);

		try {
			assertEquals((int)tree.max(), 5);
		} catch (EmptyTreeException e) {
			fail();
		}



	}

	//test the min for a strange tree
	@Test public void testStudent7() {

		Tree<Integer, Integer> tree = 
				new NonEmptyTree(5,7,EmptyTree.getInstance(),
						EmptyTree.getInstance());

		tree.addKeyWithValue(6, 12);
		tree.addKeyWithValue(7, 3);
		tree.addKeyWithValue(8, 2);

		try {
			assertEquals((int)tree.min(), 5);
		} catch (EmptyTreeException e) {
			fail();
		}			
	}
	
	//test deleteKeyAndValue on the root
	@Test public void testStudent8() {
		
		Tree<Integer, Integer> tree = makeTree1();
		
		tree.deleteKeyAndValue(5);
		
		assertEquals(tree.toString(), "3/4 7/3 14/2");
		
		
	}




}