function login () {
    var myParam = {username: $("#username").val(),
        password: $("#password").val()};
    $.ajax({
        url: "login",
        type: "post",
        data: JSON.stringify(myParam),
        contentType: "application/json",
        dataType: "json",
        success: function (response) {
            if (response.code == "0") {
                window.location.href = "main.html"
            } else {
                alert("username or password is not right")
            }
        }
    })
};
document.getElementById("register").onclick = function () {
    window.location.href="register.html";
};