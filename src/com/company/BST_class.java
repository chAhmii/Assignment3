package com.company;
class BST_class {
    //node class that defines BST node
    class Node {
        String key;
        Node left, right;

        public Node(String data){
            key = data;
            left = right = null;
        }
    }
    // BST root node
    Node root;

    // Constructor for BST =>initial empty tree
    BST_class(){

        root = null;
    }
    //delete a node from BST
    void deleteKey(String key) {

        root = delete_Recursive(root, key);
    }

    //recursive delete function
    Node delete_Recursive(Node root, String key)  {
        int compare = key.compareTo(root.key);
        //tree is empty
        if (root == null)
            return root;

        //traverse the tree
        if (compare < 0)     //traverse left subtree
            root.left = delete_Recursive(root.left, key);
        else if (compare > 0)  //traverse right subtree
            root.right = delete_Recursive(root.right, key);
        else  {
            // node contains only one child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node has two children;
            //get inorder successor (min value in the right subtree)
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = delete_Recursive(root.right, root.key);
        }
        return root;
    }

    String minValue(Node root)  {
        //initially minval = root
        String minval = root.key;
        //find minval
        while (root.left != null)  {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }

    // insert a node in BST
    void insert(String key)  {

        root = insert_Recursive(root, key);
    }

    //recursive insert function
    Node insert_Recursive(Node root, String key) {
        //tree is empty
        if (root == null) {
            root = new Node(key);
            System.out.println("inserted" );
            return root;
        }
        int compare = key.compareTo(root.key);
        System.out.println("Compare is " + compare);
        //traverse the tree
        if (compare < 0)     //insert in the left subtree
        {
            System.out.println("Lesser !!");
            root.left = insert_Recursive(root.left, key);
        }
        else if (compare > 0)    //insert in the right subtree
        {
            System.out.println("Greater");
            root.right = insert_Recursive(root.right, key);
        }
        // return pointer
        return root;
    }

    // method for inorder traversal of BST
    void inorder() {
        inorder_Recursive(root);
    }

    // recursively traverse the BST
    void inorder_Recursive(Node root) {
        if (root != null) {
            inorder_Recursive(root.left);
            System.out.println(root.key );
            inorder_Recursive(root.right);
        }
    }

    boolean search(String key)  {
        System.out.println("Key is " + key);
        root = search_Recursive(root, key);
        if (root!= null)
            return true;
        else
            return false;
    }

    //recursive insert function
    Node search_Recursive(Node root, String key)  {
        System.out.println("Root key is " + root);
        System.out.println("Key is " + key);
        // Base Cases: root is null or key is present at root
        if (root==null || root.key.equals(key))
            return root;
        int compare = key.compareTo(root.key);
        // val is greater than root's key
        if (compare < 0)
            return search_Recursive(root.left, key);
        // val is less than root's key
        return search_Recursive(root.right, key);
    }
}