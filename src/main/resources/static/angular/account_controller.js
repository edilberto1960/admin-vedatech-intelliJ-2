'use strict';

angular.module('myApp').controller('AccountController', ['$scope', 'AccountService', function($scope, AccountService) {
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
    self.accounts=[];

    console.log("VALORE DE uSER", self.user);
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    $scope.typeaccount = [

        {id:1, name: 'Income'},
        {id:2, name: 'Egress'},
        {id:3, name: 'Non deductible'},
        {id:4, name: 'Costs'}

    ];

    $scope.department = [

        {id:1, name: 'Administration'},
        {id:2, name: 'Operation'},
        {id:3, name: 'Sales'},
        {id:4, name: 'U-Receivable'},
        {id:5, name: 'U-Payments to Supplier'}

    ];


    fetchAllUsers();

    function fetchAllUsers(){
        AccountService.fetchAllUsers()
            .then(
                function(d) {
                    self.transactions = d;
                },
                function(errResponse){

                    console.error('Error while fetching Users');
                }
            );
    }

    function createUser(user){
        AccountService.createUser(user)
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
        AccountService.updateUser(user, id)
            .then(
                fetchAllUsers,
                alert("The Account is Created!"),
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
        reset();
    }

    function deleteUser(id){
        AccountService.deleteUser(id)
            .then(
                fetchAllUsers,
                function(errResponse){
                    console.error('Error while deleting User');
                }
            );
    }

    function submit() {
        if(self.account.id===null){
            console.log('Saving New User', self.account);
            createUser(self.account);

        }else{


            updateUser(self.account, self.account.id);
            console.log('User updated with id ', self.account.id);
            console.log('User updated ', self.account);
        }
        reset();

    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.transactions.length; i++){
            if(self.transactions[i].id === id) {
                self.account = angular.copy(self.transactions[i]);
                console.log("VALORES DESPUES DE COPIAR ",self.account);

                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.account.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        var r = confirm("Confirm to Delete!");
        if (r == true) {
            deleteUser(id);
        } else {
          reset();
        };

    }


    function reset(){
        self.account ={

            id:null,
            typeAccount:'',
            name:'',
            numberAccount:'',
            department:'',

        };
        $scope.myForm.$setPristine(); //reset Form
    }

}]);