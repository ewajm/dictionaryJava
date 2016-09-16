import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class WordTest{

  @Test
  public void Word_instantiatesCorrectly() {
    Word word = new Word("word");
    assertTrue(word instanceof Word);
  }

  @Test
  public void getWord_returnsWord_String() {
    Word word = new Word("word");
    assertEquals("word", word.getWord());
  }

  @Test
  public void getDefinition_instantiatesCorrectly_int() {
    Word word = new Word("word");
    assertEquals(0, word.getDefinitions().size());
  }

  @Test
  public void addDefinition_addsDefinitionToList_true() {
    Word word = new Word("word");
    Definition definition = new Definition("definition");
    word.addDefinition(definition);
    assertTrue(word.getDefinitions().contains(definition));
  }

  @Test
  public void getWords_instantiatesCorrectly() {
    Word word = new Word("word");
    Definition definition = new Definition("definition");
    word.addDefinition(definition);
    assertTrue(word.getDefinitions().contains(definition));
  }
}
