package vacademy.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import vacademy.entity.Page;
import vacademy.repository.PageRepository;


@Service
public class PageService implements IPageService {

	@Autowired
	private PageRepository pageRepository;

	@Override
	public Page getPageById(int id) {
		return pageRepository.findOne(id);
	}

	@Override
	public Collection<Page> getAllPages() {
		final Collection<Page> pages = new ArrayList<>();
		pageRepository.findAll().iterator().forEachRemaining(pages::add);

		return pages;
	}

	@Override
	public Page addPage(Page page) {
		return pageRepository.save(page);
	}

	@Override
	public Page updatePage(Page page) {
		return pageRepository.save(page);
	}

	@Override
	public void deletePage(Page page) {
		pageRepository.delete(page);
	}
	
	@Override
	public void deleteByPageId(int pageId) {
		pageRepository.deleteByPageId(pageId);
	}
	
	@Override
	public void updateByPageId(int pageId, String name, String tooltipDescription) {
		pageRepository.updateByPageId(pageId, name, tooltipDescription);
	}

}
