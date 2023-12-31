package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import aplicacao.chess.PartidaXadrez;
import aplicacao.chess.PecaXadrez;
import aplicacao.chess.PosicaoXadrez;
import aplicacao.chess.XadrezExcecao;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        List<PecaXadrez> pecasCapturadas = new ArrayList<>(); // Já tem na partidaXadrez

        while (!partidaXadrez.isEmXequeMate()) {
            try {
                UserInterface.limparTela();

                UserInterface.imprimirPartidaXadrez(partidaXadrez, pecasCapturadas);

                System.out.print("\nOrigem: ");
                PosicaoXadrez origem = UserInterface.lerPosicaoXadrez(scanner);

                boolean[][] possiveisMovimentacoes = partidaXadrez.possiveisMovimentacoes(origem);
                UserInterface.limparTela();
                UserInterface.imprimirTabuleiro(partidaXadrez.getPecasXadrez(), possiveisMovimentacoes);

                System.out.print("\nDestino: ");
                PosicaoXadrez destino = UserInterface.lerPosicaoXadrez(scanner);
                System.out.println();

                PecaXadrez pecaCapturada = partidaXadrez.moverPecaXadrez(origem, destino);

                if (pecaCapturada != null) {
                    pecasCapturadas.add(pecaCapturada);
                }

                if(partidaXadrez.getPeaoPromovido() != null) {
                    System.out.println("O peão será promovido para Bispo('B/b'), Cavalo('/c'), Torre('T/t') ou Rainha (Qualquer outra tecla): ");
                    String tipo = scanner.nextLine();
                    for(String tipoValido : new String[]{"B", "b", "C", "c", "T", "t"}){
                        if(tipoValido.equals(tipo)) {
                            partidaXadrez.trocarPeaoPromovido(tipo.toUpperCase());
                            break;
                        }
                    }
                }

            } catch (XadrezExcecao | InputMismatchException e) {
                System.err.println(e.getMessage() + " Pressione enter para continuar");
                scanner.nextLine(); // Esperar usuário da enter
            }
        }

        UserInterface.limparTela();
        UserInterface.imprimirPartidaXadrez(partidaXadrez, pecasCapturadas);

    }

}
