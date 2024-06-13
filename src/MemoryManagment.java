import java.util.List;

public class MemoryManagment {
    int memorySize;
    int partition;
    int policy;
    List<String> inputs;

    public void start(int memorySize, int partition, int policy, List<String> inputs) {
        this.memorySize = memorySize;
        this.partition = partition;
        this.policy = policy;
        this.inputs = inputs;

        System.out.println("\nO programa começou a executar!!!");

        if (this.partition == 1) {
            System.out.println("Foi escolhido a partição variável.");
            Variable variable = new Variable();

            if (this.policy == 1) {
                System.out.println("A política escolhida foi worst-fit.");
                variable.worstFit(memorySize, inputs);
            } else if (this.policy == 2) {
                System.out.println("A política escolhida foi circular-fit.");
                variable.circularFit(memorySize, inputs);
            }
            
        } else if (this.partition == 2) {
            System.out.println("Foi escolhido a partição definida (sistema buddy).");
            Buddy buddy = new Buddy();
            buddy.start(memorySize, inputs);
        }
    }
}