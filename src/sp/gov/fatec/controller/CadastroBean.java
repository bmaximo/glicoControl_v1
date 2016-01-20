package sp.gov.fatec.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import sp.gov.fatec.entity.Doenca;
import sp.gov.fatec.entity.Usuario;
import sp.gov.fatec.negocio.UsuarioRN;

@SuppressWarnings("serial")
@Named
@ManagedBean(name="cadastroBean")
public class CadastroBean implements Serializable{
	
	@Inject
	private UsuarioRN usuarioRN;
	
	private Usuario usuario;
	private Doenca doenca;
	private List<Doenca> doencas;
	
	@PostConstruct
	public void inicializar() {
	   usuario = new Usuario();  
	   usuarioRN = new UsuarioRN();
	   doenca = new Doenca();
	}
	
	public String salvar(){
		try{
			usuarioRN.salvar(this.usuario);
			System.out.println("sucesso");
			this.usuario = new Usuario();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "";
		}
		return "index?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Doenca getDoenca() {
		return doenca;
	}

	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}

	public List<Doenca> getDoencas() {
		usuarioRN.buscarDoenca();
		return doencas;
	}

	public void setDoencas(List<Doenca> doencas) {
		this.doencas = doencas;
	}
	
	

}
