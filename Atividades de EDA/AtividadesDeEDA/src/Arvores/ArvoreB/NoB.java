package ArvoreB;

import java.util.ArrayList;
import java.util.List;

public class NoB {
    private List<Integer> valores;

    private List<NoB> filhos;

    private boolean ehFolha;

    private NoB nextNoB;

    public NoB(boolean ehFolha) {
        this.valores = new ArrayList<>();
        this.filhos = new ArrayList<>();
        this.ehFolha = ehFolha;
        this.nextNoB = null;
    }

    public List<Integer> getValores() {
        return valores;
    }

    public void setValores(List<Integer> valores) {
        this.valores = valores;
    }

    public List<NoB> getFilhos() {
        return filhos;
    }

    public void setFilhos(List<NoB> filhos) {
        this.filhos = filhos;
    }

    public boolean isEhFolha() {
        return ehFolha;
    }

    public void setEhFolha(boolean ehFolha) {
        this.ehFolha = ehFolha;
    }

    public NoB getNextNoB() {
        return nextNoB;
    }

    public void setNextNoB(NoB nextNoB) {
        this.nextNoB = nextNoB;
    }


}
