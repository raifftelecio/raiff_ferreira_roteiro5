package tad.listasEncadeadas;

import tad.util.Conversor;

/**
 * Autor: Raiff Ferreira Telecio e Guilherme Ribeiro
 * Disciplina: LEDA
 * Roteiro: Implementação de uma Lista Encadeada
 * 
 * Esta classe implementa uma lista encadeada genérica com sentinelas de cabeça e cauda,
 * que suporta operações básicas como inserção, remoção, busca e conversão para array.
 * 
 * Objetivo:
 * Fornecer uma implementação de lista encadeada que armazena elementos comparáveis,
 * permitindo manipulação eficiente e ordenada dos elementos.
 * 
 * Características principais:
 * - Utiliza nós com referência para próximo elemento (encadeamento simples).
 * - Implementa métodos para inserção no final, remoção, busca, e obtenção de sucessores e predecessores.
 * - Métodos para imprimir a lista na ordem normal e inversa.
 * - Métodos para verificar se a lista está vazia e obter seu tamanho.
*/

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T>{

	
	NodoListaEncadeada<T> cabeca = null; // Estratégia usando marcação sentinela
	NodoListaEncadeada<T> cauda = null;// Estratégia usando marcação sentinela
	
	public ListaEncadeadaImpl() {// Estratégia usando marcação sentinela
		cabeca = new NodoListaEncadeada<T>();
		cauda = new NodoListaEncadeada<T>();
		cabeca.setProximo(cauda);
	}
	

	@Override
	public boolean isEmpty() {
		return (cabeca==null || cabeca.isNull()) && (cauda ==null || cauda.isNull());
	}

	@Override
	public int size() {
		int tamanho = 0;
		if (!isEmpty()){
			NodoListaEncadeada<T> atual = cabeca;
			while (atual != null) {
				tamanho++;
				atual = atual.getProximo();
			}
		}
		return tamanho;
	}

	@Override
	public NodoListaEncadeada<T> search(T chave) {
		NodoListaEncadeada<T> atual = null;
		if (!isEmpty()){
			atual = cabeca;
			while (atual.getProximo()!=null){
				if (atual.getChave().equals(chave)){
					break;
				}
				atual = atual.getProximo();
			}
			if(atual.getChave()!=chave){
				atual= null;
			}
		}

		return atual;
	}

	@Override
	public void insert(T chave) {
		NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<>(chave);
		if (isEmpty()){
			cabeca = novoNo;
			cauda = novoNo;
		} else{
			cauda.setProximo(novoNo);
			cauda = novoNo;
		}
	}

	/**
     * Remove o nodo que contém a chave especificada.
     * 
     * param chave A chave do nodo a ser removido.
     * return O nodo removido.
     * throws ListaVaziaException Se a lista estiver vazia.
     */
	@Override
	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException {
		if (isEmpty()) {
			throw new ListaVaziaException();
		}

		NodoListaEncadeada<T> removido = null;
		NodoListaEncadeada<T> atual = cabeca;
		NodoListaEncadeada<T> anterior = null;

		while (atual != null && !atual.getChave().equals(chave)) {
			anterior = atual;
			atual = atual.getProximo();
		}

		if (atual == cabeca) {
			removido = cabeca;
			cabeca = cabeca.getProximo();
			if (cabeca == null) {
				cauda = null;
			}
		} else {
			removido = atual;
			anterior.setProximo(atual.getProximo());
			if (removido == cauda) {
				cauda = anterior;
			}
		}
		return removido;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		Integer[] elementos = null;
		if (!isEmpty()){
			elementos = new Integer[this.size()];
			NodoListaEncadeada<T> atual = cabeca;
			for (int index = 0; index<this.size(); index++){
				elementos[index] = ((Integer) atual.getChave());
				atual = atual.getProximo();
			}
		}

		return (T[]) elementos;
	}

	@Override
	public String imprimeEmOrdem() {
		Integer[] elementos = null;
		String saida = "";
		if (!isEmpty()){
			elementos = new Integer[this.size()];
			NodoListaEncadeada<T> atual = cabeca;
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

	@Override
	public String imprimeInverso() {
		Integer[] elementos = null;
		String saida = "";
		if (!isEmpty()){
			elementos = new Integer[this.size()];
			NodoListaEncadeada<T> atual = cabeca;
			for (int index = this.size()-1; index>=0; index--){
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

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		NodoListaEncadeada<T> atual = null;
		if (!isEmpty()){
			atual = cabeca;
			while (atual.getProximo()!=null && !atual.getChave().equals(chave)){
				atual = atual.getProximo();
			}
			atual = atual.getProximo();
		}

		return atual;
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		NodoListaEncadeada<T> atual = null;
		NodoListaEncadeada<T> anterior = null;
		if (!isEmpty()){
			atual = cabeca;
			while (atual.getProximo()!=null && !atual.getChave().equals(chave)){
				anterior = atual;
				atual = atual.getProximo();
			}
		}

		return anterior;
	}

	@Override
	public void insert(T chave, int index) {
		throw new UnsupportedOperationException("Precisa implementar!");
	}

}
