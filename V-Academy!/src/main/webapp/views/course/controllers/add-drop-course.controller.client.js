(function() {
	angular.module("WebAppMaker").controller("AddDropCourseController",
			AddDropCourseController);

	function AddDropCourseController(UserService, CourseService, $location,
			$routeParams) {
		var viewModel = this;

		function init() {
			console.log('Before loading add drop  course');
			var userId = $routeParams['uid'];
			var roleType = $routeParams['roleType'];
			viewModel.params = {
					personId : userId,
					roleType : roleType
			};
			var promise = UserService.findUserById(userId, roleType);
			promise
			.then(function(response) {
				var user = response.data;
				// console.log(user);
				if (user != undefined) {
					viewModel.user = user;
				} else {
					viewModel.errorMessage = "Error while loading user by ID:"
						+ userId;
				}

				if (user.roleType == 'student'
					|| user.roleType == 'faculty') {
					var promise = CourseService
					.findCourseByPersonId(userId);
					promise
					.then(function(response) {
						courses = response.data;
						//console.log(courses);
						if (courses != undefined) {
							viewModel.courses = courses;

							var courseIds = [];
							for (var i = 0; i < courses.length; i++) {
								courseIds
								.push(courses[i].courseId);
							}

							viewModel.enrolledCourseIds = courseIds;

							var promise = CourseService
							.findAllCourses();
							promise
							.then(function(response) {
								allCourses = response.data;

								if (allCourses) {
									var remCourses = []
									for (var i = 0; i < allCourses.length; i++) {
										if (!courseIds
												.includes(allCourses[i].courseId)) {
											remCourses
											.push(allCourses[i]);
										}
									}

									viewModel.remCourses = remCourses;
								} else {
									viewModel.errorMessage = "Cannot fetch all courses";
								}
							});
						} else {
							viewModel.errorMessage = "Error loading courses";
						}
					});
				} else { // admin
					var promise = CourseService.findAllCourses();
					promise.then(function(response) {
						courses = response.data;
						//console.log(courses);
						if (courses != undefined) {
							viewModel.courses = courses;

							var courseIds = [];
							for (var i = 0; i < courses.length; i++) {
								courseIds
								.push(courses[i].courseId);
							}

							viewModel.enrolledCourseIds = courseIds;
						}
					});
				
				}
			});	
		}

		init();

		viewModel.join = join;
		function join(courseId) {
			var promise = CourseService.assignCourse(courseId,
					viewModel.params.personId, viewModel.params.roleType);
			promise.then(function successCallback() {
				init();
			}, function errorCallback(response) {
				viewModel.errorMessage = "Course could not be joined";
			});
		}

		viewModel.drop = drop;
		function drop(courseId) {
			var promise = CourseService.dropCourse(courseId,
					viewModel.params.personId, viewModel.params.roleType);
			promise.then(function successCallback() {
				init();
			}, function errorCallback(response) {
				viewModel.errorMessage = "Course could not be dropped";
			});
		}
		
		viewModel.deleteCourse = deleteCourse;
		function deleteCourse(course) {
			var promise = CourseService.deleteCourse(course);

			promise.then(function successCallback(response) {
				init();
			}, function errorCallback(response) {
				viewModel.errorMessage = "Course cannot be deleted";
			});
		}


	}
})();