'use strict';

angular.module('myApp').factory('BankService', ['$http', '$q', function($http, $q){


    var factory = {
        fetchAllUsers: fetchAllUsers,
        createUser: createUser,
        updateUser:updateUser,
        deleteUser:deleteUser
    };

    return factory;

    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'bankAccount/getAllBankAccounts/')
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

    function createUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+'bankAccount/addBankAccount/', user)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                    console.log("Valores de Response Data ", response);
                    if(response.status = 201){
                        console.log("SUCESS");
                        alert("The Account is Created!");
                    }else {
                        console.log("PROBLEMS");
                    }
                },
                function(errResponse){
                    console.error('Error while creating User');
                    deferred.reject(errResponse);
                    console.log("ERROR RESPONSE ", errResponse);

                    alert("The Account already exist!, please check your number Account");
                }
            );
        return deferred.promise;
    }


    function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+'bankAccount/'+id, user)
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

    function deleteUser(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+'bankAccount'+id)
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