import java.util.List;
import java.util.ArrayList;

public class Word {
  String mWord;
  List<Definition> mDefinitions;

  public Word(String word){
    mWord = word;
    mDefinitions = new ArrayList<Definition>();
  }

  public String getWord(){
    return mWord;
  }

  public List<Definition> getDefinitions(){
    return mDefinitions;
  }

  public void addDefinition(Definition definition){
    mDefinitions.add(definition);
  }
}
