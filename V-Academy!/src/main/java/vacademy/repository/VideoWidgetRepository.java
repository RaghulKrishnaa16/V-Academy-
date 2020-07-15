package vacademy.repository;

import javax.transaction.Transactional;

import vacademy.entity.VideoWidget;


@Transactional
public interface VideoWidgetRepository extends WidgetBaseRepository<VideoWidget> {
}
