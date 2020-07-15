package vacademy.repository;

import javax.transaction.Transactional;

import vacademy.entity.HtmlWidget;

/**
 * @author Biswaraj
 *
 */
@Transactional
public interface HtmlWidgetRepository extends WidgetBaseRepository<HtmlWidget> {
}
