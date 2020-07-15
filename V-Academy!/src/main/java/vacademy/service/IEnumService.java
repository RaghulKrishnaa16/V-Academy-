
package vacademy.service;

import java.util.Collection;

import vacademy.entity.HorizontalAlignment;
import vacademy.entity.Role;
import vacademy.entity.UserAction;
import vacademy.entity.VerticalAlignment;


public interface IEnumService {
	public Collection<UserAction> getUserActions();

	public Collection<Role> getRoles();

	public Collection<HorizontalAlignment> getHorizontalAlignments();

	public Collection<VerticalAlignment> getVerticalAlignments();
}
