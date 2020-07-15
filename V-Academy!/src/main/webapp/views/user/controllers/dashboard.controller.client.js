(function() {
	angular.module("WebAppMaker").controller("DashboardController",
			DashboardController);

	function DashboardController(UserService, CourseService, $location,
			$routeParams) {
		var viewModel = this;

		function init() {
			var userId = $routeParams['uid'];
			var roleType = $routeParams['roleType'];
			console.log('Inside Dashboard controller init');
			var promise = UserService.findUserById(userId, roleType);
			promise.then(function(user) {
				user = user.data;
				if (user != undefined) {
					viewModel.user = user;
				} else {
					viewModel.errorMessage = "Error while loading user by ID:"
						+ userId;
				}

				if (user.roleType == 'student' || user.roleType == 'faculty') {
					var promise = CourseService.findCourseByPersonId(userId);
					promise.then(function(response) {
						courses = response.data;
						if (courses != undefined) {
							viewModel.courses = courses;
						} else {
							viewModel.errorMessage = "Error loading courses";
						}
					});
				} else if (user.roleType == 'administrator') {
					var promise = CourseService.findAllCourses();
					promise.then(function(response) {
						courses = response.data;
						console.log(courses);
						if (courses != undefined) {
							viewModel.courses = courses;
						} else {
							viewModel.errorMessage = "Error loading courses";
						}
					});
				}
			});
		}

		init();
	}
})();