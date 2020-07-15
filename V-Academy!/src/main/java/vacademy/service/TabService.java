package vacademy.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacademy.entity.Tab;
import vacademy.repository.TabRepository;


@Service
public class TabService implements ITabService {

	@Autowired
	private TabRepository tabRepository;

	@Override
	public Tab getTabById(int id) {
		return tabRepository.findOne(id);
	}

	@Override
	public Collection<Tab> getAllTabs() {
		final Collection<Tab> tabs = new ArrayList<>();
		tabRepository.findAll().iterator().forEachRemaining(tabs::add);

		return tabs;
	}

	@Override
	public Tab addTab(Tab tab) {
		return tabRepository.save(tab);
	}

	@Override
	public Tab updateTab(Tab tab) {
		return tabRepository.save(tab);
	}

	@Override
	public void deleteTab(Tab tab) {
		tabRepository.delete(tab);
	}

	@Override
	public void deleteByTabId(int tabId) {
		tabRepository.deleteByTabId(tabId);
	}
}
