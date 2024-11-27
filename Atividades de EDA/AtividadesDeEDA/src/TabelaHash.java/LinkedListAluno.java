public class LinkedListAluno {
    private AlunoNo cabeca;
    private AlunoNo calda;
    private int tamanho;

    public LinkedList() {
        this.cabeca = null;
        this.calda = null;
        this.tamanho = 0;
    }

    public void add(Aluno valor) {
        AlunoNo novoNo = new AlunoNo(valor);
        if (cabeca == null) {
            cabeca = novoNo;
            calda = novoNo;
        } else {
            this.calda.setProx(novoNo);
            this.calda = novoNo;
        }
        this.tamanho++;

    }

    public void removeIndex(int index) {

        if (index < 0 || index >= size()) {
            System.out.println("Indice invalido. Falha na remoção.");
            return;
        }

        if (index == 0) {
            this.cabeca = this.cabeca.getProx();
        } else {
            AlunoNo corrente = this.cabeca;
            for (int i = 0; i < index - 1; i++) {
                corrente = corrente.getProx();
            }
            corrente.setProx(corrente.getProx().getProx());
        }
        this.tamanho--;
        System.out.println("O indice " + index + " foi removido.");
    }

    public int size() {
        return this.tamanho;
    }

    public Aluno get(int index) {
        if (index < 0 || index > this.tamanho) {
            System.out.println("Indice invalido.");
        } else {
            AlunoNo atual = cabeca;
            for (int i = 0; i < index - 1; i++) {
                atual = atual.getProx();
            }
            return atual.getValor();
        }
        return null;
    }

    public void mostrarLista() {
        AlunoNo atual = cabeca;
        while (atual != null) {
            System.out.print(atual.getValor() + "-> ");
            atual = atual.getProx();
        }
        System.out.println();
    }

}
