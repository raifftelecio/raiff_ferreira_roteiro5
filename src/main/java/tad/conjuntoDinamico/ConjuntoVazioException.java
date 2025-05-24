package tad.conjuntoDinamico;

public class ConjuntoVazioException extends Exception {

    public ConjuntoVazioException() {
        super("conjunto vazio!!");
    }

    public ConjuntoVazioException(String message) {
        super(message);
    }
}
