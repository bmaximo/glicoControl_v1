package sp.gov.fatec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="insulina")
public class Insulina {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column (name = "insulina_r")
	private Double insulinaR;
	@Column (name = "insulina_le")
	private Double insulinaLe;
	@OneToOne
	@JoinColumn (name = "fk_indice_glicemico")
	private IndiceGlicemico indiceGlicemico;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getInsulinaR() {
		return insulinaR;
	}
	public void setInsulinaR(Double insulinaR) {
		this.insulinaR = insulinaR;
	}
	public Double getInsulinaLe() {
		return insulinaLe;
	}
	public void setInsulinaLe(Double insulinaLe) {
		this.insulinaLe = insulinaLe;
	}
	public IndiceGlicemico getIndiceGlicemico() {
		return indiceGlicemico;
	}
	public void setIndiceGlicemico(IndiceGlicemico indiceGlicemico) {
		this.indiceGlicemico = indiceGlicemico;
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
		Insulina other = (Insulina) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	

}
