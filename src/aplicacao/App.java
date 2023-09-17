package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import aplicacao.chess.PartidaXadrez;
import aplicacao.chess.PecaXadrez;
import aplicacao.chess.PosicaoXadrez;
import aplicacao.chess.XadrezExcecao;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        
        int numeroJogadas = 50;

        while (numeroJogadas-- != 0) {
            try {
                UserInterface.limparTela();
    
                UserInterface.imprimirPartidaXadrez(partidaXadrez);
                
                System.out.print("\nOrigem: ");
                PosicaoXadrez origem = UserInterface.lerPosicaoXadrez(scanner);
    
                boolean[][] possiveisMovimentacoes = partidaXadrez.possiveisMovimentacoes(origem);
                UserInterface.limparTela();
                UserInterface.imprimirTabuleiro(partidaXadrez.getPecasXadrez(), possiveisMovimentacoes);
                

                System.out.print("\nDestino: ");
                PosicaoXadrez destino = UserInterface.lerPosicaoXadrez(scanner);
                System.out.println();
                
                PecaXadrez pecaCapturada = partidaXadrez.moverPecaXadrez(origem, destino);    
            } catch (XadrezExcecao | InputMismatchException e) {
                System.err.println(e.getMessage() + " Pressione enter para continuar");
                scanner.nextLine(); //Esperar usuário da enter
            }
        }

    }

}
