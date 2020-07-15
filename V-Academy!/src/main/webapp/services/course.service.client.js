(function () {
    angular
        .module("WebAppMaker")
        .factory("CourseService", courseService);

    function courseService($http) {
        var api = {
            "findCourseById": findCourseById,
            "findCourseByPersonId": findCourseByPersonId,
            "findAllCourses" : findAllCourses,
            "updateCourse": updateCourse,
            "createCourse": createCourse,
            "joinCourseIdByStudentId": joinCourseIdByStudentId,
            "joinCourseIdByAssistantId": joinCourseIdByAssistantId,
            "dropCourseIdByStudentId": dropCourseIdByStudentId,
            "dropCourseIdByAssistantId":dropCourseIdByAssistantId,
            "findAllCoursesByStudentId": findAllCoursesByStudentId,
            "findAllCoursesByAssistantId": findAllCoursesByAssistantId,
            "deleteCourse": deleteCourse,
            "assignCourse": assignCourse,
            "dropCourse":   dropCourse
        };
        return api;


        function createCourse(course, personId, roleType) {
            return $http({
                method: 'POST',
                url: '/api/course',
                data: JSON.stringify(course),
                params: {
                	'roleType' : roleType,
                	'personId' : personId
                }
            });
        }
        
        function assignCourse(courseId, personId, roleType) {
        	return $http({
                method: 'PUT',
                url: '/api/course/assign',
                params: {
                	'courseId': courseId,
                	'roleType' : roleType,
                	'personId' : personId
                }
            });
        }
        
        function dropCourse(courseId, personId, roleType) {
        	return $http({
                method: 'DELETE',
                url: '/api/course/drop',
                params: {
                	'courseId': courseId,
                	'roleType' : roleType,
                	'personId' : personId
                }
            });
        }

        function updateCourse(course) {
            return $http({
                method: 'PUT',
                url: '/api/course',
                data: JSON.stringify(course)
            });
        }

        function deleteCourse(course) {
        	console.log(course);
            return $http({
            	method: 'POST',
            	url:'/api/course/delete', 
                data: JSON.stringify(course)
            });
        }

        function findAllCourses() {
            return $http({
                url: '/api/course',
                method: 'GET',
                params: {}
            });
        }
        
        function findCourseById(cid) {
            return $http({
                url: '/api/course/' + cid,
                method: 'GET',
                params: {}
            });
        }

        function findCourseByPersonId(personId) {
            return $http({
                url: '/api/' + personId + '/course',
                method: 'GET'
            });
        }

        function joinCourseIdByStudentId(cid, sid) {
            var courseRegistration = new Object();
            courseRegistration.courseId = cid;
            courseRegistration.studentId = sid;
            return $http({
                method: 'POST',
                url: '/api/create/courseregistration',
                data: JSON.stringify(courseRegistration),
            });
        }

        function joinCourseIdByAssistantId(cid, aid) {

            return $http({
                url: '/api/add/course/assistant',
                method: 'POST',
                params: {cid: cid, aid: aid}
            });
        }

        function dropCourseIdByStudentId(cid, sid) {
            return $http({
                method: 'DELETE',
                url: '/api/delete/courseregistration/by/ids',
                params: {cid: cid, sid: sid}
            });
        }

        function dropCourseIdByAssistantId(cid, aid) {
            return $http({
                method: 'DELETE',
                url: '/api/delete/course/assistant',
                params: {cid: cid, aid: aid}
            });
        }


        function findAllCoursesByStudentId(studentId) {
            return $http({
                url: '/api/find/courseregistration/',
                method: 'GET',
                params: {sid: studentId}
            });
        }

        function findAllCoursesByAssistantId(assistantId) {
            return $http({
                url: '/api/find/course/assistant',
                method: 'GET',
                params: {aid: assistantId}
            });
        }
    }
})();