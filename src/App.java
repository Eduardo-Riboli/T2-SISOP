import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int policy = 0;
        List<String> inputs = new LinkedList<>();

        // tamanho da memoria
        System.out.print("\nDigite o tamanho da memória (Potência de 2): ");
        int memorySize = scanner.nextInt();

        // escolha a particao
        System.out.print("Digite qual é a partição que deseja executar "
                + "=> (1) partição variável | (2) partição definidas: ");

        int partition = scanner.nextInt();

        // caso variavel, worst-fit ou circular-fit
        if (partition == 1) {
            System.out.print("Digite qual é a politica de alocação a ser empregada "
                    + "=> (1) worst-fit | (2) circular-fit: ");

            policy = scanner.nextInt();
        }

        // Digite o nome do arquivo a ser testado
        System.out.print("Digite o nome do arquivo a ser testado: ");
        String textFile = scanner.next();
        scanner.close();

        inputs = readFile(textFile);

        MemoryManagment os = new MemoryManagment();
        os.start(memorySize, partition, policy, inputs);
    }

    public static List<String> readFile(String fileName) {
        List<String> inputs = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                inputs.add(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo! Mensagem: " + e.getMessage());
        }

        return inputs;
    }
}