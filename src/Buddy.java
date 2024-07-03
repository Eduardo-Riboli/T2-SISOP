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
                if (child.remaingSize / 2 >= processSize) {
                    // Ja está dividido
                    list.add(0, child.leftChild);
                    list.add(1, child.rightChild);
                }
            } else {
                if (child.remaingSize / 2 >= processSize) {
                    // Tem que dividir
                    child.divide();
                    list.add(0, child.leftChild);
                    list.add(1, child.rightChild);
                }
                else if (child.remaingSize >= processSize && child.process == null) {
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

        List<BuddyNode> list = new LinkedList<>();
        list.add(buddyNode);
        boolean finish = false;

        while (list.size() > 0) {
            BuddyNode child = list.remove(0);

            if (child.isFree && child.process != null && child.process.equals(process)) {
                // Caso encontre o processo, tira ele do nodo e realiza o merge
                child.process = null;
                child.remaingSize = child.size;
                finish = true;
                child.merge();
                break;
            } else {
                // adiciona os filhos, se existirem
                if (child.leftChild != null) {
                    list.add(child.leftChild);
                }
                if (child.rightChild != null) {
                    list.add(child.rightChild);
                }
            }
        }

        return finish;
    }
}