//TODO: endorse definitions? - sort by number of endorsements - remove if get to low?
//TODO: class that deals with stop words for search
//TODO: create word categories (for vocab lists?) - would this be a separate object or a property of words?
//TODO: allow users to guess definitions - award points for more accurate guesses (flash card program)

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("words", Word.sort());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String wordName = request.queryParams("word");
      String initDef = request.queryParams("init-def");
      Word newWord = new Word(wordName);
      if(!initDef.isEmpty()){
        Definition newDefinition = new Definition(initDef);
        newWord.addDefinition(newDefinition);
      }
      model.put("newWord", newWord);
      model.put("words", Word.sort());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/search", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String searchStringOriginal = request.queryParams("search-string");
      String formatString = searchStringOriginal.trim().toLowerCase().replaceAll("\\p{P}", "");
      model.put("search-string", searchStringOriginal);
      model.put("format-string", formatString);
      model.put("results", Word.findByDef(formatString));
      model.put("template", "templates/search.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/word/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/word-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/word/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      try{
        int id = Integer.parseInt(request.params(":id"));
        Word word = Word.find(id);
        model.put("word", word);
      } catch (NumberFormatException e){}
      model.put("template", "templates/word.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/word/:id/def/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      try{
        int id = Integer.parseInt(request.params(":id"));
        Word word = Word.find(id);
        model.put("word", word);
      } catch (NumberFormatException e){}
      model.put("template", "templates/word-def-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/word/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Word word = Word.find(Integer.parseInt(request.queryParams("wordId")));
      String defString = request.queryParams("def");
      Definition definition = new Definition(defString);
      word.addDefinition(definition);
      model.put("postString", defString);
      model.put("word", word);
      model.put("template", "templates/word.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
