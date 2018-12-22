package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.dao.TurnoDAO;
import modelo.dominio.Turno;

@FacesConverter(forClass = Turno.class)
public class TurnoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Integer id = null;
		try {
			id = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			id = null;
		}

		if (id != null) {
			TurnoDAO dao = new TurnoDAO();

			Turno turno = dao.lerPorId(id);
			return turno;
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Turno) {
			Turno turno = (Turno) value;
			return turno.toString();
		}

		return null;
	}

}
