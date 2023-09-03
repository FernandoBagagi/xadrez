package aplicacao.chess;

import aplicacao.boardgame.Posicao;
import lombok.Getter;

@Getter
public class PosicaoXadrez {

    private char coluna;
    private int linha;

    public PosicaoXadrez(char coluna, int linha) {
        if (coluna < 'a' || coluna > 'h') {
            throw new XadrezExcecao("É esperdo que a coluna esteja entre 'a' e 'h', encontrado " + coluna);
        }
        if (linha < 1 || linha > 8) {
            throw new XadrezExcecao("É esperdo que a linha esteja entre 1 e 8, encontrado " + linha);
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    @Override
    public String toString() {
        return "%c%d".formatted(coluna, linha);
    }

    protected Posicao toPosicao() {
        return new Posicao(8 - this.linha, this.coluna - 'a');
    }

    protected static PosicaoXadrez fromPosicao(Posicao posicao) {
        return new PosicaoXadrez((char) ('a' - posicao.getColuna()), 8 - posicao.getLinha());
    }
}
