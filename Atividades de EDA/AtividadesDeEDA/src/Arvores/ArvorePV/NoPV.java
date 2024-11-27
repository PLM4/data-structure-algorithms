public class NoPV {
    private int valor;
    private NoPV esquerda, direita, pai;
    private boolean cor; // true para vermelho, false para preto

    NoPV(int valor) {
        this.valor = valor;
        esquerda = direita = pai = null;
        cor = true; // nós novos são sempre vermelhos
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public NoPV getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoPV esquerda) {
        this.esquerda = esquerda;
    }

    public NoPV getDireita() {
        return direita;
    }

    public void setDireita(NoPV direita) {
        this.direita = direita;
    }

    public NoPV getPai() {
        return pai;
    }

    public void setPai(NoPV pai) {
        this.pai = pai;
    }

    public boolean isCor() {
        return cor;
    }

    public void setCor(boolean cor) {
        this.cor = cor;
    }
}

