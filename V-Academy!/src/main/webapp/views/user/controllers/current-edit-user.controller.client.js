(function() {
	angular.module("WebAppMaker").controller("CurrentEditUserController",
			CurrentEditUserController);

	function CurrentEditUserController(UserService, $location, $routeParams) {
		var viewModel = this;

		function init() {
			var userId = $routeParams['uid'];
			var roleType = $routeParams['roleType'];

			viewModel.params = {
					personId : userId,
					roleType : roleType,
			};

			var promise = UserService.findUserById(userId,
					roleType);

			promise.then(function(user) {
				user = user.data;
				if (user != undefined) {
					viewModel.user = user;
				} else {
					viewModel.errorMessage = "Error while loading user by ID:"
						+ userId;
				}
			});
		}

		init();

		viewModel.update = update;
		function update(user) {
			user.personId = viewModel.user.personId;
			user.roleType = viewModel.user.roleType;
			user.username = viewModel.user.username;

			if (viewModel.user.roleType == 'student') {
				user.studentId = viewModel.user.studentId;
			} else if (viewModel.user.roleType == 'faculty') {
				user.facultyId = viewModel.user.facultyId;
			} else {
				user.adminId = viewModel.user.adminId;
			}

			user.updateDate = new Date();

			if (user.name == null) {
				user.name = viewModel.user.name;
			}

			if (user.email == null) {
				user.email = viewModel.user.email;
			}

			if (user.password == null) {
				user.password = viewModel.user.password;
			}

			if (!user) {
				viewModel.errorMessage = "Please fill at least one field";
			} else {
				var promise = UserService.updateUser(user);
				promise.then(function successCallback(response) {
					user = response.data;
					if (user) {
						$location.url("/user/" + viewModel.params.roleType + "/"
								+ viewModel.params.personId);
					} else {
						init();
						viewModel.errorMessage = "User not updated";
						viewModel.successMessage = null;
					}
				}, function errorCallback(response) {
					viewModel.errorMessage = "User not updated";
				});
			}
		}
	}
})();