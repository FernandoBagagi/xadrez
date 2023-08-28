package aplicacao;

import aplicacao.boardgame.Posicao;

public class App {
    public static void main(String[] args) throws Exception {
        Posicao p = new Posicao(1, 1);
        System.out.println(p.toString());
    }
}
