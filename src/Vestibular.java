import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import candidato.Candidato;
import curso.Curso;
import curso.ListaCursos;

public class Vestibular {
    private ListaCursos listaCursos = new ListaCursos();
    private ArrayList<Candidato> listaCandidatos = new ArrayList<Candidato>();
    private int qtdCursos;
    private int qtdCandidatos;

    /**
     * 
     * Construtor padrão.
     * 
     */
    public Vestibular() {
    }

    /**
     * 
     * Realiza a leitura do arquivo de texto contendo a base de dados do vestibular.
     * 
     * @param nomeArq O nome do arquivo
     * 
     */
    public void lerEntrada(String nomeArq) {
        try {
            /// Para realizar os testes, é necessário alterar para o caminho correto do
            /// arquivo.
            Scanner arqLeit = new Scanner(
                    new FileInputStream(
                            "f:\\rodri\\Documents\\Projetos\\TP-AED\\src\\entrada.txt".replace("entrada", nomeArq)),
                    "UTF-8");

            String[] firstLine = arqLeit.nextLine().split(";");
            qtdCursos = Integer.parseInt(firstLine[0]);
            qtdCandidatos = Integer.parseInt(firstLine[1]);

            for (int x = 0; x < qtdCursos; x++) {
                if (arqLeit.hasNextLine()) {
                    lerCurso(arqLeit.nextLine());
                }
            }

            for (int x = 0; x < qtdCandidatos; x++) {
                if (arqLeit.hasNextLine()) {
                    lerCandidato(arqLeit.nextLine());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * Realiza a leitura de uma única linha do tipo Curso, gerando um novo objeto
     * Curso.
     * 
     * @param linhaCurso A linha que será lida
     * 
     */
    private void lerCurso(String linhaCurso) {
        String[] curso = linhaCurso.split(";");

        listaCursos.inserirFim(
                new Curso(
                        Integer.parseInt(curso[0]),
                        curso[1],
                        Integer.parseInt(curso[2])));
    }

    /**
     * 
     * Realiza a leitura de uma única linha do tipo Candidato, gerando um novo
     * objeto Candidato.
     * 
     * @param linhaCandidato A linha que será lida
     * 
     */
    private void lerCandidato(String linhaCandidato) {
        String[] candidato = linhaCandidato.split(";");

        listaCandidatos.add(
                new Candidato(
                        candidato[0],
                        Double.parseDouble(candidato[1]),
                        Double.parseDouble(candidato[2]),
                        Double.parseDouble(candidato[3]),
                        Integer.parseInt(candidato[4]),
                        Integer.parseInt(candidato[5])));
    }

    /**
     * 
     * Realiza a ordenação decrescente dos candidatos através do QuickSort com base
     * na média aritmética das notas.
     * 
     */
    public void ordenarCandidatos() {
        quicksort(listaCandidatos, 0, listaCandidatos.size() - 1);
    }

    /**
     * 
     * Realiza a distribuição dos candidatos pelos cursos disponíveis. Utiliza a
     * nota da redação como critério de desempate. Insere os candidatos na Lista de
     * Selecionados ou na Fila de Espera de cada curso.
     * 
     */
    public void calcularClassificacao() {
        desempate(listaCandidatos);

        for (Candidato candidato : listaCandidatos) {
            Curso cursoOpcao1 = listaCursos.pesquisar(candidato.getCodCursoOp1());
            Curso cursoOpcao2 = listaCursos.pesquisar(candidato.getCodCursoOp2());

            if (!cursoOpcao1.inserirListaSelecionados(candidato)) {
                cursoOpcao1.inserirFilaEspera(candidato);

                if (!cursoOpcao2.inserirListaSelecionados(candidato)) {
                    cursoOpcao2.inserirFilaEspera(candidato);
                }
            }
        }
    }

    /**
     * 
     * Registra o resultado da classificação em um arquivo de texto.
     * 
     * @param nomeArq O nome do arquivo de texto
     * 
     */
    public void divulgarResultado(String nomeArq) {
        try {
            /// Para realizar os testes, é necessário alterar para o caminho correto da
            /// escrita arquivo.
            Formatter arqEscrita = new Formatter(
                    "f:\\rodri\\Documents\\Projetos\\TP-AED\\src\\saida.txt".replace("saida", nomeArq), "UTF-8");

            arqEscrita.format(listaCursos.mostrar());

            arqEscrita.close();
        } catch (IOException e) {
            System.out.println("Erro na abertura do arquivo:" + e.getMessage());
        }
    }

    /**
     * 
     * Método de ordenação QuickSort. Classifica os candidatos na ordem decrescente
     * das médias das notas obtidas.
     * 
     * @param array Lista dos candidatos a serem ordenados
     * @param first A primeira posição da lista
     * @param last  A última posição da lista
     * @return Retorna a lista de candidatos
     * 
     */
    private ArrayList<Candidato> quicksort(ArrayList<Candidato> array, int first, int last) {
        int i = first, j = last;
        double pivo = array.get((first + last) / 2).getMedia();

        while (i <= j) {
            while (array.get(i).getMedia() > pivo)
                i++;

            while (array.get(j).getMedia() < pivo)
                j--;

            if (i <= j) {
                trocar(i, j, array);

                i++;
                j--;
            }
        }

        if (first < j)
            quicksort(array, first, j);

        if (i < last)
            quicksort(array, i, last);

        return array;
    }

    /**
     * 
     * Método auxiliar do QuickSort. Realiza a troca de posição entre dois elementos
     * da lista.
     * 
     * @param array Lista dos candidatos
     * @param i     A posição [i] a ser trocada
     * @param j     A posição [j] a ser trocada
     * 
     */
    private void trocar(int i, int j, ArrayList<Candidato> array) {
        Candidato temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    /**
     * 
     * Realiza o desempate entre candidatos. Em caso de empate, utiliza a maior nota
     * de redação para definir o melhor colocado.
     * 
     * @param array Lista dos candidatos a serem ordenados
     * 
     */
    private void desempate(ArrayList<Candidato> array) {
        for (int x = 0; x < array.size() - 1; x++) {
            int i = x, j = i + 1;
            if (array.get(i).getMedia().compareTo(array.get(j).getMedia()) == 0) {
                if (array.get(i).getNotaRed() < array.get(j).getNotaRed()) {
                    trocar(i, j, array);
                }
            }
        }

    }
}
