import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CircularFit {
    private int pointer = 0;
    private Map<String, List<Integer>> pageTable = new HashMap<>();

    public boolean putInMemoryCircularFit(int[] memory, String process, int size) {
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
    public boolean outInMemoryCircularFit(int[] memory, String process) {
        List<Integer> positions = pageTable.get(process);
        boolean success = false;

        if (positions != null) {
            for (int pos : positions) {
                memory[pos] = 0;
            }
            pageTable.remove(process);
            success = true;
        } 

        return success;
    }
}
