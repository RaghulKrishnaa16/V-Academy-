(function() {
	angular.module("WebAppMaker").controller("ManageUserController",
			ManageUserController);

	function ManageUserController(UserService, $location, $routeParams) {
		var viewModel = this;

		function init() {
			var userId = $routeParams['uid'];
			var roleType = $routeParams['roleType'];
			var manageRoleType = $routeParams['manageRoleType'];
			viewModel.params = {
					personId : userId,
					roleType : roleType,
					manageRoleType : manageRoleType
			};

			var promise = UserService.findAllUsersByRole(manageRoleType);

			promise
			.then(function(users) {
				users = users.data;
				if (users != undefined) {
					viewModel.allUsers = users;
				} else {
					viewModel.errorMessage = "Error while loading user by role:"
						+ roleType;
				}
			});

			var promise2 = UserService.findUserById(userId, roleType);

			promise2.then(function(user) {
				user = user.data;
				if (user != undefined) {
					viewModel.user = user;
				} else {
					viewModel.errorMessage = "Error while loading user by id:"
						+ userId;
				}
			});
		}

		init();

		viewModel.deleteUser = deleteUser;

		function deleteUser(u) {
			var promise = UserService.deleteUser(u);
			promise.then(function() {
				init();
			});
		}

	}
})();