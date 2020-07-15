(function () {
    angular.module("WebAppMaker").controller("EditWidgetController",
    		EditWidgetController);

    function EditWidgetController(UserService, PageService, WidgetService, $location, $routeParams) {
        var viewModel = this;

        function init() {
        	var userId = $routeParams['uid'];
            var roleType = $routeParams['roleType'];
            var courseId = $routeParams['courseId'];
            var pageId = $routeParams['pageId'];
            var widgetId = $routeParams['widgetId'];
            
            viewModel.params = { personId : userId, roleType : roleType, courseId : courseId, pageId: pageId}; 
            
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
            
            
            var promise2 = WidgetService.findByWidgetId(widgetId);
            promise2.then(
                function (response) {
                    var widget = response.data;
                    if (widget != undefined) {
                        viewModel.widget = widget;
                    } else {
                        viewModel.errorMessage = "Error while loading widget by ID:" + widgetId;
                    }
                }
            );
        }

        init();

        viewModel.update = update;
        function update(widget) {
        	if (!widget || (widget.name == null && widget.url == null && widget.markupText == null)) {
        		viewModel.errorMessage = "Please fill name of widget";
        		return;
        	}
        	
        	widget.widgetId = viewModel.widget.widgetId;
        	widget.widgetType = viewModel.widget.widgetType;
        	
        	if (viewModel.widget.widgetType != 'htmlwidget' && widget.url == null) {
                widget.url = viewModel.widget.url;
            } else if (widget.name == null) {
                widget.name = viewModel.widget.name;
            } else if (viewModel.widget.widgetType == 'htmlwidget' && widget.markupText == null) {
            	widget.markupText = viewModel.widget.markupText;
            }
        	
            var promise = WidgetService.updateWidget(widget);
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
})();