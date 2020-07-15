package vacademy.service;

import java.util.Collection;

import vacademy.entity.Tab;


public interface ITabService {
	public Tab getTabById(int id);

	public Collection<Tab> getAllTabs();

	public Tab addTab(Tab tab);

	public Tab updateTab(Tab tab);

	public void deleteTab(Tab tab);

	void deleteByTabId(int tabId);
}
