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

    // public abstract boolean[][] movimentacoesPossiveis();

    
    /*
     * public boolean isMovimentacaoPossivel(Posicao posicao){
     * return false;
     * }
     */

    
    /*
     * public boolean isPossivelSeMovimentar(){
     * return false;
     * }
     */
}
