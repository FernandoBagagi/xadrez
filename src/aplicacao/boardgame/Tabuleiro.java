package aplicacao.boardgame;

public class Tabuleiro {

    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1) {
            throw new TabuleiroExcecao("O número de linhas deve ser maior que 0");
        }
        if (colunas < 1) {
            throw new TabuleiroExcecao("O número de colunas deve ser maior que 0");
        }
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
        if (this.posicaoExiste(linha, coluna)) {
            return this.pecas[linha][coluna];
        }
        return null; // Deve ter exceção e não retorna null
    }

    public Peca getPeca(Posicao posicao) {
        return this.getPeca(posicao.getLinha(), posicao.getColuna());
    }

    public void posicionarPeca(Peca peca, Posicao posicao) {
        if (this.existeUmaPecaNaPosicao(posicao)) {
            throw new TabuleiroExcecao("Já existe outra peça na posição " + posicao);
        }
        this.pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao; // Consegue acessar pois é protected
    }

    /*
     * //TODO
     * public Peca removerPeca(Posicao posicao) {
     * return null;
     * }
     */

    private boolean posicaoExiste(int linha, int coluna) {
        boolean linhaExiste = linha >= 0 && linha < this.getLinhas();
        if (!linhaExiste) {
            throw new TabuleiroExcecao("Posição inválida! A linha " + linha + " não existe no tabuleiro!");
        }
        boolean colunaExiste = coluna >= 0 && coluna < this.getColunas();
        if (!colunaExiste) {
            throw new TabuleiroExcecao("Posição inválida! A coluna " + coluna + " não existe no tabuleiro!");
        }
        return true;
    }

    public boolean posicaoExiste(Posicao posicao) {
        return this.posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }

    public boolean existeUmaPecaNaPosicao(Posicao posicao) {
        return this.getPeca(posicao) != null;
    }

}
