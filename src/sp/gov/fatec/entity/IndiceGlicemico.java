package sp.gov.fatec.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "indice_glicemico")

public class IndiceGlicemico {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column (name = "data_hora_medicao")
	private Date dataHora;
	@Column (name = "indice_antes")
	private Integer indiceAntes;
	@Column (name = "indice_depois")
	private Integer indiceDepois;
	@Column (name = "status")
	private String status;
	@Column (name = "observacao")
	private String observacao;
	@ManyToOne 
	@JoinColumn (name = "fk_usuario")
	private Usuario usuario;
	@OneToOne(mappedBy = "indiceGlicemico")
	private Insulina insulina;
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Insulina getInsulina() {
		return insulina;
	}
	public void setInsulina(Insulina insulina) {
		this.insulina = insulina;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public Integer getIndiceAntes() {
		return indiceAntes;
	}
	public void setIndiceAntes(Integer indiceAntes) {
		this.indiceAntes = indiceAntes;
	}
	public Integer getIndiceDepois() {
		return indiceDepois;
	}
	public void setIndiceDepois(Integer indiceDepois) {
		this.indiceDepois = indiceDepois;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		IndiceGlicemico other = (IndiceGlicemico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
