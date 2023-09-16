package aplicacao.chess.pecas;

import aplicacao.boardgame.Posicao;
import aplicacao.boardgame.Tabuleiro;
import aplicacao.chess.Cor;
import aplicacao.chess.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return Cor.BRANCO.equals(this.getCor()) ?  "K" : "k";
    }

    @Override
    public boolean[][] movimentacoesPossiveis() {
        final int linhas = this.getTabuleiro().getLinhas();
        final int colunas = this.getTabuleiro().getColunas();
        boolean[][] matrizPossiveisMovimentos = new boolean[linhas][colunas];

        this.moverParaNorte(matrizPossiveisMovimentos);
        this.moverParaNordeste(matrizPossiveisMovimentos);
        this.moverParaLeste(matrizPossiveisMovimentos);
        this.moverParaSudeste(matrizPossiveisMovimentos);
        this.moverParaSul(matrizPossiveisMovimentos);
        this.moverParaSudoeste(matrizPossiveisMovimentos);
        this.moverParaOeste(matrizPossiveisMovimentos);
        this.moverParaNoroeste(matrizPossiveisMovimentos);

        return matrizPossiveisMovimentos;
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez peca = (PecaXadrez) this.getTabuleiro().getPeca(posicao);
        return peca == null || !this.getCor().equals(peca.getCor());
    }

    private void moverParaNorte(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - 1, this.posicao.getColuna());
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaNordeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - 1, this.posicao.getColuna() + 1);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaLeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() + 1);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaSudeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() + 1, this.posicao.getColuna() + 1);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaSul(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() + 1, this.posicao.getColuna());
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaSudoeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() + 1, this.posicao.getColuna() - 1);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaOeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() - 1);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaNoroeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - 1, this.posicao.getColuna() - 1);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

}
