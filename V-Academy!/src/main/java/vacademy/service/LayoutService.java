package vacademy.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacademy.entity.Layout;
import vacademy.repository.LayoutRepository;


@Service
public class LayoutService implements ILayoutService {

	@Autowired
	private LayoutRepository layoutRepository;

	@Override
	public Layout getLayoutById(int id) {
		return layoutRepository.findOne(id);
	}

	@Override
	public Collection<Layout> getAllLayouts() {
		final Collection<Layout> layouts = new ArrayList<>();
		layoutRepository.findAll().iterator().forEachRemaining(layouts::add);

		return layouts;
	}

	@Override
	public Layout addLayout(Layout layout) {
		return layoutRepository.save(layout);
	}

	@Override
	public Layout updateLayout(Layout layout) {
		return layoutRepository.save(layout);
	}

	@Override
	public void deleteLayout(Layout layout) {
		layoutRepository.delete(layout);
	}

}
