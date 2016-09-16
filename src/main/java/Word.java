import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Word {
  private String mWord;
  private List<Definition> mDefinitions;
  private int mId;
  private static List<Word> instances = new ArrayList<Word>();

  public Word(String word){
    mWord = word;
    mDefinitions = new ArrayList<Definition>();
    instances.add(this);
    mId=instances.size();
  }

  public String getWord(){
    return mWord;
  }

  public List<Definition> getDefinitions(){
    return mDefinitions;
  }

  public int getId(){
    return mId;
  }

  public void addDefinition(Definition definition){
    mDefinitions.add(definition);
  }

  public boolean removeDefinition(String defString){
    for(Definition definition: mDefinitions){
      if(definition.getDefString().equals(defString)){
        return mDefinitions.remove(definition);
      }
    }
    return false;
  }

  public List<Definition> searchDefinitions(String searchString){
    String[] searchTerms = searchString.split(" ");
    List<Definition> foundDefs = mDefinitions;
    for(String term : searchTerms){
      foundDefs = foundDefs.stream().filter(definition -> (definition.getDefString().toLowerCase().contains(term))).collect(Collectors.toList());
    }
    return foundDefs;
  }

  public static List<Word> all(){
    return instances;
  }

  public static void clear(){
    instances.clear();
  }

  public static Word find(int id){
    return instances.get(id-1);
  }

  public static List<Word> findByDef(String searchString){
    List<Word> foundWords = instances.stream().filter(instance -> (instance.searchDefinitions(searchString).size() > 0)).collect(Collectors.toList());
    return foundWords;
  }
}
