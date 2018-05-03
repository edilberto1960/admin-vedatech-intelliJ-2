'use strict';

angular.module('myApp').controller('TransactionsController', ['$scope', 'TransactionsService', function($scope, TransactionsService) {
    var self = this;
    self.transaction={
        id:null,
        date:'',
        operationDate:'',
        reference:'',
        description:'',
        expandedTo:'',
        status:'',
        depositAmount:0.0,
        checkAmount:0.0,
        accounts:{
            id:null,
            name:'',
            department:''
        },
        bankAccount:{
            id:null,
            nameBank:'',
            accountNumber:'',
            createAt:'',
            email:'',
            phone:'',
            balance:null
        }
    };

    self.transactions=[];


    $scope.allstatus = [

        {id:1, name: 'transit'},
        {id:2, name: 'cancel'},
        {id:3, name: 'completed'}

    ];

    $scope.accounts =[];
    $scope.typeaccounts =[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllBankAccount();
    fetchAllAccounts();

    function fetchAllBankAccount(){
        TransactionsService.fetchAllBankAccount()
            .then(
                function(d) {
                    $scope.accounts = d;
                    console.log("Valores de Accounts ", $scope.accounts);
                },
                function(errResponse){

                    console.error('Error while fetching Users');
                }
            );
    }

    function fetchAllAccounts(){
        TransactionsService.fetchAllAccounts()
            .then(
                function(d) {
                    $scope.typeaccounts = d;
                    console.log("Valores de Accounts ", $scope.typeaccounts);
                },
                function(errResponse){

                    console.error('Error while fetching Users');
                }
            );
    }



    function createUser(user){
        TransactionsService.createUser(user)
            .then(
                function(d) {

                },
                function(errResponse){
                    console.log("RESPONSE ERROR DATA ", errResponse.config.data.accountNumber);
                    console.error('Error while creating User');
                    self.user = errResponse.config.data;
                }
            );
    }

    function updateUser(user, id){
        TransactionsService.updateUser(user, id)
            .then(
                function(d) {

                },
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
    }

    function deleteUser(id){
        TransactionsService.deleteUser(id)
            .then(
                function(d) {

                },
                function(errResponse){
                    console.error('Error while deleting User');
                }
            );
    }

    function submit() {
        if(self.transaction.id===null){
            console.log('Saving Transactions ', self.transaction);

           createUser(self.transaction);
        }else{

            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
            console.log('User updated ', self.user);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
               console.log("DATE ", self.users[i].createAt);
               var msg =new Date(self.users[i].createAt);
              console.log("Valor de msg ",msg);
                self.user = angular.copy(self.users[i]);
                self.user.createAt = msg;
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.transaction={
            id:null,
            date:'',
            operationDate:'',
            reference:'',
            description:'',
            expandedTo:'',
            status:'',
            depositAmount:0.0,
            checkAmount:0.0,
            accounts:{
                id:null,
                name:'',
                department:''
            },
            bankAccount:{
                id:null,
                nameBank:'',
                accountNumber:'',
                createAt:'',
                email:'',
                phone:'',
                balance:null
            }
        };

        $scope.myForm.$setPristine(); //reset Form
    }

}]);