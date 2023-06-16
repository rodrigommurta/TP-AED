public class Candidato {
    private String nome;
    private double notaRed;
    private double notaMat;
    private double notaLing;
    public int codCursoOp1;
    public int codCursoOp2;
    private double media;

    public Candidato(String nome, double notaRed, double notaMat, double notaLing, int codCursoOp1, int codCursoOp2) {
        this.nome = nome;
        this.notaRed = notaRed;
        this.notaMat = notaMat;
        this.notaLing = notaLing;
        this.codCursoOp1 = codCursoOp1;
        this.codCursoOp2 = codCursoOp2;
        this.media = calcularMedia();
    }

    public String getNome() {
        return nome;
    }

    public double getMedia() {
        return media;
    }

    private double calcularMedia() {
        return (notaRed + notaMat + notaLing) / 3.0;
    }
}
