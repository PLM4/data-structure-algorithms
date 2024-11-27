package AlgoritimosDeOrdenacao;

public class ListaOrdenanda {

    public static int[] countingSort(int[] A, int k) {

        int[] C = new int[k]; //inicializa uma lista com k sendo o maior valor.

        // frequência
        for (int i = 0; i < A.length; i++) {
            C[A[i] - 1] += 1; //Conta as ocorrencias. Exemplo: [0,1,1,3,2,2]
        }

        // cumulativa
        for (int i = 1; i < C.length; i++) {
            C[i] += C[i - 1]; //Soma as ocorrencias. Exemplo: [0,1,2,5,7,9]
        }

        int[] B = new int[A.length]; //Novo array para depositar a lista ordenada.

        for (int i = A.length - 1; i >= 0; i--) {
            B[C[A[i] - 1] - 1] = A[i];
            C[A[i] - 1] -= 1;
        }

        return B;

    }

    public static int[] ordenaLista(int[] lista) {
        int n = lista.length;
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista[j] > lista[j + 1]) {
                    int aux = lista[j];
                    lista[j] = lista[j + 1];
                    lista[j + 1] = aux;
                }
            }
        }
        return lista;
    }

    public static void ordenarListaRecursivo(int[] lista) {
        int n = lista.length;

        if (n > 1) {
            int mid = n / 2;

            int[] left = new int[mid];
            int[] right = new int[n - mid];

            System.arraycopy(lista, 0, left, 0, mid);
            System.arraycopy(lista, mid, right, 0, n - mid);

            ordenarListaRecursivo(left);
            ordenarListaRecursivo(right);

            merge(lista, left, right);
        }
    }

    public static void merge(int[] lista, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                lista[k++] = left[i++];
            } else {
                lista[k++] = right[j++];
            }
        }

        while (i < left.length) {
            lista[k++] = left[i++];
        }

        while (j < right.length) {
            lista[k++] = right[j++];
        }
    }

    public static void printLista(int[] lista) {
        for (int num : lista) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
