(function() {
	angular.module("WebAppMaker").controller("AddCourseController",
			AddCourseController);

	function AddCourseController(UserService, CourseService, $location,
			$routeParams) {
		var viewModel = this;

		function init() {
			var userId = $routeParams['uid'];
			var roleType = $routeParams['roleType'];
			viewModel.params = {
					personId : userId,
					roleType : roleType
			};
			var promise = UserService.findUserById(userId, roleType);
			promise.then(function(response) {
				var user = response.data;
				if (user != undefined) {
					viewModel.user = user;
				} else {
					viewModel.errorMessage = "Error while loading user by ID:"
						+ userId;
				}
			});
		}

		init();

		viewModel.add = add;
		function add(course) {
			if (!course) {
				viewModel.errorMessage = "Please fill course identifier and name";
			} else if (course.identifier == null) {
				viewModel.errorMessage = "Course identifier cannot be empty ";
			} else if (course.name == null) {
				viewModel.errorMessage = "Course name cannot be empty ";
			} else {
				var promise = CourseService.createCourse(course,
						viewModel.params.personId, viewModel.params.roleType);
				promise.then(function successCallback(response) {
					course = response.data;
					if (course) {
						$location.url("/user/" + viewModel.params.roleType
								+ "/" + viewModel.params.personId);
					} else {
						init();
						viewModel.errorMessage = "Course not created";
						viewModel.successMessage = null;
					}
				}, function errorCallback(response) {
					viewModel.errorMessage = "Course not created";
				});
			}
		}

	}
})();