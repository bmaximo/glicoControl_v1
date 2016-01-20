package sp.gov.fatec.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import sp.gov.fatec.entity.Doenca;
import sp.gov.fatec.entity.IndiceGlicemico;
import sp.gov.fatec.entity.Insulina;
import sp.gov.fatec.entity.Peso;
import sp.gov.fatec.entity.Usuario;

import sp.gov.fatec.negocio.UsuarioRN;

@SuppressWarnings("serial")
@Named
@SessionScoped
@ManagedBean(name="usuarioBean")
public class UsuarioBean implements Serializable{
	@Inject
	private UsuarioRN usuarioRN;
	
	
	private Usuario usuario;
	private Doenca doenca;
	private List<Doenca> doencas;
	//private Usuario us;
	private IndiceGlicemico indiceGlicemico = new IndiceGlicemico();
	private Insulina insulina = new Insulina();
	private Peso peso = new Peso();
	private List<Peso> pesos;
	private Date dataInicio;
	private Date dataFim;
	private List<IndiceGlicemico> indices;
	private boolean pesquisa;
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
	public String editar(){
		try{
			usuarioRN.salvar(this.usuario);
			System.out.println("sucesso");
			this.usuario = new Usuario();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "";
		}
		return "/restrito/perfil?faces-redirect=true";
	}
	
	
	
	public String verificarLogin() throws Exception {
		Usuario us;
		String resultado;

		try {
			// Enviando la encriptacion
			//String encript = DigestUtils.md5Hex(this.usuario.getNombre());
			//String encript = DigestUtils.sha1Hex(this.usuario.getClave());
			//this.usuario.setClave(encript);

			us = usuarioRN.verificarLogin(this.usuario);
			if (us != null) {

				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().put("usuario", us);
				this.usuario = us;
				resultado = "/restrito/perfil?faces-redirect=true";
				
			} else {
				if(us == null){
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Erro", "Email ou senha incorretos"));
				}
				resultado = "error";
			}
		} catch (Exception e) {
			throw e;
		}
		System.out.println(usuario.getNome());
		return resultado;	
	}
	
	public String indice(){
		return "/restrito/cadastroindice?faces-redirect=true";		
	}
	
	public String peso() throws Exception{
		getPesos();
		return "/restrito/peso?faces-redirect=true";
	}
	
	public String relatorio(){
		getIndices();
		dataInicio = null;
		dataFim = null;
		return "/restrito/relatorio?faces-redirect=true";
	}
	public void geraRelatorio(){
		this.indices = null;
		this.pesquisa = true;
	}
	public String grafico(){
		dataInicio = null;
		dataFim = null;
		return "/restrito/grafico?faces-redirect=true";
	}
	public String editarCadastro(){
		return "/restrito/editarcadastro?faces-redirect=true";
	}
	public String voltar(){
		return "/restrito/perfil?faces-redirect=true";
	}
	public String sair(){
		this.usuario = new Usuario();
		return "/PI/index.xhtml";
	}
	public String salvarIndice(){
		try{
			indiceGlicemico.setUsuario(getUsuario());
			//indiceGlicemicoRN.salvar(this.indiceGlicemico);
			usuarioRN.salvarIndice(this.indiceGlicemico);
			this.insulina.setIndiceGlicemico(this.indiceGlicemico);
			usuarioRN.salvarInsulina(this.insulina);
			System.out.println("sucesso");
			this.indiceGlicemico = new IndiceGlicemico();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "";
		}
		return "/restrito/perfil";
	}
	
	public void salvarPeso(){
		try{
			peso.setUsuario(getUsuario());
			//indiceGlicemicoRN.salvar(this.indiceGlicemico);
			usuarioRN.salvarPeso(this.peso);
			//indiceGlicemicoRN.salvarInsulina(this.insulina);
			System.out.println("sucesso");
			this.peso = new Peso();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	

	
	
	
	
	

	public List<Doenca> getDoencas() {
		usuarioRN.buscarDoenca();
		System.out.println(doenca.getDoencaNome());
		return doencas;
	}

	public void setDoencas(List<Doenca> doencas) {
		this.doencas = doencas;
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

	/*public Usuario getUs() {
		return us;
	}

	public void setUs(Usuario us) {
		this.us = us;
	}*/

	public IndiceGlicemico getIndiceGlicemico() {
		return indiceGlicemico;
	}

	public void setIndiceGlicemico(IndiceGlicemico indiceGlicemico) {
		this.indiceGlicemico = indiceGlicemico;
	}

	public Insulina getInsulina() {
		return insulina;
	}

	public void setInsulina(Insulina insulina) {
		this.insulina = insulina;
	}

	public Peso getPeso() {
		return peso;
	}

	public void setPeso(Peso peso) {
		this.peso = peso;
	}

	public List<Peso> getPesos() throws Exception {
		pesos = usuarioRN.ListaPeso(this.usuario);
		return pesos;
	}

	public void setPesos(List<Peso> pesos) {
		this.pesos = pesos;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<IndiceGlicemico> getIndices() {
		indices = usuarioRN.listaIndice(usuario);
		return indices;
	}

	public void setIndices(List<IndiceGlicemico> indices) {
		this.indices = indices;
	}

	public boolean isPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(boolean pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	
	
	
	
	
	
	
}
