package impl;
import leitor.LeituraBPM;

/**
 * Contém todos os algoritmos de processamento dos dados de monitoramento de BPM.
 *
 * Cada método possui comentários detalhados com:
 *   - A complexidade em notação Big-O
 *   - A justificativa da complexidade
 *   - As consequências para entradas grandes
 *
 * Nenhuma biblioteca interna ou externa foi utilizada para as operações
 * de impressão, ordenação, filtragem ou acumulação — todos os algoritmos
 * foram implementados manualmente.
 *
 * Disciplina: Complexidade de Algoritmos - IFBA
 */
public class Processador {

    // =========================================================================
    // d.1 — LISTAR USUÁRIOS MONITORADOS
    // =========================================================================

    /**
     * Imprime a lista de todos os N usuários sendo monitorados pelo sistema.
     *
     * Complexidade: O(N)
     * Justificativa: um único laço percorre o array de N usuários uma vez,
     *   executando uma impressão de custo O(1) por iteração.
     * Consequência: com N muito grande, o tempo de impressão cresce de forma
     *   linear e proporcional a N — considerado eficiente para esta operação.
     *
     * @param usuarios Array com os N usuários monitorados
     */
    public static void imprimirUsuarios(Usuario[] usuarios) {
        System.out.println("\n+========================================================+");
        System.out.println("|      USUARIOS MONITORADOS — SMARTWATCH BPM             |");
        System.out.println("+======+==========================+================+");
        System.out.println("|  ID  | Nome                     | Genero Musical |");
        System.out.println("+------+--------------------------+----------------+");

        // O(N): percorre cada usuário exatamente uma vez
        for (int i = 0; i < usuarios.length; i++) {
            System.out.println(usuarios[i].toString());
        }

        System.out.println("+------+--------------------------+----------------+");
        System.out.println("| Total de usuarios monitorados: " + usuarios.length
                + "                         |");
        System.out.println("+========================================================+");
    }

    // =========================================================================
    // d.2 — LISTAR LEITURAS POR USUÁRIO
    // =========================================================================

    /**
     * Imprime todas as leituras de BPM agrupadas por usuário,
     * deixando explícito a qual usuário cada leitura pertence.
     *
     * Complexidade: O(N * M)
     * Justificativa: dois laços aninhados — o externo percorre N usuários
     *   e o interno percorre M leituras de cada usuário.
     *   Cada impressão é O(1), totalizando N*M operações.
     * Consequência: se N e M crescerem na mesma proporção (M proporcional a N),
     *   o custo se torna O(N²). Para volumes muito grandes, a impressão pode
     *   demandar tempo significativo; considerar paginação seria recomendável.
     *
     * @param usuarios Array com os N usuários
     * @param leituras Matriz [N][M] com as leituras de cada usuário
     */
    public static void imprimirLeiturasPorUsuario(Usuario[] usuarios,
                                                   LeituraBPM[][] leituras) {
        System.out.println("\n+========================================================+");
        System.out.println("|           LEITURAS DE BPM POR USUARIO                  |");
        System.out.println("+========================================================+");

        // Laço externo: O(N)
        for (int i = 0; i < usuarios.length; i++) {
            System.out.println("\n  >> Usuario ID " + usuarios[i].getId()
                    + " | " + usuarios[i].getNome()
                    + " | Genero: " + usuarios[i].getGeneroMusical());
            System.out.println("  +--------------------------+");

            // Laço interno: O(M) por usuário
            for (int j = 0; j < leituras[i].length; j++) {
                System.out.println("  |" + leituras[i][j] + "  |");
            }

            System.out.println("  +--------------------------+");
        }
    }

    // =========================================================================
    // d.3 — ORDENAÇÃO CRESCENTE DE BPM (BUBBLE SORT)
    // =========================================================================

