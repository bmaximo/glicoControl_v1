package sp.gov.fatec.negocio;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import sp.gov.fatec.controller.UsuarioBean;
import sp.gov.fatec.entity.Doenca;
import sp.gov.fatec.entity.IndiceGlicemico;
import sp.gov.fatec.entity.Insulina;
import sp.gov.fatec.entity.Peso;
import sp.gov.fatec.entity.Usuario;
import util.JPAUtil;

@Stateless
public class UsuarioRN {
	@Inject
	private UsuarioBean usuarioBean;
	
	EntityManager em = JPAUtil.getEntityManager();
	public void salvar (Usuario usuario){
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if(usuario.getId() == null || usuario.getId() == 0){
			em.persist(usuario);
		}else{
			em.merge(usuario);
		}	
		tx.commit();
		
	}
	
	public void salvarIndice (IndiceGlicemico indiceGlicemico){
		/*if(indiceGlicemico.getId() == null || indiceGlicemico.getId() == 0){
			entityManager.persist(indiceGlicemico);
		}else{
			entityManager.merge(indiceGlicemico);
		}*/	
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(indiceGlicemico);
		tx.commit();
		
	}
	public void salvarInsulina (Insulina insulina){
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(insulina);
		tx.commit();
		
	}
	
	public void salvarPeso (Peso peso){
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(peso);
		tx.commit();
		em.close();
	}
	
	
	
	public List<Doenca> buscarDoenca(){
		String hql = "select d from Doenca d";
		Query consulta = em.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Doenca> result = consulta.getResultList();
		return result;
	}
	
	
	public Usuario verificarLogin(Usuario usuario) throws Exception {
		Usuario us = null;

		try {
			String hql = "Select u FROM Usuario u WHERE u.email = '" + usuario.getEmail()
					+ "' and u.senha = '" + usuario.getSenha() + "'";
			Query query = em.createQuery(hql);

			if (!query.getResultList().isEmpty()) {
				us = (Usuario) query.getResultList().get(0);
			}

		} catch (Exception e) {
			throw e;
		}

		return us;
	}
	
	public List<Peso> ListaPeso(Usuario usuario) throws Exception {
		try {
			String hql = "select p from Peso p where p.usuario = '"+ usuario +"'";
			Query consulta = em.createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Peso> result = consulta.getResultList();
			return result;
		} catch (Exception ex) {
			throw ex;
		}
		
	}
	
	public List<IndiceGlicemico> listaIndice(Usuario usuario){
		String hql = "select i from IndiceGlicemico i where i.usuario = '"+usuario+
				"'and i.dataHora >= '"+usuarioBean.getDataInicio()+"' and i.dataHora <= '"+usuarioBean.getDataFim()
				+"'";
		Query consulta = em.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<IndiceGlicemico> result = consulta.getResultList();
		return result;
	}
}
