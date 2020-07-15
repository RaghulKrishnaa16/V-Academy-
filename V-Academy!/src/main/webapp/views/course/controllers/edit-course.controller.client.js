(function() {
	angular.module("WebAppMaker").controller("EditCourseController",
			EditCourseController);

	function EditCourseController(UserService, CourseService, $location,
			$routeParams) {
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
				console.log(course);
				if (course != undefined) {
					viewModel.course = course;
				} else {
					viewModel.errorMessage = "Error while loading course by ID:"
						+ courseId;
				}
			});

		}

		init();

		viewModel.update = update;
		function update(course) {
			course.courseId = viewModel.course.courseId;
			course.identifier = viewModel.course.identifier;
			course.layout = viewModel.course.layout;
			course.createDate = viewModel.course.createDate;
			course.updateDate = new Date();

			if (course.name == null) {
				course.name = viewModel.course.name;
			}
			if (course.description == null) {
				course.description = viewModel.course.description;
			}

			if (!course) {
				viewModel.errorMessage = "Please fill course identifier and name";
			} else if (course.identifier == null) {
				viewModel.errorMessage = "Course identifier cannot be empty ";
			} else if (course.name == null) {
				viewModel.errorMessage = "Course name cannot be empty ";
			} else {
				var promise = CourseService.updateCourse(course);
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

		viewModel.deleteCourse = deleteCourse;
		function deleteCourse() {
			var promise = CourseService.deleteCourse(viewModel.course);

			promise.then(function successCallback(response) {
				$location.url("/user/" + viewModel.params.roleType + "/"
						+ viewModel.params.personId);
			}, function errorCallback(response) {
				viewModel.errorMessage = "Course cannot be deleted";
			});
		}

	}
})();