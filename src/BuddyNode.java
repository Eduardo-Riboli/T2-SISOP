public class BuddyNode {
    int size;
    int remaingSize;
    String process;
    Boolean isFree;
    BuddyNode leftChild;
    BuddyNode rightChild;
    BuddyNode father;

    public BuddyNode(int size) {
        this.size = size;
        this.remaingSize = size;
        this.isFree = true;
        this.leftChild = null;
        this.rightChild = null;
        this.father = null;
        this.process = null;
    }

    public void divide() {
        if (isFree) {
            this.leftChild = new BuddyNode(this.size / 2);
            this.rightChild = new BuddyNode(this.size / 2);
            this.leftChild.father = this;
            this.rightChild.father = this;
            this.isFree = false; // ou seja, ele não está mais livre
        }
    }

    public void merge() {
        BuddyNode buddyNode = this;
        BuddyNode father = buddyNode.father;
        if (father != null && !father.isFree && (father.leftChild.remaingSize == father.leftChild.size) 
                && (father.rightChild.remaingSize == father.rightChild.size) && father.leftChild.isFree && father.rightChild.isFree) {
            father.leftChild = null;
            father.rightChild = null;
            father.isFree = true;
            father.merge(); // Realiza meio q uma recursão.
        }
    }
}
