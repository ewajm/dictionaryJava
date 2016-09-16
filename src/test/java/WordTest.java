import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class WordTest{
  @After
  public void tearDown() {
    Word.clear();
  }

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
  public void removeDefinition_doesNothingIfDefinitionNotFound_false() {
    Word word = new Word("word");
    Definition definition = new Definition("definition");
    word.addDefinition(definition);
    assertTrue(!word.removeDefinition("definition2"));
  }

  @Test
  public void removeDefinition_removesDefinitionIfFound_true() {
    Word word = new Word("word");
    Definition definition = new Definition("definition");
    word.addDefinition(definition);
    assertTrue(word.removeDefinition("definition"));
    assertEquals(0, word.getDefinitions().size());
  }

  @Test
  public void findDefinition_returnsEmptyListIfNotFound_true() {
    Word word = new Word("word");
    Definition definition = new Definition("definition");
    word.addDefinition(definition);
    assertEquals(0, word.searchDefinitions("pants").size());
  }

  @Test
  public void findDefinition_findsMatchingDefinitionsInAWord_true() {
    Word word = new Word("word");
    Definition definition = new Definition("some definition");
    word.addDefinition(definition);
    assertTrue(word.searchDefinitions("definition").contains(definition));
  }

  @Test
  public void all_returnsAllInstancesOfword_true() {
    Word word = new Word("word");
    Word word2 = new Word("word2");
    assertTrue(Word.all().contains(word));
    assertTrue(Word.all().contains(word2));
  }

  @Test
  public void clear_removesAllWordsFromList_0() {
    Word word = new Word("word");
    Word word2 = new Word("word2");
    Word.clear();
    assertEquals(0, Word.all().size());
  }

  @Test
  public void getId_returnsId_int() {
    Word word = new Word("word");
    assertEquals(1, word.getId());
  }

  @Test
  public void find_returnsCorrectWord_Word(){
    Word word = new Word("word");
    Word word2 = new Word("word2");
    assertEquals(word2, Word.find(word2.getId()));
  }

  @Test
  public void findByDef_returnsCorrectWord_true(){
    Word word = new Word("word");
    Definition definition = new Definition("definition");
    word.addDefinition(definition);
    assertTrue(Word.findByDef("definition").contains(word));
  }

  @Test
  public void findByDef_returnsAllCorrectWords_true(){
    Word word = new Word("word");
    Definition definition = new Definition("definition");
    word.addDefinition(definition);
    Word word2 = new Word("word2");
    Definition definition2 = new Definition("also definition");
    word2.addDefinition(definition2);
    assertTrue(Word.findByDef("definition").contains(word));
    assertTrue(Word.findByDef("definition").contains(word2));
  }

  @Test
  public void findByDef_doesNotReturnIncorrectWords_true(){
    Word word = new Word("word");
    Definition definition = new Definition("definition");
    word.addDefinition(definition);
    Word word2 = new Word("word2");
    Definition definition2 = new Definition("something else");
    word2.addDefinition(definition2);
    assertTrue(Word.findByDef("definition").size() > 0);
    assertTrue(!Word.findByDef("definition").contains(word2));
  }

  @Test
  public void findByDef_worksWithMultipleWordSearch_true(){
    Word word = new Word("word");
    Definition definition = new Definition("something with words");
    word.addDefinition(definition);
    Word word2 = new Word("word2");
    Definition definition2 = new Definition("something or else and stuff");
    word2.addDefinition(definition2);
    assertEquals(1, Word.findByDef("something else").size());
  }
}
