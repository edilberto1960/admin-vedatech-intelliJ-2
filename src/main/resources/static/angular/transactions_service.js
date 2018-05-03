'use strict';

angular.module('myApp').factory('TransactionsService', ['$http', '$q', function($http, $q){

    /*When deploy in a MochaHost Server*/
   // var REST_SERVICE_URI = 'http://admin-vedatech.com/transactions/';
    //var REST_SERVICE_URI_ACCOUNTS = 'http://admin-vedatech.com/account/';
    //var REST_SERVICE_URI_BANK_ACCOUNTS = 'http://admin-vedatech.com/bankAccount/';
    //var REST_SERVICE_URI_TYPE_TRANSACTION = 'http://admin-vedatech.com/transactions/';

    /* When deploy in a local host */
   // var REST_SERVICE_URI = 'http://localhost:8080/transactions/';
   //  var REST_SERVICE_URI_ACCOUNTS = 'http://localhost:8080/account/';
   //  var REST_SERVICE_URI_BANK_ACCOUNTS = 'http://localhost:8080/bankAccount/';
   //  var REST_SERVICE_URI_TYPE_TRANSACTION = 'http://localhost:8080/transactions/';

    var factory = {
        fetchAllBankAccount: fetchAllBankAccount,
        fetchAllAccounts:fetchAllAccounts,
        createUser: createUser,
        updateUser:updateUser,
        deleteUser:deleteUser
    };

    return factory;



    function fetchAllBankAccount() {
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

    function fetchAllAccounts() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'transactions/getAllAccounts/')
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
        $http.post(REST_SERVICE_URI+'transactions/addTransaction/', user)
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
        $http.put(REST_SERVICE_URI+'transactions/'+id, user)
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
        $http.delete(REST_SERVICE_URI+'transactions/'+id)
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