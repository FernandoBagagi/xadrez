package aplicacao.boardgame;

public abstract class Peca {

    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) {
        this.posicao = null;
        this.tabuleiro = tabuleiro;
    }

    protected Tabuleiro getTabuleiro() {
        return this.tabuleiro;
    }

    public abstract boolean[][] movimentacoesPossiveis();

    /*Hook Methods -> Métodos concretos que chamam métodos abstratos*/
    /*Template Methods -> oferece um padrão de método que depende de objetos abstratos*/
    public boolean isMovimentacaoPossivel(Posicao posicao) {
        return this.movimentacoesPossiveis()[posicao.getLinha()][posicao.getColuna()];
    }

    public boolean existePeloMenosUmaMovimentacaoPossivel() {
        for (boolean[] linha : this.movimentacoesPossiveis()) {
            for (boolean item : linha) {
                if (item) {
                    return true;
                }
            }
        }
        return false;
    }

}
