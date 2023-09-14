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
        this.moverParaBaixo(matrizPossiveisMovimentos);
        this.moverParaEsquerda(matrizPossiveisMovimentos);
        this.moverParaDireita(matrizPossiveisMovimentos);
        
        return matrizPossiveisMovimentos;
    }

    private void moverParaCima(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - 1, this.posicao.getColuna());
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

    private void moverParaBaixo(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() + 1, this.posicao.getColuna());
        while (true) {
            boolean existePosicao = this.getTabuleiro().posicaoExiste(posicaoAux);
            boolean isEspacoLivre = existePosicao && !this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            boolean temPecaDoOponente = existePosicao && !isEspacoLivre && this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            if (isEspacoLivre) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
                posicaoAux.setLinha(posicaoAux.getLinha() + 1);
            } else if (temPecaDoOponente) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;                
            } else {
                break;
            }
        }
    }

    private void moverParaEsquerda(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() - 1);
        while (true) {
            boolean existePosicao = this.getTabuleiro().posicaoExiste(posicaoAux);
            boolean isEspacoLivre = existePosicao && !this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            boolean temPecaDoOponente = existePosicao && !isEspacoLivre && this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            if (isEspacoLivre) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
                posicaoAux.setColuna(posicaoAux.getColuna() - 1);
            } else if (temPecaDoOponente) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;                
            } else {
                break;
            }
        }
    }

    private void moverParaDireita(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() + 1);
        while (true) {
            boolean existePosicao = this.getTabuleiro().posicaoExiste(posicaoAux);
            boolean isEspacoLivre = existePosicao && !this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            boolean temPecaDoOponente = existePosicao && !isEspacoLivre && this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            if (isEspacoLivre) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
                posicaoAux.setColuna(posicaoAux.getColuna() + 1);
            } else if (temPecaDoOponente) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;                
            } else {
                break;
            }
        }
    }

}
