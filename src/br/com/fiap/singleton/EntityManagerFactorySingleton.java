package br.com.fiap.singleton;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {

	//Atributo Estático
	private static EntityManagerFactory fabrica;
	//Contrutor Privado
	private  EntityManagerFactorySingleton(){}
	//Método Estático getIntace
	public static EntityManagerFactory getInstance(){
		if(fabrica == null){
			fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
		}
		return fabrica;
	}
	
}