    /**
     * Ordena, em ordem crescente de BPM, as leituras de cada usuário,
     * utilizando o algoritmo Bubble Sort implementado manualmente.
     *
     * O Bubble Sort funciona comparando pares de elementos adjacentes e
     * trocando-os quando estão fora de ordem. A cada passagem, o maior
     * elemento "borbulha" para a posição correta no final do array.
     *
     * Complexidade: O(N * M²)
     * Justificativa: para cada um dos N usuários, executa o Bubble Sort sobre
     *   M leituras. O Bubble Sort realiza até (M-1) passagens com até (M-1)
     *   comparações cada, resultando em O(M²) por usuário.
     *   Multiplicado pelos N usuários: O(N * M²).
     *   Se M for proporcional a N (escalonamento conjunto), a complexidade
     *   global pode atingir O(N³).
     * Consequência: o Bubble Sort torna-se muito lento para valores grandes de M.
     *   Com 1000 leituras por usuário, seriam ~10^6 comparações por usuário.
     *   Algoritmos como Merge Sort (O(M log M)) seriam mais adequados nesse cenário.
     *
     * @param leituras Matriz [N][M] modificada in-place (ordenação direta)
     */
    public static void ordenarLeiturasCrescente(LeituraBPM[][] leituras) {
        // Laço externo: itera sobre cada um dos N usuários
        for (int i = 0; i < leituras.length; i++) {
            LeituraBPM[] arr = leituras[i];
            int m = arr.length;

            // Bubble Sort: O(M²) por usuário
            // Cada iteração j garante que os j maiores elementos estejam no lugar correto
            for (int j = 0; j < m - 1; j++) {
                for (int k = 0; k < m - j - 1; k++) {
                    // Compara par adjacente e troca se estiver fora de ordem crescente
                    if (arr[k].getValorBPM() > arr[k + 1].getValorBPM()) {
                        LeituraBPM temp = arr[k];
                        arr[k]     = arr[k + 1];
                        arr[k + 1] = temp;
                    }
                }
            }
        }
    }

    // =========================================================================
    // d.4 — FUNCIONALIDADE EXTRA: MATRIZ DE SIMILARIDADE DE BPM   O(N² × M)
    // =========================================================================

