package com.company;

public class Main{
    public static void main(String[] args)  {
        //create a BST object
        BST_class bst = new BST_class();
        /* BST tree example
              45
           /     \
          10      90
         /  \    /
        7   12  50   */
        //insert data into BST
        bst.insert("Usama");
        bst.insert("Ali");
        bst.insert("Ahmed");
        bst.insert("Haider");
        bst.insert("Umer");
        bst.insert("Baber");
        //print the BST
        System.out.println("The BST Created with input data(Left-root-right):");
        bst.inorder();

        //delete leaf node
        System.out.println("\nThe BST after Delete 12(leaf node):");
        bst.deleteKey("Haider");
        bst.inorder();
        //delete the node with one child
        System.out.println("\nThe BST after Delete 90 (node with 1 child):");
        bst.deleteKey("Umer");
        bst.inorder();

        //delete node with two children
        System.out.println("\nThe BST after Delete 45 (Node with two children):");
        bst.deleteKey("Usama");
        bst.inorder();
        //search a key in the BST
        boolean ret_val = bst.search ("Baber");
        System.out.println("\nKey Baber(50) found in BST:" + ret_val );
        ret_val = bst.search ("Haider");
        System.out.println("\nKey Haider(12) found in BST:" + ret_val );
    }
}