package poi.utilidades;

import static spark.Spark.post;
import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.controllers.AdminController;
import poi.controllers.BuscadorDePoiController;
import poi.controllers.HomeController;
import poi.controllers.LoginController;
import poi.controllers.ReportePorFechaController;
import poi.controllers.ReportePorFechaYTerminalController;
import poi.controllers.ReportePorTerminalController;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main implements WithGlobalEntityManager{
	
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		port(8080);

		staticFileLocation("/public");
		
//		after((request, responese) -> { 
//			PerThreadEntityManagers.getEntityManager(); 
//			PerThreadEntityManagers.closeEntityManager();
//		});
		CargaInicial.getInstance().inicializar();
		
//		usr.persistir(usr);
		
		// INDEX PRINCIPAL
		HomeController home = new HomeController();
		get("/", home::mostrar, engine);
		get("/index.html", (request, response) -> {
			response.redirect("/");
			return null;
		});
		
		//LOGIN
		LoginController login = new LoginController();
		post("/login", login::validarUsrYPass, engine);
		get("/login_fail", login::loginFail , engine);
		
		//HOME GENERICO DE TERMINAL Y ADMINISTRADOR
		get("/my_home_page", login::redirectUser, engine);//get controla si hay usuario logueado sino hay lo envia al login
		
		//post("/my_home_page", login::validarUsrYPass, engine);
		//get("/my_home_page", login::validarUsrYPass, engine);
		
		//HOME DE TERMINAL
		BuscadorDePoiController poiBrowser = new BuscadorDePoiController();
		//get("/terminalHome", poiBrowser::render, engine);
		post("/my_home_page", poiBrowser::search, engine);//post es usado para las busquedas
		

		//HOME DE ADMINISTRADOR

		ReportePorFechaController reportePorFecha = new ReportePorFechaController();
		get("/BusquedaPorFecha", reportePorFecha::mostrar, engine);

		ReportePorTerminalController reportePorTerminal = new ReportePorTerminalController();
		get("/BusquedaPorTerminal", reportePorTerminal::mostrar, engine);

		ReportePorFechaYTerminalController reportePorFechaYTerminal = new ReportePorFechaYTerminalController();
		get("/BusquedaPorFechaYTerminal", reportePorFechaYTerminal::mostrar, engine);
				
		AdminController admin = new AdminController();
		get("/abmPois",admin::mostrarABM,engine);
		get("/reportes", admin::mostrarReporte, engine);		
		get("/admTerminales", admin::mostrarAdmTerminal, engine);
	}
	
}
