package aplicacao.chess.pecas;

import aplicacao.boardgame.Posicao;
import aplicacao.boardgame.Tabuleiro;
import aplicacao.chess.Cor;
import aplicacao.chess.PartidaXadrez;
import aplicacao.chess.PecaXadrez;

public class Peao extends PecaXadrez {

    private PartidaXadrez partidaXadrez;

    public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
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
        final int sinal = Cor.BRANCO.equals(this.getCor()) ? 1 : -1;
        this.mover(matrizPossiveisMovimentos, sinal);
        this.capturar(matrizPossiveisMovimentos, sinal);
        this.capturarEnPassant(matrizPossiveisMovimentos, sinal);
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
    
    private void capturarEnPassant(boolean[][] matrizPossiveisMovimentos, final int sinal) {
        final int linhaEnPassant = sinal == 1 ? 3 : 4; //Se for branca é na linha 3, se for preta é linha 4
        if(this.posicao.getLinha() == linhaEnPassant) {
            //Capturar esquerda
            final Posicao esquerda = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() - 1);
            boolean existePosicao = this.getTabuleiro().posicaoExisteTratado(esquerda);
            boolean isPecaOponente = existePosicao && this.existePecaDoOponenteNa(esquerda);
            boolean isPeaoVuneravelEnPassant = isPecaOponente && this.getTabuleiro().getPeca(esquerda).equals(this.partidaXadrez.getPeaoVuneravelEnPassant());
            if(isPeaoVuneravelEnPassant) {
                matrizPossiveisMovimentos[esquerda.getLinha() - (1 * sinal)][esquerda.getColuna()] = true;
            }
            //Capturar direita
            final Posicao direita = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() + 1);
            existePosicao = this.getTabuleiro().posicaoExisteTratado(direita);
            isPecaOponente = existePosicao && this.existePecaDoOponenteNa(direita);
            isPeaoVuneravelEnPassant = isPecaOponente && this.getTabuleiro().getPeca(direita).equals(this.partidaXadrez.getPeaoVuneravelEnPassant());
            if(isPeaoVuneravelEnPassant) {
                matrizPossiveisMovimentos[direita.getLinha() - (1 * sinal)][direita.getColuna()] = true;
            }
        }
        
        
        /*if(sinal == 1) {
            if(this.posicao.getLinha() == 3) {
                final Posicao esquerda = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() - 1);
                boolean existePosicao = this.getTabuleiro().posicaoExisteTratado(esquerda);
                boolean isPecaOponente = existePosicao && this.existePecaDoOponenteNa(esquerda);
                boolean isPeaoVuneravelEnPassant = isPecaOponente && this.getTabuleiro().getPeca(esquerda).equals(this.partidaXadrez.getPeaoVuneravelEnPassant());
                if(isPeaoVuneravelEnPassant) {
                    matrizPossiveisMovimentos[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }
                final Posicao direita = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() + 1);
                existePosicao = this.getTabuleiro().posicaoExisteTratado(direita);
                isPecaOponente = existePosicao && this.existePecaDoOponenteNa(direita);
                isPeaoVuneravelEnPassant = isPecaOponente && this.getTabuleiro().getPeca(direita).equals(this.partidaXadrez.getPeaoVuneravelEnPassant());
                if(isPeaoVuneravelEnPassant) {
                    matrizPossiveisMovimentos[direita.getLinha() - 1][direita.getColuna()] = true;
                }
            }
        } else {
            if(this.posicao.getLinha() == 4) {
                final Posicao esquerda = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() - 1);
                boolean existePosicao = this.getTabuleiro().posicaoExisteTratado(esquerda);
                boolean isPecaOponente = existePosicao && this.existePecaDoOponenteNa(esquerda);
                boolean isPeaoVuneravelEnPassant = isPecaOponente && this.getTabuleiro().getPeca(esquerda).equals(this.partidaXadrez.getPeaoVuneravelEnPassant());
                if(isPeaoVuneravelEnPassant) {
                    matrizPossiveisMovimentos[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
                }
                final Posicao direita = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() + 1);
                existePosicao = this.getTabuleiro().posicaoExisteTratado(direita);
                isPecaOponente = existePosicao && this.existePecaDoOponenteNa(direita);
                isPeaoVuneravelEnPassant = isPecaOponente && this.getTabuleiro().getPeca(direita).equals(this.partidaXadrez.getPeaoVuneravelEnPassant());
                if(isPeaoVuneravelEnPassant) {
                    matrizPossiveisMovimentos[direita.getLinha() + 1][direita.getColuna()] = true;
                }
            }
        }*/
    }
}
