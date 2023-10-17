package aplicacao;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import aplicacao.chess.PartidaXadrez;
import aplicacao.chess.PecaXadrez;
import aplicacao.chess.PosicaoXadrez;

public class UserInterface {

    /* Utility classes should not have public constructors */
    private UserInterface() {
    }

    public static PosicaoXadrez lerPosicaoXadrez(Scanner scanner) {
        try {
            String valorLido = scanner.nextLine();
            char coluna = valorLido.charAt(0);
            int linha = Integer.parseInt(valorLido.substring(1));
            return new PosicaoXadrez(coluna, linha);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Erro ao ler posição. Os valores válidos são de a1 até h8");
        }
    }

    public static void imprimirPartidaXadrez(PartidaXadrez partidaXadrez, List<PecaXadrez> pecasCapturadas) {
        UserInterface.imprimirTabuleiro(partidaXadrez.getPecasXadrez());
        UserInterface.imprimirPecasCapturadas(pecasCapturadas);
        System.out.println(String.format("%nTurno: %d)!", partidaXadrez.getTurno()));
        System.out.println(String.format("Esperando a jogada do jogador das peças %s", partidaXadrez.getCorPecasJogadorDoTurno()));
        if(partidaXadrez.isEmXeque()) {
            System.out.println("Xeque!");
        }
    }

    public static void imprimirTabuleiro(PecaXadrez[][] pecas) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                UserInterface.imprimirPeca(pecas[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void imprimirTabuleiro(PecaXadrez[][] pecas, boolean[][] possiveisMovimentacoes) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                UserInterface.imprimirPeca(pecas[i][j], possiveisMovimentacoes[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void imprimirPeca(PecaXadrez peca, boolean background) {
        if (peca == null) {
            System.out.print(background ? "#" : " ");
        } else {
            System.out.print(peca);
        }
        System.out.print(" ");
    }

    public static void limparTela() {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Runtime.getRuntime().exec("clear");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void imprimirPecasCapturadas(List<PecaXadrez> pecasCapturadas) {
        List<PecaXadrez> pecasBrancasCapturadas = pecasCapturadas.stream().filter(PecaXadrez::isBranca)
                .collect(Collectors.toList());
        Object[] pecasPretasCapturadas = pecasCapturadas.stream().filter(PecaXadrez::isPreta).toArray();

        System.out.println("\nPeças capturadas:");
        System.out.print("Brancas: ");
        System.out.println(Arrays.toString(pecasBrancasCapturadas.toArray()));
        System.out.print("Pretas: ");
        System.out.println(Arrays.toString(pecasPretasCapturadas));
    }

}
