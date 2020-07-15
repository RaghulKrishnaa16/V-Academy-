package vacademy.controller;

import java.util.Collection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vacademy.vacademyApplication;
import vacademy.entity.Theme;
import vacademy.service.IThemeService;


@Controller
public class ThemeController {

	private static final Logger logger = LogManager.getLogger(vacademyApplication.class);

	@Autowired
	private IThemeService themeService;

	
	@GetMapping("api/theme")
	public ResponseEntity<Collection<Theme>> getAllThemes() {
		Collection<Theme> themes = themeService.getAllThemes();
		return new ResponseEntity<>(themes, HttpStatus.OK);
	}

	
	@GetMapping("api/theme/{id}")
	public ResponseEntity<Theme> getThemeById(@PathVariable("id") Integer themeId) {
		Theme theme = themeService.getThemeById(themeId);
		return new ResponseEntity<>(theme, HttpStatus.OK);
	}

	
	@GetMapping("api/theme/0")
	public ResponseEntity<Theme> getDefaultTheme() {
		Theme theme = themeService.getThemeByName("default");
		return new ResponseEntity<>(theme, HttpStatus.OK);
	}

	
	@PostMapping("api/theme")
	public ResponseEntity<Theme> addTheme(@RequestBody Theme theme) {
		Theme newTheme = themeService.addTheme(theme);
		logger.warn("Theme '" + newTheme.getThemeId() + ":" + newTheme.getName() + "' added successfully");
		return new ResponseEntity<>(newTheme, HttpStatus.OK);
	}

	
	@PutMapping("api/theme")
	public ResponseEntity<Theme> updateThemeInTheme(@RequestBody Theme theme) {
		Theme updatedTheme = themeService.updateTheme(theme);
		logger.warn("Theme '" + updatedTheme.getThemeId() + ":" + updatedTheme.getName() + "' updated successfully");
		return new ResponseEntity<>(updatedTheme, HttpStatus.OK);
	}

	
	@DeleteMapping("api/theme")
	public ResponseEntity<Void> deleteTheme(@RequestBody Theme theme) {
		if (theme.equals(themeService.getThemeByName("default"))) {
			logger.error("Default Theme cannot be deleted");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		themeService.deleteTheme(theme);
		logger.warn("ADMIN ALERT: Theme '" + theme.getThemeId() + ":" + theme.getName() + "' deleted successfully");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
