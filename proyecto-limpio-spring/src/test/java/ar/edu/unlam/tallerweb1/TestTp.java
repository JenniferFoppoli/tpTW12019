package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public class TestTp extends SpringTest {

	@Test @Transactional @Rollback
	public void testQueBuscaPaisesDeHablaInglesa() {

		Pais eeuu= new Pais();
		eeuu.setIdioma("ingles");
		
		Pais inglaterra= new Pais();
		inglaterra.setIdioma("ingles");
		
		Pais argentina= new Pais();
		argentina.setIdioma("español");
		
		Pais mexico= new Pais();
		mexico.setIdioma("español");
		
		
		Session session= getSession();
		
		session.save(eeuu);
		session.save(inglaterra);
		session.save(argentina);
		session.save(mexico);
		
		
		List<Pais> PaisesDeHablaInglesa = session.createCriteria(Pais.class)
				.add(Restrictions.eq("idioma","ingles"))
				.list();
		
	assertThat(PaisesDeHablaInglesa.size()).isEqualTo(2);
		
		
	}
	
	@Test @Transactional @Rollback
	public void testQueBuscaPaisesDeContinenteEuropeo() {

		Continente europa= new Continente();
		europa.setNombre("europa");
		Session session= getSession();
		session.save(europa);
		
		Continente america= new Continente();
		europa.setNombre("america");
		session.save(america);

		
		
		
		Pais eeuu= new Pais();
		eeuu.setContinente(america);
		session.save(eeuu);
		
		Pais francia= new Pais();
		francia.setContinente(europa);
		session.save(francia);
		
		Pais inglaterra= new Pais();
		inglaterra.setContinente(europa);
		session.save(inglaterra);
		

		
		List<Pais> PaisesDeContinenteEuropeo = session.createCriteria(Pais.class)
				.add(Restrictions.eq("continente",europa))
				.list();
		
	assertThat(PaisesDeContinenteEuropeo.size()).isEqualTo(2);
	}
	
	//4 buscar todos los paises cuya capital esta al norte del tropico de cancer > latitud de 23º 26′ 14″
	
	@Test @Transactional @Rollback
	public void testQueBuscaPaisesConCapitalAlNorteDeCancer() {
		
		//capital de eeuu Washington D. C.Latitud: 38°53′42″ N Longitud: 77°02′10″ O 
		
		Ubicacion ubicacionWashington = new Ubicacion();
		ubicacionWashington.setLatitud(38.53);
		ubicacionWashington.setLongitud(-77.02);
		Session session= getSession();
		session.save(ubicacionWashington);
		
		Ciudad whashington = new Ciudad();
		whashington.setNombre("Washington");
		whashington.setUbicacionGeografica(ubicacionWashington);
		session.save(whashington);
		
		Continente america = new Continente();
		america.setNombre("America");
		session.save(america);
		
		Pais eeuu = new Pais();
		eeuu.setNombre("EEUU");
		eeuu.setCapital(whashington);
		eeuu.setContinente(america);
		session.save(eeuu);
		
		// buenos aires Latitud: -34.6131500°	Longitud: -58.3772300°
		
		Ubicacion ubicacionBuenosAires = new Ubicacion();
		ubicacionBuenosAires.setLatitud(-34.61);
		ubicacionBuenosAires.setLongitud(-58.38);
		session.save (ubicacionBuenosAires);
		
		Ciudad buenosAires = new Ciudad();
		buenosAires.setNombre("Buenos Aires");
		buenosAires.setUbicacionGeografica(ubicacionBuenosAires);
		session.save(buenosAires);
		
				
		Pais argentina = new Pais();
		argentina.setNombre("Argentina");
		argentina.setCapital(buenosAires);
		argentina.setContinente(america);
		session.save(argentina);
		
		//paris Latitud: 48.8534100°	Longitud: 2.3488000°
		
		Ubicacion ubicacionParis = new Ubicacion();
		ubicacionParis.setLatitud(48.85);
		ubicacionParis.setLongitud(2.35);
		session.save(ubicacionParis);
		
		Ciudad paris = new Ciudad();
		paris.setNombre("Paris");
		paris.setUbicacionGeografica(ubicacionParis);
		session.save(paris);
		
		Continente europa= new Continente();
		europa.setNombre("europa");
		session.save(europa);
		
		Pais francia = new Pais();
		francia.setNombre("Francia");
		francia.setCapital(paris);
		francia.setContinente(europa);
		session.save(francia);
		
		
		
		List<Pais>lista = session.createCriteria(Pais.class)
    			.createAlias("capital", "capitalBuscada")
    			.createAlias("capitalBuscada.ubicacionGeografica", "ubicacionBuscada")
    			.add(Restrictions.gt("ubicacionBuscada.latitud",23.43))
    			.list();
    	
    	assertThat(lista.size()).isEqualTo(2);
    	
	}
    	
    	//5) Ciudades del hemisferio sur = latitud <0
    	public void testQueBuscaCiudadesHemisferioSur() {
    		
    		//capital de eeuu Washington D. C.Latitud: 38°53′42″ N Longitud: 77°02′10″ O 
    		
    		Ubicacion ubicacionWashington = new Ubicacion();
    		ubicacionWashington.setLatitud(38.53);
    		ubicacionWashington.setLongitud(-77.02);
    		Session session= getSession();
    		session.save(ubicacionWashington);
    		
    		Ciudad whashington = new Ciudad();
    		whashington.setNombre("Washington");
    		whashington.setUbicacionGeografica(ubicacionWashington);
    		session.save(whashington);
    		
    		Continente america = new Continente();
    		america.setNombre("America");
    		session.save(america);
    		
    		Pais eeuu = new Pais();
    		eeuu.setNombre("EEUU");
    		eeuu.setCapital(whashington);
    		eeuu.setContinente(america);
    		session.save(eeuu);
    		
    		// buenos aires Latitud: -34.6131500°	Longitud: -58.3772300°
    		
    		Ubicacion ubicacionBuenosAires = new Ubicacion();
    		ubicacionBuenosAires.setLatitud(-34.61);
    		ubicacionBuenosAires.setLongitud(-58.38);
    		session.save (ubicacionBuenosAires);
    		
    		Ciudad buenosAires = new Ciudad();
    		buenosAires.setNombre("Buenos Aires");
    		buenosAires.setUbicacionGeografica(ubicacionBuenosAires);
    		session.save(buenosAires);
    		
    				
    		Pais argentina = new Pais();
    		argentina.setNombre("Argentina");
    		argentina.setCapital(buenosAires);
    		argentina.setContinente(america);
    		session.save(argentina);
    		
    		//paris Latitud: 48.8534100°	Longitud: 2.3488000°
    		
    		Ubicacion ubicacionParis = new Ubicacion();
    		ubicacionParis.setLatitud(48.85);
    		ubicacionParis.setLongitud(2.35);
    		session.save(ubicacionParis);
    		
    		Ciudad paris = new Ciudad();
    		paris.setNombre("Paris");
    		paris.setUbicacionGeografica(ubicacionParis);
    		session.save(paris);
    		
    		Continente europa= new Continente();
    		europa.setNombre("europa");
    		session.save(europa);
    		
    		Pais francia = new Pais();
    		francia.setNombre("Francia");
    		francia.setCapital(paris);
    		francia.setContinente(europa);
    		session.save(francia);
    	List<Ciudad> ciudades = getSession().createCriteria(Ciudad.class)
					.createAlias("ubicacionGeografica", "ubicacionBuscada")
					.add(Restrictions.le("ubicacionBuscada.latitud",00.00))
					.list();

    	assertThat(ciudades.size()).isEqualTo(1);
    	
    	
    	
    	
		
	}
	

	
	
	
	
	
	
	

}
