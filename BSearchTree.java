package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 搜索二叉树
 */
public class BSearchTree{

    private Node root;

    public static  void   main(String[] args){

        BSearchTree bst = new BSearchTree();
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(2);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);

//        System.out.println("Pre-order traversal:");
//        bst.preOrder(bst.root);
//        System.out.println("In-order traversal:");
//        bst.inOrder(bst.root);
//        System.out.println("Post-order traversal:");
//        bst.postOrder(bst.root);
        System.out.println("LevelOrder traversal:");
        bst.leverOrder(bst.root);


    }

    /**
     * 搜索二叉搜索树中的值
     * @param node  根节点
     * @param value 要搜索的值
     * @return 找到的节点，如果没有找到则返回null
     */
    public Node search(Node node, int value) {
        if (node == null || node.getValue() == value) {
            return node;
        }
        if (node.getValue() < value) {
            return search(node.getRight(), value);
        } else {
            return search(node.getLeft(), value);
        }
    }






    /**
     * 插入节点
     * @param value
     */
    public void insert(int value) {
        root = insertRec(value, root);
    }



    /**
     * 递归插入寻找节点位置
     * @param value
     * @param node
     * @return
     */
    public  Node insertRec(int value,Node node){
        if(node==null){
            Node root=new Node(value);
            return root;
        }
       if(value<node.getValue()){
           node.setLeft(insertRec(value,node.getLeft()));
       }
       if(value> node.getValue()){
           node.setRight(insertRec(value,node.getRight()));
       }
        return node;
    }


    /**
     * 前序遍历
     * @param node
     */
    public void preOrder(Node node){
     if(node!=null){
      System.out.println(node.getValue());
      preOrder(node.getLeft());
      preOrder(node.getRight());
     }
    }


    /**
     * 层次遍历 
    * @param node
     */
    public void  leverOrder(Node node){
        if(node==null){
            return;
        }
        Queue<Node>  queue=new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
          Node current= queue.poll();
          System.out.println(current.getValue());
          if(current.getLeft()!=null){
              queue.offer(current.getLeft());
          }
          if(current.getRight()!=null){
              queue.offer(current.getRight());
          }
        }
    }

    /**
     * 中序遍历
     * @param node
     */
    public void inOrder(Node node){
        if(node!=null){
            inOrder(node.getLeft());
            System.out.println(node.getValue());
            inOrder(node.getRight());
        }
    }

    /**
     * 后续遍历
     * @param node
     */
    public void postOrder(Node node){
        if(node!=null){
           postOrder(node.getLeft());
           postOrder(node.getRight());
           System.out.println(node.getValue());
        }

    }

    /**
     * 获取前驱节点
     * @param node
     * @return
     */
    public Node getPrecursorNode(Node node){
        if(node==null){
            return null;
        }
        Node  newNode=node.getLeft();
        while (newNode!=null&&newNode.getRight()!=null){
            newNode=newNode.getRight();
        }
        return newNode;
    }


    /**
     * 查找后继节点
     * @param node
     * @return
     */
    public  Node  getSuccessorNode(Node node){
        if(node==null){
            return null;
        }
        Node  newNode=node.getRight();
        while (newNode!=null&&newNode.getLeft()!=null){
         newNode=newNode.getLeft();
        }
        return newNode;
    }

    /**
     * 二叉树删除节点非递归版本
     * @param root
     * @param value
     * @return
     */
    public Node delete(Node root,int value) {
        Node parentNode=null;
        Node current=root;
        //找到要删除的节点及其父节点
        while(current.getValue()!=value&&current!=null){
            parentNode=current;
            if(current.getValue()>value){
                current=current.getLeft();
            }if(current.getValue()<value){
                current=current.getRight();
            }
        }
        //说明没有找到要删除的节点，直接返回根节点
        if(current==null){
            return root;
        }
        if(current.getLeft()!=null&&current.getRight()!=null){
            Node successorParent=current;
            //找到后继节点
            Node successor=current.getRight();
            while (successor!=null&&successor.getLeft()!=null){
                successor=successor.getLeft();
            }
            //将后继节点的值赋值给要删除的节点
            current.setValue(successor.getValue());

            current=successor;
            parentNode=successorParent;
        }
        Node child = (current.getLeft() != null) ? current.getLeft() : current.getRight();
        //说明要删除的节点是父亲节点
        if(parentNode==null){
            return  child;
        }
        if (parentNode.getLeft() == current) {
            parentNode.setLeft(child);
        } else {
            parentNode.setRight(child);
        }
        return root;
    }



}

/**
 * 二叉树节点
 */
class Node{

    private int value;

    private  Node left;

    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
