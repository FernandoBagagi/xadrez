package aplicacao.chess;

public class XadrezExcecao extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public XadrezExcecao(String mensagemErro) {
        super(mensagemErro);
    }

}
