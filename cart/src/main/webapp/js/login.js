$(document).ready(function () {
    $("#toHomePageButton").click(toHomePage)
    $("#toRegisterPageButton").click(toRegisterPage)
    $("#loginButton").click(login)
})

function toHomePage() {
    window.location.href = "../pages/goods.html"
}

function login() {
    const username = $("#usernameInput").val()
    const password = $("#passwordInput").val()

    $.ajax({
        url: "http://localhost:8080/users/login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            username: username,
            password: password
        }),
        dataType: "json",
        success: function (result) {
            if (result.code === 20041) {
                alert("登陆成功")
                setTimeout(function () {
                    window.location.href = "../pages/goods.html"
                }, 500)
            } else {
                alert(result.msg)
            }
        },
        error: function () {
            alert("系统繁忙")
        }
    })
}


function toRegisterPage() {
    window.location.href = "/pages/register.html"
}