
package vacademy.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import vacademy.entity.HorizontalAlignment;
import vacademy.entity.Role;
import vacademy.entity.UserAction;
import vacademy.entity.VerticalAlignment;
import vacademy.repository.HorizontalAlignmentRepository;
import vacademy.repository.RoleRepository;
import vacademy.repository.UserActionRepository;
import vacademy.repository.VerticalAlignmentRepository;


public class EnumService implements IEnumService {
	@Autowired
	private UserActionRepository userActionRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private HorizontalAlignmentRepository hAlignRepository;

	@Autowired
	private VerticalAlignmentRepository vAlignRepository;

	
	@Override
	public Collection<UserAction> getUserActions() {
		final Collection<UserAction> userActions = new ArrayList<>();
		userActionRepository.findAll().iterator().forEachRemaining(userActions::add);

		return userActions;
	}

	
	@Override
	public Collection<Role> getRoles() {
		final Collection<Role> roles = new ArrayList<>();
		roleRepository.findAll().iterator().forEachRemaining(roles::add);

		return roles;

	}

	
	@Override
	public Collection<HorizontalAlignment> getHorizontalAlignments() {
		final Collection<HorizontalAlignment> hAligns = new ArrayList<>();
		hAlignRepository.findAll().iterator().forEachRemaining(hAligns::add);

		return hAligns;

	}

	
	@Override
	public Collection<VerticalAlignment> getVerticalAlignments() {
		final Collection<VerticalAlignment> vAligns = new ArrayList<>();
		vAlignRepository.findAll().iterator().forEachRemaining(vAligns::add);

		return vAligns;
	}

}
