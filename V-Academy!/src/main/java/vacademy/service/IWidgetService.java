package vacademy.service;

import java.util.Collection;

import vacademy.entity.Widget;


public interface IWidgetService {

	
	public Widget getWidgetById(int id);

	
	public Collection<Widget> getAllWidgets();

	
	public Widget addWidget(Widget wid);

	
	public Widget updateWidget(Widget wid);

	
	public void deleteWidget(Widget wid);

	void deleteByWidgetId(int wid);
}
