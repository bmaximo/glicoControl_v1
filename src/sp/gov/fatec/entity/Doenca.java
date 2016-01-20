package sp.gov.fatec.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "doenca")
public class Doenca {
	
	@Id @GeneratedValue
	private Integer id;
	@Column (name = "doenca_nome")
	private String doencaNome;
	@OneToMany(mappedBy = "doenca", cascade = CascadeType.ALL)
	private Set<Usuario> usuario = new LinkedHashSet<Usuario>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDoencaNome() {
		return doencaNome;
	}
	public void setDoencaNome(String doencaNome) {
		this.doencaNome = doencaNome;
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
		Doenca other = (Doenca) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	

}
