package aplicacao.boardgame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Posicao {
    
    private int linha;
    private int coluna;

    @Override
    public String toString() {
        return "%d, %d".formatted(linha, coluna);
    }

    /*
     * String mensagem = MessageFormat.format("{0} tem {1} {1,choice,1#ano|1<anos}", nome, idade);
     * https://pt.stackoverflow.com/questions/512957/h%C3%A1-como-interpolar-strings-em-java
     */
}
