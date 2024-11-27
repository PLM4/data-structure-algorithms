

public class ArvoreAVL {
    private NoAVL raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    public NoAVL getRaiz() {
        return raiz;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public void insert(int valor, NoAVL atual) {
        if (isEmpty()) {
            NoAVL novoNo = new NoAVL(valor);
            this.raiz = novoNo;
            defineFb(raiz);
        } else {
            if (valor > atual.getValor()) {
                if (atual.getDireita() == null) {
                    NoAVL novo = new NoAVL(valor);
                    atual.setDireita(novo);
                    novo.setPai(atual);
                    defineFb(raiz);
                    raiz = balanceia(raiz);
                } else {
                    insert(valor, atual.getDireita());
                }
            } else if (valor < atual.getValor()) {
                if (atual.getEsquerda() == null) {
                    NoAVL novo = new NoAVL(valor);
                    atual.setEsquerda(novo);
                    novo.setPai(atual);
                    defineFb(raiz);
                    raiz = balanceia(raiz);
                } else {
                    insert(valor, atual.getEsquerda());
                }
            } else {
                System.out.println("Impossivel inserir!");
            }
        }
    }

    public void remover() {

    }

    public int altura(NoAVL atual) {
        if (atual == null) {
            return -1;
        }
        if (atual.getDireita() == null && atual.getEsquerda() == null) {
            return 0;
        } else if (atual.getEsquerda() == null) {
            return 1 + altura(atual.getDireita());
        } else if (atual.getDireita() == null) {
            return 1 + altura(atual.getEsquerda());
        } else {
            if (altura(atual.getEsquerda()) > altura(atual.getDireita())) {
                return 1 + altura(atual.getEsquerda());
            } else {
                return 1 + altura(atual.getDireita());
            }
        }
    }   

    public void defineFb(NoAVL atual) {
        atual.setBalanceamento(altura(atual.getDireita()) - altura(atual.getEsquerda()));
        if (atual.getDireita() != null) {
            defineFb(atual.getDireita());
        }
        if (atual.getEsquerda() != null) {
            defineFb(atual.getEsquerda());
        }
    }

    public NoAVL rotacaoDireita(NoAVL atual) {
        NoAVL aux = atual.getEsquerda();

        if (aux == null) {
            return atual;
        }

        aux.setPai(atual.getPai());

        if (aux.getDireita() != null) {
            aux.getDireita().setPai(atual);
        }

        atual.setPai(aux);
        atual.setEsquerda(aux.getDireita());
        aux.setDireita(atual);

        if (aux.getPai() != null) {
            if (aux.getPai().getDireita() == atual) {
                aux.getPai().setDireita(aux);
            } else if (aux.getPai().getEsquerda() == atual) {
                aux.getPai().setEsquerda(aux);
            }
        }

        defineFb(aux);
        return aux;
    }

    public NoAVL rotacaoEsquerda(NoAVL atual) {
        NoAVL aux = atual.getDireita();

        if (aux == null) {
            return atual;
        }

        aux.setPai(atual.getPai());

        if (aux.getEsquerda() != null) {
            aux.getEsquerda().setPai(atual);
        }

        atual.setPai(aux);
        atual.setDireita(aux.getEsquerda());
        aux.setEsquerda(atual);

        if (aux.getPai() != null) {
            if (aux.getPai().getEsquerda() == atual) {
                aux.getPai().setEsquerda(aux);
            } else if (aux.getPai().getDireita() == atual) {
                aux.getPai().setDireita(aux);
            }
        }

        defineFb(aux);
        return aux;
    }

    public NoAVL rotacaoDuplaDireita(NoAVL atual) {
        atual.setEsquerda(rotacaoEsquerda(atual.getEsquerda()));
        return rotacaoDireita(atual);
    }

    public NoAVL rotacaoDuplaEsquerda(NoAVL atual) {
        atual.setDireita(rotacaoDireita(atual.getDireita()));
        return rotacaoEsquerda(atual);
    }

    public NoAVL balanceia(NoAVL atual) {
        if (atual.getBalanceamento() == 2) {
            if (atual.getEsquerda() != null && atual.getEsquerda().getBalanceamento() >= 0) {
                atual = rotacaoDireita(atual);
            } else if (atual.getEsquerda() != null && atual.getEsquerda().getBalanceamento() < 0) {
                atual = rotacaoDuplaDireita(atual);
            }
        } else if (atual.getBalanceamento() == -2) {
            if (atual.getDireita() != null && atual.getDireita().getBalanceamento() >= 0) {
                atual = rotacaoEsquerda(atual);
            } else if (atual.getDireita() != null && atual.getDireita().getBalanceamento() < 0) {
                atual = rotacaoDuplaEsquerda(atual);
            }
        }

        if (atual.getDireita() != null) {
            balanceia(atual.getDireita());
        }
        if (atual.getEsquerda() != null) {
            balanceia(atual.getEsquerda());
        }
        return atual;
    }

    public void printaArvore(NoAVL atual) {
        if (atual != null) {
            printaArvore(atual.getEsquerda());
            System.out.println(atual.getValor());
            printaArvore(atual.getDireita());
        }
    }
}
// public NoAVL removerMaior(NoAVL raiz) {
// if (raiz == null) {
// return null;
// }

// if (raiz.getDireita() == null) {
// return raiz.getEsquerda();
// }

// raiz.setDireita(removerMaior(raiz.getDireita()));
// return raiz;
// }

// public NoAVL removerMenor(NoAVL raiz) {
// if (raiz == null) {
// return null;
// }

// if (raiz.getEsquerda() == null) {
// return raiz.getDireita();
// }

// raiz.setEsquerda(removerMenor(raiz.getEsquerda()));
// return raiz;
// }

// public int procuraValor(int valor) {
// return procurarValorRecursico(raiz, valor);
// }

// public int procurarValorRecursico(NoAVL atual, int valor) {
// if (atual == null) {
// return -1;
// }
// if (valor == atual.getValor()) {
// return atual.getValor();
// } else if (valor < atual.getValor()) {
// return procurarValorRecursico(atual.getEsquerda(), valor);
// } else {
// return procurarValorRecursico(atual.getDireita(), valor);
// }
// }

// public void emOrdem(NoAVL atual) {
// if (atual != null) {
// emOrdem(atual.getEsquerda());
// System.out.println(atual.getValor());
// emOrdem(atual.getDireita());
// }
// }

// public void preOrdem(NoAVL atual) {
// if (atual != null) {
// System.out.println(atual.getValor());
// preOrdem(atual.getEsquerda());
// preOrdem(atual.getDireita());
// }
// }

// public void posOrdem(NoAVL atual) {
// if (atual != null) {
// posOrdem(atual.getEsquerda());
// posOrdem(atual.getDireita());
// System.out.println(atual.getValor());
// }
// }
