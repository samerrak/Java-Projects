import java.util.ArrayList;
import java.util.Iterator;

public class TST<T extends Comparable<T>> implements Iterable<T> {
    // root node of the tree
    TSTNode<T> root;

    // constructor
    public TST() {
        this.root = null;
    }


    public void insert(T element){
        TSTNode<T> newNode = new TSTNode<T>(element);
        if (this.root == null)
            this.root=newNode;
        else
            this.root.addi(element);}

    public void remove(T element) {
        //ArrayList<T> rmArray = new ArrayList<>();
        //for (T elements: this)
            //rmArray.add(elements);

        if (this.contains(element)) {
            this.root = this.root.removei(element);

        }



        //this.root.removei(element);

    }

    public boolean contains(T element) {
        if (this.root == null)
            return false;
        else
            return this.root.contain(element);
    }

    public void rebalance() {
        ArrayList<T> sortArray = new ArrayList<>();
        for (T elements : this)
            sortArray.add(elements);

        if (!sortArray.isEmpty())
            this.root = helperBalance(sortArray);
    }


    public int height() {
        if (this.root == null)
            return -1;
        return this.root.height();
    }

    @Override
    public Iterator iterator() {

        return new TSTIterator(this.root);
    }

    @Override
    public String toString() {
        if (this.root == null)
            return "empty tree";
        // creates a buffer of 100 characters for the string representation
        StringBuilder buffer = new StringBuilder(100);
        // build the string
        stringfy(buffer, this.root, "", "");
        return buffer.toString();
    }


    private void stringfy(StringBuilder buffer, TSTNode<T> node, String nodePrefix, String childrenPrefix) {
        buffer.append(nodePrefix);
        buffer.append(node.element);
        buffer.append('\n');
        if (node.left != null)
            stringfy(buffer, node.left, childrenPrefix + "|~~ ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|~~ null\n");
        if (node.mid != null)
            stringfy(buffer, node.mid, childrenPrefix + "|-- ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|-- null\n");
        if (node.right != null)
            stringfy(buffer, node.right, childrenPrefix + "|__ ", childrenPrefix + "    ");
        else
            buffer.append(childrenPrefix + "|__ null\n");
    }

    public void inorderPrintAsList() {
        String buffer = "[";
        for (T element : this) {
            buffer += element + ", ";
        }
        int len = buffer.length();
        if (len > 1)
            buffer = buffer.substring(0, len - 2);
        buffer += "]";
        System.out.println(buffer);
    }

    TSTNode<T> helperBalance(ArrayList<T> sortedArray) {
        ArrayList<T> leftArray = new ArrayList<>();
        ArrayList<T> rightArray = new ArrayList<>();
        ArrayList<T> midArray = new ArrayList<>();
        T pivot = sortedArray.get(sortedArray.size() / 2);

        TSTNode<T> rooty = new TSTNode(pivot);

        for (T element : sortedArray) {
            int result = pivot.compareTo(element);
            if (result > 0)
                leftArray.add(element);
            else if (result == 0) {
                midArray.add(element);
            } else if (result < 0)
                rightArray.add(element);
        }

        midArray.remove(0);

        if (leftArray.isEmpty())
            rooty.left = null;
        else {
            rooty.left = helperBalance(leftArray);

        }
        if (rightArray.isEmpty())
            rooty.right = null;
        else {
            rooty.right = helperBalance(rightArray);
        }
        if (midArray.isEmpty())
            rooty.mid = null;
        else
            rooty.mid = helperBalance(midArray);

        return rooty;


    }
}