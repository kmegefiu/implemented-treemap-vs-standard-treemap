/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.util.ArrayList;

/**
 *
 * @author Ken
 */

public class UnbalancedTreeMap {
    ArrayList<String> inOrderList = new ArrayList<String>();
    
    //OrderedKeyValue Class
private class OrderedKeyValue implements Comparable {
		public String key;
		public int value;

		public OrderedKeyValue(String key, int value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(Object passed) {
			return ((OrderedKeyValue) passed).key.toLowerCase().compareTo(this.key.toLowerCase());
		}

	}
//BinaryNode Class
	private class  BinaryNode {
		public BinaryNode leftChild;
		public BinaryNode rightChild;
		public OrderedKeyValue data;

		public BinaryNode(String key, int value) {
			data = new OrderedKeyValue(key, value);
		}
	}
	private BinaryNode root;
    public UnbalancedTreeMap (){
        root = null;
    }
    public int get (String key){
        return get (key, root);
    }
    private int get(String key, BinaryNode node)
    {
        if ( node == null )
        	return 0;
        int comparisonResult = node.data.compareTo(new OrderedKeyValue(key,0));
        if ( comparisonResult < 0 )
            return get (key, node.leftChild);
        else if ( comparisonResult > 0 )
            return get (key, node.rightChild);
        else
            return node.data.value;
    }
    public int put (String key, int value){
        return put (key, value, root); 
    }
    
    public String[] keySet(){
        inOrder(root);
        String[] keySet = new String[inOrderList.size()];
        int i = 0;
        for(String s : inOrderList){
        keySet[i] = s;
        i++;
        }
        return keySet;
    }
    private void inOrder(BinaryNode root){
        if(root == null){
            return;
        }
        inOrder(root.leftChild);
        inOrderList.add(root.data.key);
        inOrder(root.rightChild);
    }

    private int put (String key, int value, BinaryNode tree){
        if(tree == null){
            root = new BinaryNode(key, value);
            return 0;
        }       
        int comparisonResult = tree.data.compareTo(new OrderedKeyValue(key, 0));      
        if ( comparisonResult < 0 ){
            if(tree.leftChild == null) {
            	tree.leftChild = new BinaryNode(key, value);
            	return 0;
            }
            return put (key, value, tree.leftChild );
            
        }
        else if ( comparisonResult > 0 ){
            if(tree.rightChild == null) {
            	tree.rightChild = new BinaryNode(key, value);
            	return 0;
            }
            return put (key, value, tree.rightChild );
        }
        else{
            int temp = tree.data.value; 
            tree.data.value = value;
            return temp;
        }      
    }
    
}
