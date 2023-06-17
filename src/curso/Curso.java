package curso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import candidato.Candidato;

public class Curso {
    private int codigo;
    private String nome;
    private int vagas;
    private ArrayList<Candidato> listaSelecionados = new ArrayList<Candidato>();
    private Queue<Candidato> filaEspera = new LinkedList<Candidato>();

    public Curso(int codigo, String nome, int vagas) {
        this.codigo = codigo;
        this.nome = nome;
        this.vagas = vagas;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getVagas() {
        return vagas;
    }

    /**
     * 
     * @return Retorna a nota de corte do curso. A nota de corte é a nota média mais
     *         baixa dentre os candidatos selecionados.
     * 
     */
    public Double getNotaCorte() {
        return listaSelecionados.get(listaSelecionados.size() - 1).getMedia();
    }

    /**
     * 
     * Insere um candidato na lista de selecionados. Verifica se a lista já está
     * cheia.
     * 
     * @param candidato Candidato a ser inserido
     * @return Retorna true se o candidato é inserido
     * 
     */
    public boolean inserirListaSelecionados(Candidato candidato) {
        if (listaSelecionados.size() < vagas) {
            listaSelecionados.add(candidato);
            return true;
        }

        return false;
    }

    /**
     * 
     * Insere um candidato na fila de espera.
     * 
     * @param candidato Candidato a ser inserido
     * 
     */
    public void inserirFilaEspera(Candidato candidato) {
        filaEspera.add(candidato);
    }

    /**
     * 
     * Remove um candidato da fila de espera.
     * 
     * @param candidato Candidato a ser removido
     * 
     */
    public void removerFilaEspera(Candidato candidato) {
        filaEspera.remove(candidato);
    }

    /**
     * 
     * @return Retorna o nome do curso e sua nota de corte, juntamente com os
     *         candidatos selecionados e os candidatos na fila de espera.
     * 
     */
    public String toString() {
        String curso = nome + " " + String.format("%.2f", getNotaCorte());
        String selecionados = "Selecionados", fila = "Fila de Espera";

        for (Candidato candidato : listaSelecionados) {
            selecionados += "\n" + candidato.toString();
        }

        for (Candidato candidato : filaEspera) {
            fila += "\n" + candidato.toString();
        }

        return curso + "\n" + selecionados + "\n" + fila;
    }
}
