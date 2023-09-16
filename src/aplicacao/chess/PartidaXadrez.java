package aplicacao.chess;

import aplicacao.boardgame.Peca;
import aplicacao.boardgame.Posicao;
import aplicacao.boardgame.Tabuleiro;
import aplicacao.chess.pecas.*;

public class PartidaXadrez {

    private int turno;
    private Cor jogadorDoTurno;
    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        this.turno = 1;
        this.jogadorDoTurno = Cor.BRANCO;
        this.posicionarPecasInicio();
    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorDoTurno() {
        return jogadorDoTurno;
    }

    public PecaXadrez[][] getPecasXadrez() {
        final int linhas = this.tabuleiro.getLinhas();
        final int colunas = this.tabuleiro.getColunas();
        PecaXadrez[][] tabuleiroXadrez = new PecaXadrez[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                tabuleiroXadrez[i][j] = (PecaXadrez) this.tabuleiro.getPeca(i, j);
            }
        }

        return tabuleiroXadrez;
    }

    private void mudarTurno() {
        this.turno++;
        this.jogadorDoTurno = Cor.BRANCO.equals(this.jogadorDoTurno) ? Cor.PRETO : Cor.BRANCO;
    }

    public boolean[][] possiveisMovimentacoes(PosicaoXadrez origem) {
        Posicao posicao = origem.toPosicao();
        this.validarPosicaoOrigem(posicao);
        return this.tabuleiro.getPeca(posicao).movimentacoesPossiveis();
    }

    public PecaXadrez moverPecaXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
        Posicao origem = posicaoOrigem.toPosicao();
        Posicao destino = posicaoDestino.toPosicao();
        this.validarPosicaoOrigem(origem);
        this.validarPosicaoDestino(origem, destino);
        Peca pecaCapturada = this.moverPeca(origem, destino);
        this.mudarTurno();
        return (PecaXadrez)pecaCapturada;
    }

    private void validarPosicaoOrigem(Posicao origem) {
        if(!this.tabuleiro.existeUmaPecaNaPosicao(origem)) {
            throw new XadrezExcecao(String.format("Não há nenhuma peça na posição de origem (%s)!",origem.toPosicaoXadrex()));
        }
        Cor corPecaOrigem = ((PecaXadrez)this.tabuleiro.getPeca(origem)).getCor();
        if(!this.jogadorDoTurno.equals(corPecaOrigem)) {
            throw new XadrezExcecao(String.format("A peça na posição de origem (%s) não é %s!",origem.toPosicaoXadrex(), Cor.BRANCO.equals(this.jogadorDoTurno) ? "branca" : "preta"));
        }
        if(!this.tabuleiro.getPeca(origem).existePeloMenosUmaMovimentacaoPossivel()) {
            throw new XadrezExcecao(String.format("Não há nenhum possível movimento para a peça na posição (%s)!",origem.toPosicaoXadrex()));
        }
    }

    private void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if(!this.tabuleiro.getPeca(origem).isMovimentacaoPossivel(destino)) {
            throw new XadrezExcecao(String.format("A peça na posição de origem (%s) não pode se mover para a posição de destino (%s)",origem.toPosicaoXadrex(), destino.toPosicaoXadrex()));
        }
    }

    private Peca moverPeca(Posicao origem, Posicao destino) {
        Peca pecaMovida = this.tabuleiro.removerPeca(origem);
        Peca pecaCapturada = this.tabuleiro.removerPeca(destino);
        this.tabuleiro.posicionarPeca(pecaMovida, destino);
        return pecaCapturada;
    }

    private void posicionarNovaPeca(char coluna, int linha, PecaXadrez peca) {
        this.tabuleiro.posicionarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
    }

    private void posicionarPecasInicio() {
        this.posicionarNovaPeca('a', 8, new Torre(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('b', 8, new Cavalo(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('c', 8, new Bispo(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('d', 8, new Rainha(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('e', 8, new Rei(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('f', 8, new Bispo(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('g', 8, new Cavalo(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('h', 8, new Torre(this.tabuleiro, Cor.PRETO));

        for (char c = 'a'; c <= 'h'; c++) {
            this.posicionarNovaPeca(c, 7, new Peao(this.tabuleiro, Cor.PRETO));
        }
    }

}
