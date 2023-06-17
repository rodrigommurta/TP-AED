package candidato;
public class CelulaCandidato {
    public Candidato elemento;
        public CelulaCandidato proximo;
    
        public CelulaCandidato(){}

        public CelulaCandidato(Candidato candidato) {
            this.elemento = candidato;
            this.proximo = null;
        }
}
