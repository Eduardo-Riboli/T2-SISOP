import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorstFit {
    private Map<String, List<Integer>> pageTable = new HashMap<>();

    public boolean putInMemoryWorstFit(int[] memory, String process, int size) {
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

    // Remove o processo da memória, com base nas posições respectivas da tabela de
    // página.
    public boolean outInMemoryWorstFit(int[] memory, String process) {
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
}
