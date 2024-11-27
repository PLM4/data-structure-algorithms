public class Main {
    public static void main(String[] args) {
        ArvoreAVL tree = new ArvoreAVL();
        tree.insert(50, tree.getRaiz());
        tree.insert(3, tree.getRaiz());
        tree.insert(18, tree.getRaiz());
        tree.insert(90, tree.getRaiz());
        tree.insert(35, tree.getRaiz());
        tree.insert(22, tree.getRaiz());
        tree.insert(37, tree.getRaiz());
        tree.insert(390, tree.getRaiz());
        tree.insert(145, tree.getRaiz());
        tree.insert(277, tree.getRaiz());
        System.out.printf("A altura da arvore é:");
        System.out.println(tree.altura(tree.getRaiz()));
        System.out.println();
        tree.printaArvore(tree.getRaiz());

        ArvoreVermelhaPreta arvore = new ArvoreVermelhaPreta();
        int[] valores = { 7, 3, 18, 10, 22, 8, 11, 26 };
        for (int valor : valores) {
            arvore.inserir(valor);
        }
        arvore.exibir(); // Exibe: 3 7 8 10 11 18 22 26

        arvore.remover(18);
        arvore.exibir(); // Exibe: 3 7 8 10 11 22 26
    }
}