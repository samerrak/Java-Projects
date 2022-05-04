import java.util.ArrayList;
import java.util.Iterator;


class TSTIterator<T extends Comparable<T>> implements Iterator<T> {

    ArrayList<TSTNode<T>> nodeArrayList = new ArrayList<>();

    public TSTIterator(TSTNode<T> node) {
        inorder(node);
    }

    public void inorder(TSTNode<T> node){
        if (node != null){
            inorder(node.left);
            this.nodeArrayList.add(node);
            inorder(node.mid);
            inorder(node.right);
        }
    }

    @Override
    public boolean hasNext() {
        return (!nodeArrayList.isEmpty());
    }

    @Override
    public T next() {
        return nodeArrayList.remove(0).element;
    }
}
