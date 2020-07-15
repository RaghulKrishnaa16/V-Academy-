(function () {
    angular.module("WebAppMaker").controller("AddWidgetController",
    		AddWidgetController);

    function AddWidgetController(UserService, PageService, WidgetService, $location, $routeParams) {
        var viewModel = this;

        function init() {
        	var userId = $routeParams['uid'];
            var roleType = $routeParams['roleType'];
            var courseId = $routeParams['courseId'];
            var pageId = $routeParams['pageId'];
            var widgetType = $routeParams['widgetType'];
            
            viewModel.params = { personId : userId, roleType : roleType, courseId : courseId, pageId: pageId, widgetType : widgetType}; 
            
            var promise = UserService.findUserById(userId, roleType);
            promise.then(
                function (response) {
                    var user = response.data;
                    if (user != undefined) {
                        viewModel.user = user;
                    } else {
                        viewModel.errorMessage = "Error while loading user by ID:" + userId;
                    }
                }
            );
        }

        init();

        viewModel.add = add;
        function add(widget) {
        	if (!widget) {
        		viewModel.errorMessage = "Please fill name of widget";
        	} else if (viewModel.params.widgetType != 'htmlwidget' && widget.url == null) {
                viewModel.errorMessage = "Url cannot be empty ";
            } else if (widget.name == null) {
                viewModel.errorMessage = "Widget name cannot be empty ";
            } else {
                var promise = WidgetService.createWidget(viewModel.params.pageId, viewModel.params.widgetType, widget);
                promise.then(function successCallback(response) {
                    widget = response.data;
                    if (widget) {
                        $location.url(viewModel.params.roleType + "/" + viewModel.params.personId + "/course/" + viewModel.params.courseId + "/page/" + viewModel.params.pageId + "/widget");
                    } else {
                        init();
                        viewModel.errorMessage = "Widget not created";
                        viewModel.successMessage = null;
                    }
                }, function errorCallback(response) {
                    viewModel.errorMessage = "Widget not created";
                });
            }
        }
        
        viewModel.jump = jump;
        function jump(url) {
        	$location.url(url);
        }

    }
})();