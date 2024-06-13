import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variable {
    private int pointer = 0;
    private Map<String, List<Integer>> pageTable = new HashMap<>();

    public void circularFit(int memorySize, List<String> inputs) {
        int[] memory = new int[memorySize];

        for (String line : inputs) {
            String[] parts = line.split("[(),]");

            String command = parts[0].trim();
            String process = parts[1].trim();

            if (command.equals("IN")) {
                int processSize = Integer.parseInt(parts[2].trim());
                boolean success = putInMemoryCircularFit(memory, process, processSize);

                if (success) {
                    System.out.println(
                            "O processo (" + process + ", " + processSize + ") foi alocado com sucesso!");
                } else {
                    System.out.println(
                            "ESPAÇO INSUFICIENTE DE MEMÓRIA para o processo (" + process + ", " + processSize + ")");
                }

                printMemory(memory, memorySize);

            } else if (command.equals("OUT")) {
                boolean verification = outInMemoryCircularFit(memory, process);

                if (verification) {
                    System.out.println("O processo (" + process + ") foi desalocado com sucesso!");

                    printMemory(memory, memorySize);
                }
            }
        }
    }

    private boolean putInMemoryCircularFit(int[] memory, String process, int size) {
        int memoryLength = memory.length;
        int startMemory = pointer;
        int freeSpace = 0;
        List<Integer> allocatedPositions = new ArrayList<>();

        // Validação para ver se tem local para esse processo.
        int totalFreeSpace = 0;
        for (int i = 0; i < memoryLength; i++) {
            if (memory[i] == 0) {
                totalFreeSpace++;
            }
        }
        if (size > totalFreeSpace) {
            return false;
        }

        // Se há local, valida as posições consecutivas para a alocação do mesmo.
        while (true) {
            while (pointer < memoryLength && memory[pointer] == 0) {
                freeSpace++;
                allocatedPositions.add(pointer);
                if (freeSpace == size) {
                    for (int pos : allocatedPositions) {
                        memory[pos] = 1;
                    }
                    pageTable.put(process, new ArrayList<>(allocatedPositions));
                    pointer = (pointer + 1) % memoryLength;

                    return true;
                }
                pointer = (pointer + 1) % memoryLength;
            }

            freeSpace = 0;
            allocatedPositions.clear();
            pointer = (pointer + 1) % memoryLength;

            // Caso o ponteiro volte para a primeira posição da memória, significa que não
            // achou
            // uma posição válida, ou seja, retorna falso.
            if (pointer == startMemory) {
                return false;
            }
        }
    }

    // Remove o processo da memória, com base nas posições da tabela de página.
    private boolean outInMemoryCircularFit(int[] memory, String process) {
        List<Integer> positions = pageTable.get(process);
        if (positions != null) {
            for (int pos : positions) {
                memory[pos] = 0;
            }
            pageTable.remove(process);
            return true;
        } else {
            return false;
        }
    }

    private void printMemory(int[] memory, int memorySize) {
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

        // Realiza prints para facilitar a visualização da memória.
        System.out.print("Estado atual da memória: | ");
        for (int i : memory) {
            System.out.print(i != 0 ? "#" : ".");
        }
        System.out.println(" |");

        System.out.println("Memória livre: " + freeMemory + " unidades");
        System.out.println("Memória alocada: " + allocatedMemory + " unidades");
        System.out.println("Memória total: " + memorySize + " unidades");
        System.out.println("---------------------------");
    }

    public void worstFit(int memorySize, List<String> inputs) {
        for (String line : inputs) {

        }
    }
}
