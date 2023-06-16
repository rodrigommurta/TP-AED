public class Lista {
    Celula primeiro;
    private Celula ultimo;

    public Lista() {
        primeiro = new Celula(-1); // Célula sentinela
        ultimo = primeiro;
    }
    public void inserirFim(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int tamanho() {
        int tamanho = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            tamanho++;
        }
        return tamanho;
    }

    public void inserirInicio(int x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    public void inserir(int x, int pos) throws Exception {
        int tamanho = tamanho();
        if (pos < 0 || pos > tamanho) {
            throw new Exception("Posição inválida.");
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tamanho) {
            inserirFim(x);
        } else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = null;
            i = null;
        }
    }

    public int removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("A lista está vazia.");
        }
        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox);
        int elemento = ultimo.elemento;
        ultimo = i;
        ultimo.prox = null;
        i = null;
        return elemento;
    }
    public int removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("A lista está vazia.");
        }
        Celula tmp = primeiro.prox;
        int elemento = tmp.elemento;
        primeiro.prox = tmp.prox;
        tmp.prox = null;
        if (tmp == ultimo) {
            ultimo = primeiro;
        }
        tmp = null;
        return elemento;
    }

    public int remover(int pos) throws Exception {
        int elemento, tamanho = tamanho();
        if (primeiro == ultimo || pos < 0 || pos >= tamanho) {
            throw new Exception("Posição inválida.");
        } else if (pos == 0) {
            elemento = removerInicio();
        } else if (pos == tamanho - 1) {
            elemento = removerFim();
        } else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = i.prox;
            elemento = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            tmp = null;
            i = null;
        }
        return elemento;
    }

    public void mostrar() {
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println();
    }
    }
    

