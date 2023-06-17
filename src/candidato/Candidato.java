package candidato;
public class Candidato {
    private String nome;
    private double notaRed;
    private double notaMat;
    private double notaLing;
    private int codCursoOp1;
    private int codCursoOp2;

    public Candidato(String nome, double notaRed, double notaMat, double notaLing, int codCursoOp1, int codCursoOp2) {
        this.nome = nome;
        this.notaRed = notaRed;
        this.notaMat = notaMat;
        this.notaLing = notaLing;
        this.codCursoOp1 = codCursoOp1;
        this.codCursoOp2 = codCursoOp2;
    }

    public String getNome() {
        return nome;
    }

    public double getNotaRed() {
        return notaRed;
    }

    public double getNotaMat() {
        return notaMat;
    }

    public double getNotaLing() {
        return notaLing;
    }

    public int getCodCursoOp1() {
        return codCursoOp1;
    }

    public int getCodCursoOp2() {
        return codCursoOp2;
    }

    /**
     * 
     * @return Retorna a média aritmética das notas obtidas pelo candidato.
     * 
     */
    public Double getMedia() {
        return (notaRed + notaMat + notaLing) / 3;
    }

    /**
     * 
     * @return Retorna o nome do candidato e sua nota média.
     * 
     */
    public String toString() {
        return nome + " " + String.format("%.2f", getMedia());
    }
}
