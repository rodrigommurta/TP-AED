import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ProcessoSeletivo {
    static Lista[] cursos;
    static Lista classificados;

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            List<String> linhas = Files.readAllLines(Paths.get("G:\\AEDS 2023\\trabalho\\teste.txt"));

            int numeroCursos = Integer.parseInt(linhas.get(0));
            int numeroCandidatos = Integer.parseInt(linhas.get(1));

            cursos = new Lista[numeroCursos];
            for (int i = 0; i < numeroCursos; i++) {
                cursos[i] = new Lista();
            }

            for (int i = 0; i < numeroCandidatos; i++) {
                String[] infoCandidato = linhas.get(i + 2).split(";");
                String nome = infoCandidato[0];
                double notaRedacao = Double.parseDouble(infoCandidato[1]);
                double notaMatematica = Double.parseDouble(infoCandidato[2]);
                double notaLinguagens = Double.parseDouble(infoCandidato[3]);
                int opcao1 = Integer.parseInt(infoCandidato[4]);
                int opcao2 = Integer.parseInt(infoCandidato[5]);
                Candidato candidato = new Candidato(nome, notaRedacao, notaMatematica, notaLinguagens, opcao1, opcao2);

                int primeiraOpcao = candidato.codCursoOp1;
                int segundaOpcao = candidato.codCursoOp2;

                if (primeiraOpcao >= 0 && primeiraOpcao < numeroCursos) {
                    cursos[primeiraOpcao].inserir(i, cursos[primeiraOpcao].tamanho());
                }

                if (segundaOpcao >= 0 && segundaOpcao < numeroCursos && segundaOpcao != primeiraOpcao) {
                    cursos[segundaOpcao].inserir(i, cursos[segundaOpcao].tamanho());
                }
            }

            classificados = new Lista();
            for (int i = 0; i < cursos.length; i++) {
                ordenarCandidatosPorNota(cursos[i]);
                preencherVagas(cursos[i]);
                adicionarClassificados(cursos[i]);
            }

            String arquivoSaida = "classificados.txt";
            escreverClassificadosArquivo(arquivoSaida);

            System.out.println("Processo seletivo concluído. Classificados salvos em " + arquivoSaida);
        } catch (IOException e) {
            System.out.println("Erro ao ler ou gravar arquivo.");
        } catch (NoSuchElementException e) {
            System.out.println("Entrada inválida.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void ordenarCandidatosPorNota(Lista lista) {
        int[] vetor = new int[lista.tamanho()];

        for (int i = 0; i < vetor.length; i++) {
            try {
                vetor[i] = lista.remover(i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        quicksort(vetor, 0, vetor.length - 1);

        for (int i = vetor.length - 1; i >= 0; i--) {
            try {
                lista.inserir(vetor[i], 0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void quicksort(int[] array, int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[(esq + dir) / 2];
        
        while (i <= j) {
            while (array[i] < pivo) {
                i++;
            }
            while (array[j] > pivo) {
                j--;
            }
            if (i <= j) {
                trocar(array, i, j);
                i++;
                j--;
            }
        }
        
        if (esq < j) {
            quicksort(array, esq, j);
        }
        if (i < dir) {
            quicksort(array, i, dir);
        }
    }

    public static void trocar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void preencherVagas(Lista lista) {
        while (lista.tamanho() > cursos[lista.tamanho() - 1].tamanho()) {
            try {
                lista.removerFim();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void adicionarClassificados(Lista lista) {
        for (int i = 0; i < lista.tamanho(); i++) {
            try {
                int indice = lista.remover(i);
                classificados.inserirInicio(indice);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void escreverClassificadosArquivo(String arquivo) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));
        writer.write("Classificados no processo seletivo:\n");
        for (Celula i = classificados.primeiro.prox; i != null; i = i.prox) {
            writer.write(i.elemento + "\n");
        }
        writer.close();
    }
}
