package sp.gov.fatec.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "usuario")
@SuppressWarnings("serial")
public class Usuario implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "nome_usuario")
	private String nome;
	@Column (name = "email_usuario")
	private String email;
	@Column (name = "senha_usuario")
	private String senha;
	@Column (name = "sexo")
	private String sexo;
	@Column (name = "data_nascimento")
	private Date dataNascimento;
	@ManyToOne
	@JoinColumn (name="fk_doenca")
	private Doenca doenca;
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<Peso> peso = new LinkedHashSet<Peso>();
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<IndiceGlicemico> indiceGlicemico = new LinkedHashSet<IndiceGlicemico>();
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Set<Peso> getPeso() {
		return peso;
	}
	public void setPeso(Set<Peso> peso) {
		this.peso = peso;
	}
	public Set<IndiceGlicemico> getIndiceGlicemico() {
		return indiceGlicemico;
	}
	public void setIndiceGlicemico(Set<IndiceGlicemico> indiceGlicemico) {
		this.indiceGlicemico = indiceGlicemico;
	}
	
	
	public Doenca getDoenca() {
		return doenca;
	}
	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
