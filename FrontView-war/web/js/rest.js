var rootURL = "http://localhost:8080/Front-View-war/UserManagement/users";

$(document).ready(function() {
    $("#searchbtn").on("click", function () {

    });
});

function search(id) {
    if (id === "") {
        findAll();
    }
}
