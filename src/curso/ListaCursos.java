package curso;

public class ListaCursos {
    private CelulaCurso primeiro, ultimo;
    private int tamanho;

    public ListaCursos() {
        primeiro = new CelulaCurso();
        ultimo = primeiro;
        tamanho = 0;
    }

    /**
     * 
     * Insere um curso no início da lista.
     * 
     * @param curso   Curso a ser inserido
     * 
     */
    public void inserirInicio(Curso curso) {
        CelulaCurso celulaTemporaria = new CelulaCurso(curso);

        celulaTemporaria.proximo = primeiro.proximo;
        primeiro.proximo = celulaTemporaria;

        if (primeiro == ultimo) {
            ultimo = celulaTemporaria;
        }

        celulaTemporaria = null;
        tamanho++;
    }

    /**
     * 
     * Insere um curso no final da lista.
     * 
     * @param curso   Curso a ser inserido
     * 
     */
    public void inserirFim(Curso curso) {
        if (primeiro == null) {
            ultimo.proximo = new CelulaCurso(curso);
            ultimo = ultimo.proximo;
        } else
            inserirInicio(curso);

        tamanho++;
    }

    /**
     * 
     * Insere um curso na lista em qualquer posição válida.
     * 
     * @param curso   Curso a ser inserido
     * @param posicao Posição da lista a ser inserida
     * 
     */
    public void inserir(Curso curso, int posicao) throws Exception {
        if (posicao < 0 || posicao > tamanho) {
            throw new Exception("Erro, posição inválida!");
        } else if (posicao == 0) {
            inserirInicio(curso);
        } else if (posicao == tamanho) {
            inserirFim(curso);
        } else {
            CelulaCurso i = primeiro;

            for (int j = 0; j < posicao; j++, i = i.proximo)
                ;

            CelulaCurso celulaTemporaria = new CelulaCurso(curso);

            celulaTemporaria.proximo = i.proximo;
            i.proximo = celulaTemporaria;

            celulaTemporaria = null;
            i = null;

            tamanho++;
        }
    }

    /**
     * 
     * Remove um curso do início da lista.
     * 
     * @return Retorna o curso removido
     * 
     */
    public Curso removerInicio() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Erro, a lista está vazia!");

        CelulaCurso celulaTemporaria = primeiro;

        primeiro = primeiro.proximo;

        Curso elemento = primeiro.elemento;

        celulaTemporaria.proximo = null;
        celulaTemporaria = null;

        tamanho--;

        return elemento;
    }

    /**
     * 
     * Remove um curso do fim da lista.
     * 
     * @return Retorna o curso removido
     * 
     */
    public Curso removerFim() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Erro, a lista está vazia!");

        CelulaCurso celulaAtual;
        for (celulaAtual = primeiro; celulaAtual.proximo != ultimo; celulaAtual = celulaAtual.proximo)
            ;

        Curso elemento = ultimo.elemento;
        ultimo = celulaAtual;

        ultimo.proximo = null;
        celulaAtual = null;

        tamanho--;

        return elemento;
    }

    /**
     * 
     * Remove um curso da lista em qualquer posição válida.
     * 
     * @param posicao Posição da lista a ser removida
     * @return Retorna o curso removido
     * 
     */
    public Curso remover(int posicao) throws Exception {
        Curso elemento;

        if (primeiro == ultimo) {
            throw new Exception("Erro, a lista está vazia!");
        } else if (posicao < 0 || posicao >= tamanho) {
            throw new Exception("Erro, posição inválida!");
        } else if (posicao == 0) {
            elemento = removerInicio();
        } else if (posicao == tamanho - 1) {
            elemento = removerFim();
        } else {
            CelulaCurso i = primeiro;
            for (int j = 0; j < posicao; j++, i = i.proximo)
                ;

            CelulaCurso celulaTemporaria = i.proximo;

            elemento = celulaTemporaria.elemento;

            i.proximo = celulaTemporaria.proximo;

            celulaTemporaria.proximo = null;
            celulaTemporaria = null;
            i = null;

            tamanho--;
        }
        return elemento;
    }

    /**
     * 
     * Pesquisa um curso na lista pelo código do curso.
     * 
     * @param codigoCurso Código do curso procurado
     * @return Retorna o curso caso encontrado
     * 
     */
    public Curso pesquisar(int codigoCurso) {
        CelulaCurso i = primeiro.proximo;

        while (i.elemento.getCodigo() != codigoCurso && i.proximo != null) {
            i = i.proximo;
        }

        return i.elemento;
    }

    /**
     * 
     * @return Retorna a lista de cursos.
     * 
     */
    public String mostrar() {
        String result = "";
        for (CelulaCurso i = primeiro.proximo; i != null; i = i.proximo) {
            result += (i.elemento.toString() + "\n\n");
        }
        return result;
    }
}
