package aplicacao.chess.pecas;

import aplicacao.boardgame.Posicao;
import aplicacao.boardgame.Tabuleiro;
import aplicacao.chess.Cor;
import aplicacao.chess.PecaXadrez;

public class Torre extends PecaXadrez {

    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return Cor.BRANCO.equals(this.getCor()) ?  "T" : "t";
    }

    @Override
    public boolean[][] movimentacoesPossiveis() {
        final int linhas = this.getTabuleiro().getLinhas();
        final int colunas = this.getTabuleiro().getColunas();
        boolean[][] matrizPossiveisMovimentos = new boolean[linhas][colunas];

        this.moverParaCima(matrizPossiveisMovimentos);
        //Mover para baixo
        //Mover para esquerda
        //Mover para direita
        
        return matrizPossiveisMovimentos;
    }

    private void moverParaCima(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao();

        //Mover para cima
        posicaoAux.setLinha(this.posicao.getLinha() - 1);
        posicaoAux.setColuna(this.posicao.getColuna());
        while (true) {
            boolean existePosicao = this.getTabuleiro().posicaoExiste(posicaoAux);
            boolean isEspacoLivre = existePosicao && !this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            boolean temPecaDoOponente = existePosicao && !isEspacoLivre && this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            if (isEspacoLivre) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
                posicaoAux.setLinha(posicaoAux.getLinha() - 1);
            } else if (temPecaDoOponente) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;                
            } else {
                break;
            }
        }
    }

}
