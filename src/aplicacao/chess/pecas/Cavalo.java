package aplicacao.chess.pecas;

import aplicacao.boardgame.Posicao;
import aplicacao.boardgame.Tabuleiro;
import aplicacao.chess.Cor;
import aplicacao.chess.PecaXadrez;

public class Cavalo extends PecaXadrez {

    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return Cor.BRANCO.equals(this.getCor()) ? "C" : "c";
    }

    @Override
    public boolean[][] movimentacoesPossiveis() {
        final int linhas = this.getTabuleiro().getLinhas();
        final int colunas = this.getTabuleiro().getColunas();
        boolean[][] matrizPossiveisMovimentos = new boolean[linhas][colunas];
        this.moverParaNorteOeste(matrizPossiveisMovimentos);
        this.moverParaNorteLeste(matrizPossiveisMovimentos);
        this.moverParaLesteNorte(matrizPossiveisMovimentos);
        this.moverParaLesteSul(matrizPossiveisMovimentos);
        this.moverParaSulLeste(matrizPossiveisMovimentos);
        this.moverParaSulOeste(matrizPossiveisMovimentos);
        this.moverParaOesteSul(matrizPossiveisMovimentos);
        this.moverParaOesteNorte(matrizPossiveisMovimentos);
        return matrizPossiveisMovimentos;
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez peca = (PecaXadrez) this.getTabuleiro().getPeca(posicao);
        return peca == null || !this.getCor().equals(peca.getCor());
    }

    private void moverParaNorteOeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - 2, this.posicao.getColuna() - 1);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaNorteLeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - 2, this.posicao.getColuna() + 1);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaLesteNorte(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - 1, this.posicao.getColuna() + 2);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaLesteSul(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() + 1, this.posicao.getColuna() + 2);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaSulLeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() + 2, this.posicao.getColuna() + 1);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaSulOeste(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() + 2, this.posicao.getColuna() - 1);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaOesteSul(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() + 1, this.posicao.getColuna() - 2);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void moverParaOesteNorte(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - 1, this.posicao.getColuna() - 2);
        if (this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

}
