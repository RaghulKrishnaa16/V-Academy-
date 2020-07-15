package vacademy.service;

import java.util.Collection;

import vacademy.entity.Theme;

public interface IThemeService {
	public Theme getThemeById(Integer id);

	public Theme getThemeByName(String name);

	public Collection<Theme> getAllThemes();

	public Theme addTheme(Theme theme);

	public Theme updateTheme(Theme theme);

	public void deleteTheme(Theme theme);
}
