package aplicacao.boardgame;

public class TabuleiroExcecao extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public TabuleiroExcecao(String mensagemErro) {
        super(mensagemErro);
    }

}
