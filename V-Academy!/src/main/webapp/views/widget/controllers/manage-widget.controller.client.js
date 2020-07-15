(function () {
    angular
        .module("WebAppMaker")
        .controller("ManageWidgetController", ManageWidgetController);


    function ManageWidgetController(UserService, PageService, WidgetService, $location, $routeParams) {
        var viewModel = this;

        function init() {
            var userId = $routeParams['uid'];
            var roleType = $routeParams['roleType'];
            var courseId = $routeParams['courseId'];
            var pageId = $routeParams['pageId']; 
            viewModel.params = { personId : userId, roleType : roleType, courseId: courseId, pageId: pageId};
            var promise = UserService.findUserById(userId, roleType);
            promise.then(
                function (user) {
                    user = user.data;
                    console.log(user);
                    if (user != undefined) {
                        viewModel.user = user;
                    } else {
                        viewModel.errorMessage = "Error while loading user by ID:" + userId;
                    }
                });
            
            var promise2 = PageService.findPageById(courseId, pageId);
            
            promise2.then(
                function (page) {
                	page = page.data;
                	console.log(page);
                	if (page != undefined) {
                		viewModel.page = page;
                	} else {
                		viewModel.errorMessage = "Error while loading widgets:" + pageId;
                	}
                }
            );
            
            var promise3 = WidgetService.getAllWidgetsByPage(pageId);
            
            promise3.then(
                function (widgets) {
                	widgets = widgets.data;
                	if (widgets != undefined) {
                		viewModel.widgets = widgets;
                	} else {
                		viewModel.errorMessage = "Error while loading widgets for page:" + pageId;
                	}
                }
            );
            
        }
        
        init();
        
        viewModel.deleteWidget = deleteWidget;
        
        function deleteWidget(widgetId) {
        	var promise = WidgetService.deleteByWidgetId(widgetId);
        	
        	promise.then(function successCallback() {
        		viewModel.errorMessage = null;
        		init();
        	}, function errorCallback(response) {
                viewModel.errorMessage = "Widget cannot be deleted";
            });
        }
        
        viewModel.toYoutube = toYoutube;
        
        function toYoutube(url) {
        	var equals = url.indexOf('=');
        	return "https://www.youtube.com/embed/" + url.substring(equals + 1);
        }
    }
})();