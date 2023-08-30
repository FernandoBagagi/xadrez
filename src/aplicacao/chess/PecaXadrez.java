package aplicacao.chess;

import aplicacao.boardgame.Peca;
import aplicacao.boardgame.Tabuleiro;

public class PecaXadrez extends Peca{
    
    private Color cor;

    public PecaXadrez(Tabuleiro tabuleiro, Color cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Color getCor() {
        return cor;
    }

}
