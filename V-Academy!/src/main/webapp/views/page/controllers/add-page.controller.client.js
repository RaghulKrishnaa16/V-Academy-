(function() {
	angular.module("WebAppMaker").controller("AddPageController",
			AddPageController);

	function AddPageController(UserService, CourseService, PageService,
			$location, $routeParams) {
		var viewModel = this;

		function init() {
			//console.log('Before loading add new course');
			var userId = $routeParams['uid'];
			var roleType = $routeParams['roleType'];
			var courseId = $routeParams['courseId'];

			viewModel.params = {
					personId : userId,
					roleType : roleType
			};
			var promise = UserService.findUserById(userId, roleType);
			promise.then(function(response) {
				var user = response.data;
				//console.log(user);
				if (user != undefined) {
					viewModel.user = user;
				} else {
					viewModel.errorMessage = "Error while loading user by ID:"
						+ userId;
				}
			});

			var promiseCourse = CourseService.findCourseById(courseId);
			promiseCourse
			.then(function(response) {
				var course = response.data;
				if (course != undefined) {
					viewModel.course = course;
				} else {
					viewModel.errorMessage = "Error while loading course by ID:"
						+ courseId;
				}
			});
		}

		init();

		viewModel.add = add;
		function add(page) {
			if (page==undefined) {
				viewModel.errorMessage = "Must set at least name of page";
			} else if (page.name == null) {
				viewModel.errorMessage = "Page name cannot be empty";
			} else {
				var promise = PageService.createPage(
						viewModel.course.courseId,page);
				promise.then(function successCallback(response) {
					page = response.data;
					if (page) {
						$location.url(viewModel.user.roleType + "/"
								+ viewModel.user.personId + "/course/"
								+ viewModel.course.courseId + "/page");
					} else {
						init();
						viewModel.errorMessage = "Page not created";
						viewModel.successMessage = null;
					}
				}, function errorCallback(response) {
					viewModel.errorMessage = "Page not created";
				});
			}

		}
	}
})();