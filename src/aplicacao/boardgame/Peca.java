package aplicacao.boardgame;

public abstract class Peca {
    
    protected Posicao posicao;

    public abstract boolean[][] movimentacoesPossiveis();

    public boolean isMovimentacaoPossivel(Posicao posicao){
        //TODO: 
        return false;
    }

    public boolean isPossivelSeMovimentar(){
        //TODO: 
        return false;
    }
}
