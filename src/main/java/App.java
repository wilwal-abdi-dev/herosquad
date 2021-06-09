import models.Hero;
import models.Squad;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;



        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);
        staticFileLocation("/public");


        //get to show new hero form
        get("/heros/new",(request, response) ->  {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model,"heroform.hbs");

        },new HandlebarsTemplateEngine());

//
        //task: process new hero form
        post ("/heros/new",(request, response) -> {
            Map<String,Object>model = new HashMap<String, Object>();
            String name =request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String strength= request.queryParams("strength");
            String weakness = request.queryParams("weakness");
            Hero newIdentity =new Hero(name,age,strength,weakness);
            model.put("hero",newIdentity);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

//
        //get to show all heros
        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            ArrayList<Hero> heros = Hero.getAll();
            ArrayList<Squad>squads =Squad.getAll();
            model.put("heros",heros);
//            model.put("squads",squads);
            return new ModelAndView(model,"index.hbs");

        }, new HandlebarsTemplateEngine());
//
        //show an individual hero
        get("/heros/:id",(request, response) -> {
            Map<String,Object>model = new HashMap<String, Object>();
            int idOfHeroToFind = Integer.parseInt(request.params(":id"));
            Hero foundHero= Hero.findById(idOfHeroToFind);
            model.put("hero",foundHero);
            return new ModelAndView(model,"herodetail.hbs");
        }, new HandlebarsTemplateEngine());

//        get show a form to update hero
        get("/heros/:id/update",(request, response) -> {
            Map <String,Object>model = new HashMap<>();

            int idOfTheHeroToEdit =Integer.parseInt(request.params("id"));
            Hero editHero = Hero.findById(idOfTheHeroToEdit);
            model.put("editHero",editHero);
            return new ModelAndView(model,"heroform.hbs");

        },new HandlebarsTemplateEngine());
//
        //task:process a form to update a hero
        post("/heros/:id/update",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            String newName = request.queryParams("name");
            int newAge =Integer.parseInt(request.queryParams("age"));
            String newStrength = request.queryParams("strength");
            String newWeakness = request.queryParams("weakness");
            int idOfTheHeroToEdit =Integer.parseInt(request.params("id"));
            Hero editHero = Hero.findById(idOfTheHeroToEdit);
            editHero.update(newName,newAge,newStrength,newWeakness);

            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());



        //get: delete an individual hero
        get("/heros/:id/delete" ,(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(request.params("id"));
            Hero deleteHero = Hero.findById(idOfHeroToDelete);
            deleteHero.deleteById(idOfHeroToDelete);

            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

        //squad

        //get to show new squad form
        get("/squads/list",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model,"squadform.hbs");

        },new HandlebarsTemplateEngine());


        //task: process new squad form
        post ("/squads/list",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            String squadName =request.queryParams("squadName");
            int squadNumber = Integer.parseInt(request.queryParams("squadNumber"));
            String squadPurpose= request.queryParams("squadPurpose");
            Squad newSquadIdentity =new Squad(squadName,squadNumber,squadPurpose);
            model.put("squad",newSquadIdentity );
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/squads/list/:id",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "squadlist.hbs");

        },new HandlebarsTemplateEngine());


    }

}

