import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class DefinitionTest{
  @Test
  public void definition_instantiatesCorrectly() {
    Definition defTest = new Definition("definition");
    assertTrue(defTest instanceof Definition);
  }

  @Test
  public void getDefString_returnsDefstring_String() {
    Definition defTest = new Definition("definition");
    assertEquals("definition", defTest.getDefString());
  }
}
