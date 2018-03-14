'use strict';

angular.module('myApp').factory('EmployeeService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:9090/JavaBookShop-1.0-SNAPSHOT/welcome/rest/cart';

    var factory = {
        fetchAllCartItems: fetchAllCartItems,
        createCartItem: createCartItem,
        updateCartItem: updateCartItem,
        deleteCartItem: deleteCartItem
    };

    return factory;

    function fetchAllCartItems() {
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

    function createCartItem(cartItem) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, cartItem)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Employee');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateCartItem(cartItem, isbn) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+isbn, cartItem)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Employee');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteCartItem(isbn) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+isbn)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Employee');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
