// add your imports here

import java.util.ArrayList;

class TSTNode<T extends Comparable<T>>{
    T element;     	            // The data in the node
    TSTNode<T>  left;   		// left child
    TSTNode<T>  mid;   		    // middle child
    TSTNode<T>  right;  		// right child

    // TODO: implement the node class here

    TSTNode(T element){
        this.element = element;
        this.left = null;
        this.mid = null;
        this.right = null;
    }

    public boolean contain(T element)
    {
        int result;
        result = this.element.compareTo(element);

        if (result == 0) {
            return true;}
        else if (result > 0) {
            if (this.left == null)
                return false;
            else
                return this.left.contain(element); }
        else if (result < 0) {
            if (this.right == null)
                return false;
            else
                return this.right.contain(element); }
        else {
            return false;
        }

    }

    public TSTNode<T> removei(T element) {
        int result;
        result = this.element.compareTo(element);

        //node = node.left if theres not right or mid

        if (this == null){
            return null;}

        if (result == 0) {
            if (this.mid != null)
                this.mid = this.mid.removei(this.mid.element);
            else {
                if (this.left != null && this.right !=null){
                this.element = this.left.findMax().element;
                this.mid = this.left.findMax().mid;
                this.left.findMax().mid = null;
                this.left = this.left.removei(this.left.element);}
                else if (this.left == null)
                    return this.right;
                else if (this.right == null)
                    return this.left;
            }

        }
        else if (result < 0){
            this.right = this.right.removei(this.right.element);
        }
        else if (result > 0){
            this.left = this.left.removei(this.left.element);
        }

        return this;

    }
    

    public TSTNode<T> addi(T element){
        int result;
        result = this.element.compareTo(element);
        TSTNode<T> newNode = new TSTNode<T>(element);

        if (result == 0) {
            if (this.mid == null)
                this.mid = newNode;
            else
                this.mid.addi(element);}

        else if (result > 0) {
            if (this.left == null)
                this.left = newNode;
            else
                this.left.addi(element);}

        else if (result < 0) {
            if (this.right == null)
                this.right = newNode;
            else
                this.right.addi(element);
        }
        return newNode;}

    TSTNode<T> findMax(){
        if (this == null)
            return null;
        else if (this.right == null)
            return this;
        else
            return this.right.findMax();
        //highest call function over and over agaimn
    }

    TSTNode<T> findMin(){
        if (this == null)
            return null;
        else if (this.left == null)
            return this;
        else
            return this.left.findMin();
    }

    int height(){
        if (this.right == null && this.left == null && this.mid == null )
            return 0;
        else {
            int h=0;
            int lHeight = -1, rHeight = -1, mHeight = -1;
            if (this.left != null){
                lHeight = this.left.height();}
            if (this.mid != null)
                mHeight = this.mid.height();
            if (this.right != null)
                rHeight = this.right.height();

            h = Math.max(lHeight, Math.max(rHeight, mHeight));

            return h+1;
            //for (T ele: this.right)

        }
    }

    // add your own helper methods if necessary
}
