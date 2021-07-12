/*
 * These test cases are provided to assist your implementation.
 * However, note that these test cases may not be used in actual marking.
 * You are encouraged to write your own test case to test the correctness
 * of your implementation.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RBTreeTest {
	RBTree<String> tree;

	@Before
	public void setUp() {
		tree = new RBTree<String>();
		Node.PersonName<String> node1 = new Node.PersonName<String>("Tyson","Terrell");
		Node.PersonName<String> node2 = new Node.PersonName<String>("John","Evison");
		Node.PersonName<String> node3 = new Node.PersonName<String>("Maxwell","Williams");
		tree.insert(node1,"Watson");
		tree.insert(node2,"Braddon");
		tree.insert(node3,"Acton");
	}

	@Test(timeout=1000)
	public void testInsert() {
		Assert.assertEquals("(Tyson,Terrell,Watson) (John,Evison,Braddon) (Maxwell,Williams,Acton)", tree.preOrder());
	}

	@Test(timeout=1000)
	public void testInsertDuplicate() {
		Node.PersonName<String> node4 = new Node.PersonName<String>("John","Evison");
		tree.insert(node4,"Belconnen");
		Assert.assertEquals("(Tyson,Terrell,Watson) (John,Evison,Belconnen) (Maxwell,Williams,Acton)", tree.preOrder());
	}

	@Test(timeout=1000)
	public void testInsertNewNode() {
		Node.PersonName<String> node5 = new Node.PersonName<String>("Zachary","Williams");
		tree.insert(node5, "Kingston");
		Assert.assertEquals("(Tyson,Terrell,Watson) (John,Evison,Braddon) (Maxwell,Williams,Acton) (Zachary,Williams,Kingston)", tree.preOrder());
	}

	@Test(timeout=1000)
	public void testInsertNewNode1() {
		Node.PersonName<String> node5 = new Node.PersonName<String>("Zachary","Williams");
		Node.PersonName<String> node8 = new Node.PersonName<String>("Dominic","Evison");
		tree.insert(node5,"Kingston");
		tree.insert(node8,"Coombs");
		Assert.assertEquals("(Tyson,Terrell,Watson) (John,Evison,Braddon) (Dominic,Evison,Coombs) (Maxwell,Williams,Acton) (Zachary,Williams,Kingston)", tree.preOrder());
	}

	@Test(timeout=1000)
	public void testInsertNewNode2() {
        Node.PersonName<String> node5 = new Node.PersonName<String>("Zachary","Williams");
        Node.PersonName<String> node8 = new Node.PersonName<String>("Dominic","Evison");
		Node.PersonName<String> node9 = new Node.PersonName<String>("Luke","Williams");
        tree.insert(node5,"Kingston");
        tree.insert(node8,"Coombs");
		tree.insert(node9,"Fyshwick");
		Assert.assertEquals("(Tyson,Terrell,Watson) (John,Evison,Braddon) (Dominic,Evison,Coombs) (Maxwell,Williams,Acton) (Luke,Williams,Fyshwick) (Zachary,Williams,Kingston)", tree.preOrder());
	}

	@Test(timeout=1000)
	public void testSearch1() {
		Node.PersonName<String> node6 = new Node.PersonName<String>("John","Evison");
		Assert.assertTrue(tree.search(node6).key.firstName == "John" && tree.search(node6).key.lastName == "Evison");
	}

	@Test(timeout=1000)
	public void testSearch2() {
		Node.PersonName<String> node7 = new Node.PersonName<String>("Issac","Green");
		Assert.assertNull(tree.search(node7));
	}

	@Test(timeout=1000)
	public void testNodeRedToBlack() {
        Node.PersonName<String> node5 = new Node.PersonName<String>("Zachary","Williams");
        Node.PersonName<String> node3 = new Node.PersonName<String>("Maxwell","Williams");
        Node.PersonName<String> node4 = new Node.PersonName<String>("John","Evison");
		tree.insert(node5,"Kingston");
		Assert.assertTrue(tree.search(node3).colour == Colour.BLACK && tree.search(node4).colour == Colour.BLACK);
	}

	@Test(timeout=1000)
	public void testInsertNewNodeRed() {
        Node.PersonName<String> node5 = new Node.PersonName<String>("Zachary","Williams");
        tree.insert(node5,"Kingston");
		Assert.assertTrue(tree.search(node5).colour == Colour.RED);
	}

	@Test(timeout=1000)
	public void testInsertNewNodeNoColourChange() {
		Node.PersonName<String> node1 = new Node.PersonName<String>("Zachary","Williams");
		Node.PersonName<String> node2 = new Node.PersonName<String>("Declan","Unger");
		Node.PersonName<String> node3 = new Node.PersonName<String>("Maxwell","Williams");
		Node.PersonName<String> node4 = new Node.PersonName<String>("John","Evison");
		tree.insert(node1,"Kingston");
		tree.insert(node2,"Greenway");
		Assert.assertTrue(tree.search(node3).colour == Colour.BLACK
				&& tree.search(node4).colour == Colour.BLACK
				&& tree.search(node1).colour == Colour.RED
				&& tree.search(node2).colour == Colour.RED);
	}

	@Test(timeout=1000)
	public void testInsertRotate() {
		Node.PersonName<String> node1 = new Node.PersonName<String>("Zachary","Williams");
		Node.PersonName<String> node2 = new Node.PersonName<String>("Callum","Terrell");
		Node.PersonName<String> node3 = new Node.PersonName<String>("Leon","Terrell");
        tree.insert(node1,"Kingston");
		tree.insert(node2,"Kingston");
		tree.insert(node3,"Kingston");
		Assert.assertEquals("(Tyson,Terrell,Watson) (Callum,Terrell,Kingston) (John,Evison,Braddon) (Leon,Terrell,Kingston) (Maxwell,Williams,Acton) (Zachary,Williams,Kingston)", tree.preOrder());
	}

}
