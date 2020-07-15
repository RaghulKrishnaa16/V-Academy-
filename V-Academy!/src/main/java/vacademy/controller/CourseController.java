
package vacademy.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import vacademy.vacademyApplication;
import vacademy.entity.Course;
import vacademy.entity.Layout;
import vacademy.entity.Theme;
import vacademy.model.CourseRole;
import vacademy.service.ICourseService;
import vacademy.service.ILayoutService;
import vacademy.service.IThemeService;


@Controller
public class CourseController {

	private static final Logger logger = LogManager.getLogger(vacademyApplication.class);

	@Autowired
	private ICourseService courseService;
	@Autowired
	private ILayoutService layoutService;
	@Autowired
	private IThemeService themeService;

	@GetMapping("api/course/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") Integer id) {
		Course course = courseService.getCourseById(id);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@GetMapping("api/course")
	public ResponseEntity<Collection<Course>> getAllCourses() {
		Collection<Course> course = courseService.getAllCourses();
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@GetMapping("api/{userId}/courserole")
	public ResponseEntity<Collection<CourseRole>> getCourseRoleByUser(@PathVariable("userId") int userId) {
		Collection<CourseRole> course = courseService.getCourseRoleByPersonId(userId);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@GetMapping("api/{userId}/course")
	public ResponseEntity<Collection<Course>> getCourseByUser(@PathVariable("userId") int userId) {
		Collection<CourseRole> courseRole = courseService.getCourseRoleByPersonId(userId);
		Collection<Course> courses = new ArrayList<>();
		if (courseRole != null) {
			for (CourseRole cr : courseRole) {
				courses.add(cr.getCourse());
			}
		}
		
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	

	@PostMapping("api/course")
	public ResponseEntity<Course> addCourse(@RequestBody Course course, @RequestParam("personId") int personId, @RequestParam("roleType") String role) {
		Course newCourse = courseService.addCourse(course, personId, role);
		assignDefaultThemeToCourse(newCourse.getCourseId());
		return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
	}
	
	@PutMapping("api/course")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
		Course foundCourse = courseService.getCourseById(course.getCourseId());
		course.setLayout(foundCourse.getLayout());
		Course newCourse = courseService.updateCourse(course);

		return new ResponseEntity<>(newCourse, HttpStatus.OK);
	}
	
	@PostMapping("api/course/delete")
	@DeleteMapping("api/course")
	public ResponseEntity<Void> deleteCourse(@RequestBody Course course) {
		course.setLayout(null);
		courseService.deleteCourseById(course.getCourseId());

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("api/course/assign")
	public ResponseEntity<Void> assignCourseToPerson(@RequestParam("courseId")int courseId, @RequestParam("personId") int personId,
													@RequestParam("roleType") String role) {
		courseService.assignCourse(courseId, personId, role);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("api/course/drop")
	public ResponseEntity<Void> dropCourseForPerson(@RequestParam("courseId")int courseId, @RequestParam("personId") int personId,
													@RequestParam("roleType") String role) {
		courseService.dropCourse(courseId, personId, role);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@PostMapping("api/course/{cid}/layout")
	public ResponseEntity<Layout> addLayoutToCourse(@PathVariable("cid") Integer courseId, @RequestBody Layout layout) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);

			if (givenCourse == null) {
				logger.error("Supplied Course does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			Layout newLayout = layoutService.addLayout(layout);
			newLayout.setCourse(givenCourse);
			newLayout.setUpdateDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
			givenCourse.setLayout(newLayout);
			givenCourse.setUpdateDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
			Course updatedCourse = courseService.updateCourse(givenCourse);
			Layout updatedLayout = layoutService.updateLayout(newLayout);
			logger.info("Layout '" + updatedLayout.getLayoutId() + ":" + updatedLayout.getName()
					+ "' created successfully in Course '" + givenCourse.getName() + "'");
			return new ResponseEntity<>(updatedCourse.getLayout(), HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in adding Layout to Course '" + courseId + "']\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	
	@PostMapping("api/course/{cid}/theme")
	public ResponseEntity<Layout> assignThemeToCourse(@PathVariable("cid") Integer courseId, @RequestBody Theme theme) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);

			if (givenCourse == null || theme == null || themeService.getThemeById(theme.getThemeId()) == null) {
				logger.error("Supplied Course or Theme does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			
			Layout layout;

			if (givenCourse.getLayout() == null) {
				layout = new Layout();
				layout.setName("default_" + givenCourse.getName().replaceAll("\\s+", ""));
				layout.setTitle("Default: " + givenCourse.getName());
				layout.setDescription("Default: " + givenCourse.getDescription());
			} else {
				layout = givenCourse.getLayout();
				layout.setUpdateDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
			}

			
			layout.setTheme(theme);
			layout.setCustomBackground(theme.getThemeBackgroundImage());
			layout.setStylesheetLink(theme.getStylesheetLink());
			Layout updatedLayout = layoutService.updateLayout(layout);

			
			theme.setNoOfUses(theme.getNoOfUses() + 1);
			themeService.updateTheme(theme);

			
			if (givenCourse.getLayout() == null)
				updatedLayout = addLayoutToCourse(givenCourse.getCourseId(), updatedLayout).getBody();

			logger.info("Layout '" + updatedLayout.getLayoutId() + ":" + updatedLayout.getName()
					+ "' successfully assigned with Theme '" + theme.getName() + "' for Course '"
					+ givenCourse.getName() + "'" + givenCourse.toString());
			return new ResponseEntity<>(updatedLayout, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in assigning Theme to Course '" + courseId + "']\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	
	@PostMapping("api/course/{cid}/theme/0")
	public ResponseEntity<Layout> assignDefaultThemeToCourse(@PathVariable("cid") Integer courseId) {
		return assignThemeToCourse(courseId, themeService.getThemeByName("default"));
	}
}