    /**
     * Calcula e exibe a Matriz de Similaridade de BPM entre todos os pares
     * de usuários, usando o Erro Quadrático Médio (MSE) como métrica.
     *
     * Para cada par (i, j), o MSE é calculado comparando os valores de BPM
     * de cada leitura sequencial dos dois usuários:
     *
     *   MSE(i,j) = (1/M) * SUM_k [ (BPM_i[k] - BPM_j[k])^2 ]
     *
     * Quanto MENOR o MSE, mais SIMILAR foi a resposta cardíaca dos dois usuários
     * sob seus respectivos estímulos musicais. Ao final, o par mais similar é
     * identificado e destacado.
     *
     * Complexidade: O(N² × M)
     * Justificativa: três laços aninhados — os dois externos iteram sobre os N²
     *   pares de usuários (metade da matriz, ~N²/2 pares), e o laço interno
     *   percorre M leituras de cada par para calcular o MSE.
     *   Total: N² × M operações aritméticas.
     *   Se M for proporcional a N, a complexidade total sobe para O(N³).
     * Consequência: com N grande (ex.: 1000 usuários com 1000 leituras cada),
     *   seriam 10^9 operações — impraticável sem otimização ou paralelização
     *   via Threads. Este é um exemplo concreto de onde o uso de Threads Java
     *   (processamento paralelo de linhas da matriz) poderia reduzir o tempo real.
     *
     * NOTA: Esta funcionalidade é completamente diferente de d.1, d.2 e d.3,
     *   e NÃO é a "contagem de altas oscilações" vedada pelo enunciado.
     *
     * @param usuarios Array com os N usuários
     * @param leituras Matriz [N][M] de leituras de BPM
     */
    public static void analisarSimilaridadeEntrePares(Usuario[] usuarios,
                                                       LeituraBPM[][] leituras) {
        int n = usuarios.length;
        int m = leituras[0].length;

        // Matriz N×N que armazenará o MSE de cada par (i, j)
        double[][] mse = new double[n][n];

        double menorMSE  = Double.MAX_VALUE;
        int    parI      = 0;
        int    parJ      = 1;

        // ── Cálculo: O(N² × M) ───────────────────────────────────────────
        for (int i = 0; i < n; i++) {                      // laço 1: N iterações
            for (int j = 0; j < n; j++) {                  // laço 2: N iterações
                if (i == j) {
                    mse[i][j] = 0.0;                       // diagonal: usuário vs si mesmo
                    continue;
                }
                double somaQuadrados = 0.0;
                for (int k = 0; k < m; k++) {              // laço 3: M iterações
                    double diff = leituras[i][k].getValorBPM()
                                - leituras[j][k].getValorBPM();
                    somaQuadrados += diff * diff;
                }
                mse[i][j] = somaQuadrados / m;

                // Registra o par com menor MSE (maior similaridade)
                if (i < j && mse[i][j] < menorMSE) {
                    menorMSE = mse[i][j];
                    parI     = i;
                    parJ     = j;
                }
            }
        }

        // ── Impressão da matriz ──────────────────────────────────────────
        System.out.println("\n+============================================================+");
        System.out.println("|   MATRIZ DE SIMILARIDADE DE BPM (Erro Quadratico Medio)   |");
        System.out.println("|   Valor MENOR = par MAIS SIMILAR   Complexidade: O(N2*M)   |");
        System.out.println("+============================================================+");

        // Cabeçalho das colunas
        System.out.printf("%-6s", "");
        for (int i = 0; i < n; i++) {
            System.out.printf(" U%-7d", i + 1);
        }
        System.out.println();

        // Linhas da matriz
        for (int i = 0; i < n; i++) {
            System.out.printf("U%-5d", i + 1);
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    System.out.printf(" %-8s", "  ---  ");
                } else {
                    System.out.printf(" %-8.2f", mse[i][j]);
                }
            }
            System.out.println();
        }

        // ── Resultado: par mais similar ──────────────────────────────────
        System.out.println("\n  >> Par com maior similaridade cardíaca:");
        System.out.printf("     [U%d] %s  (Genero: %s)%n",
                parI + 1,
                usuarios[parI].getNome(),
                usuarios[parI].getGeneroMusical());
        System.out.printf("     [U%d] %s  (Genero: %s)%n",
                parJ + 1,
                usuarios[parJ].getNome(),
                usuarios[parJ].getGeneroMusical());
        System.out.printf("     MSE = %.4f%n", menorMSE);
        System.out.println("+============================================================+");
    }

    // =========================================================================
    // UTILITÁRIO — Resumo estatístico (BPM médio e BPM máx por usuário)
    // =========================================================================

    /**
     * Imprime o BPM médio e o BPM máximo de cada usuário como resumo estatístico.
     *
     * Complexidade: O(N * M)
     * Justificativa: dois laços aninhados — externo percorre N usuários, interno
     *   percorre M leituras para acumular soma e encontrar máximo em cada usuário.
     * Consequência: mesmo comportamento que imprimirLeiturasPorUsuario. Para M e N
     *   muito grandes, a operação pode ser lenta, mas é inevitável para estatísticas
     *   completas sem pré-processamento.
     *
     * @param usuarios Array de N usuários
     * @param leituras Matriz [N][M] de leituras
     */
    public static void imprimirResumoEstatistico(Usuario[] usuarios,
                                                  LeituraBPM[][] leituras) {
        System.out.println("\n+========================================================+");
        System.out.println("|           RESUMO ESTATISTICO DE BPM POR USUARIO        |");
        System.out.println("+------+------------------------+-----------+-----------+");
        System.out.println("| ID   | Nome                   |  BPM Med  |  BPM Max  |");
        System.out.println("+------+------------------------+-----------+-----------+");

        // O(N * M): para cada usuário, percorre todas as M leituras
        for (int i = 0; i < usuarios.length; i++) {
            double soma   = 0.0;
            int    maxBPM = Integer.MIN_VALUE;

            for (int j = 0; j < leituras[i].length; j++) {
                int bpm = leituras[i][j].getValorBPM();
                soma   += bpm;
                if (bpm > maxBPM) maxBPM = bpm;
            }

            double media = soma / leituras[i].length;
            System.out.printf("| %-4d | %-22s |   %6.2f  |   %6d  |%n",
                    usuarios[i].getId(),
                    usuarios[i].getNome(),
                    media,
                    maxBPM);
        }

        System.out.println("+------+------------------------+-----------+-----------+");
    }
}
