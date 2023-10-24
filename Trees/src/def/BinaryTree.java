package def;

import java.util.LinkedList;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> {
	private Btnode<T> root = null;
	public void setRoot(Btnode<T> root) {
		this.root = root;
	}
	public Btnode<T> getRoot() {
		return root;
	}
	public boolean isEmpty() {
		return root==null;
	}
	
	public int getSize() {
		return root==null ? 0 : getSize(root);
	}
	private int getSize(Btnode<T> current) {
		int leftSize =  0;
		Btnode<T> left = current.getLeft();
		if(left!=null)
			leftSize = getSize(left);
		int rightSize = 0;
		Btnode<T> right = current.getRight();
		if(right!=null)
			rightSize = getSize(right);
		return 1+leftSize+rightSize;
	}
	
	public int getDepth() {
		return root==null ? -1 : getDepth(root);
	}
	private int getDepth(Btnode<T> current) {
		int leftDepth =  -1;
		Btnode<T> left = current.getLeft();
		if(left!=null)
			leftDepth = getSize(left);
		int rightDepth = -1;
		Btnode<T> right = current.getRight();
		if(right!=null)
			rightDepth = getSize(right);
		return leftDepth>rightDepth ? 1+leftDepth : 1+rightDepth;
	}
	
	public Btnode<T> dfs(T info){
		return root==null ? null : dfs(info, root);
	}
	private Btnode<T> dfs(T info, Btnode<T> current) {
		if(current.getInfo().equals(info))
			return current;
		Btnode<T> left = current.getLeft();
		Btnode<T> right = current.getRight();
		Btnode<T> res = null;
		if(left!=null) {
			res = dfs(info, left);
			if(res!=null)
				return res;
		}
		if(right!=null) {
			res = dfs(info, right);
			if(res!=null)
				return res;
		}
		return null;
	}
	
	public Btnode<T> bfs(T info){
		LinkedList<Btnode<T>> queue = new LinkedList<>();
		queue.addLast(root);
		while(!queue.isEmpty()) {
			Btnode<T> f = queue.removeFirst();
			if(f.getInfo().equals(info))
				return f;
			Btnode<T> left = f.getLeft();
			if(left!=null)
				queue.addLast(left);
			Btnode<T> right = f.getRight();
			if(right!=null)
				queue.addLast(left);
		}
		return null;
	}
	
	public Btnode<T> dfsIter(T info){
		if(root==null)
			return null;
		Stack<Btnode<T>> s = new Stack<>();
		s.push(root);
		while(!s.isEmpty()) {
			Btnode<T> c = s.pop();
			if(c.getInfo().equals(info))
				return c;
			Btnode<T> left = c.getLeft();
			Btnode<T> right = c.getRight();
			if(left!=null)
				s.push(left);
			if(right!=null)
				s.push(right);
		}
		return null;
	}
	
	public void printPreOrder() {
		printPreOrder(root);
	}
	private void printPreOrder(Btnode<T> current) {
		if(current!=null) {
			System.out.println(current);
			printPreOrder(current.getLeft());
			printPreOrder(current.getRight());
		}
	}
}
//size, depth, dfs, bfs, dfsiter, preorder,inorder,postorder

























