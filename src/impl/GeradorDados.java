package impl;

import leitor.LeituraBPM;

/**
 * Responsável pela geração pseudoaleatória dos dados de monitoramento.
 *
 * Implementa um Gerador Congruente Linear (LCG - Linear Congruential Generator)
 * para produzir valores de BPM de forma reprodutível e sem uso de bibliotecas
 * externas ou internas de aleatoriedade.
 *
 * Fórmula do LCG:  Xn+1 = (A * Xn + C) mod M
 *
 * Disciplina: Complexidade de Algoritmos - IFBA
 */
public class GeradorDados {

    // ── Constantes do Gerador Congruente Linear (LCG) ────────────────────────
    private static final long LCG_A   = 1664525L;
    private static final long LCG_C   = 1013904223L;
    private static final long LCG_MOD = 4294967296L; // 2^32

    private long estado; // estado atual (semente)

    // ── Dados fixos para criação dos usuários ────────────────────────────────

    private static final String[] NOMES = {
        "Ana Beatriz Silva",
        "Bruno Costa Rocha",
        "Carla Mendes Lima",
        "Diego Ferreira Luz",
        "Elena Santos Cruz",
        "Felipe Nunes Dias",
        "Gabriela Alves Pinto",
        "Henrique Oliveira",
        "Isabela Moreira",
        "Joao Victor Souza"
    };

    private static final String[] GENEROS = {
        "Classica",
        "Pop",
        "Rock",
        "Eletronica",
        "Jazz",
        "Hip-Hop",
        "Sertanejo",
        "Reggae",
        "Metal",
        "R&B"
    };

    /*
     * Faixas de BPM típicas por gênero musical.
     * Baseadas na influência conhecida de cada estilo no ritmo cardíaco:
     *   Clássica  : 55-75  (relaxamento, BPM baixo)
     *   Pop       : 68-92  (ritmo moderado)
     *   Rock      : 78-112 (animado, BPM elevado)
     *   Eletrônica: 83-132 (alta energia, BPM muito elevado)
     *   Jazz      : 58-82  (relaxado, moderado)
     *   Hip-Hop   : 68-102
     *   Sertanejo : 63-92
     *   Reggae    : 58-82  (ritmo lento, relaxante)
     *   Metal     : 88-132 (extremo, BPM alto)
     *   R&B       : 62-86
     */
    private static final int[] BPM_MIN = { 55, 68, 78, 83, 58, 68, 63, 58, 88, 62 };
    private static final int[] BPM_MAX = { 75, 92, 112, 132, 82, 102, 92, 82, 132, 86 };

    // ── Construtor ────────────────────────────────────────────────────────────

    /**
     * Cria um gerador com a semente fornecida.
     *
     * @param seed Semente inicial — garante reprodutibilidade dos dados gerados.
     */
    public GeradorDados(long seed) {
        this.estado = seed;
    }

    // ── Geração pseudoaleatória ───────────────────────────────────────────────

    /**
     * Gera o próximo número inteiro pseudoaleatório no intervalo [min, max]
     * utilizando o algoritmo LCG.
     *
     * Complexidade: O(1)
     * Justificativa: executa exatamente 3 operações aritméticas, independentemente
     *   de qualquer tamanho de entrada.
     * Consequência: custo fixo, não há impacto com entradas grandes.
     *
     * @param min Valor mínimo (inclusive)
     * @param max Valor máximo (inclusive)
     * @return Inteiro pseudoaleatório no intervalo [min, max]
     */
    private int proximoInteiro(int min, int max) {
        estado = (LCG_A * estado + LCG_C) % LCG_MOD;
        return min + (int)(estado % (max - min + 1));
    }

    // ── Geração de usuários ───────────────────────────────────────────────────

    /**
     * Gera um array de N usuários, cada um com nome e gênero musical atribuídos.
     *
     * Complexidade: O(N)
     * Justificativa: um único laço percorre N vezes para instanciar N usuários.
     *   Cada iteração executa operações de custo constante O(1).
     * Consequência: com N muito grande, o tempo de criação cresce linearmente —
     *   trata-se de crescimento aceitável e eficiente.
     *
     * @param n Quantidade de usuários a gerar
     * @return Array de N usuários gerados
     */
    public Usuario[] gerarUsuarios(int n) {
        Usuario[] usuarios = new Usuario[n];
        for (int i = 0; i < n; i++) {
            String nome   = NOMES [i % NOMES.length];
            String genero = GENEROS[i % GENEROS.length];
            usuarios[i] = new Usuario(i + 1, nome, genero);
        }
        return usuarios;
    }

    // ── Geração de leituras de BPM ────────────────────────────────────────────

    /**
     * Gera M leituras pseudoaleatórias de BPM para cada um dos N usuários.
     * Os valores são gerados dentro da faixa típica do gênero musical de cada usuário,
     * simulando o efeito do estímulo musical sobre os batimentos cardíacos.
     *
     * Complexidade: O(N * M)
     * Justificativa: dois laços aninhados — o externo itera N usuários e o interno
     *   gera M leituras para cada usuário; cada geração é O(1), totalizando N*M operações.
     * Consequência: se N e M crescerem proporcionalmente (M = N), o custo sobe para O(N²),
     *   o que pode tornar a geração mais lenta para datasets muito grandes.
     *
     * @param usuarios         Array de usuários para os quais as leituras serão geradas
     * @param leiturasPorUsuario Quantidade M de leituras por usuário
     * @return Matriz [N][M] de leituras de BPM
     */
    public LeituraBPM[][] gerarLeituras(Usuario[] usuarios, int leiturasPorUsuario) {
        LeituraBPM[][] leituras = new LeituraBPM[usuarios.length][leiturasPorUsuario];
        for (int i = 0; i < usuarios.length; i++) {
            int idx    = encontrarIndiceGenero(usuarios[i].getGeneroMusical());
            int bpmMin = BPM_MIN[idx];
            int bpmMax = BPM_MAX[idx];
            for (int j = 0; j < leiturasPorUsuario; j++) {
                int bpm = proximoInteiro(bpmMin, bpmMax);
                leituras[i][j] = new LeituraBPM(usuarios[i].getId(), bpm, j + 1);
            }
        }
        return leituras;
    }

    // ── Utilitário privado ────────────────────────────────────────────────────

    /**
     * Localiza o índice de um gênero musical no array GENEROS.
     *
     * Complexidade: O(G) onde G = número de gêneros fixo (10) → efetivamente O(1).
     * Justificativa: G é constante (não escala com N ou M), portanto o custo é fixo.
     */
    private int encontrarIndiceGenero(String genero) {
        for (int i = 0; i < GENEROS.length; i++) {
            if (GENEROS[i].equals(genero)) return i;
        }
        return 0; // padrão: Clássica
    }
}
