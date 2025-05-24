package tad.conjuntoDinamico;

/**
 * Autor: Raiff Ferreira Telecio e Guilherme Ribeiro
 * Disciplina: LEDA
 * Roteiro: Implementação de Conjunto Dinâmico Encadeado com lista encadeada (simples ou duplamente encadeada)
 * 
 * Esta classe implementa um conjunto dinâmico encadeado de inteiros utilizando uma
 * lista simplesmente encadeada. Permite inserção, remoção, busca e
 * operações como predecessor, sucessor, mínimo e máximo.
*/

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

    private class Node {
        Integer valor;
        Node proximo;

        Node(Integer valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    private Node cabeca = null;
    private int tamanho = 0;

    @Override
    public void inserir(Integer item) {
        if (buscar(item) != null) return;
        Node novo = new Node(item);
        if (cabeca == null) {
            cabeca = novo;
        } else {
            Node atual = cabeca;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
        tamanho++;
    }

    @Override
    public Integer remover(Integer item) {
        Node atual = cabeca;
        Node anterior = null;

        while (atual != null) {
            if (atual.valor.equals(item)) {
                if (anterior == null) {
                    cabeca = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                tamanho--;
                return atual.valor;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return null;
    }

    @Override
    public Integer predecessor(Integer item) {
        Integer anterior = null;
        Node atual = cabeca;

        while (atual != null) {
            if (atual.valor < item) {
                if (anterior == null || atual.valor > anterior) {
                    anterior = atual.valor;
                }
            }
            atual = atual.proximo;
        }

        return anterior;
    }

    @Override
    public Integer sucessor(Integer item) {
        Integer proximo = null;
        Node atual = cabeca;

        while (atual != null) {
            if (atual.valor > item) {
                if (proximo == null || atual.valor < proximo) {
                    proximo = atual.valor;
                }
            }
            atual = atual.proximo;
        }

        return proximo;
    }

    @Override
    public int tamanho() {
        return tamanho;
    }

    @Override
    public Integer buscar(Integer item) {
        Node atual = cabeca;
        while (atual != null) {
            if (atual.valor.equals(item)) return atual.valor;
            atual = atual.proximo;
        }
        return null;
    }

    @Override
    public Integer minimum() {
        if (cabeca == null) return null;

        Integer min = cabeca.valor;
        Node atual = cabeca.proximo;
        while (atual != null) {
            if (atual.valor < min) min = atual.valor;
            atual = atual.proximo;
        }

        return min;
    }

    @Override
    public Integer maximum() {
        if (cabeca == null) return null;

        Integer max = cabeca.valor;
        Node atual = cabeca.proximo;
        while (atual != null) {
            if (atual.valor > max) max = atual.valor;
            atual = atual.proximo;
        }

        return max;
    }
}
