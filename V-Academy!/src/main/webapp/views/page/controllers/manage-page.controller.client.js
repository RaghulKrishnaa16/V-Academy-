(function() {
	angular.module("WebAppMaker").controller("ManagePageController",
			ManagePageController);

	function ManagePageController(UserService, CourseService, PageService,
			$location, $routeParams) {
		var viewModel = this;

		function init() {
			var userId = $routeParams['uid'];
			var roleType = $routeParams['roleType'];
			var courseId = $routeParams['courseId'];

			var promise = UserService.findUserById(userId, roleType);
			promise
			.then(function(user) {
				user = user.data;
				if (user != undefined) {
					viewModel.user = user;
				} else {
					viewModel.errorMessage = "Error while loading user by ID:"
						+ userId;
				}

				var promise1 = PageService
				.getAllPagesByCourse(courseId);

				promise1
				.then(function(pages) {
					pages = pages.data;

					if (pages != undefined) {
						viewModel.pages = pages;
					} else {
						viewModel.errorMessage = "Error while loading pages for "
							+ courseId;
					}
				});

			});

			var promise1 = CourseService.findCourseById(courseId);

			promise1.then(function(course) {
				course = course.data;
				console.log(course);
				if (course != undefined) {
					viewModel.course = course;
				} else {
					viewModel.errorMessage = "Error while loading course:"
						+ courseId;
				}
			});
		}

		init();
	}
})();