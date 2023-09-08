package aplicacao.boardgame;

public class Peca {

    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) {
        this.posicao = null;
        this.tabuleiro = tabuleiro;
    }

    protected Tabuleiro getTabuleiro() {
        return this.tabuleiro;
    }

    public boolean[][] movimentacoesPossiveis() {
        //TODO: 
        return null;
    }

    public boolean isMovimentacaoPossivel(Posicao posicao) {
        //TODO: 
        return false;
    }

    public boolean isPossivelSeMovimentar() {
        //TODO: 
        return false;
    }

}
