package aplicacao.boardgame;

public class Tabuleiro {
    
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        this.pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return this.pecas.length;
    }

    public int getColunas() {
        return this.pecas[0].length;
    }

    public Peca[][] getPecas() {
        return this.pecas;
    }

    public Peca getPeca(int linha, int coluna) {
        return this.pecas[linha][coluna];
    }

    public Peca getPeca(Posicao posicao) {
        return this.pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void posicionarPeca(Peca peca, Posicao posicao) {
        //TODO:
    }

    public Peca removerPeca(Posicao posicao) {
        //TODO
        return null;
    }

    public boolean posicaoExiste(Posicao posicao) {
        //TODO
        return false;
    }

    public boolean existeUmaPecaNaPosicao(Posicao posicao){
        //TODO
        return false;
    }
}
