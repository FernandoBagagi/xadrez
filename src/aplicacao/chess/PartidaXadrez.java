package aplicacao.chess;

import aplicacao.boardgame.Posicao;
import aplicacao.boardgame.Tabuleiro;
import aplicacao.chess.pecas.*;

public class PartidaXadrez {
    
    Tabuleiro tabuleiro;

    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        this.posicionarPecasInicio();
    }

    public PecaXadrez[][] getPecasXadrez() {
        final int linhas = this.tabuleiro.getLinhas();
        final int colunas = this.tabuleiro.getColunas();
        PecaXadrez[][] tabuleiroXadrez = new PecaXadrez[linhas][colunas];
        for(int i = 0; i < linhas; i++) {
            for(int j = 0; j < colunas; j++) {
                tabuleiroXadrez[i][j] = (PecaXadrez) this.tabuleiro.getPeca(i, j);
            }
        }
        
        return tabuleiroXadrez;
    }

    private void posicionarPecasInicio() {
        this.tabuleiro.posicionarPeca(new Torre(this.tabuleiro, Cor.PRETO), new Posicao(0, 0));
        this.tabuleiro.posicionarPeca(new Cavalo(this.tabuleiro, Cor.PRETO), new Posicao(0, 1));
        this.tabuleiro.posicionarPeca(new Bispo(this.tabuleiro, Cor.PRETO), new Posicao(0, 2));
        this.tabuleiro.posicionarPeca(new Rainha(this.tabuleiro, Cor.PRETO), new Posicao(0, 3));
        this.tabuleiro.posicionarPeca(new Rei(this.tabuleiro, Cor.PRETO), new Posicao(0, 4));
        this.tabuleiro.posicionarPeca(new Bispo(this.tabuleiro, Cor.PRETO), new Posicao(0, 5));
        this.tabuleiro.posicionarPeca(new Cavalo(this.tabuleiro, Cor.PRETO), new Posicao(0, 6));
        this.tabuleiro.posicionarPeca(new Torre(this.tabuleiro, Cor.PRETO), new Posicao(0, 7));

        
    }
}
