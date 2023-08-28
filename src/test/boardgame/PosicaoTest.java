package test.boardgame;

import aplicacao.boardgame.Posicao;
import junit.framework.TestCase;

public class PosicaoTest extends TestCase {

    public void testeIntancia() {
        Posicao p = new Posicao(1, 1);
        assertEquals(p.toString(), "1, 1");
    }

    public void testeIntancia2() {
        Posicao p = new Posicao(1, 1);
        assertFalse(p.toString().equals("2, 1"));
    }

}
