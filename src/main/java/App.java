//TODO: endorse definitions?

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
  }
}
