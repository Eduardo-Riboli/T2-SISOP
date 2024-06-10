import java.util.Scanner;

public class App {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int politica = 0;

        // tamanho da memoria
        System.out.print("\nDigite o tamanho da memória (Potência de 2): ");
        int tamanhoMemoria = scanner.nextInt();

        // escolha a particao
        System.out.print("Digite qual é a partição que deseja executar "
                + "=> (1) partição variável | (2) partição definidas: ");

        int particao = scanner.nextInt();

        // caso variavel, worst-fit ou circular-fit
        if (particao == 1) {
            System.out.print("Digite qual é a politica de alocação a ser empregada "
                    + "=> (1) worst-fit | (2) circular-fit: ");

            politica = scanner.nextInt();
        }

        System.out.println(tamanhoMemoria);
        System.out.println(particao);
        System.out.println(politica);

        scanner.close();
    }
}