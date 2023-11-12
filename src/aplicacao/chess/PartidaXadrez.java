package aplicacao.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aplicacao.boardgame.Peca;
import aplicacao.boardgame.Posicao;
import aplicacao.boardgame.Tabuleiro;
import aplicacao.chess.pecas.*;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;
    private int turno;
    private Cor jogadorDoTurno;
    private boolean isEmXeque;
    private boolean isEmXequeMate;
    private PecaXadrez peaoVuneravelEnPassant;
    
    private List<Peca> pecasNoTabuleiro;
    private List<Peca> pecasCapturadas;
    
    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        this.turno = 1;
        this.jogadorDoTurno = Cor.BRANCO;
        this.isEmXeque = false;
        this.pecasNoTabuleiro = new ArrayList<>();
        this.pecasCapturadas = new ArrayList<>();
        this.posicionarPecasInicio();
    }
    
    public int getTurno() {
        return turno;
    }
    
    public Cor getJogadorDoTurno() {
        return jogadorDoTurno;
    }
    
    public boolean isEmXeque() {
        return isEmXeque;
    }
    
    public boolean isEmXequeMate() {
        return isEmXequeMate;
    }
    
    public PecaXadrez getPeaoVuneravelEnPassant() {
        return peaoVuneravelEnPassant;
    }

    public String getCorPecasJogadorDoTurno() {
        return Cor.BRANCO.equals(this.jogadorDoTurno) ? "BRANCAS" : "PRETAS";
    }
    
    private Cor getOponente(Cor jogadorDoTurno) {
        return Cor.BRANCO.equals(jogadorDoTurno) ? Cor.PRETO : Cor.BRANCO;
    }

    private PecaXadrez getRei(Cor cor) {
        return this.pecasNoTabuleiro.stream()
                .map(PecaXadrez.class::cast)
                .filter(peca -> peca instanceof Rei && cor.equals(peca.getCor()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("O rei do oponente não foi encontrado no tabuleiro"));
    }

    private boolean isReiEmXeque(Cor corDoRei) {
        Posicao posicaoDoRei = this.getRei(corDoRei).getPosicaoXadrez().toPosicao();
        final Cor cor = this.getOponente(corDoRei);
        List<PecaXadrez> pecasDoOponente = this.pecasNoTabuleiro.stream()
                .map(PecaXadrez.class::cast)
                .filter(peca -> cor.equals(peca.getCor()))
                .collect(Collectors.toList());
        for(PecaXadrez peca : pecasDoOponente) {
            if(peca.movimentacoesPossiveis()[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    private boolean isReiEmXequeMate(Cor corDoRei) {
        if(this.isReiEmXeque(corDoRei)) {
            List<PecaXadrez> pecas = this.pecasNoTabuleiro.stream()
                .map(PecaXadrez.class::cast)
                .filter(peca -> corDoRei.equals(peca.getCor()))
                .collect(Collectors.toList());
            for(PecaXadrez peca : pecas) {
                boolean[][] possiveisMovimentacoes = peca.movimentacoesPossiveis();
                for(int i = 0; i < this.tabuleiro.getLinhas(); i++) {
                    for(int j = 0; j < this.tabuleiro.getColunas(); j++) {
                        if(possiveisMovimentacoes[i][j]) {
                            Posicao origem = peca.getPosicaoXadrez().toPosicao();
                            Posicao destino = new Posicao(i, j);
                            Peca pecaCapturada = this.moverPeca(origem, destino);
                            boolean testeXeque = this.isReiEmXeque(corDoRei);
                            this.desfazerJogada(origem, destino, pecaCapturada);
                            if(!testeXeque) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
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
        if(this.isReiEmXeque(jogadorDoTurno)) {
            this.desfazerJogada(origem, destino, pecaCapturada);
            throw new XadrezExcecao("Você não pode se colocar em xeque!");
        }
        this.isEmXeque = this.isReiEmXeque(this.getOponente(jogadorDoTurno));
        if(this.isReiEmXequeMate(this.getOponente(jogadorDoTurno))) {
            this.isEmXequeMate = true;
        } else {
            this.mudarTurno();
        }
        //Movimento Especial En Passant
        PecaXadrez pecaMovida = (PecaXadrez)this.tabuleiro.getPeca(destino);
        final boolean moveuDuasCasas = destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2;
        this.peaoVuneravelEnPassant = pecaMovida instanceof Peao && moveuDuasCasas ? pecaMovida : null;
        return (PecaXadrez) pecaCapturada;
    }

    private void validarPosicaoOrigem(Posicao origem) {
        if (!this.tabuleiro.existeUmaPecaNaPosicao(origem)) {
            throw new XadrezExcecao(
                    String.format("Não há nenhuma peça na posição de origem (%s)!", origem.toPosicaoXadrex()));
        }
        Cor corPecaOrigem = ((PecaXadrez) this.tabuleiro.getPeca(origem)).getCor();
        if (!this.jogadorDoTurno.equals(corPecaOrigem)) {
            throw new XadrezExcecao(String.format("A peça na posição de origem (%s) não é %s!",
                    origem.toPosicaoXadrex(), Cor.BRANCO.equals(this.jogadorDoTurno) ? "branca" : "preta"));
        }
        if (!this.tabuleiro.getPeca(origem).existePeloMenosUmaMovimentacaoPossivel()) {
            throw new XadrezExcecao(String.format("Não há nenhum possível movimento para a peça na posição (%s)!",
                    origem.toPosicaoXadrex()));
        }
    }

    private void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if (!this.tabuleiro.getPeca(origem).isMovimentacaoPossivel(destino)) {
            throw new XadrezExcecao(
                    String.format("A peça na posição de origem (%s) não pode se mover para a posição de destino (%s)",
                            origem.toPosicaoXadrex(), destino.toPosicaoXadrex()));
        }
    }

    private Peca moverPeca(Posicao origem, Posicao destino) {
        PecaXadrez pecaMovida = (PecaXadrez)this.tabuleiro.removerPeca(origem);
        pecaMovida.incrementarContadorMovimentos();
        Peca pecaCapturada = this.tabuleiro.removerPeca(destino);
        this.tabuleiro.posicionarPeca(pecaMovida, destino);
        //Movimentação especial: Roque
        if(pecaMovida instanceof Rei) {
            if(destino.getColuna() == origem.getColuna() + 2) {
                Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
                Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
                PecaXadrez torre = (PecaXadrez)this.tabuleiro.removerPeca(origemTorre);
                this.tabuleiro.posicionarPeca(torre, destinoTorre);
                torre.incrementarContadorMovimentos();
            } else if(destino.getColuna() == origem.getColuna() - 2) {
                Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
                Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
                PecaXadrez torre = (PecaXadrez)this.tabuleiro.removerPeca(origemTorre);
                this.tabuleiro.posicionarPeca(torre, destinoTorre);
                torre.incrementarContadorMovimentos();
            }
        }
        //Movimentação especial: En Passant
        if(pecaMovida instanceof Peao) {
            boolean moveuNaDiagonal = destino.getColuna() != origem.getColuna();
            boolean naoCapturouPeca = pecaCapturada == null;
            if(moveuNaDiagonal && naoCapturouPeca) {
                final int linhaPecaCapturada = Cor.BRANCO.equals(pecaMovida.getCor()) ? 1 : -1;
                Posicao posicaoPeaoCapturado = new Posicao(destino.getLinha() + linhaPecaCapturada, destino.getColuna());
                pecaCapturada = this.tabuleiro.removerPeca(posicaoPeaoCapturado);
            }
        }
        if (pecaCapturada != null) {
            this.pecasNoTabuleiro.remove(pecaCapturada);
            this.pecasCapturadas.add(pecaCapturada);
        }
        return pecaCapturada;
    }
    
    private void desfazerJogada(Posicao origem, Posicao destino, Peca pecaCapturada) {
        PecaXadrez pecaMovida = (PecaXadrez)this.tabuleiro.removerPeca(destino);
        pecaMovida.decrementarContadorMovimentos();
        this.tabuleiro.posicionarPeca(pecaMovida, origem);
        if (pecaCapturada != null) {
            this.tabuleiro.posicionarPeca(pecaCapturada, destino);
            this.pecasCapturadas.remove(pecaCapturada);
            this.pecasNoTabuleiro.add(pecaCapturada);
        }
        //Movimentação especial, Roque
        if(pecaMovida instanceof Rei) {
            if(destino.getColuna() == origem.getColuna() + 2) {
                Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
                Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
                PecaXadrez torre = (PecaXadrez)this.tabuleiro.removerPeca(origemTorre);
                this.tabuleiro.posicionarPeca(torre, destinoTorre);
                torre.decrementarContadorMovimentos();
            } else if(destino.getColuna() == origem.getColuna() - 2) {
                Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
                Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
                PecaXadrez torre = (PecaXadrez)this.tabuleiro.removerPeca(origemTorre);
                this.tabuleiro.posicionarPeca(torre, destinoTorre);
                torre.decrementarContadorMovimentos();
            }
        }
        //Movimentação especial: En Passant
        if(pecaMovida instanceof Peao) {
            boolean moveuNaDiagonal = destino.getColuna() != origem.getColuna();
            boolean capturouPeaoVuneravelEnPassant = pecaCapturada == this.getPeaoVuneravelEnPassant();
            if(moveuNaDiagonal && capturouPeaoVuneravelEnPassant) {
                PecaXadrez peaoCapturado = (PecaXadrez)this.tabuleiro.removerPeca(destino);
                final int linhaPecaCapturada = Cor.BRANCO.equals(pecaMovida.getCor()) ? 3 : 4;
                Posicao posicaoPeaoCapturado = new Posicao(linhaPecaCapturada, destino.getColuna());
                this.tabuleiro.posicionarPeca(peaoCapturado, posicaoPeaoCapturado);
            }
        }
    }

    private void posicionarNovaPeca(char coluna, int linha, PecaXadrez peca) {
        this.tabuleiro.posicionarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
        this.pecasNoTabuleiro.add(peca);
    }

    private void posicionarPecasInicio() {
        
        this.posicionarNovaPeca('a', 8, new Torre(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('e', 8, new Rei(this.tabuleiro, Cor.PRETO, this));
        this.posicionarNovaPeca('g', 8, new Torre(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('b', 4, new Peao(this.tabuleiro, Cor.PRETO, this));
        this.posicionarNovaPeca('h', 7, new Peao(this.tabuleiro, Cor.PRETO, this));

        this.posicionarNovaPeca('a', 1, new Torre(this.tabuleiro, Cor.BRANCO));
        this.posicionarNovaPeca('g', 1, new Rei(this.tabuleiro, Cor.BRANCO, this));
        //this.posicionarNovaPeca('h', 1, new Torre(this.tabuleiro, Cor.BRANCO));
        this.posicionarNovaPeca('a', 2, new Peao(this.tabuleiro, Cor.BRANCO, this));
        this.posicionarNovaPeca('g', 5, new Peao(this.tabuleiro, Cor.BRANCO, this));
        
        /*this.posicionarNovaPeca('a', 8, new Torre(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('b', 8, new Cavalo(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('c', 8, new Bispo(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('d', 8, new Rainha(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('e', 8, new Rei(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('f', 8, new Bispo(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('g', 8, new Cavalo(this.tabuleiro, Cor.PRETO));
        this.posicionarNovaPeca('h', 8, new Torre(this.tabuleiro, Cor.PRETO));

        for (char c = 'a'; c <= 'h'; c++) {
            this.posicionarNovaPeca(c, 7, new Peao(this.tabuleiro, Cor.PRETO, this));
        }

        this.posicionarNovaPeca('a', 3, new Torre(this.tabuleiro, Cor.BRANCO));
        this.posicionarNovaPeca('b', 1, new Cavalo(this.tabuleiro, Cor.BRANCO));
        this.posicionarNovaPeca('c', 1, new Bispo(this.tabuleiro, Cor.BRANCO));
        this.posicionarNovaPeca('d', 1, new Rainha(this.tabuleiro, Cor.BRANCO));
        this.posicionarNovaPeca('e', 1, new Rei(this.tabuleiro, Cor.BRANCO));
        this.posicionarNovaPeca('f', 1, new Bispo(this.tabuleiro, Cor.BRANCO));
        this.posicionarNovaPeca('g', 1, new Cavalo(this.tabuleiro, Cor.BRANCO));
        this.posicionarNovaPeca('h', 1, new Torre(this.tabuleiro, Cor.BRANCO));

        for (char c = 'a'; c <= 'h'; c++) {
            this.posicionarNovaPeca(c, 2, new Peao(this.tabuleiro, Cor.BRANCO, this));
        }*/
    }

}
