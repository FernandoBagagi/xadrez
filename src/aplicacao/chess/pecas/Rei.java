package aplicacao.chess.pecas;

import aplicacao.boardgame.Tabuleiro;
import aplicacao.chess.Cor;
import aplicacao.chess.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return Cor.BRANCO.equals(this.getCor()) ?  "K" : "k";
    }

    @Override
    public boolean[][] movimentacoesPossiveis() {
        final int linhas = this.getTabuleiro().getLinhas();
        final int colunas = this.getTabuleiro().getColunas();
        boolean[][] matrizPossiveisMovimentos = new boolean[linhas][colunas];
        return matrizPossiveisMovimentos;
    }

}
