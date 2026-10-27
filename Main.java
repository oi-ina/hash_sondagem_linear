import java.util.Random;

public class Main {

    // Busca usando a mesma sondagem linear aplicada na insercao.
    static int buscar(int[] tabela, int numero) {
        int posicao = numero % 100;
        int tentativas = 0;
        int encontrada = -1;

        while (tentativas < 100 && encontrada == -1 && tabela[posicao] != -1) {
            if (tabela[posicao] == numero) {
                encontrada = posicao;
            } else {
                posicao = (posicao + 1) % 100;
                tentativas++;
            }
        }

        return encontrada;
    }

    public static void main(String[] args) {
        int[] tabela = new int[100];
        int[] valores = new int[90];
        int[] colisoesPorElemento = new int[90];
        Random random = new Random(1090);

        for (int i = 0; i < 100; i++) {
            tabela[i] = -1; // -1 identifica uma posicao livre.
        }

        int ocupadas = 0;
        int bloqueados = 0;
        int totalColisoes = 0;

        // Sao gerados 100 numeros, mas a ocupacao nao ultrapassa 90%.
        for (int i = 0; i < 100; i++) {
            int numero = random.nextInt(100001); // De 0 ate 100000.

            if (ocupadas < 90) {
                int posicao = numero % 100;
                int colisoes = 0;

                // Se a posicao estiver ocupada, tenta a proxima.
                while (tabela[posicao] != -1) {
                    colisoes++;
                    posicao = (posicao + 1) % 100;
                }

                tabela[posicao] = numero;
                valores[ocupadas] = numero;
                colisoesPorElemento[ocupadas] = colisoes;
                totalColisoes += colisoes;
                ocupadas++;
            } else {
                bloqueados++;
            }
        }

        // Seleciona os tres numeros que mais encontraram ocupacoes.
        int primeiro = -1;
        int segundo = -1;
        int terceiro = -1;

        for (int i = 0; i < ocupadas; i++) {
            if (primeiro == -1 || colisoesPorElemento[i] > colisoesPorElemento[primeiro]) {
                terceiro = segundo;
                segundo = primeiro;
                primeiro = i;
            } else if (segundo == -1 || colisoesPorElemento[i] > colisoesPorElemento[segundo]) {
                terceiro = segundo;
                segundo = i;
            } else if (terceiro == -1 || colisoesPorElemento[i] > colisoesPorElemento[terceiro]) {
                terceiro = i;
            }
        }

        System.out.println("TABELA HASH - SONDAGEM LINEAR");
        System.out.println("Numeros gerados: 100");
        System.out.println("Inseridos: " + ocupadas);
        System.out.println("Impedidos pelo limite de 90%: " + bloqueados);
        System.out.println("Total de colisoes: " + totalColisoes);

        System.out.print("Posicoes livres: ");
        for (int i = 0; i < 100; i++) {
            if (tabela[i] == -1) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        int[] maisColidiram = {primeiro, segundo, terceiro};
        System.out.println("Tres elementos com mais colisoes durante a insercao:");

        for (int i = 0; i < 3; i++) {
            int indice = maisColidiram[i];
            System.out.println((i + 1) + " - Valor: " + valores[indice]
                    + " | Colisoes: " + colisoesPorElemento[indice]);
        }

        System.out.println("Teste de busca desses tres elementos:");
        for (int i = 0; i < 3; i++) {
            int numero = valores[maisColidiram[i]];
            int posicao = buscar(tabela, numero);

            if (posicao != -1) {
                System.out.println("Valor " + numero + " encontrado na posicao " + posicao);
            } else {
                System.out.println("Valor " + numero + " nao encontrado");
            }
        }
    }
}
