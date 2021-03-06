package poi.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import poi.modelo.usuario.Terminal;
import poi.reportes.ReporteBusquedasPorFecha;
import poi.repositorios.RepositorioConsultas;
import poi.utilidades.Consulta;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ReportePorFechaController {

	public ModelAndView mostrar(Request request, Response response){						
		ReporteBusquedasPorFecha reporte = new ReporteBusquedasPorFecha();
		HashMap<LocalDate, Integer> resultado = reporte.recolectarFechas();	
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("hasResults", !resultado.isEmpty());
		viewModel.put("resultado",armarLista(resultado));
		return new ModelAndView(viewModel, "BusquedaPorFecha.html");
	}

	private List<HashMap<String, Object>> armarLista(HashMap<LocalDate, Integer> resultado) {
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();		
		Iterator it = resultado.entrySet().iterator();
		while (it.hasNext()) {
			HashMap<String,Object> element = new HashMap<String,Object>();		    
			Map.Entry e = (Map.Entry)it.next();
			element.put("fecha", e.getKey());
			element.put("cantidad", e.getValue());
			array.add(element);
		}
		return array;
	}
}
