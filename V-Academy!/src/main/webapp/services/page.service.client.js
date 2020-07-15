(function(){
	angular
    .module("WebAppMaker")
    .factory("PageService", pageService);
	
	function pageService($http) {
        var api = {
        	"createPage": createPage,
            "findPageById": findPageById,
            "deleteByPageId": deleteByPageId,
            "updateByPageId": updateByPageId,
            "getAllPagesByCourse": getAllPagesByCourse
        };
        
        
        return api;
        
        function createPage(courseId, page) {
            return $http({
                method: 'POST',
                url: 'api/course/' + courseId + '/page',
                data: JSON.stringify(page),
                params: {
                	
                }
            });
        }
        
        function getAllPagesByCourse(courseId) {
        	return $http({
                method: 'GET',
                url: 'api/course/' + courseId + '/page',
                params: {
                	
                }
            });
        }
        
        function findPageById(courseId, pageId) {
        	return $http({
                method: 'GET',
                url: 'api/course/' + courseId + '/page/' + pageId,
                params: {
                	
                }
            });
        }
        
        function deleteByPageId(pageId) {
        	return $http({
                method: 'DELETE',
                url: 'api/page/' + pageId,
                params: {
                	
                }
            });
        }
        
        function updateByPageId(pageId, name, tooltipDescription) {
        	return $http({
                method: 'PUT',
                url: 'api/page/' + pageId,
                params: {
                	'name' : name,
                	'tooltipDescription' : tooltipDescription,
                }
            });
        }
        
        
	}
})();