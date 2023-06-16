import java.util.NoSuchElementException;

public class Fila {
    private Celula primeiro, ultimo;

    public Fila() {
        primeiro = new Celula(-1); // Célula sentinela
        ultimo = primeiro;
    }

    public void inserir(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int remover() {
        if (primeiro == ultimo) {
            throw new NoSuchElementException("A fila está vazia.");
        }
        Celula tmp = primeiro.prox;
        primeiro.prox = tmp.prox;
        if (tmp == ultimo) {
            ultimo = primeiro;
        }
        int elemento = tmp.elemento;
        tmp = null;
        return elemento;
    }

    public void mostrar() {
        Celula atual = primeiro.prox;
        while (atual != null) {
            System.out.print(atual.elemento + " ");
            atual = atual.prox;
        }
        System.out.println();
    }
}
