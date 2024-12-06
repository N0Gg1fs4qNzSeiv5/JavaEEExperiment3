$(document).ready(function () {
    $("#toHomePageButton").click(toHomePage)
    $("#toLoginPageButton").click(toLoginPage)
    $("#registerButton").click(register)
})

function toHomePage() {
    window.location.href = "../pages/goods.html"
}

function register() {
    const username = $("#usernameInput").val()
    const password = $("#passwordInput").val()
    const confPassword = $("#confPasswordInput").val()

    if (password !== confPassword) {
        alert("两次输入的密码不一致")
        return
    }

    $.ajax({
        url: "http://localhost:8080/users/register",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            username: username,
            password: password
        }),
        dataType: "json",
        success: function (result) {
            if (result.code === 20011) {
                alert("注册成功")
                setTimeout(function () {
                    window.location.href = "../pages/login.html"
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

function toLoginPage() {
    window.location.href = "../pages/login.html"
}