package tad.conjuntoDinamico;

/**
 * Autor: Raiff Ferreira Telecio e Guilherme Ribeiro
 * Disciplina: LEDA
 * Roteiro: Implementação de Conjunto Dinâmico com Array
 * 
 * Esta classe implementa a interface ConjuntoDinamicoIF utilizando um array dinâmico para armazenar 
 * elementos do tipo Integer. O conjunto permite inserções, buscas, remoções, e operações como predecessor, 
 * sucessor, mínimo e máximo. O array é redimensionado automaticamente quando atinge a capacidade máxima.
*/

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

    private Integer[] meusDados = new Integer[10];
    private int posInsercao = 0;

    /**
     * Insere um novo item no conjunto. Caso o array esteja cheio,
     * o método aumenta sua capacidade.
     *
     * @param item o valor inteiro a ser inserido
     */
    @Override
    public void inserir(Integer item) {
        if (arrayEstaCheio()) {
            aumentarArray();
        }
        meusDados[posInsercao] = item;
        posInsercao++;
    }

    /**
     * Verifica se o array interno está cheio.
     * 
     * return true se o array estiver cheio, false caso contrário
     */
    private boolean arrayEstaCheio() {
        return this.posInsercao == this.meusDados.length;
    }

    /**
     * Aumenta a capacidade do array interno para o dobro do tamanho atual,
     * copiando os elementos do array antigo para um novo.
     */
    private void aumentarArray() {
        Integer[] copia = new Integer[this.meusDados.length * 2];
        for (int index = 0; index < this.meusDados.length; index++) {
            copia[index] = this.meusDados[index];
        }
        this.meusDados = copia;
    }

    /**
     * Remove a primeira ocorrência do item no conjunto.
     * Realoca os elementos seguintes para preencher o espaço removido.
     *
     * param item o valor a ser removido
     * return o item removido, ou null se não encontrado
     * throws ConjuntoVazioException se o conjunto estiver vazio
     */
    @Override
    public Integer remover(Integer item) throws ConjuntoVazioException {
        Integer retorno = null;
        if (this.tamanho() == 0) {
            throw new ConjuntoVazioException();
        } else {
            for (int index = 0; index < this.posInsercao; index++) {
                if (this.meusDados[index].equals(item)) {
                    for (int iterador = index; iterador < this.posInsercao - 1; iterador++) {
                        this.meusDados[iterador] = this.meusDados[iterador + 1];
                    }
                    this.meusDados[this.posInsercao - 1] = null;
                    this.posInsercao--;
                    retorno = item;
                }
            }
        }
        return retorno;
    }

    /**
     * Retorna o valor imediatamente anterior ao item informado.
     *
     * param item o item de referência
     * return o predecessor, se existir
     * throws ConjuntoVazioException se o conjunto estiver vazio ou o item não existir
     */
    @Override
    public Integer predecessor(Integer item) throws ConjuntoVazioException {
        Integer retorno = null;
        if (this.tamanho() == 0) {
            throw new ConjuntoVazioException();
        } else {
            for (int index = 0; index < this.posInsercao; index++) {
                if (this.meusDados[index].equals(item)) {
                    if (index > 0) {
                        retorno = this.meusDados[index - 1];
                    }
                    break;
                }
                if (index == this.posInsercao - 1 && retorno == null) {
                    throw new ConjuntoVazioException();
                }
            }
        }
        return retorno;
    }

    /**
     * Retorna o valor imediatamente seguinte ao item informado.
     *
     * param item o item de referência
     * return o sucessor, se existir
     * throws ConjuntoVazioException se o conjunto estiver vazio ou o item não existir
     */
    @Override
    public Integer sucessor(Integer item) throws ConjuntoVazioException {
        Integer retorno = null;
        if (this.tamanho() == 0) {
            throw new ConjuntoVazioException();
        } else {
            for (int index = 0; index < this.posInsercao; index++) {
                if (this.meusDados[index].equals(item)) {
                    if (index < this.posInsercao - 1) {
                        retorno = this.meusDados[index + 1];
                    }
                    break;
                }
                if (index == this.posInsercao - 1 && retorno == null) {
                    throw new ConjuntoVazioException();
                }
            }
        }
        return retorno;
    }

    /**
     * Retorna a quantidade de elementos válidos no conjunto.
     *
     * return o tamanho do conjunto
     */
    @Override
    public int tamanho() {
        int cont = 0;
        if (this.meusDados != null) {
            for (Integer dado : this.meusDados) {
                if (dado != null) {
                    cont++;
                }
            }
        }
        return cont;
    }

    /**
     * Busca e retorna o item informado, se estiver presente no conjunto.
     *
     * param item o valor a ser buscado
     * return o item encontrado, ou null se não existir
     */
    @Override
    public Integer buscar(Integer item) {
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                return meusDados[i];
            }
        }
        return null;
    }

    /**
     * Retorna o menor valor armazenado no conjunto.
     *
     * return o valor mínimo
     * throws ConjuntoVazioException se o conjunto estiver vazio
     */
    @Override
    public Integer minimum() throws ConjuntoVazioException {
        Integer retorno = null;
        if (this.tamanho() == 0) {
            throw new ConjuntoVazioException();
        } else {
            Integer min = this.meusDados[0];
            for (int index = 1; index < this.posInsercao; index++) {
                if (this.meusDados[index] < min) {
                    min = this.meusDados[index];
                }
            }
            retorno = min;
        }
        return retorno;
    }

    /**
     * Retorna o maior valor armazenado no conjunto.
     *
     * return o valor máximo
     * throws ConjuntoVazioException se o conjunto estiver vazio
     */
    @Override
    public Integer maximum() throws ConjuntoVazioException {
        Integer retorno = null;
        if (this.tamanho() == 0) {
            throw new ConjuntoVazioException();
        } else {
            Integer max = this.meusDados[0];
            for (int index = 1; index < this.posInsercao; index++) {
                if (this.meusDados[index] > max) {
                    max = this.meusDados[index];
                }
            }
            retorno = max;
        }
        return retorno;
    }
}
