package vacademy.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacademy.entity.Theme;
import vacademy.repository.ThemeRepository;


@Service
public class ThemeService implements IThemeService {

	@Autowired
	private ThemeRepository themeRepository;

	@Override
	public Theme getThemeById(Integer id) {
		return themeRepository.findOne(id);
	}

	@Override
	public Theme getThemeByName(String name) {
		return themeRepository.findByName(name.trim());
	}

	@Override
	public Collection<Theme> getAllThemes() {
		final Collection<Theme> themes = new ArrayList<>();
		themeRepository.findAll().iterator().forEachRemaining(themes::add);

		return themes;
	}

	@Override
	public Theme addTheme(Theme theme) {
		return themeRepository.save(theme);
	}

	@Override
	public Theme updateTheme(Theme theme) {
		return themeRepository.save(theme);
	}

	@Override
	public void deleteTheme(Theme theme) {
		themeRepository.delete(theme);
	}

}
