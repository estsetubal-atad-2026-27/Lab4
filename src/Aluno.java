public class Aluno implements Comparable<Aluno> {

    private final int numero;
    private final String nome;
    private final double nota;

    public Aluno(int numero, String nome, double nota) {
        this.numero = numero;
        this.nome = nome;
        this.nota = nota;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return numero + " | " + nome + " | " + nota;
    }

    // ---------------------------------------------------------------
    // A implementar
    // ---------------------------------------------------------------

    // Ordem natural: por número de aluno.
    @Override
    public int compareTo(Aluno outro) {
        // TODO
        return 0;
    }

    // Igualdade: dois registos com o mesmo número são o mesmo aluno.
    @Override
    public boolean equals(Object obj) {
        // TODO
        return this == obj;
    }

    // Tem de ser consistente com equals.
    @Override
    public int hashCode() {
        // TODO
        return 0;
    }
}
