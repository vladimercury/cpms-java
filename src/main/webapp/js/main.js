$(document).ready(function() {
    var $loginButton = $("#loginButton"),
        $loginInput = $("#loginInput"),
        $passwordInput = $("#passwordInput");

    $loginButton.click(function() {
        $.ajax({
            type: "POST",
            url: "login",
            data: {
                login: $loginInput.val(),
                password: $passwordInput.val()
            }
        }).then(function() {
            console.log(arguments);
        }).fail(function() {
            console.error(arguments);
        });
    });
});