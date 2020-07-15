package vacademy.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacademy.entity.Widget;
import vacademy.repository.WidgetRepository;


@Service
public class WidgetService implements IWidgetService {

	@Autowired
	private WidgetRepository widgetRepository;

	
	@Override
	public Widget getWidgetById(int id) {

		return widgetRepository.findOne(id);
	}

	
	@Override
	public Collection<Widget> getAllWidgets() {
		final Collection<Widget> widgets = new ArrayList<>();
		widgetRepository.findAll().iterator().forEachRemaining(widgets::add);

		return widgets;
	}

	
	 
	@Override
	public Widget addWidget(Widget wid) {
		return widgetRepository.save(wid);
	}

	
	@Override
	public Widget updateWidget(Widget wid) {
		return widgetRepository.save(wid);
	}

	
	@Override
	public void deleteWidget(Widget wid) {
		widgetRepository.delete(wid);
	}
	
	@Override
	public void deleteByWidgetId(int wid) {
		widgetRepository.deleteByWidgetId(wid);
	}
}
