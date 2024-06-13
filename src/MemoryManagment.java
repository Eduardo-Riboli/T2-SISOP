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

            if (this.policy == 1) {
                System.out.println("A política escolhida foi worst-fit.\n");

                WorstFit worstFit = new WorstFit();
                worstFit.start(memorySize, inputs);

            } else if (this.policy == 2) {
                System.out.println("A política escolhida foi circular-fit.\n");

                CircularFit circularFit = new CircularFit();
                circularFit.start(memorySize, inputs);
            }

        } else if (this.partition == 2) {
            System.out.println("Foi escolhido a partição definida (sistema buddy).\n");
            Buddy buddy = new Buddy();
            buddy.start(memorySize, inputs);
        }
    }
}