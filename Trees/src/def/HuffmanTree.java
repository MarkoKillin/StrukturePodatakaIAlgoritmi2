package def;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class HuffmanTree {
	private class CharFrequency implements Comparable<CharFrequency>{
		char c;
		int freq;
		public CharFrequency(char c, int freq) {
			super();
			this.c = c;
			this.freq = freq;
		}
		public int compareTo(CharFrequency o) {
			return freq-o.freq;
		}
	}
	private LinkedList<CharFrequency> freqList;
	private BinaryTree<CharFrequency> hTree;
	public HuffmanTree(String text) {
		compute(text);
		construct();
	}
	private void compute(String text) {
		HashMap<Character, CharFrequency> map = new HashMap<>();
		freqList = new LinkedList<>();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			CharFrequency cf = map.get(c);
			if(cf==null) {
				cf = new CharFrequency(c, 1);
				freqList.addLast(cf);
				map.put(c, cf);
			} else
				cf.freq++;
		}
	}
	private void construct() {
		PriorityQueue<Btnode<CharFrequency>> pq = new PriorityQueue<>(freqList.size());
		Iterator<CharFrequency> it = freqList.iterator();
		while(it.hasNext()) {
			CharFrequency cf = it.next();
			Btnode<CharFrequency> n = new Btnode<HuffmanTree.CharFrequency>(cf);
			pq.add(n);
		}
		while(pq.size()>1) {
			Btnode<CharFrequency> rst = pq.poll();
			int rf = rst.getInfo().freq;
			Btnode<CharFrequency> lst = pq.poll();
			int lf = lst.getInfo().freq;
			CharFrequency ncf = new CharFrequency('#', lf+rf);
			Btnode<CharFrequency> nn = new Btnode<HuffmanTree.CharFrequency>(ncf, lst, rst);
			pq.add(nn);
		}
		hTree = new BinaryTree<>();
		hTree.setRoot(pq.poll());
	}
	
	public void printCodes() {
		Btnode<CharFrequency> root = hTree.getRoot();
		if(root.getLeft()==null && root.getRight()==null)
			System.out.println(root.getInfo() + " --> 1");
		else 
			printCodes(root, "");
	}
	private void printCodes(Btnode<CharFrequency> curr, String code) {
		if(curr.getLeft()==null && curr.getRight()==null)
			System.out.println(curr.getInfo() + " --> " + code);
		else {
			Btnode<CharFrequency> left = curr.getLeft();
			Btnode<CharFrequency> right = curr.getRight();
			if(left!=null)
				printCodes(left, code + "0");
			if(right!=null)
				printCodes(right, code + "1");
		}
	}
	
}













