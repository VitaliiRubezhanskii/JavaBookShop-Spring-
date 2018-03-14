'use strict';


angular.module('myApp').controller('BookController', ['$scope', 'BookService', function($scope, BookService) {

    var self = this;
    self.book={isbn: '',title:'',author:'',author1:'',author2:'',author3:'',categoryName:'',price:'',photo:'',releaseYear:'',
                 rentalDuration:'',rentalRate:'',replacementCost:'',shortTitle:''    }

    self.books=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllBooks();

    function fetchAllBooks(){
        BookService.fetchAllBooks()
            .then(
            function(d) {
                self.book = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function createBook(book){
        BookService.createBook(book)
            .then(
            fetchAllBooks(),
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }

    function updateBook(book, isbn){
        BookService.updateBook(book, isbn)
            .then(
            fetchAllBooks(),
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }

    function deleteBook(isbn){
        BookService.deleteBook(isbn)
            .then(
            fetchAllBooks(),
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function submit() {
        if(self.book.isbn===null){
            console.log('Saving New User', self.book);
            createBook(self.book);
        }else{
            updateUser(self.book, self.book.isbn);
            console.log('User updated with id ', self.book.isbn);
        }
        reset();
    }

    function edit(isbn){
        console.log('id to be edited', isbn);
        for(var i = 0; i < self.books.length; i++){
            if(self.books[i].isbn === isbn) {
                self.book = angular.copy(self.books[i]);
                break;
            }
        }
    }

    function remove(isbn){
        console.log('id to be deleted', isbn);
        if(self.book.isbn === isbn) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteBook(isbn);
    }


    function reset(){
        self.book={isbn: '',title:'',author:'',author1:'',categoryName:'',price:'',photo:'',releaseYear:'',author2:'',author3:'',
            rentalDuration:'',rentalRate:'',replacementCost:'',shortTitle:''    }
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
