'use strict';

angular.module('cart').controller('cartController', ['$scope', 'CartService', function($scope, CartService) {

    var self = this;
    self.cartItem={isbn: '', title:'', categoryName:'',quantity:0};
    self.cartItems=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllCartItems();
    function fetchAllCartItems(){
        CartService.fetchAllCartItems()
            .then(
            function(d) {
                self.cartItem = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function createCartItem(cartItem){
        CartService.createCartItem(cartItem)
            .then(
            fetchAllCartItems,
            function(errResponse){
                console.error('Error while creating Employee');
            }
        );
    }

    function updateCartItem(cartItem, isbn){
        CartService.updateCartItem(cartItem, isbn)
            .then(
            fetchAllCartItems(),
            function(errResponse){
                console.error('Error while updating Employee');
            }
        );
    }

    function deleteCartItem(isbn){
        CartService.deleteCartItem(isbn)
            .then(
            fetchAllCartItems(),
            function(errResponse){
                console.error('Error while deleting Employee');
            }
        );
    }

    function submit() {
        if(self.cartItem.isbn===null){
            console.log('Saving New Employee', self.cartItem);
            createUser(self.cartItem);
        }else{
            updateUser(self.cartItem, self.cartItem.isbn);
            console.log('Employee updated with emp_no ', self.cartItem.isbn);
        }
        reset();
    }

    function edit(isbn){
        console.log('emp_no to be edited', isbn);
        for(var i = 0; i < self.employees.length; i++){
            if(self.cartItems[i].isbn === isbn) {
                self.cartItem = angular.copy(self.cartItems[i]);
                break;
            }
        }
    }

    function remove(isbn){
        console.log('emp_no to be deleted', isbn);
        if(self.cartItem.isbn===isbn ) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteCartItem(isbn);
    }


    function reset(){
        self.cartItem={isbn: '', title:'', categoryName:'',quantity:0};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
