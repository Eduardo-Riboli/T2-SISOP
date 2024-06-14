public class Buddy {

    public boolean putInMemoryBuddy(BuddyNode buddyNode, String process, int size) {
        if (buddyNode.isFree && buddyNode.size > size) {
            if (buddyNode.leftChild == null) {
                buddyNode.leftChild = new BuddyNode(size / 2);
                buddyNode.rightChild = new BuddyNode(size / 2);
                buddyNode.leftChild.father = buddyNode;
                buddyNode.rightChild.father = buddyNode;

                if (buddyNode.leftChild.size > size) {
                    if (this.putInMemoryBuddy(buddyNode.leftChild, process, buddyNode.leftChild.size)
                            || this.putInMemoryBuddy(buddyNode.rightChild, process, buddyNode.rightChild.size)) {
                        buddyNode.isFree = false;
                        return true;
                    }
                } else {
                    buddyNode.isFree = false;
                    return true;
                }
            } else if (buddyNode.leftChild.remaingSize >= size || buddyNode.rightChild.remaingSize >= size) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

        return false;
    }

    public boolean outInMemoryBuddy(BuddyNode buddyNode, String process) {
        return false;
    }
}