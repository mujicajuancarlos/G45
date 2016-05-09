package poi.modelo.puntoDeInteres;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import poi.utilidades.Calculo;
import poi.utilidades.Posicion;

public class CGP extends POI{
	private List<Posicion> verticesComuna = new ArrayList<Posicion>();
	private ArrayList<ServicioDeCGP> servicios = new ArrayList<ServicioDeCGP>();
	
	public CGP(List<String> etiquetas,Posicion posicion,List<Posicion> verticesComuna) {
		this.etiquetas = etiquetas;
		this.posicion = posicion;
		this.verticesComuna = verticesComuna;
	}
	
	public boolean estaCercaDe(Posicion posicionUsuario){
		boolean estaCerca = Calculo.coordenadasEnComuna(verticesComuna, posicionUsuario); 
		return estaCerca;
	}

	public void agregarServicio(ServicioDeCGP unServicio) {
		this.servicios.add(unServicio);
	}
	
	public boolean estaDisponible(LocalDateTime unaFechaHora) {
		return (this.getServicios().stream().anyMatch(
	            unServicio -> unServicio.estaDisponible(unaFechaHora)));
	}

	private ArrayList<ServicioDeCGP> getServicios() {
		return this.servicios;
	}
}