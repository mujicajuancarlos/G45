package poi.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class ExcepcionSinAtencion {
	
	private List<LocalDateTime> feriados = new ArrayList<LocalDateTime>();

	public List<LocalDateTime> getFeriados() {
		return feriados;
	}

	public void agregarFeriados(LocalDateTime unFeriado){
		feriados.add(unFeriado);
	}
	
	public boolean noEsUnFeriado(LocalDateTime diaActual){
		return this.feriados.stream().noneMatch(unDia -> unDia.getDayOfMonth()== diaActual.getDayOfMonth())
				|| this.feriados.stream().noneMatch(unDia -> unDia.getMonth() == diaActual.getMonth());
	}
}
