package vacademy.service;

import java.util.Collection;

import vacademy.entity.Layout;


public interface ILayoutService {
	public Layout getLayoutById(int id);

	public Collection<Layout> getAllLayouts();

	public Layout addLayout(Layout layout);

	public Layout updateLayout(Layout layout);

	public void deleteLayout(Layout layout);
}
