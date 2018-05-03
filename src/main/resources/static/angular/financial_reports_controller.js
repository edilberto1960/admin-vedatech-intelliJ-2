'use strict';

angular.module('myApp').controller('FinancialReportsController', ['$scope', 'FinancialReportsService', function($scope,FinancialReportsService) {
    var self = this;

    self.data={
        department:'',
            name:'',
           total:null,
       };

    self.report =[];
    self.depositReport =[];


    $scope.user2={
        bank:{
            id:null,
            nameBank:'',
            accountNumber:'',
            createAt:'',
            email:'',
            phone:'',
            balance:null
        },
        initialDate:'',
        finalDate:''
    };

    self.user={
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

    $scope.userS={
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


    $scope.accounts =[];
    self.transactions=[];
    $scope.transactions =[];
    $scope.bank = [];
    $scope.typeaccounts =[];
    $scope.allstatus = [

        {id:1, name: 'transit'},
        {id:2, name: 'cancel'},
        {id:3, name: 'completed'}

    ];

    $scope.status;




    self.submit = submit;
    self.submitUser = submitUser;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllBankAccount();
    fetchAllAccounts();

    function fetchAllBankAccount(){
        FinancialReportsService.fetchAllBankAccount()
            .then(
                function(d) {
                    $scope.accounts = d;
                },
                function(errResponse){

                    console.error('Error while fetching Users');
                }
            );
    }

    function fetchAllAccounts(){
        FinancialReportsService.fetchAllAccounts()
            .then(
                function(d) {
                    $scope.typeaccounts = d;
              },
                function(errResponse){

                    console.error('Error while fetching Users');
                }
            );
    }


    function createUser(user){
        FinancialReportsService.createUser(user)
            .then(

                function(d){
                    self.transactions = d;
                    $scope.bank = self.transactions[0].bankAccount;
                 },
                function(errResponse){
                    console.log("RESPONSE ERROR DATA ", errResponse.config.data.accountNumber);
                    console.error('Error while creating User');
                }

            );
    }


    function getTransactionsByBankAccount(user){

        FinancialReportsService.getTransactionsByBankAccount(user)
            .then(
                function(d){
                  self.transactions = d;
                    self.report =[];
                    var array =[];
              //    console.log("Valores recibidos ", self.transactions[0][0]);
                    console.log("VALOR PARA REPROT ANTESW ", array);
                  for(var i =0; i< d.length ; i++){
                      for(var j =0; j< d[i].length; j++){

                          var data = new Object();

                      console.log("Valores desmenusados ", d[i][j]);
                      data.department = d[i][j];
                      j=j+1;
                      data.name = d[i][j];
                      j=j+1;
                      data.total = d[i][j];
                      j=j+1;
                      data.total2 = d[i][j];
                          console.log("VALOR PARA DATA ", data);
                          array.push(data);
                          self.report.push(data);
                          j=j+1;
                      }


                      console.log("Valor de j ",j);
                      console.log("REPORT ", array);
                  }
                },
                function(errResponse){
                    console.log("RESPONSE ERROR DATA ", errResponse.config.data.accountNumber);
                    console.error('Error while creating User');
                    self.user = errResponse.config.data;
                }
            );
    }


    function getAllDeposit(user){

        FinancialReportsService.getAllDeposit(user)
            .then(
                function(d){
                    self.transactions = d;
                    self.report2 =[];
                    var array2 =[];
                  //  console.log("Valores recibidos ", self.transactions[0][0]);
                    console.log("VALOR PARA REPROT ANTESW ", array2);
                    for(var i =0; i< d.length ; i++){
                        for(var j =0; j< d[i].length; j++){

                            var data2 = new Object();

                            console.log("Valores desmenusados ", d[i][j]);
                            data2.department = d[i][j];
                            j=j+1;
                            data2.name = d[i][j];
                            j=j+1;
                            data2.total = d[i][j];
                            console.log("VALOR PARA DATA ", data2);
                            array2.push(data2);
                            self.report2.push(data2)

                            j=j+1;
                        }


                        console.log("Valor de j ",j);
                        console.log("REPORT2 ", array2);
                    }
                },
                function(errResponse){
                    console.log("RESPONSE ERROR DATA ", errResponse.config.data.accountNumber);
                    console.error('Error while creating User');
                    self.user = errResponse.config.data;
                }
            );
    }



    function updateUser(user,user2, id){
        console.log("ENTRAMOS A UPDATEUSER");
        FinancialReportsService.updateUser(user,user2,id)
            .then(
               submit,
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
    }

    function deleteUser(id){
        FinancialReportsService.deleteUser(id)
            .then(
                submit,
                function(errResponse){
                    console.error('Error while deleting User');
                }
            );
    }

    function submit() {
        $scope.user2 = self.user2;
         getTransactionsByBankAccount(self.user2);
         getAllDeposit(self.user2);
         reset();
    }

    function submitUser() {
        if(self.user.id===null){
             createUser($scope.user);
        }else{
            console.log("VAMOS A DAR UPDATE")
            updateUser(self.user, $scope.user2, self.user.id);
            $('#myModalDeposit').modal('hide');
            $('#myModalCheck').modal('hide');
        }

        reset();

    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.transactions.length; i++){
            if(self.transactions[i].id === id) {
               var msg =new Date(self.transactions[i].date);
               var msg2 = new Date(self.transactions[i].operationDate);
                self.user = angular.copy(self.transactions[i]);
                self.user.date = msg;
                self.user.operationDate = msg2;
                $scope.status = self.user.status;
                $scope.userS = self.user;
                console.log("VALOR DE ACCOUNTS EN EDIT", self.user.accounts);
                if(self.user.checkAmount >0){
                    $('#myModalCheck').modal('show');
                }else {
                    $('#myModalDeposit').modal('show');
                }


                break;
            }

        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
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
        self.data={
            department:'',
            name:'',
            total:null,
        };

        $scope.myForm.$setPristine(); //reset Form
    }

}]);