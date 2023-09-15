package aplicacao;

import java.io.IOException;
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

    /**
     * 
     */
    public static void limparTela() {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }  catch (IOException e) {
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


}
