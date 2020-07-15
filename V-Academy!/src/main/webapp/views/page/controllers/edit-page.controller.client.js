(function() {
	angular.module("WebAppMaker").controller("EditPageController",
			EditPageController);

	function EditPageController(UserService, CourseService, PageService,
			$location, $routeParams) {
		var viewModel = this;

		function init() {
			//console.log('Before loading add new course');
			var userId = $routeParams['uid'];
			var roleType = $routeParams['roleType'];
			var courseId = $routeParams['courseId'];
			var pageId = $routeParams['pageId'];

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

				var promisePage = PageService.findPageById(courseId,
						pageId);

				promisePage
				.then(function(page) {
					page = page.data;
					if (page != undefined) {
						viewModel.page = page;
					} else {
						viewModel.errorMessage = "Error while loading page by ID:"
							+ pageId;
					}
				});
			});

		}

		init();

		viewModel.update = update;
		function update(page) {
			if (page == undefined) {
				viewModel.errorMessage = "Must set at least one parameter";
				return;
			}
			page.pageId = viewModel.page.pageId;
			page.createDate = viewModel.page.createDate;
			page.updateDate = new Date();

			if (page.name == null) {
				page.name = viewModel.page.name;
			}
			if (page.tooltipDescription == null) {
				page.tooltipDescription = viewModel.page.tooltipDescription;
			}

			var promise = PageService.updateByPageId(page.pageId, page.name,
					page.tooltipDescription);
			promise.then(function successCallback(response) {
				page = response.data;
				if (page) {
					$location.url(viewModel.user.roleType + "/"
							+ viewModel.user.personId + "/course/"
							+ viewModel.course.courseId + "/page");
				} else {
					init();
					viewModel.errorMessage = "Page not updated";
					viewModel.successMessage = null;
				}
			}, function errorCallback(response) {
				viewModel.errorMessage = "Page not updated";
			});

		}

		viewModel.deletePage = deletePage;
		function deletePage() {
			var promise = PageService.deleteByPageId(viewModel.page.pageId);

			promise.then(function successCallback() {
				$location.url(viewModel.user.roleType + "/"
						+ viewModel.user.personId + "/course/"
						+ viewModel.course.courseId + "/page");
			}, function errorCallback(response) {
				viewModel.errorMessage = "Page cannot be deleted";
			});
		}

	}
})();