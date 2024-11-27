import java.util.LinkedList;

public class TabelaHash {

    private int tamanho;
    private LinkedList[] lista;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        lista = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            lista[i] = new LinkedList();
        }
    }

    public int funcaoHash(int matricula) {
        return matricula % this.tamanho;
    }

    public void inserirValor(int matricula, Aluno valor) {
        int indice = funcaoHash(matricula);
        lista[indice].add(valor);
    }

    public String pegarValor(int matricula) {
        int indice = funcaoHash(matricula);
        LinkedList sublista = lista[indice];
        for (int i = 0; i < sublista.size(); i++) {
            if (sublista.get(i).matricula == matricula) {
                return sublista.get(i).nome;
            }
        }
        return null;
    }

    public void removerValor(int matricula) {
        int indice = funcaoHash(matricula);
        LinkedList sublista = lista[indice];
        for (int i = 0; i < sublista.size(); i++) {
            if (sublista.get(i).matricula == matricula) {
                sublista.removeIndex(i);
            }
        }
    }

}
