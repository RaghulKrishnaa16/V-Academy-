package vacademy.service;

import java.util.Collection;

import vacademy.entity.Page;



public interface IPageService {
	public Page getPageById(int id);

	public Collection<Page> getAllPages();

	public Page addPage(Page page);

	public Page updatePage(Page page);

	public void deletePage(Page page);

	void deleteByPageId(int pageId);

	void updateByPageId(int pageId, String name, String tooltipDescription);
}
