(function(){
	angular
    .module("WebAppMaker")
    .factory("WidgetService", widgetService);
	
	function widgetService($http) {
        var api = {
        	"createWidget": createWidget,
            "findByWidgetId": findByWidgetId,
            "deleteByWidgetId": deleteByWidgetId,
            "updateWidget": updateWidget,
            "getAllWidgetsByPage": getAllWidgetsByPage
        };
        
        
        return api;
        
        function createWidget(pageId, widgetType, widget) {
            return $http({
                method: 'POST',
                url: 'api/page/' + pageId + '/' + widgetType,
                data: JSON.stringify(widget)
            });
        }
        
        function getAllWidgetsByPage(pageId) {
        	console.log('Fetching widgets by ' + pageId);
        	return $http({
                method: 'GET',
                url: 'api/page/' + pageId + '/widget',
                params: {
                	
                }
            });
        }
        
        function findByWidgetId(widgetId) {
        	return $http({
                method: 'GET',
                url: 'api/widget/' + widgetId,
                params: {
                	
                }
            });
        }
        
        function deleteByWidgetId(widgetId) {
        	return $http({
                method: 'DELETE',
                url: 'api/widget/' + widgetId,
                params: {
                	
                }
            });
        }
        
        function updateWidget(widget) {
        	return $http({
                method: 'PUT',
                url: 'api/' + widget.widgetType,
                data: JSON.stringify(widget)
            });
        }
        
        
	}
})();