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
    
    //public abstract boolean[][] movimentacoesPossiveis();

    //TODO: 
    /*public boolean isMovimentacaoPossivel(Posicao posicao){
        return false;
    }*/

    //TODO: 
    /*public boolean isPossivelSeMovimentar(){
        return false;
    }*/
}
