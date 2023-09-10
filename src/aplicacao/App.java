package aplicacao;

import aplicacao.chess.PartidaXadrez;

public class App {
    public static void main(String[] args) throws Exception {
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        UserInterface.imprimirTabuleiro(partidaXadrez.getPecasXadrez());
    }
    /*Não zerar */
    /*Não zerar 2*/
}
