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
        this.pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao; //Consegue acessar pois é protected
    }

    /*//TODO
    public Peca removerPeca(Posicao posicao) {
        return null;
    }*/

    private boolean posicaoExiste(int linha, int coluna) {
        boolean linhaExiste = linha >= 0 && linha < this.getLinhas();
        boolean colunaExiste = coluna >= 0 && linha < this.getColunas();
        return linhaExiste && colunaExiste;
    }

    public boolean posicaoExiste(Posicao posicao) {
        return this.posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }

    //TODO
    public boolean existeUmaPecaNaPosicao(Posicao posicao){
        return false;
    }
}
