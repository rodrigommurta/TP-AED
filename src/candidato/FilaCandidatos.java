package candidato;
public class FilaCandidatos {
    private CelulaCandidato primeiro, ultimo;

    public FilaCandidatos() {
        primeiro = new CelulaCandidato();
        ultimo = primeiro;
    }

    /**
     * 
     * Insere um candidato no fim da fila.
     * 
     */
    public void inserir(Candidato candidato) {
        ultimo.proximo = new CelulaCandidato(candidato);
        ultimo = ultimo.proximo;
    }

    /**
     * 
     * Remove um candidato do início da fila.
     * 
     * @return Retorna o candidato removido
     * 
     */
    public Candidato remover() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro, a fila está vazia!");
        }
        CelulaCandidato celulaTemporaria = primeiro;

        primeiro = primeiro.proximo;
        
        Candidato elemento = primeiro.elemento;
        
        celulaTemporaria.proximo = null;
        celulaTemporaria = null;

        return elemento;
    }

    /**
     * 
     * @return Retorna a fila de candidatos
     * 
     */
    public void mostrar() {
        for (CelulaCandidato atual = primeiro.proximo; atual != null; atual = atual.proximo) {
            System.out.print(atual.elemento.toString() + "\n");
        }
    }
}
