package sp.gov.fatec.conversor;

import static net.sf.trugger.element.Elements.element;
import static net.sf.trugger.reflection.ReflectionPredicates.annotatedWith;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;

/**
 * Converter para entidades JPA. Baseia-se nas anotações @Id e @EmbeddedId para
 * identificar o atributo que representa a identidade da entidade. Também
 * as anotações nas super classes.
 *
 * Lembrar de adicionar nas dependencias o trugger-2.8.jar
 */
@FacesConverter("entityConversor")
public class EntityConversor implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {
		if (value != null) {
			return component.getAttributes().get(value);
		}
		return null;
	}

	public String getAsString(FacesContext ctx, UIComponent component,
			Object obj) {
		if (obj != null && !"".equals(obj)) {
			String id;
		
				id = this.getId(obj);
				if (id == null) {
					id = "";
				}
				id = id.trim();
				component.getAttributes().put(id,
						getClazz(ctx, component).cast(obj));
				return id;
		}
		return null;
	}

	/**
	 * Obtém, via expression language, a classe do objeto
	 * 
	 * @param FacesContext
	 *            facesContext
	 * 
	 * @param UICompoment
	 *            compoment
	 * 
	 * @return Class<?>
	 */
	private Class<?> getClazz(FacesContext facesContext, UIComponent component) { 
		return component.getValueExpression("value").getType(facesContext.getELContext()); 
		} 

	/**
	 * Retorna a representação em String do retorno do método anotado com @Id ou @EmbeddedId
	 * do objeto.
	 * 
	 * @param Object obj
	 * 
	 * @return String
	 */
	public String getId(Object obj) {
		Object idValue = element()
				.thatMatches(
						annotatedWith(Id.class).or(
								annotatedWith(EmbeddedId.class))).in(obj)
				.value();
		return String.valueOf(idValue);
	}
}


