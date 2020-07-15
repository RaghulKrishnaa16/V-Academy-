(function() {
	angular.module("WebAppMaker").controller("AddUserController",
			AddUserController);

	function AddUserController(UserService, $location, $routeParams) {
		var viewModel = this;

		function init() {
			var userId = $routeParams['uid'];
			var roleType = $routeParams['roleType'];

			var manageRoleType = $routeParams['manageRoleType'];
			
			var addRoleType = $routeParams['addRoleType'];

			viewModel.params = {
					personId : userId,
					roleType : roleType,
					manageRoleType : manageRoleType,
					addRoleType : addRoleType
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

		viewModel.signup = signup;
		function signup(user) {
			if (user == undefined) {
				viewModel.errorMessage = "Please enter  following details";
			} else if (user.username == null) {
				viewModel.errorMessage = "Username cannot be null";
			} else if (user.email == null) {
				viewModel.errorMessage = "Email cannot be empty ";
			} else if (user.password == null) {
				viewModel.errorMessage = "Password cannot be empty ";
			} else {
				var userType = viewModel.params.addRoleType;
				
				var promise = UserService.createUser(user, userType);
				promise.then(function successCallback(response) {
					user = response.data;
					if (user) {
						$location.url("/" + viewModel.params.roleType + '/' + viewModel.params.personId + '/manage/' + viewModel.params.manageRoleType);
					} else {
						viewModel.errorMessage = "User not created";
					}
				}, function errorCallback(response) {
					viewModel.errorMessage = "User not created";
				});
			}
		}

	}
})();