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

        System.out.println("\n ==> O programa começou a executar!!!\n");

        if (this.partition == 1) {
            System.out.println(" ===> Foi escolhido a partição variável.");

            if (this.policy == 1) {
                System.out.println(" ===> A política escolhida foi worst-fit.\n");
                this.startAllocationVariable(memorySize, inputs, policy);

            } else if (this.policy == 2) {
                System.out.println(" ===> A política escolhida foi circular-fit.\n");
                this.startAllocationVariable(memorySize, inputs, policy);
            }

        } else if (this.partition == 2) {
            System.out.println("Foi escolhido a partição definida (sistema buddy).\n");
            this.startAllocationBuddy(memorySize, inputs);
            // 3 se refere ao buddy, para conseguirmos utilizar a mesma estrutura que
            // construimos
        }
    }

    // alocação dos métodos buddy
    public void startAllocationBuddy(int memorySize, List<String> inputs) {
        Buddy buddy = new Buddy();
        BuddyNode rootBuddyNode = new BuddyNode(memorySize);

        for (String line : inputs) {
            String[] parts = line.split("[(),]");

            String command = parts[0].trim();
            String process = parts[1].trim();

            if (command.equals("IN")) {
                int processSize = Integer.parseInt(parts[2].trim());
                boolean success = buddy.putInMemoryBuddy(rootBuddyNode, process, processSize);

                if (success) {
                    System.out.println(
                            "O processo (" + process + ", " + processSize + ") foi alocado com sucesso!");
                } else {
                    System.out.println(
                            "ESPAÇO INSUFICIENTE DE MEMÓRIA para o processo (" + process + ", " + processSize + ")");
                }

                System.out.println("");
                // print buddy
                this.printMemoryBuddy(rootBuddyNode, 0);

                System.out.println("---------------------------");

            } else if (command.equals("OUT")) {

                boolean verification = buddy.outInMemoryBuddy(rootBuddyNode, process);
                               
                if (verification) {
                    System.out.println("O processo (" + process + ") foi desalocado com sucesso!");

                    System.out.println("");
                    // print buddy
                    this.printMemoryBuddy(rootBuddyNode, 0);

                    System.out.println("---------------------------");
                }      
            }
        }
    }

    // Alocação dos métodos variáveis
    public void startAllocationVariable(int memorySize, List<String> inputs, int policy) {
        int[] memory = new int[memorySize];
        
        WorstFit worstFit = new WorstFit();
        CircularFit circularFit = new CircularFit();
        
        for (String line : inputs) {
            String[] parts = line.split("[(),]");

            String command = parts[0].trim();
            String process = parts[1].trim();

            if (command.equals("IN")) {
                int processSize = Integer.parseInt(parts[2].trim());
                boolean success = policy == 1 ? worstFit.putInMemoryWorstFit(memory, process, processSize) 
                        : circularFit.putInMemoryCircularFit(memory, process, processSize);

                if (success) {
                    System.out.println(
                            "O processo (" + process + ", " + processSize + ") foi alocado com sucesso!");
                } else {
                    System.out.println(
                            "ESPAÇO INSUFICIENTE DE MEMÓRIA para o processo (" + process + ", " + processSize + ")");
                }

                // print variavel
                this.printMemory(memory, memorySize);

            } else if (command.equals("OUT")) {
                boolean verification = policy == 1 ? worstFit.outInMemoryWorstFit(memory, process)
                        : circularFit.outInMemoryCircularFit(memory, process);
                               
                if (verification) {
                    System.out.println("O processo (" + process + ") foi desalocado com sucesso!");

                    // print variavel
                    this.printMemory(memory, memorySize);
                }
            }
        }
    }

    // print memory variable
    public void printMemory(int[] memory, int memorySize) {
        int freeMemory = 0;
        int allocatedMemory = 0;

        // valida o que é memória livre e alocada.
        for (int i : memory) {
            if (i == 0) {
                freeMemory++;
            } else {
                allocatedMemory++;
            }
        }

        System.out.println("\nMemória livre: " + freeMemory + " unidades");
        System.out.println("Memória alocada: " + allocatedMemory + " unidades");
        System.out.println("Memória total: " + memorySize + " unidades\n");

        // Realiza prints para facilitar a visualização da memória.
        System.out.print("Estado atual da memória: | ");
        for (int i : memory) {
            System.out.print(i != 0 ? "#" : ".");
        }
        System.out.println(" |");

        System.out.println("---------------------------");
    }

    // print memory buddy
    public void printMemoryBuddy(BuddyNode buddyNode, int layer) {
        if (buddyNode == null) {
            return;
        }

        // Para identar o codigo com base na camada que o buddy esta
        String space = "  ".repeat(layer);
        System.out.println(space + "| Memória Total: " + buddyNode.size + ", Memória Restante: " + buddyNode.remaingSize + ", Processo: " 
                + (buddyNode.process != null ? buddyNode.process : "Nenhum") + ", Status: " + (buddyNode.isFree ? "Livre" : "Dividido") + " |");

        printMemoryBuddy(buddyNode.leftChild, layer + 1);
        printMemoryBuddy(buddyNode.rightChild, layer + 1);
    }
}