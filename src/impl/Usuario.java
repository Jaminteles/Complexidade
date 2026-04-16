package impl;
/**
 * Representa um usuário monitorado pelo sistema.
 *
 * Cada usuário utiliza um smartwatch que registra seus batimentos
 * cardíacos (BPM) enquanto está exposto a um determinado gênero musical.
 *
 * Disciplina: Complexidade de Algoritmos - IFBA
 */
public class Usuario {

    private int    id;
    private String nome;
    private String generoMusical;

    /**
     * Construtor completo.
     *
     * @param id            Identificador único do usuário
     * @param nome          Nome do usuário
     * @param generoMusical Gênero musical ao qual o usuário está exposto
     */
    public Usuario(int id, String nome, String generoMusical) {
        this.id            = id;
        this.nome          = nome;
        this.generoMusical = generoMusical;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public int    getId()            { return id; }
    public String getNome()          { return nome; }
    public String getGeneroMusical() { return generoMusical; }

    /**
     * Retorna uma linha formatada para exibição em tabela.
     */
    @Override
    public String toString() {
        return String.format("| %-4d | %-24s | %-14s |",
                id, nome, generoMusical);
    }
}
