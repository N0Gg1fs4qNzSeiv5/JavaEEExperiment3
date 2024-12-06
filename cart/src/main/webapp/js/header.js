$(document).ready(function () {
    $("#toGoodsPageButton").click(toGoodsPage)
    $("#toCartPageButton").click(toCartPage)
    $("#toOrderPageButton").click(toOrderPage)
    $("#toLoginPageButton").click(toLoginPage)
    $("#toRegisterPageButton").click(toRegisterPage)
})

function toGoodsPage() {
    window.location.href = "../pages/goods.html"
}

function toCartPage() {
    window.location.href = "/pages/cart.html"
}

function toOrderPage() {
    window.location.href = "/pages/order.html"
}

function toLoginPage() {
    window.location.href = "/pages/login.html"
}


function toRegisterPage() {
    window.location.href = "/pages/login.html"
}