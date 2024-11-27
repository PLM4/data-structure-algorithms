package Arvore;

public class Arvore {
    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    public void insert(int valor) {
        No novoNo = new No(valor);

        if (raiz == null) {
            this.raiz = novoNo;
        } else {
            No atual = this.raiz;
            while (true) {
                if (novoNo.getValor() < atual.getValor()) {
                    if (atual.getEsquerda() != null) {
                        atual = atual.getEsquerda();
                    } else {
                        atual.setEsquerda(novoNo);
                        break;
                    }
                } else {
                    if (atual.getDireita() != null) {
                        atual = atual.getDireita();
                    } else {
                        atual.setDireita(novoNo);
                        break;
                    }
                }
            }
        }
    }

    public No removerMaior(No raiz) {
        if (raiz == null) {
            return null;
        }

        if (raiz.getDireita() == null) {
            return raiz.getEsquerda();
        }

        raiz.setDireita(removerMaior(raiz.getDireita()));
        return raiz;
    }

    public No removerMenor(No raiz) {
        if (raiz == null) {
            return null;
        }

        if (raiz.getEsquerda() == null) {
            return raiz.getDireita();
        }

        raiz.setEsquerda(removerMenor(raiz.getEsquerda()));
        return raiz;
    }

    public int procuraValor(int valor) {
        return procurarValorRecursico(raiz, valor);
    }

    public int procurarValorRecursico(No atual, int valor) {
        if (atual == null) {
            return -1;
        }
        if (valor == atual.getValor()) {
            return atual.getValor();
        } else if (valor < atual.getValor()) {
            return procurarValorRecursico(atual.getEsquerda(), valor);
        } else {
            return procurarValorRecursico(atual.getDireita(), valor);
        }
    }

    public void emOrdem(No atual) {
        if (atual != null) {
            emOrdem(atual.getEsquerda());
            System.out.println(atual.getValor());
            emOrdem(atual.getDireita());
        }
    }

    public void preOrdem(No atual) {
        if (atual != null) {
            System.out.println(atual.getValor());
            preOrdem(atual.getEsquerda());
            preOrdem(atual.getDireita());
        }
    }

    public void posOrdem(No atual) {
        if (atual != null) {
            posOrdem(atual.getEsquerda());
            posOrdem(atual.getDireita());
            System.out.println(atual.getValor());
        }
    }
}