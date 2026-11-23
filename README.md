# ATAD 2026/27 · Laboratório 4 — Contratos e ordem

| | |
|---|---|
| **Unidade curricular** | Algoritmos e Tipos Abstratos de Dados |
| **Semana** | 5 (aplica a matéria da semana 4) |
| **Duração** | 1 h 45 min |
| **Meio** | IntelliJ IDEA, JDK 21 (ou o disponível na sala) |
| **Conteúdo de suporte** | Tópicos 13–14: interfaces como contrato, `Comparable`, `Comparator`, `equals`/`hashCode`, ordenação de objetos |
| **Trabalho** | Individual ou em grupo de 2 (à escolha dos estudantes) |

---

## 1. Objetivos

No final deste laboratório deves ser capaz de:

1. Implementar a **ordem natural** de uma classe com `Comparable`;
2. Implementar **critérios externos** com `Comparator`, sem alterar a classe;
3. Implementar `equals` e `hashCode` de forma **consistente**;
4. Explicar o efeito de um contrato violado numa coleção da biblioteca;
5. Distinguir pesquisa por **igualdade** de pesquisa por **critério**.

---

## 2. O que é fornecido

| Ficheiro       | Estado                                                                                                     |
| -------------- | ---------------------------------------------------------------------------------------------------------- |
| `Aluno.java`   | campos, construtor, getters e `toString` implementados; `compareTo`, `equals` e `hashCode` por implementar |
| `PorNota.java` | estrutura pronta; `compare` por implementar                                                                |
| `Main.java`    | demonstração; **não precisa de ser alterado**                                                              |

Os campos de `Aluno` são `numero`, `nome` e `nota`. A lista de demonstração tem **cinco** elementos, dois dos quais com o mesmo número (`45`).

---

## 3. Plano da sessão

| Parte | Tarefa                                        | Tempo  |
| :---: | --------------------------------------------- | :----: |
|   1   | Criar o projeto e executar o código fornecido | 10 min |
|   2   | Ordem natural com `Comparable`                | 10 min |
|   3   | Critério externo com `Comparator`             | 15 min |
|   4   | `equals` e `hashCode`                         | 20 min |
|   5   | Pesquisa e conclusões                         | 25 min |
|   6   | Algoritmos de ordenação sobre objetos         | 25 min |

---

## 4. Parte 1 — Criar o projeto através de `git clone` (10 min)

> [!TIP]
> Se no *wizard* indicar que o `Git` não está instalado na máquina, podes instalar através da opção indicada.

> [!IMPORTANT]
> Se o projeto não compilar, vai a `File` → `Project Structure` e seleciona uma JDK existente na máquina.

1. Clica no botão 🟩 `<> Code` na página do repositório e copia o URL.
2. `File` → `New` → `Project from Version Control…`;
3. Escolha a pasta onde será criado o projeto e cola o URL no campo indicado;
4. Executa `Main`.

O programa corre, mas **nada está ordenado**, as pesquisas devolvem `-1`/`false` e o `HashSet` tem **5** elementos. É o comportamento dos métodos ainda por implementar — não é um erro de instalação.

---

## 5. Parte 2 — Ordem natural (10 min)

### 5.1. Implementar `compareTo`

A ordem natural é por **número de aluno**. Segue a convenção: negativo, zero ou positivo.

Não uses `this.numero - outro.numero` — pensa no que acontece com números muito distantes.

### 5.2. Verificar

Depois de implementares, a secção «ordenada por numero» deve mostrar:

```text
45 | Rui | 9.5
45 | Rui | 9.5
78 | Marta | 17.2
90 | Inês | 9.5
123 | Ana | 14.0
```

**Responde em `notas.md`:**

1. Porque é que os dois alunos com número `45` ficam **juntos**, e não separados por outros?
2. Se `compareTo` passasse a comparar a **nota**, o que mudaria nesta listagem? E na pesquisa por número?
3. Duas notas iguais tornam dois alunos «equivalentes» para `compareTo`? São o mesmo aluno?

---

## 6. Parte 3 — Critério externo (15 min)

### 6.1. Implementar `PorNota`

Nota **decrescente**; em caso de empate, **nome crescente**.

### 6.2. Verificar

```text
78 | Marta | 17.2
123 | Ana | 14.0
90 | Inês | 9.5
45 | Rui | 9.5
45 | Rui | 9.5
```

**Responde em `notas.md`:**

1. Porque é que a `Inês` aparece antes do `Rui`, se ambos têm `9.5`?
2. O ficheiro `Aluno.java` foi alterado? O que isso diz sobre a separação entre objeto e critério?
3. Se quisesses a nota **crescente**, o que mudavas? E se quisesses desempatar por nome decrescente?

> [!TIP]
> Experimenta criar um segundo `Comparator` (por exemplo, `PorNome`) sem tocar em `Aluno`. É o teste decisivo de que o critério está separado.  
> Exemplifica o uso desse comparador no `main`.

---

## 7. Parte 4 — `equals` e `hashCode` (20 min)

### 7.1. Implementar

A igualdade é por **número**: dois registos com o mesmo número são o mesmo aluno.

- `equals` recebe `Object` — não `Aluno`;
- `hashCode` tem de usar **o mesmo critério** que `equals`.

### 7.2. Verificar

A secção «pesquisas» deve mostrar:

