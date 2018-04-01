function attachLogin() {
    $("#loginButton").click(function() {
        $.ajax({
            type: "POST",
            url: "login",
            data: {
                login: $("#loginInput").val(),
                password: $("#passwordInput").val()
            }
        }).then(console.log)
            .fail(console.error);
    });
}

function attachLogout() {
    $("#logoutButton").click(function () {
        $.ajax({
            type: "POST",
            url: "logout",
            data: {}
        }).then(console.log)
            .fail(console.error);
    });
}

function attachReceivedMessages() {
    $("#receivedMessagesButton").click(function() {
        $.ajax({
            type: "GET",
            url: "message"
        }).then(console.log)
            .fail(console.error);
    });
}


$(document).ready(function() {
    attachLogin();
    attachLogout();
    attachReceivedMessages();
});