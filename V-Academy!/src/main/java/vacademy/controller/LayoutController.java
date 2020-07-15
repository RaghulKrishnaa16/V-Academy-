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
import vacademy.entity.Course;
import vacademy.entity.Layout;
import vacademy.entity.Page;
import vacademy.service.ICourseService;
import vacademy.service.ILayoutService;
import vacademy.service.IPageService;

@Controller
public class LayoutController {

	private static final Logger logger = LogManager.getLogger(vacademyApplication.class);

	@Autowired
	private ICourseService courseService;
	@Autowired
	private ILayoutService layoutService;
	@Autowired
	private IPageService pageService;

	@GetMapping("api/layout")
	public ResponseEntity<Collection<Layout>> getAllLayouts() {
		Collection<Layout> layouts = layoutService.getAllLayouts();
		return new ResponseEntity<>(layouts, HttpStatus.OK);
	}

	@GetMapping("api/layout/{id}")
	public ResponseEntity<Layout> getLayoutById(@PathVariable("id") Integer id) {
		Layout layout = layoutService.getLayoutById(id);
		return new ResponseEntity<>(layout, HttpStatus.OK);
	}

	
	@GetMapping("api/course/{cid}/layout")
	public ResponseEntity<Layout> getLayoutFromCourse(@PathVariable("cid") Integer courseId) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);

			if (givenCourse == null) {
				logger.error("Supplied Course does not exist ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			if (givenCourse.getLayout() == null) {
				logger.error("Supplied Course has no Layout defined ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			Layout layout = layoutService.getLayoutById(givenCourse.getLayout().getLayoutId());
			return new ResponseEntity<>(layout, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in retrieving Layout from Course '" + courseId + "'\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	
	@PutMapping("api/course/{cid}/layout")
	public ResponseEntity<Layout> updateLayoutInLayout(@PathVariable("cid") Integer courseId,
			@RequestBody Layout layout) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Layout layoutInCourse = givenCourse.getLayout();

			if (givenCourse == null || !layoutInCourse.equals(layout)) {
				logger.error("Supplied Course or Layout does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			Layout updatedLayout = layoutService.updateLayout(layout);
			logger.info("Layout '" + updatedLayout.getLayoutId() + ":" + updatedLayout.getName()
					+ "' updated successfully in Course: " + givenCourse.getName());
			return new ResponseEntity<>(updatedLayout, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(
					"Error[" + e.getMessage() + "]: Issue in updating existing Layout in Course '" + courseId + "'\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

		@DeleteMapping("api/layout")
	public ResponseEntity<Void> deleteLayout(@RequestBody Layout layout) {
		layoutService.deleteLayout(layout);
		logger.warn("ADMIN ALERT: Layout '" + layout.getLayoutId() + ":" + layout.getName() + "' deleted successfully");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	@DeleteMapping("api/course/{cid}/layout")
	public ResponseEntity<Void> deleteLayoutFromCourse(@PathVariable("cid") Integer courseId,
			@RequestBody Layout layout) {

		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Layout layoutInCourse = givenCourse.getLayout();

			if (givenCourse.equals(null) || !layoutInCourse.equals(layout)) {
				logger.error("Supplied Course or Layout does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			layoutService.deleteLayout(layout);
			givenCourse.setLayout(null);
			courseService.updateCourse(givenCourse);
			logger.info("Layout '" + layout.getLayoutId() + ":" + layout.getName()
					+ "' deleted successfully from Course: " + givenCourse.getName());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in Deleting Layout from Course '" + courseId + "'\n");
			logger.error(e.toString());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	
	@PostMapping("api/course/{cid}/page")
	public ResponseEntity<Page> addPageToCourse(@PathVariable("cid") Integer courseId, @RequestBody Page page) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);

			if (givenCourse == null) {
				logger.error("Supplied Course does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			if (givenCourse.getLayout() == null) {
				logger.error("Supplied Course has no Layout/Theme assigned yet ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			Layout layout = layoutService.getLayoutById(givenCourse.getLayout().getLayoutId());

			Page newPage = pageService.addPage(page);
			newPage.setLayout(layout);
			newPage.setUpdateDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
			Page updatedPage = pageService.updatePage(newPage);
			layout.getPages().add(newPage);
			layout.setUpdateDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
			layoutService.updateLayout(layout);
			logger.info("Page '" + updatedPage.getPageId() + ":" + updatedPage.getName()
					+ "' created successfully in Course '" + givenCourse.getName() + "'");
			return new ResponseEntity<>(updatedPage, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in adding Page to Course '" + courseId + "']\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
}
