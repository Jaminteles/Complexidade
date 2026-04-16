package leitor;

/**
 * Representa uma leitura de BPM (batimentos por minuto) registrada
 * pelo smartwatch de um usuário durante a exposição musical.
 *
 * Disciplina: Complexidade de Algoritmos - IFBA
 */
public class LeituraBPM {

    private int usuarioId;   // ID do usuário dono desta leitura
    private int valorBPM;    // valor do batimento cardíaco medido
    private int sequencia;   // número sequencial da leitura (1, 2, 3 ...)

    /**
     * Construtor completo.
     *
     * @param usuarioId ID do usuário ao qual a leitura pertence
     * @param valorBPM  Valor de BPM capturado pelo sensor
     * @param sequencia Número sequencial desta leitura
     */
    public LeituraBPM(int usuarioId, int valorBPM, int sequencia) {
        this.usuarioId = usuarioId;
        this.valorBPM  = valorBPM;
        this.sequencia = sequencia;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public int getUsuarioId() { return usuarioId; }
    public int getValorBPM()  { return valorBPM;  }
    public int getSequencia() { return sequencia; }

    /**
     * Retorna uma linha formatada para exibição.
     */
    @Override
    public String toString() {
        return String.format("  Leitura #%-3d -> %3d BPM", sequencia, valorBPM);
    }
}
