(function() {
	angular.module("WebAppMaker").controller("HomeController", HomeController);

	function HomeController(UserService, $location) {
		var viewModel = this;
		viewModel.errorMessage = null;

		// event handlers
		viewModel.login = login;
		viewModel.signup = signup;

		function signup(user) {
			var isAdmin = $("#admin").is(':checked');
			var isStudent = $("#student").is(':checked');
			var isFaculty = $("#faculty").is(':checked');

			if (user == undefined) {
				viewModel.errorMessage = "Please enter the following details";
			} else if (user.username == null) {
				viewModel.errorMessage = "Username cannot be null";
			} else if (user.email == null) {
				viewModel.errorMessage = "Email cannot be empty ";
			} else if (user.password == null) {
				viewModel.errorMessage = "Password cannot be empty ";
			} else if (isAdmin == null && isStudent == null
					&& isFaculty == null) {
				viewModel.errorMessage = "Please select a type of user";
			} else {
				var userType = 'student';
				if (isAdmin) {
					userType = 'administrator';
				} else if (isFaculty) {
					userType = 'faculty'
				}
				var promise = UserService.createUser(user, userType);
				promise.then(function successCallback(response) {
					user = response.data;
					if (user) {
						$location.url("/user/" + user.roleType + '/'
								+ user.personId);
					} else {
						viewModel.errorMessage = "User not created";
					}
				}, function errorCallback(response) {
					viewModel.errorMessage = "User not created";
				});
			}
		}

		function login(user) {
			if (user == undefined) {
				viewModel.errorMessage = "Please enter User's login details";
			} else if (user.username == null || user.username.trim() == "") {
				viewModel.errorMessage = "Username cannot be empty ";
			} else if (user.password == null || user.password.trim() == "") {
				viewModel.errorMessage = "Password cannot be empty ";
			} else {
				var promise = UserService.findUserByCredentials(user.username,
						user.password, user.roleType);
				promise
				.then(
						function successCallback(response) {

							user = response.data;
							console.log(user);
							if (user != "") {
								$location.url("/user/" + user.roleType
										+ "/" + user.personId);
							} else {
								document
								.getElementById('login-username').style.borderColor = "red";
								document
								.getElementById('login-password').style.borderColor = "red";
								viewModel.errorMessage = "Invalid Username/Password and(or) Role selection"
							}
						},
						function errorCallback(response) {
							viewModel.errorMessage = "User/Role not found";
						});
			}
		}
	}
})();