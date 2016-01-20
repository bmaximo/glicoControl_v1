package teste;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import sp.gov.fatec.entity.Usuario;
import util.JPAUtil;


public class Teste {

	public static void main(String[] args) {
		

		EntityManager em = JPAUtil.getEntityManager();
		//DateTime today = new DateTime();
		Calendar data = Calendar.getInstance();
		
		Usuario usuario = new Usuario ();
		
		usuario.setNome("barbara");
		usuario.setEmail("b@b.com");
		usuario.setSenha("1234");
		usuario.setSexo("f");
		usuario.setDataNascimento(data.getTime());
		//usuario.getDoenca().setId(1);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(usuario);
		tx.commit();
		em.close();
		/*UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(usuario);*/
		
	}

}
