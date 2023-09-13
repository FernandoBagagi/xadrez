package aplicacao.chess;

import aplicacao.boardgame.Peca;
import aplicacao.boardgame.Posicao;
import aplicacao.boardgame.Tabuleiro;

public abstract class PecaXadrez extends Peca {

    private Cor cor;

    protected PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    protected boolean existePecaDoOponenteNa(Posicao posicao) {
        PecaXadrez peca = (PecaXadrez) this.getTabuleiro().getPeca(posicao);
        return peca != null && !this.cor.equals(peca.getCor());
    }
}
