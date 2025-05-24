package tad.fila;

/**
 * Autor: Raiff Ferreira Telecio
 * Disciplina: LEDA
 * Roteiro: Implementação Fila com arrays
 * 
 * Classe que implementa uma fila circular utilizando array estático de Integers.
 * Essa implementação possui tamanho fixo, que pode ser definido na criação da fila.
 * Caso a fila esteja cheia, a inserção lançará exceção.
 * Implementa a interface {FilaIF} para manipulação básica de fila (enfileirar, desenfileirar, verificar).
*/

public class MinhaFila implements FilaIF<Integer> {

    private int tamanho = 10;
    private int cauda = 0;
    private int cabeca = 0;
    private int quantidade = 0;

    private Integer[] meusDados;

    public MinhaFila(int tamanhoInicial) {
        tamanho = tamanhoInicial;
        meusDados = new Integer[tamanho];
    }

    public MinhaFila() {
        meusDados = new Integer[tamanho];
    }

    /**
     * Insere um elemento no final da fila.
     * 
     * Se a fila estiver cheia, lança {@link FilaCheiaException}.
     *
     * param item o elemento a ser inserido
     * throws FilaCheiaException quando a fila já está cheia
     */
    @Override
    public void enfileirar(Integer item) throws FilaCheiaException {
        if (isFull()) {
            throw new FilaCheiaException();
        }
        meusDados[cauda] = item;
        cauda = (cauda + 1) % tamanho;
        quantidade++;
    }

    /**
     * Remove e retorna o elemento no início da fila.
     * 
     * Se a fila estiver vazia, lança {@link FilaVaziaException}.
     *
     * return o elemento removido
     * throws FilaVaziaException quando a fila está vazia
     */
    @Override
    public Integer desenfileirar() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException();
        }
        Integer item = meusDados[cabeca];
        meusDados[cabeca] = null;
        cabeca = (cabeca + 1) % tamanho;
        quantidade--;
        return item;
    }

    /**
     * Retorna o elemento que está na cauda (fim) da fila sem removê-lo.
     *
     * return o elemento na cauda da fila, ou null se a fila estiver vazia
     */
    @Override
    public Integer verificarCauda() {
        if (isEmpty()) return null;
        int pos = (cauda - 1 + tamanho) % tamanho;
        return meusDados[pos];
    }

    /**
     * Retorna o elemento que está na cabeça (início) da fila sem removê-lo.
     *
     * return o elemento na cabeça da fila, ou null se a fila estiver vazia
     */
    @Override
    public Integer verificarCabeca() {
        if (isEmpty()) return null;
        return meusDados[cabeca];
    }

    /**
     * Verifica se a fila está vazia.
     *
     * return true se a fila estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return quantidade == 0;
    }

    /**
     * Verifica se a fila está cheia.
     *
     * return true se a fila estiver cheia, false caso contrário
     */
    @Override
    public boolean isFull() {
        return quantidade == tamanho;
    }
}
