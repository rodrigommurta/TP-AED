import java.util.Scanner;

public class UniversidadeStark {
    public static void main(String[] args) {
        Vestibular vestibular = new Vestibular();
        Scanner input = new Scanner(System.in);

        System.out.println("UNIVERSIDADE STARK");
        System.out.println("Vestibular 2023");

        System.out.println("Digite o nome do arquivo com a base de dados");
        System.out.println("Favor desconsiderar a extensão");
        vestibular.lerEntrada(input.next());

        vestibular.ordenarCandidatos();
        vestibular.calcularClassificacao();
        vestibular.divulgarResultado("Resultado");

        input.close();

        System.out.println("O resultado final foi gerado e disponibilizado no arquivo de texto 'Resultado'");
        System.out.println("OBRIGADO PELA PARTICIPAÇÃO!");
    }
}
