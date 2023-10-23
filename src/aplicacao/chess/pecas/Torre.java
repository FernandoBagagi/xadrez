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
        return Cor.BRANCO.equals(this.getCor()) ? "T" : "t";
    }

    @Override
    public boolean[][] movimentacoesPossiveis() {
        final int linhas = this.getTabuleiro().getLinhas();
        final int colunas = this.getTabuleiro().getColunas();
        boolean[][] matrizPossiveisMovimentos = new boolean[linhas][colunas];

        this.moverParaNorte(matrizPossiveisMovimentos);
        this.moverParaLeste(matrizPossiveisMovimentos);
        this.moverParaSul(matrizPossiveisMovimentos);
        this.moverParaOeste(matrizPossiveisMovimentos);

        return matrizPossiveisMovimentos;
    }

    private void moverParaNorte(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - 1, this.posicao.getColuna());
        while (true) {
            boolean existePosicao = this.getTabuleiro().posicaoExisteTratado(posicaoAux);
            boolean isEspacoLivre = existePosicao && !this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            boolean temPecaDoOponente = existePosicao && !isEspacoLivre && this.existePecaDoOponenteNa(posicaoAux);
            if (isEspacoLivre || temPecaDoOponente) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            if(!isEspacoLivre || temPecaDoOponente) {
                break;
            }
            posicaoAux.setLinha(posicaoAux.getLinha() - 1);
        }
    }

    private void moverParaLeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() + 1);
        while (true) {
            boolean existePosicao = this.getTabuleiro().posicaoExisteTratado(posicaoAux);
            boolean isEspacoLivre = existePosicao && !this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            boolean temPecaDoOponente = existePosicao && !isEspacoLivre && this.existePecaDoOponenteNa(posicaoAux);
            if (isEspacoLivre || temPecaDoOponente) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            if(!isEspacoLivre || temPecaDoOponente) {
                break;
            }
            posicaoAux.setColuna(posicaoAux.getColuna() + 1);
        }
    }

    private void moverParaSul(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() + 1, this.posicao.getColuna());
        while (true) {
            boolean existePosicao = this.getTabuleiro().posicaoExisteTratado(posicaoAux);
            boolean isEspacoLivre = existePosicao && !this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            boolean temPecaDoOponente = existePosicao && !isEspacoLivre && this.existePecaDoOponenteNa(posicaoAux);
            if (isEspacoLivre || temPecaDoOponente) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            if(!isEspacoLivre || temPecaDoOponente) {
                break;
            }
            posicaoAux.setLinha(posicaoAux.getLinha() + 1);
        }
    }

    private void moverParaOeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() - 1);
        while (true) {
            boolean existePosicao = this.getTabuleiro().posicaoExisteTratado(posicaoAux);
            boolean isEspacoLivre = existePosicao && !this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
            boolean temPecaDoOponente = existePosicao && !isEspacoLivre && this.existePecaDoOponenteNa(posicaoAux);
            if (isEspacoLivre || temPecaDoOponente) {
                matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            if(!isEspacoLivre || temPecaDoOponente) {
                break;
            }
            posicaoAux.setColuna(posicaoAux.getColuna() - 1);
        }
    }

}
