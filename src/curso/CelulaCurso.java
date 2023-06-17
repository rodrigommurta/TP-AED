package curso;
public class CelulaCurso {
        public Curso elemento;
        public CelulaCurso proximo;
    
        public CelulaCurso(){}

        public CelulaCurso(Curso curso) {
            this.elemento = curso;
            this.proximo = null;
        }
    }

