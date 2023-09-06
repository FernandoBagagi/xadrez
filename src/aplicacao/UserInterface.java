package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import aplicacao.chess.PecaXadrez;
import aplicacao.chess.PosicaoXadrez;

public class UserInterface {

    /*Utility classes should not have public constructors*/
    private UserInterface(){
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

    public static void imprimirTabuleiro(PecaXadrez[][] pecas) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                UserInterface.imprimirPeca(pecas[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void imprimirPeca(PecaXadrez peca) {
        if (peca == null) {
            System.out.print("-");
        } else {
            System.out.print(peca);
        }
        System.out.print(" ");
    }


}
