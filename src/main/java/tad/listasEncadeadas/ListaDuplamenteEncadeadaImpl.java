package tad.listasEncadeadas;

/**
 * Autor: Raiff Ferreira Telecio e Guilherme Ribeiro
 * Disciplina: LEDA
 * Roteiro: Implementação de uma Lista Duplamente Encadeada
 * 
 * Esta classe é uma implementação de uma lista duplamente encadeada genérica com sentinelas para
 * facilitar operações de inserção, remoção e navegação.
 *
 * param <T> Tipo dos elementos armazenados na lista, que devem ser comparáveis.
*/

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {
	
	//TODO: implementar o nó cabeça
	//TODO: implementar o nó cauda 
	//TODO: implementar as sentinelas
	
	NodoListaDuplamenteEncadeada<T> cabeca = null; // Estratégia usando marcação sentinela
	NodoListaDuplamenteEncadeada<T> cauda = null;// Estratégia usando marcação sentinela
	
	/**
	 * Construtor que inicializa a lista com os nós sentinelas cabeça e cauda,
	 * conectando-os de forma circular para facilitar a manipulação da lista.
	*/

	public ListaDuplamenteEncadeadaImpl() {// Estratégia usando marcação sentinela
		cabeca = new NodoListaDuplamenteEncadeada<T>();
		cauda = new NodoListaDuplamenteEncadeada<T>();
		cabeca.setProximo(cauda);
		
		// lista circular
		cabeca.setAnterior(cauda);
		cauda.setAnterior(cabeca);
		
	}

	@Override
	public boolean isEmpty() {
		return (cabeca==null || cabeca.isNull()) && (cauda ==null || cauda.isNull());
	}

	@Override
	public int size() {
		int tamanho = 0;
		if(!isEmpty()){
			NodoListaDuplamenteEncadeada<T> atual = cabeca;
			do {
				tamanho++;
				atual = atual.getProximo();
			} while (atual != cabeca);
		}
		return tamanho;
	}

	/**
	 * Procura na lista o primeiro nó cujo valor seja igual à chave fornecida.
	 *
	 * param chave elemento a ser buscado na lista.
	 * return o nó que contém a chave, ou null se não encontrado.
	*/

	@Override
	public NodoListaDuplamenteEncadeada<T> search(T chave) {
		NodoListaDuplamenteEncadeada<T> atual = null;
		if (!isEmpty()){
			atual = cabeca;
			if (!atual.getChave().equals(chave)){
				do {
					if (atual.getChave().equals(chave)) {
						break;
					}atual = atual.getProximo();
				} while (atual != cabeca);
				if (atual.getChave() != chave){
					atual = null;
				}
			}
		}
		return atual;
	}

	/**
	 * Insere um novo elemento no final da lista.
	 *
	 * param chave elemento a ser inserido.
	 */
	@Override
	public void insert(T chave) {
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(chave);
		if (isEmpty()) {
			cabeca = novoNo;
			cauda = novoNo;
			cabeca.setProximo(cabeca);
			cabeca.setAnterior(cabeca);
		} else if (cauda == null) {
			novoNo.setProximo(cabeca);
			novoNo.setAnterior(cabeca);
			cauda = novoNo;
		} else {
			NodoListaDuplamenteEncadeada<T> ultimo = cauda;

			ultimo.setProximo(novoNo);
			novoNo.setAnterior(ultimo);
			novoNo.setProximo(cabeca);
			cabeca.setAnterior(novoNo);

			cauda = novoNo;
		}
	}

	/**
	 * Remove o nó que contém o elemento igual à chave informada.
	 *
	 * param chave elemento que identifica o nó a ser removido.
	 * return o nó removido, ou null caso não encontrado.
	 * throws ListaVaziaException se a lista estiver vazia.
	 */
	@Override
	public NodoListaDuplamenteEncadeada<T> remove(T chave) throws ListaVaziaException {
		if (isEmpty()){
			throw new ListaVaziaException();
		}
		NodoListaDuplamenteEncadeada<T> removido = search(chave);
		if (removido != null) {
			if (removido == cabeca && removido == cauda) {
				cabeca = null;
				cauda = null;
			} else {
				if (removido == cabeca) {
					cabeca = removido.getProximo();
				}

				if (removido == cauda) {
					cauda = removido.getAnterior();
				}

				removido.getAnterior().setProximo(removido.getProximo());
				removido.getProximo().setAnterior(removido.getAnterior());
			}
		}
		return removido;
	}

	/**
	 * Retorna uma string contendo os elementos da lista na ordem natural.
	 *
	 * return String com elementos separados por vírgula e espaço, ou vazio se a lista estiver vazia.
	 */
	@Override
	public String imprimeEmOrdem() {
		Integer[] elementos;
		String saida = "";
		if (!isEmpty()){
			elementos = new Integer[this.size()];
			NodoListaDuplamenteEncadeada<T> atual = cabeca;
			for (int index = 0; index<elementos.length; index++){
				elementos[index] = (Integer) atual.getChave();
				atual = atual.getProximo();
			}
			StringBuilder sb = new StringBuilder();
			sb.append(elementos[0]);

			for (int index = 1; index < elementos.length; index++) {
				sb.append(", ").append(elementos[index]);
			}

			saida = sb.toString();
		}

		return saida;
	}

	/**
	 * Retorna uma string contendo os elementos da lista na ordem inversa.
	 *
	 * @return String com elementos separados por vírgula e espaço, ou vazio se a lista estiver vazia.
	 */
	@Override
	public String imprimeInverso() {
		Integer[] elementos;
		String saida = "";
		if (!isEmpty()) {
			elementos = new Integer[this.size()];
			NodoListaDuplamenteEncadeada<T> atual = cabeca;
			for (int index = this.size() - 1; index >= 0; index--) {
				elementos[index] = (Integer) atual.getChave();
				atual = atual.getProximo();
			}
			StringBuilder sb = new StringBuilder();
			sb.append(elementos[0]);

			for (int index = 1; index < elementos.length; index++) {
				sb.append(", ").append(elementos[index]);
			}

			saida = sb.toString();
		}

		return saida;
	}

	/**
	 * Retorna o nó sucessor ao nó que contém a chave informada.
	 *
	 * param chave elemento cujo sucessor será retornado.
	 * return nó sucessor, ou null se o nó for o último (cauda) ou não existir.
	 */
	@Override
	public NodoListaDuplamenteEncadeada<T> sucessor(T chave) {
		NodoListaDuplamenteEncadeada<T> atual = search(chave);
		if (atual == cauda) {
			atual = null;
		} else {
			atual = atual.getProximo();
		}
		return atual;
	}

	/**
	 * Retorna o nó predecessor ao nó que contém a chave informada.
	 *
	 * param chave elemento cujo predecessor será retornado.
	 * return nó predecessor, ou null se o nó for o primeiro (cabeça) ou não existir.
	 */
	@Override
	public NodoListaDuplamenteEncadeada<T> predecessor(T chave) {
		NodoListaDuplamenteEncadeada<T> atual = search(chave);
		if (atual == cabeca) {
			atual = null;
		} else {
			atual = atual.getAnterior();
		}
		return atual;
	}

	/**
	 * Converte os elementos da lista em um array.
	 *
	 * param clazz classe do tipo T para criação do array.
	 * return array contendo os elementos da lista, ou null se a lista estiver vazia.
	 */
	@Override
	public T[] toArray(Class<T> clazz) {
		Integer[] elementos = null;
		if (!isEmpty()) {
			elementos = new Integer[this.size()];
			NodoListaDuplamenteEncadeada<T> atual = cabeca;
			for (int index = 0; index < this.size(); index++) {
				elementos[index] = ((Integer) atual.getChave());
				atual = atual.getProximo();
			}
		}

		return (T[]) elementos;
	}

	/**
	 * Insere um elemento no início da lista.
	 *
	 * param elemento elemento a ser inserido.
	 */
	@Override
	public void inserePrimeiro(T elemento) {
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(elemento);
		novoNo.setProximo(cabeca);
		novoNo.setAnterior(cauda);
		cabeca.setAnterior(null);
		cauda.setProximo(novoNo);
		cabeca = novoNo;
	}

	/**
	 * Remove o último elemento da lista.
	 *
	 * return o nó que foi removido, ou null se a lista estiver vazia.
	 */
	@Override
	public NodoListaDuplamenteEncadeada<T> removeUltimo() {
		NodoListaDuplamenteEncadeada<T> ultimo = cauda;

		if (cabeca == cauda) {
			cabeca = null;
			cauda = null;
		} else {
			NodoListaDuplamenteEncadeada<T> penultimo = ultimo.getAnterior();
			penultimo.setProximo(cabeca);
			cabeca.setAnterior(penultimo);
			cauda = penultimo;
		}

		return ultimo;
	}

	/**
	 * Remove o primeiro elemento da lista.
	 *
	 * return o nó que foi removido, ou null se a lista estiver vazia.
	 */
	@Override
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
		NodoListaDuplamenteEncadeada<T> primeiro = cabeca;

		if (cabeca == cauda) {
			cabeca = null;
			cauda = null;
		} else {
			NodoListaDuplamenteEncadeada<T> segundo = primeiro.getProximo();
			cabeca = segundo;
			cabeca.setAnterior(null);

			cauda.setProximo(cabeca);
		}

		return primeiro;
	}

	/**
	 * Método para inserir um elemento em uma posição específica da lista.
	 * Atualmente não implementado.
	 *
	 * param chave elemento a ser inserido.
	 * param index posição na lista onde o elemento será inserido.
	 * throws UnsupportedOperationException indica que o método não foi implementado.
	 */
	@Override
	public void insert(T chave, int index) {
		throw new UnsupportedOperationException("Precisa implementar!");
	}

}

