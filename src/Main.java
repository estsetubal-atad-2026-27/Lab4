import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ATAD 2026/27 - Laboratorio 4
 *
 * Este ficheiro não precisa de ser alterado para a Parte 1.
 * Usa-o (ou acrescenta metodos) para as medições e verificações do enunciado.
 */
public class Main {

    static List<Aluno> criarLista() {
        List<Aluno> l = new ArrayList<>();
        l.add(new Aluno(123, "Ana", 14.0));
        l.add(new Aluno(45, "Rui", 9.5));
        l.add(new Aluno(78, "Marta", 17.2));
        l.add(new Aluno(45, "Rui", 9.5));
        l.add(new Aluno(90, "Inês", 9.5));
        return l;
    }

    static void imprimir(String titulo, List<Aluno> l) {
        System.out.println("--- " + titulo);
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
    }

    public static void main(String[] args) {
        imprimir("original", criarLista());

        List<Aluno> porNumero = criarLista();
        Collections.sort(porNumero);
        imprimir("ordenada por numero (Comparable)", porNumero);

        List<Aluno> porNota = criarLista();
        Collections.sort(porNota, new PorNota());
        imprimir("ordenada por nota (Comparator)", porNota);

        List<Aluno> lista = criarLista();
        System.out.println("--- pesquisas");
        System.out.println("indexOf(78, outro nome)  = " + lista.indexOf(new Aluno(78, "Outro", 0.0)));
        System.out.println("contains(45, outro nome) = " + lista.contains(new Aluno(45, "Zé", 0.0)));
        System.out.println("indexOf(99)              = " + lista.indexOf(new Aluno(99, "Zé", 0.0)));

        Set<Aluno> conjunto = new HashSet<>();
        for (int i = 0; i < lista.size(); i++) {
            conjunto.add(lista.get(i));
        }
        System.out.println("--- HashSet");
        System.out.println("tamanho = " + conjunto.size());
    }
}
