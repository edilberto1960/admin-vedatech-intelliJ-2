'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'BankService', function($scope, BankService) {
    var self = this;
    self.user={id:null,nameBank:'',accountNumber:null,createAt:'',email:'',balance:null,phone:''};
    self.users=[];
    self.account ={

        id:null,
        typeAccount:'',
        name:'',
        numberAccount:'',
        department:'',

    };

    console.log("VALORE DE uSER", self.user);
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    $scope.typeaccount = [

        {id:1, name: 'Income'},
        {id:2, name: 'Egress'},
        {id:3, name: 'Non deductible'}

    ];

    $scope.department = [

        {id:1, name: 'Administration'},
        {id:2, name: 'Operation'},
        {id:3, name: 'Sales'}

    ];

    fetchAllUsers();

    function fetchAllUsers(){
        BankService.fetchAllUsers()
            .then(
                function(d) {
                    self.users = d;
                },
                function(errResponse){

                    console.error('Error while fetching Users');
                }
            );
    }

    function createUser(user){
        BankService.createUser(user)
            .then(
                fetchAllUsers,
                function(errResponse){
                    console.log("RESPONSE ERROR DATA ", errResponse.config.data.accountNumber);
                    console.error('Error while creating User');
                    self.user = errResponse.config.data;
                }
            );
    }

    function updateUser(user, id){
        BankService.updateUser(user, id)
            .then(
                fetchAllUsers,
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
    }

    function deleteUser(id){
        BankService.deleteUser(id)
            .then(
                fetchAllUsers,
                function(errResponse){
                    console.error('Error while deleting User');
                }
            );
    }

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{


            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
            console.log('User updated ', self.user);
        }
        reset();
    }

    function submitAccount() {
        if(self.account.id===null){
            console.log('Saving New User', self.account);
            createUser(self.account);
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
        self.user={id:null,nameBank:'',accountNumber:'',createAt:'',email:'',initialBalance:'',phone:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);