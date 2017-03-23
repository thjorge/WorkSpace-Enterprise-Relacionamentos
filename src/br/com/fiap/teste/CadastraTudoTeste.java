package br.com.fiap.teste;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.dao.impl.TimeDAOImpl;
import br.com.fiap.entity.Campeonato;
import br.com.fiap.entity.Jogador;
import br.com.fiap.entity.Tecnico;
import br.com.fiap.entity.Time;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class CadastraTudoTeste {

	public static void main(String[] args) {
		//Obter o EntityManager
		EntityManager em = EntityManagerFactorySingleton
				.getInstance().createEntityManager();

		//Instanciar um TimeDAOImpl
		TimeDAOImpl dao = new TimeDAOImpl(em);

		//Instaciar a galera (Entidades!)		
		Tecnico tecnico = new Tecnico("ROGERIO CENI");
		Time time = new Time(0, "SÃO PAULO", Calendar.getInstance(), 12, tecnico);
		time.addJogador(new Jogador("LUGANO"));
		time.addJogador(new Jogador("PRATO"));
		time.addJogador(new Jogador("CUEVA"));
		
		List<Campeonato>lista = new ArrayList<Campeonato>();
		lista.add(new Campeonato("PAULISTA"));
		lista.add(new Campeonato("LIBERTADORES"));
		
		time.setCampeonatos(lista);
		//Cadastrar o time e commit (o resto vai por cascata)
		try {
			dao.cadastrar(time);
			dao.salvar();
		} catch (Exception e) {
			em.close();			
		}
		
		//Sucesso!

	}

}
