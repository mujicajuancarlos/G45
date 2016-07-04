package poi.acciones;

import java.time.LocalDateTime;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;

public class EstaDisponible implements Accion {
	public boolean estaHabilitada;
	private LocalDateTime fechaYHora;
	private POI poiVerDisponibilidad;
	public boolean estaDisponible;

	public EstaDisponible(POI poi, LocalDateTime unaFechaHora) {
		this.poiVerDisponibilidad = poi;
		this.fechaYHora = unaFechaHora;
		estaHabilitada = true; // Habilitada por defecto
	}

	@Override
	public String getNombreAccion() {
		return "EstaDisponible";
	}

	@Override
	public void ejecutarAccion() {
		if (estaHabilitada) {
			if (poiVerDisponibilidad.getClass().equals(ParadaColectivo.class)) {
				estaDisponible = true; // Los colectivos siempre estan
										// disponibles
			} else {//lo saque porque no es responsabilidad de la accion}
		} else {
			System.out.println("Esta accion fue inhabilitada");
		}
	}

	@Override
	public void habilitar() {
		this.estaHabilitada = true;
	}

	@Override
	public void deshabilitar() {
		this.estaHabilitada = false;
	}

	public boolean getDisponibilidad() {
		return estaDisponible;
	}
}
