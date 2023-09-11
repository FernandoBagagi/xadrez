package aplicacao;

import java.util.Scanner;

import aplicacao.chess.PartidaXadrez;
import aplicacao.chess.PecaXadrez;
import aplicacao.chess.PosicaoXadrez;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        
        while (true) {
            
            UserInterface.limparTela();

            UserInterface.imprimirTabuleiro(partidaXadrez.getPecasXadrez());
            
            System.out.print("\nOrigem: ");
            PosicaoXadrez origem = UserInterface.lerPosicaoXadrez(scanner);

            System.out.print("\nDestino: ");
            PosicaoXadrez destino = UserInterface.lerPosicaoXadrez(scanner);
            System.out.println();
            
            PecaXadrez pecaCapturada = partidaXadrez.moverPecaXadrez(origem, destino);

        }

        //UserInterface.imprimirTabuleiro(partidaXadrez.getPecasXadrez());
    }
    /*Não zerar */
    /*Não zerar 2*/
}
