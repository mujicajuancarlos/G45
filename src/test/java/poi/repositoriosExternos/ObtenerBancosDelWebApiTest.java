package poi.repositoriosExternos;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import poi.repositorios.RepositorioAbstracto;
import poi.repositorios.RepositorioBancosExternos;

public class ObtenerBancosDelWebApiTest {

	
	RepositorioAbstracto repo = RepositorioBancosExternos.getInstance();
	
	@After
	public void vaciarRepositorio(){
		repo.limpiarPOIs();
	}
	
	@Test
	public void probarJson() throws Exception{
		//Trae de a 2 bancos que obtiene de la API provista por la catedra
		((RepositorioBancosExternos) repo).actualizarRepositorio();
		
		Assert.assertEquals(2, repo.getBancos().size());
	}
}
