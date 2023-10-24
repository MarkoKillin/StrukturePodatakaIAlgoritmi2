package implementacija;

import interfejsi.Map;

public class OHashMap<K, V> implements Map<K, V> {
	private static final int DEF = 101;
	private static class Node{
		Object key;
		Object value;
		Node next;
		public Node(Object key, Object value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
	private Node[] table;
	
	public OHashMap(int size) {
		if(size<=0)
			throw new IllegalArgumentException();
		this.table = new Node[size];
	}
	
	public OHashMap() {
		this(DEF);
	}
	
	private int hash(K key) {
		if(key==null)
			throw new IllegalArgumentException();
		return Math.abs(key.hashCode()%table.length);
	}
	
	private Node[] searchCollisionChain(K k, int hashValue) {
		Node current = table[hashValue];
		Node prev = null;
		while(current!=null) {
			if(current.key.equals(k)) {
				Node[] res = new Node[2];
				res[0] = current;
				res[1] = prev;
				return res;
			}
			prev = current;
			current = current.next;
		}
		return null;
	}
	
	@Override
	public boolean insert(K key, V value) {
		int hashValue = hash(key);
		Node[] res = searchCollisionChain(key, hashValue);
		if(res!=null)
			return false;
		Node n = new Node(key, value);
		n.next = table[hashValue];
		table[hashValue] = n;
		return true;
	}

	@Override
	public boolean delete(K key) {
		int hashValue = hash(key);
		Node[] res = searchCollisionChain(key, hashValue);
		if(res==null)
			return false;
		if(res[0] == table[hashValue])
			table[hashValue] = table[hashValue].next;
		else
			res[1].next = res[0].next;
		return true;
	}

	@Override
	public V get(K key) {
		int hashValue = hash(key);
		Node[] res = searchCollisionChain(key, hashValue);
		if(res==null)
			return null;
		if(res[0] != table[hashValue]) {
			res[1].next = res[0].next;
			res[0].next = table[hashValue];
			table[hashValue] = res[0];
		}
		return (V) res[0].value;
	}

	@Override
	public boolean modify(K key, V value) {
		int hashValue = hash(key);
		Node[] res = searchCollisionChain(key, hashValue);
		if(res==null)
			return false;
		res[0].value = value;
		return true;
	}
	
	
	
}
