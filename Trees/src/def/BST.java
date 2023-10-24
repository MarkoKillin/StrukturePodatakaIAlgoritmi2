package def;

import interfejsi.Set;

public class BST<T extends Comparable<T>> implements Set<T>{
	private class SearchResult{
		Btnode<T> node;
		Btnode<T> parent;
		public SearchResult(Btnode<T> node, Btnode<T> parent) {
			this.node = node;
			this.parent = parent;
		}
	}
	private Btnode<T> root = null;
	
	private SearchResult search(T element) {
		Btnode<T> current = root;
		Btnode<T> prev = null;
		boolean found = false;
		while(current!=null && !found) {
			int cmp = element.compareTo(current.getInfo());
			if(cmp==0)
				found = true;
			else {
				prev = current;
				if(cmp<0) current = current.getLeft();
				else current = current.getRight();
			}
		}
		return new SearchResult(current, prev);
	}
	
	@Override
	public boolean member(T element) {
		return search(element).node!=null;
	}
	@Override
	public boolean insert(T element) {
		if(root == null) {
			root = new Btnode<>(element);
			return true;
		} else {
			SearchResult sr = search(element);
			if(sr.node!=null)
				return false;
			Btnode<T> n = new Btnode<>(element);
			if(n.getInfo().compareTo(sr.parent.getInfo()) < 0)
				sr.parent.setLeft(n);
			else
				sr.parent.setRight(n);
		}
		return true;
	}
	@Override
	public boolean remove(T element) {
		SearchResult sr = search(element);
		if(sr.node==null)
			return false;
		Btnode<T> toRemove = sr.node;
		Btnode<T> parent = sr.parent;
		if(toRemove.getLeft()==null && toRemove.getRight()==null)
			removeLeaf(toRemove, parent);
		else if(toRemove.getLeft()==null || toRemove.getRight()==null)
			removeInternalWithOneChild(toRemove, parent);
		else
			removeInternal(toRemove, parent);
		return true;
	}

	private void removeLeaf(Btnode<T> toRemove, Btnode<T> parent) {
		if(parent==null)
			root = null;
		else {
			if(parent.getLeft() == toRemove)
				parent.setLeft(null);
			else
				parent.setRight(null);
		}
	}

	private void removeInternalWithOneChild(Btnode<T> toRemove, Btnode<T> parent) {
		Btnode<T> child = toRemove.getLeft();
		if(child==null)
			child = toRemove.getRight();
		if(parent == null)
			root = child;
		else {
			if(parent.getLeft()==toRemove)
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
	}

	private void removeInternal(Btnode<T> toRemove, Btnode<T> parent) {
		Btnode<T> min = toRemove.getRight();
		Btnode<T> minParent = toRemove;
		while(min.getLeft()!=null) {
			minParent = min;
			min = min.getLeft();
		}
		T minInfo = min.getInfo();
		if(minParent==toRemove)
			minParent.setRight(min.getRight());
		else
			minParent.setLeft(min.getRight());
		toRemove.setInfo(minInfo);
	}
	
	
}






















