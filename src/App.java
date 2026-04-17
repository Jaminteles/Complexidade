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
        final int  LEITURAS_POR_USUARIO = 5;   // M: leituras por usuario
        final long SEED                 = 2026L; // semente para reproducibilidade

        System.out.println("== MONITORAMENTO DE BPM COM ESTIMULO MUSICAL ");
        System.out.println("Parametros: N=" + NUM_USUARIOS + " usuarios, M=" + LEITURAS_POR_USUARIO + " leituras/usuario");

        // ── Geracao pseudoaleatoria dos dados ─────────────────────────────
        // Complexidade: O(N) para usuarios + O(N*M) para leituras
        GeradorDados gerador  = new GeradorDados(SEED);
        Usuario[]    usuarios = gerador.gerarUsuarios(NUM_USUARIOS);
        LeituraBPM[][] leituras = gerador.gerarLeituras(usuarios, LEITURAS_POR_USUARIO);

        System.out.println("\nDados gerados (seed=" + SEED + ")");

        System.out.println("\n-- d.1: Usuarios Monitorados (O(N))");
        Processador.imprimirUsuarios(usuarios);

        System.out.println("\n-- d.2: Leituras por Usuario (O(N*M))");
        Processador.imprimirLeiturasPorUsuario(usuarios, leituras);

        System.out.println("\n-- Resumo Estatistico (O(N*M))");
        Processador.imprimirResumoEstatistico(usuarios, leituras);

        System.out.println("\n-- d.3: Ordenacao de Leituras - Bubble Sort (O(N*M²))");
        Processador.ordenarLeiturasCrescente(leituras);

        System.out.println("\n-- Leituras Ordenadas (O(N*M))");
        Processador.imprimirLeiturasPorUsuario(usuarios, leituras);

        System.out.println("\n-- d.4: Matriz de Similaridade (O(N²*M))");
        Processador.analisarSimilaridadeEntrePares(usuarios, leituras);

        System.out.println("\n== Simulacao Concluida");
    }
}
