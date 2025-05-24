package tad.pilha;

/**
 * Autor: Raiff Ferreira Telecio e Guilherme Ribeiro
 * Disciplina: LEDA
 * Roteiro: Implementação de Pilha com arrays
 * 
 * Implementação de uma pilha utilizando um array de tamanho fixo.
 * Suporta operações básicas de pilha como empilhar, desempilhar, verificar topo e verificar se está vazia.
*/

public class MinhaPilha implements PilhaIF<Integer> {
	
	private int tamanho = 10;
	private Integer[] meusDados = null;
	private int topo = -1;

	/**
	 * Construtor que permite definir o tamanho da pilha.
	 * 
	 * param tamanho o tamanho máximo da pilha
	 */
	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		this.meusDados = new Integer[tamanho];
	}

	/**
	 * Construtor padrão com tamanho predefinido (10).
	 */
	public MinhaPilha() {
		this.meusDados = new Integer[this.tamanho];
	}

	/**
	 * Insere um novo elemento no topo da pilha.
	 * 
	 * param item o elemento a ser empilhado
	 * throws PilhaCheiaException se a pilha estiver cheia
	 */
	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (topo == tamanho - 1) {
			throw new PilhaCheiaException();
		}
		meusDados[++topo] = item;
	}

	/**
	 * Remove e retorna o elemento no topo da pilha.
	 * 
	 * return o elemento removido do topo
	 * throws PilhaVaziaException se a pilha estiver vazia
	 */
	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException();
		}
		Integer valor = meusDados[topo];
		meusDados[topo--] = null;
		return valor;
	}

	/**
	 * Retorna o elemento no topo da pilha sem removê-lo.
	 * 
	 * return o elemento no topo ou {@code null} se a pilha estiver vazia
	 */
	@Override
	public Integer topo() {
		if (isEmpty()) {
			return null;
		}
		return meusDados[topo];
	}

	/**
	 * Retorna uma nova pilha contendo os {@code k} elementos do topo.
	 * 
	 * param k a quantidade de elementos a serem copiados
	 * return nova pilha com os {@code k} elementos do topo
	 */
	@Override
	public PilhaIF<Integer> multitop(int k) {
		MinhaPilha novaPilha = new MinhaPilha(k);
		for (int i = topo, count = 0; i >= 0 && count < k; i--, count++) {
			try {
				novaPilha.empilhar(meusDados[i]);
			} catch (PilhaCheiaException e) {
				break;
			}
		}
		return novaPilha;
	}

	/**
	 * Verifica se a pilha está vazia.
	 * 
	 * return {@code true} se estiver vazia, {@code false} caso contrário
	 */
	@Override
	public boolean isEmpty() {
		return topo == -1;
	}
}
