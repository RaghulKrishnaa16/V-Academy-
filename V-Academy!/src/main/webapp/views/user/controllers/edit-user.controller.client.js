(function() {
	angular.module("WebAppMaker").controller("EditUserController",
			EditUserController);

	function EditUserController(UserService, $location, $routeParams) {
		var viewModel = this;

		function init() {
			var userId = $routeParams['uid'];
			var roleType = $routeParams['roleType'];

			var managedUserId = $routeParams['manageduid'];
			var managedRoleType = $routeParams['managedRoleType'];

			viewModel.params = {
					personId : userId,
					roleType : roleType,
					managedRoleType : managedRoleType
			};

			var promise = UserService.findUserById(managedUserId,
					managedRoleType);

			promise.then(function(user) {
				user = user.data;
				if (user != undefined) {
					viewModel.user = user;
				} else {
					viewModel.errorMessage = "Error while loading user by ID:"
						+ userId;
				}
			});
			
			var promise2 = UserService.findUserById(userId,
					roleType);

			promise2.then(function(user) {
				user = user.data;
				if (user != undefined) {
					viewModel.params.user = user;
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
						$location.url("/" + viewModel.params.roleType + "/"
								+ viewModel.params.personId + "/manage/"
								+ viewModel.params.managedRoleType);
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