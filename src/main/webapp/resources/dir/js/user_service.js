'use strict';

angular.module('myApp').factory('BookService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/welcome/rest/createBook';

    var factory = {
        fetchAllBooks: fetchAllBooks,
        createBook: createBook,
        updateBook: updateBook,
        deleteBook: deleteBook
    };

    return factory;

    function fetchAllBooks() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createBook(book) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, book)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateBook(book, isbn) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+isbn, book)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteBook(isbn) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+isbn)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
