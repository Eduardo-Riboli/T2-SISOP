import java.util.*;

public class Variable {
    private int pointer = 0; // Ponteiro para Circular-Fit
    private Map<String, List<Integer>> pageTable = new HashMap<>(); // Tabela de páginas

    public void circularFit(int memorySize, List<String> inputs) {
        // Inicializa a memória com 0s
        int[] memory = new int[memorySize];
        
        for (String line : inputs) {
            String[] parts = line.split("[(),]");
            String command = parts[0].trim();
            String process = parts[1].trim();
            if (command.equals("IN")) {
                int size = Integer.parseInt(parts[2].trim());
                if (!allocateCircularFit(memory, process, size)) {
                    System.out.println("ESPAÇO INSUFICIENTE DE MEMÓRIA para " + process);
                } else {
                    System.out.println("Processo " + process + " alocado com sucesso!");
                }
            } else if (command.equals("OUT")) {
                deallocate(memory, process);
                System.out.println("Processo " + process + " desalocado com sucesso!");
            }
            printMemory(memory, memorySize);
        }
    }

    private boolean allocateCircularFit(int[] memory, String process, int size) {
        int memorySize = memory.length;
        int start = pointer;
        int freeSpace = 0;
        List<Integer> allocatedPositions = new ArrayList<>();
        
        while (true) {
            // Verifica se há espaço suficiente a partir da posição atual
            while (pointer < memorySize && memory[pointer] == 0) {
                freeSpace++;
                allocatedPositions.add(pointer);
                if (freeSpace == size) {
                    // Encontra um bloco livre adequado
                    for (int pos : allocatedPositions) {
                        memory[pos] = 1; // Marca a memória como ocupada
                    }
                    pageTable.put(process, new ArrayList<>(allocatedPositions)); // Registra as posições alocadas
                    pointer = (pointer + 1) % memorySize; // Avança o ponteiro
                    return true;
                }
                pointer = (pointer + 1) % memorySize;
            }
            // Se a posição atual não for livre, reseta freeSpace e avança o ponteiro
            freeSpace = 0;
            allocatedPositions.clear();
            pointer = (pointer + 1) % memorySize;

            // Se voltarmos ao ponto de partida, a memória está cheia ou fragmentada
            if (pointer == start) {
                return false;
            }
        }
    }

    private void deallocate(int[] memory, String process) {
        List<Integer> positions = pageTable.get(process);
        if (positions != null) {
            for (int pos : positions) {
                memory[pos] = 0; // Marca a memória como livre
            }
            pageTable.remove(process); // Remove o processo da tabela
        }
    }

    private void printMemory(int[] memory, int memorySize) {
        int freeMemory = 0;
        int allocatedMemory = 0;

        for (int i : memory) {
            if (i == 0) {
                freeMemory++;
            } else {
                allocatedMemory++;
            }
        }

        System.out.print("Estado atual da memória: | ");
        for (int i : memory) {
            System.out.print(i == 0 ? "." : "#");
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