```text
indexOf(78, outro nome)  = 2
contains(45, outro nome) = true
indexOf(99)              = -1
```

e o `HashSet` deve ter **4** elementos.

**Responde em `notas.md`:**

1. A lista tem 5 elementos e o conjunto tem 4. Qual desapareceu, e porquê?
2. O nome do aluno influencia as pesquisas? Justifica com os resultados.
3. O que devolveria `indexOf(new Aluno(45, "Zé", 0.0))`? E se existissem dois alunos com número `45`?

### 7.3. Provocar a violação

1. **Comenta** o método `hashCode` e volta a correr. O que acontece ao tamanho do conjunto? Explica.
2. Repõe o `hashCode`.
3. Altera `equals` para comparar o **nome** e mantém `hashCode` a devolver o **número**. Testa com dois alunos de **nome igual e números diferentes** — por exemplo, `(45, "Rui", 9.5)` e `(46, "Rui", 8.0)`. Quantos ficam no conjunto? Porquê?
4. Repõe a versão correta.

---

## 8. Parte 5 — Pesquisa e conclusões (25 min)

### 8.1. Pesquisa por critério

Acrescenta a `Main` um método:

```java
// Número de alunos com nota >= 9.5
static int contarAprovados(List<Aluno> lista) { ... }
```

e **outro** que devolva o **índice do primeiro** aluno com nota inferior a 10 (ou `-1`).

**Responde:** porque é que `indexOf` não serve para nenhuma destas duas pesquisas?

### 8.2. Pesquisa binária sobre objetos

Depois de ordenares a lista por número, experimenta:

```java
List<Aluno> ordenada = criarLista();
Collections.sort(ordenada);
System.out.println(Collections.binarySearch(ordenada, new Aluno(123, "X", 0.0)));
```

**Responde:**

1. Que índice devolve? Qual é a comparação usada — `equals` ou `compareTo`?
2. O que acontece se fizeres a mesma chamada **sem** ordenar a lista primeiro? O valor devolvido é negativo? O aluno existe?
3. Porque é que a pesquisa binária exige que a lista esteja ordenada **pela mesma ordem** que `compareTo` define?

### 8.3. Conclusões

Escreve em `notas.md` uma tabela:

| Contrato            | Onde está definido | Usado por | Efeito se faltar |
| ------------------- | ------------------ | --------- | ---------------- |
| `Comparable`        |                    |           |                  |
| `Comparator`        |                    |           |                  |
| `equals`/`hashCode` |                    |           |                  |

---

## 9. Parte 6 — Algoritmos de ordenação sobre objetos (25 min)

O método `Collections.sort(...)` permite ordenar listas de objetos, mas utiliza internamente um algoritmo de ordenação que "desconhecemos".

Pretende-se implementar manualmente um algoritmo de ordenação, aplicando um dos algoritmos estudados nas aulas: **Bubble Sort** ou **Selection Sort**.

### 9.1. Implementar o método `ordena`

Acrescenta à classe `Main` o seguinte método:

```java
static void ordena(List<Aluno> lista, Comparator<Aluno> criterio) {
    // Implementar Bubble Sort ou Selection Sort
}
```

O método deve:
- Ordenar a lista recebida, utilizando o algoritmo escolhido.
- Utilizar o `Comparator<Aluno>` recebido para determinar a ordem relativa entre dois alunos.
- Modificar a própria lista, sem criar uma nova lista.
- Não recorrer a métodos de ordenação da biblioteca Java, nomeadamente `Collections.sort()` ou `List.sort()`.

### 9.2. Verificar

Testa o método com diferentes critérios de ordenação, por exemplo:
- Por nota.
- Por nome.

Confirma que a lista fica corretamente ordenada em ambos os casos.

---

## 10. Fecho

- [ ] O projeto compila sem erros;
- [ ] A ordenação por número e por nota produz as ordens esperadas;
- [ ] `indexOf` e `contains` funcionam com um aluno de nome diferente;
- [ ] O `HashSet` tem 4 elementos;
- [ ] Experimentaste comentar o `hashCode` e sabes explicar o resultado;
- [ ] O `notas.md` tem as respostas e a tabela final.

---

## 11. Erros comuns

| Erro                                                 | Efeito                            | Como detetar                   |
| ---------------------------------------------------- | --------------------------------- | ------------------------------ |
| `equals(Aluno outro)` em vez de `equals(Object obj)` | não é `equals` — é uma sobrecarga | com `@Override`, não compila   |
| `equals` sem `hashCode`                              | duplicados no `HashSet`           | tamanho 5 em vez de 4          |
| `hashCode` com critério diferente de `equals`        | duplicados no `HashSet`           | idem                           |
| `compareTo` com o sinal invertido                    | ordem decrescente inesperada      | ver a listagem                 |
| `Comparator` que devolve sempre 0                    | ordenação não faz nada            | a lista fica na ordem original |
| `==` para comparar `String`                          | igualdade por referência          | `equals` deixa de funcionar    |
| Utilizar `>` ou `<` para comparar objetos            | erro                              | não compila                    |

---

## 12. Referências

- Slides do bloco 4 (semana 4) — disponíveis no Moodle;
- Exercícios de aula da semana 4 — disponíveis no Moodle;
- Documentação Java: [`Comparable`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Comparable.html), [`Comparator`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Comparator.html), [`List`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html).
