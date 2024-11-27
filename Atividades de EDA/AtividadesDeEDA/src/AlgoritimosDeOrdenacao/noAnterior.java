package AlgoritimosDeOrdenacao;

public class noAnterior {
    private int valor;
    private noAnterior proximo;
    private noAnterior anterior;

    public noAnterior(int valor) {
        this.valor = valor;
        this.proximo = null;
        this.anterior = null;
    }

    public noAnterior getAnterior() {
        return anterior;
    }

    public void setAnterior(noAnterior anterior) {
        this.anterior = anterior;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public noAnterior getProx() {
        return proximo;
    }

    public void setProx(noAnterior proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return "No: " + getValor();
    }

}
