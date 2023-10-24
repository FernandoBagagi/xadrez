package aplicacao.chess.pecas;

import aplicacao.boardgame.Posicao;
import aplicacao.boardgame.Tabuleiro;
import aplicacao.chess.Cor;
import aplicacao.chess.PartidaXadrez;
import aplicacao.chess.PecaXadrez;

public class Rei extends PecaXadrez {

    private PartidaXadrez partidaXadrez; //Usado só pra verificar se está em xeque

    public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
    }

    @Override
    public String toString() {
        return Cor.BRANCO.equals(this.getCor()) ? "K" : "k";
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

    private boolean torrePodeFazerRoque(Posicao posicao) {
        PecaXadrez peca = (PecaXadrez)this.getTabuleiro().getPeca(posicao);
        //O instanceof retorna false se for null
        return peca instanceof Torre && this.getCor().equals(peca.getCor()) && peca.getContadorMovimentos() == 0;
    }

    private void roqueDireita(boolean[][] matrizPossiveisMovimentos) {
        //Porque testar se está em cheque?
        if(this.getContadorMovimentos() == 0 && !this.partidaXadrez.isEmXeque()) {
            Posicao posicaoTorreDireita = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() + 3);
            if(this.torrePodeFazerRoque(posicaoTorreDireita)) {
                Posicao posicaoAux = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() + 1);
                final boolean isCasaDireitaLivre = (PecaXadrez) this.getTabuleiro().getPeca(posicaoAux) == null;
                posicaoAux.setColuna(posicaoAux.getColuna() + 1);
                final boolean isDuasCasasDireitaLivre = isCasaDireitaLivre && (PecaXadrez) this.getTabuleiro().getPeca(posicaoAux) == null;
                if(isDuasCasasDireitaLivre) {
                    matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
                } 
            }
        }
    }

    private void roqueEsquerda(boolean[][] matrizPossiveisMovimentos) {
        //Porque testar se está em cheque?
        if(this.getContadorMovimentos() == 0 && !this.partidaXadrez.isEmXeque()) {
            Posicao posicaoTorreEsquerda = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() - 4);
            if(this.torrePodeFazerRoque(posicaoTorreEsquerda)) {
                Posicao posicaoAux = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() - 1);
                final boolean isCasaEsquerdaLivre = (PecaXadrez) this.getTabuleiro().getPeca(posicaoAux) == null;
                posicaoAux.setColuna(posicaoAux.getColuna() - 1);
                final boolean isDuasCasasEsquerdaLivre = isCasaEsquerdaLivre && (PecaXadrez) this.getTabuleiro().getPeca(posicaoAux) == null;
                posicaoAux.setColuna(posicaoAux.getColuna() - 1);
                final boolean isTresCasasEsquerdaLivre = isDuasCasasEsquerdaLivre && (PecaXadrez) this.getTabuleiro().getPeca(posicaoAux) == null;
                if(isTresCasasEsquerdaLivre) {
                    //O rei só move duas casas, por isso o ajuste da coluna
                    matrizPossiveisMovimentos[posicaoAux.getLinha()][posicaoAux.getColuna() + 1] = true;
                } 
            }
        }
    }

    /*private void roqueDireita(boolean[][] matrizPossiveisMovimentos) {
        Posicao posicaoAux = new Posicao(this.posicao.getLinha(), this.posicao.getColuna() + 1);
        final boolean podeMoverUmaCasaDireita = this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux);
        posicaoAux.setColuna(posicaoAux.getColuna() + 1);
        final boolean podeMoverDuasCasaDireita = podeMoverUmaCasaDireita && this.getTabuleiro().posicaoExisteTratado(posicaoAux) && this.podeMover(posicaoAux);
        final boolean isPermitidoRoqueRei = podeMoverDuasCasaDireita && this.getContadorMovimentos() == 0;
        if(isPermitidoRoqueRei) {

        } 
        Posicao posicaoAux = new Posicao(this.posicao.getLinha() - 1, this.posicao.getColuna() - 1);
    }*/
}
