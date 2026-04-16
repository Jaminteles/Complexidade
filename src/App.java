import impl.GeradorDados;
import impl.Processador;
import impl.Usuario;
import leitor.LeituraBPM;

/**
 * ============================================================
 *  SISTEMA DE MONITORAMENTO DE BATIMENTOS CARDIACOS (BPM)
 *  via Smartwatches com Exposicao a Diferentes Generos Musicais
 * ============================================================
 *
 * Mini-mundo:
 *   Dez usuarios utilizam smartwatches que registram seus batimentos
 *   cardíacos (BPM) enquanto estao expostos a diferentes generos
 *   musicais. O sistema gera dados pseudoaleatorios, organiza e
 *   analisa as leituras, permitindo comparar as respostas cardíacas
 *   de cada usuario e identificar padroes de similaridade.
 *
 * Funcionalidades implementadas (conforme criterios do trabalho):
 *   d.1 — Listar usuarios monitorados                   O(N)
 *   d.2 — Listar leituras por usuario                   O(N*M)
 *   d.3 — Ordenar leituras crescente (Bubble Sort)      O(N*M²)
 *   d.4 — Matriz de Similaridade de BPM (MSE)           O(N²*M)
 *   Bônus — Resumo estatístico (media e maximo por user) O(N*M)
 *
 * Linguagem: Java (suporte nativo a Threads — Thread, Runnable, etc.)
 * Sem banco de dados. Sem bibliotecas externas/internas de ordenacao.
 *
 * Disciplina : Complexidade de Algoritmos — IFBA
 * Professor  : Luis Paulo da Silva Carvalho
 * Entrega    : 29/04/2026
 */
public class App {

    public static void main(String[] args) {

        // ── Parametros da simulacao ────────────────────────────────────────
        final int  NUM_USUARIOS         = 10;   // N: numero de usuarios
        final int  LEITURAS_POR_USUARIO = 15;   // M: leituras por usuario
        final long SEED                 = 2026L; // semente para reproducibilidade

        // ── Cabecalho do sistema ───────────────────────────────────────────
        System.out.println("+========================================================+");
        System.out.println("|   MONITORAMENTO DE BPM COM ESTIMULO MUSICAL            |");
        System.out.println("|   Simulacao: N=" + NUM_USUARIOS
                + " usuarios | M=" + LEITURAS_POR_USUARIO
                + " leituras/usuario        |");
        System.out.println("+========================================================+");

        // ── Geracao pseudoaleatoria dos dados ─────────────────────────────
        // Complexidade: O(N) para usuarios + O(N*M) para leituras
        GeradorDados gerador  = new GeradorDados(SEED);
        Usuario[]    usuarios = gerador.gerarUsuarios(NUM_USUARIOS);
        LeituraBPM[][] leituras = gerador.gerarLeituras(usuarios, LEITURAS_POR_USUARIO);

        System.out.println("\n[GERACAO] Dados gerados com semente=" + SEED
                + " | Complexidade O(N*M)");

        // ── d.1: Listar usuarios monitorados — O(N) ───────────────────────
        System.out.println("\n[d.1] LISTAGEM DOS USUARIOS MONITORADOS | Complexidade: O(N)");
        Processador.imprimirUsuarios(usuarios);

        // ── d.2: Listar leituras por usuario — O(N*M) ─────────────────────
        System.out.println("\n[d.2] LEITURAS ORIGINAIS POR USUARIO | Complexidade: O(N*M)");
        Processador.imprimirLeiturasPorUsuario(usuarios, leituras);

        // ── BONUS: Resumo estatistico — O(N*M) ────────────────────────────
        System.out.println("\n[BONUS] RESUMO ESTATISTICO | Complexidade: O(N*M)");
        Processador.imprimirResumoEstatistico(usuarios, leituras);

        // ── d.3: Ordenacao crescente por BPM (Bubble Sort) — O(N*M²) ─────
        System.out.println("\n[d.3] ORDENANDO LEITURAS CRESCENTE (Bubble Sort)"
                + " | Complexidade: O(N * M^2)");
        Processador.ordenarLeiturasCrescente(leituras);

        System.out.println("\n[d.3] LEITURAS APOS ORDENACAO CRESCENTE | Complexidade: O(N*M)");
        Processador.imprimirLeiturasPorUsuario(usuarios, leituras);

        // ── d.4: Matriz de similaridade de BPM — O(N²*M) ─────────────────
        System.out.println("\n[d.4] MATRIZ DE SIMILARIDADE ENTRE PARES"
                + " | Complexidade: O(N^2 * M)");
        Processador.analisarSimilaridadeEntrePares(usuarios, leituras);

        // ── Rodape ─────────────────────────────────────────────────────────
        System.out.println("\n+========================================================+");
        System.out.println("|                  SIMULACAO CONCLUIDA                  |");
        System.out.println("+========================================================+");
    }
}
