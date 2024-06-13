import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorstFit {
    private Map<String, List<Integer>> pageTable = new HashMap<>();

    public void start(int memorySize, List<String> inputs) {
        int[] memory = new int[memorySize];

        for (String line : inputs) {
            String[] parts = line.split("[(),]");

            String command = parts[0].trim();
            String process = parts[1].trim();

            if (command.equals("IN")) {
                int processSize = Integer.parseInt(parts[2].trim());
                boolean success = putInMemoryWorstFit(memory, process, processSize);

                if (success) {
                    System.out.println(
                            "O processo (" + process + ", " + processSize + ") foi alocado com sucesso!");
                } else {
                    System.out.println(
                            "ESPAÇO INSUFICIENTE DE MEMÓRIA para o processo (" + process + ", " + processSize + ")");
                }

                printMemory(memory, memorySize);

            } else if (command.equals("OUT")) {
                boolean verification = outInMemoryWorstFit(memory, process);

                if (verification) {
                    System.out.println("O processo (" + process + ") foi desalocado com sucesso!");

                    printMemory(memory, memorySize);
                }
            }
        }
    }

    private boolean putInMemoryWorstFit(int[] memory, String process, int size) {
        int memoryLength = memory.length;
        int initialPoint = 0;
        int maxMemoryFind = 0;
        int freeSpace = 0;
        List<Integer> allocatedPositions = new ArrayList<>();
        List<Integer> allocatedPositionsFinal = new ArrayList<>();

        // Verifica a memória para achar o maior espaço disponível.
        for (int i = 0; i < memoryLength; i++) {
            if (memory[i] == 0) {
                freeSpace++;

                if (freeSpace == 1) {
                    initialPoint = i;
                }

            } else {
                if (maxMemoryFind < freeSpace) {
                    maxMemoryFind = freeSpace;
                    allocatedPositions.clear();

                    for (int j = initialPoint; j < initialPoint + freeSpace; j++) {
                        allocatedPositions.add(j);
                    }
                }

                freeSpace = 0;
            }
        }

        // Caso ele tenha percorrido tudo, sem achar um espaço de memória ocupado, cai
        // nesse if
        // Para atualizar a memória máxima e inserir na memória
        if (freeSpace > maxMemoryFind) {
            maxMemoryFind = freeSpace;
            allocatedPositions.clear();

            for (int j = initialPoint; j < initialPoint + freeSpace; j++) {
                allocatedPositions.add(j);
            }
        }

        // Caso o espaço encontrado seja menor do que o espaço necessário, falha!
        if (size > maxMemoryFind) {
            return false;
        } else {
            // Caso contrário, aloca na memória e na tabela de páginas.
            for (int i = 0; i < size; i++) {
                memory[allocatedPositions.get(i)] = 1;
                allocatedPositionsFinal.add(allocatedPositions.get(i));
            }
            pageTable.put(process, new ArrayList<>(allocatedPositionsFinal));

            return true;
        }
    }

    // Remove o processo da memória, com base nas posições da tabela de página.
    private boolean outInMemoryWorstFit(int[] memory, String process) {
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

}
