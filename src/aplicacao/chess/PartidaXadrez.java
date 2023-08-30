package aplicacao.chess;

import aplicacao.boardgame.Tabuleiro;

public class PartidaXadrez {
    
    Tabuleiro tabuleiro;

    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
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

}
