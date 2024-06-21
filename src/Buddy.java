import java.util.LinkedList;
import java.util.List;

public class Buddy {
  

    public boolean putInMemoryBuddy(BuddyNode buddyNode, String process, int processSize) {
    
        List<BuddyNode> list = new LinkedList<>();
        list.add(buddyNode);
        boolean finish = false;
        
        while (list.size() > 0) {
            BuddyNode child = list.remove(0);

            if (!child.isFree) {
                if (child.size / 2 >= processSize) {
                    // Ja está dividido
                    list.add(child.leftChild);
                    list.add(child.rightChild);
                }
            } else {
                if (child.size / 2 >= processSize) {
                    // Tem que dividir
                    child.divide();
                    list.add(0, child.leftChild);
                    list.add(1, child.rightChild);
                }
                else if (child.remaingSize >= processSize) {
                    child.process = process;
                    child.remaingSize -= processSize;
                    finish = true;
                    break;
                }
                else {
                    continue;
                }
            }
        }

        return finish;
    }

    public boolean outInMemoryBuddy(BuddyNode buddyNode, String process) {
        return false;
    }
}