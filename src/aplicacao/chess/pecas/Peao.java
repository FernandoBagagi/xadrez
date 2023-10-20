package aplicacao.chess.pecas;

import aplicacao.boardgame.Posicao;
import aplicacao.boardgame.Tabuleiro;
import aplicacao.chess.Cor;
import aplicacao.chess.PecaXadrez;

public class Peao extends PecaXadrez {

    public Peao(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return Cor.BRANCO.equals(this.getCor()) ? "P" : "p";
    }

    @Override
    public boolean[][] movimentacoesPossiveis() {
        final int linhas = this.getTabuleiro().getLinhas();
        final int colunas = this.getTabuleiro().getColunas();
        boolean[][] matrizPossiveisMovimentos = new boolean[linhas][colunas];
        this.mover(matrizPossiveisMovimentos, Cor.BRANCO.equals(this.getCor()) ? 1 : -1);
        this.capturar(matrizPossiveisMovimentos, Cor.BRANCO.equals(this.getCor()) ? 1 : -1);
        return matrizPossiveisMovimentos;
    }

    private void mover(boolean[][] matrizPossiveisMovimentos, final int sinal) {
        
        //Andar uma casa
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - (1 * sinal), this.posicao.getColuna());        
        boolean existePosicao = this.getTabuleiro().posicaoExisteTratado(posicaoAux);
        boolean isEspacoLivre = existePosicao && !this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
        final boolean isPrimeiraCasaLivre = isEspacoLivre;

        if(isEspacoLivre) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        //Andar duas casas, caso especial da primeira jogada
        posicaoAux.setLinha(this.posicao.getLinha() - (2 * sinal));
        existePosicao = this.getTabuleiro().posicaoExisteTratado(posicaoAux);
        isEspacoLivre = existePosicao && !this.getTabuleiro().existeUmaPecaNaPosicao(posicaoAux);
        final boolean isPrimeiraJogada = this.getContadorMovimentos() == 0;
        
        if(isEspacoLivre && isPrimeiraCasaLivre && isPrimeiraJogada) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

    private void capturar(boolean[][] matrizPossiveisMovimentos, final int sinal) {

        //Capturar esquerda
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - (1 * sinal), this.posicao.getColuna() - 1);
        boolean existePosicao = this.getTabuleiro().posicaoExisteTratado(posicaoAux);
        if(existePosicao && this.existePecaDoOponenteNa(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        //Capturar direita
        posicaoAux.setLinha(this.posicao.getLinha() - (1 * sinal));
        posicaoAux.setColuna(this.posicao.getColuna() + 1);
        existePosicao = this.getTabuleiro().posicaoExisteTratado(posicaoAux);
        if(existePosicao && this.existePecaDoOponenteNa(posicaoAux)) {
            matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
    }

}
