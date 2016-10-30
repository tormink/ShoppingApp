var rootURL = "http://localhost:8080/FrontView-war/UserManagement/users";

var currentUser;

function search(id) {
    if (id === "") {
        findAll();
    } else {
        searchById(id);
    }
}

function newUser() {
    renderDetails({});
}

function searchById(id) {
    console.log("Search by ID: " + id);
    $.ajax({
        type: 'GET',
        url: rootURL + '/user/' + id,
        dataType: "json",
        success: function(data) {
            //$('#btnDelete').show();
            console.log('Search by ID successful: ' + data.name);
            currentUser = data;
            renderDetails(currentUser);
        },
        error: function(data) {
            console.log('Error: ');
        }
    });
}

function saveUser(){
    console.log("Save user ");
    $.ajax({
        type:"PUT",
        contentType: 'application/xml',
        url: rootURL+'/newuser',
        dataType: "xml",
        data: formToXml(),
        success: function(data){
            $("#userId").val(data.id);
        }
    });
}

function renderDetails(user){
    $("#userId").val(user.id);
    $("#name").val(user.name);
    $("#surname").val(user.surname);
}

function formToXml(){
    var userId = $("#userId").val();
    if(userId === "") userId = 1;
    var name = $("#name").val();
    var surname = $("#surname").val();
}

function findAll(){

}

$(document).ready(function() {
    $("#searchbtn").on("click", function(e) {
        e.preventDefault();
        search($("#searchid").val());
    });
    $("#addbtn").on("click", function(e) {
        e.preventDefault();
        newUser();
    });
    $("#btnSave").on("click", function(e) {
        if ($("#userId").val() === "") {
            e.preventDefault();
            saveUser();
        }
    });
});
