package Junit.jcharif;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

// TODO: Replace examples and use TDD development by writing your own tests

public class KataUnoTest {
    @Test
    public void exampleCases()
    {assertEquals("ehT kciuq nworb xof spmuj revo eht yzal .god", KataUno.reverseWords("The quick brown fox jumps over the lazy dog."));}
    @Test
    public void exampleCases ()
    {assertEquals("elppa", KataUno.reverseWords("apple"));}
    @Test
    public void exampleCases ()
    { assertEquals("a b c d", KataUno.reverseWords("a b c d"));}
    @Test
    public void exampleCases ()
    {assertEquals("elbuod  decaps  sdrow", KataUno.reverseWords("double  spaced  words"));
    }
}