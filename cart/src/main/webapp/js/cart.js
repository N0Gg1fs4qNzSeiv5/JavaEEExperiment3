$(document).ready(function () {
    getAllCarts()
    $("#placeOrderButton").click(placeOrder)
})


function getAllCarts() {
    $.ajax({
        url: "http://localhost:8080/carts",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.code === 20041) {
                addCartsToPage(result.data)
            } else {
                alert(result.msg)
            }
        },
        error: function () {
            alert("系统繁忙")
        }
    })
}

function addCartsToPage(data) {
    const len = data.length
    for (let i = 0; i < len; i ++) {
        $.ajax({
            url: "http://localhost:8080/goods/" + data[i].goodsId,
            type: "GET",
            dataType: "json",
            success: function (result) {
                if (result.code === 20041) {
                    addToPage(data[i], result.data)
                } else {
                    alert(result.msg)
                }
            },
            error: function () {
                alert("系统繁忙")
            }
        })
    }
}

function addToPage(cartItem, goods) {
    const divGoods = document.createElement("div")
    divGoods.className = "goods"
    const divGoodsLeft = document.createElement("div")
    divGoodsLeft.className = "goods-left"
    const imgGoodsImg = document.createElement("img")
    imgGoodsImg.className = "goods-img"
    imgGoodsImg.src = "../img/goods/" + goods.img
    imgGoodsImg.alt = "图片没了"
    divGoodsLeft.appendChild(imgGoodsImg)
    divGoods.appendChild(divGoodsLeft)
    const divGoodsRight = document.createElement("div")
    divGoodsRight.className = "goods-right"
    const divGoodsRightTop = document.createElement("div")
    divGoodsRightTop.className = "goods-right-top"
    const divGoodsName = document.createElement("div")
    divGoodsName.className = "goods-name"
    divGoodsName.innerHTML = goods.name
    divGoodsRightTop.appendChild(divGoodsName)
    const buttonDeleteCartButton = document.createElement("button")
    buttonDeleteCartButton.className = "delete-cart-button"
    buttonDeleteCartButton.innerHTML = "删除"
    buttonDeleteCartButton.onclick = () => deleteCart(cartItem.id)
    divGoodsRightTop.appendChild(buttonDeleteCartButton)
    divGoodsRight.appendChild(divGoodsRightTop)
    const divGoodsRightBottom = document.createElement("div")
    divGoodsRightBottom.className = "goods-right-bottom"
    const divGoodsPrice = document.createElement("div")
    divGoodsPrice.className = "goods-price"
    divGoodsPrice.innerHTML = "价格：" + goods.price + "￥"
    divGoodsRightBottom.appendChild(divGoodsPrice)
    const divGoodsButton = document.createElement("div")
    divGoodsButton.className = "goods-button"
    const divGoodsCount = document.createElement("div")
    divGoodsCount.className = "goods-count"
    divGoodsCount.innerHTML = cartItem.count
    const buttonSubCount = document.createElement("button")
    buttonSubCount.onclick = () => subCount(cartItem.id, divGoodsCount)
    buttonSubCount.innerHTML = "-"
    const buttonAddCount = document.createElement("button")
    buttonAddCount.onclick = () => addCount(cartItem.id, divGoodsCount)
    buttonAddCount.innerHTML = "+"
    divGoodsButton.appendChild(buttonSubCount)
    divGoodsButton.appendChild(divGoodsCount)
    divGoodsButton.appendChild(buttonAddCount)
    divGoodsRightBottom.appendChild(divGoodsButton)
    divGoodsRight.appendChild(divGoodsRightBottom)
    divGoods.appendChild(divGoodsRight)

    $(".carts").append(divGoods)
}

function deleteCart(cartId) {
    $.ajax({
        url: "http://localhost:8080/carts/" + cartId,
        type: "DELETE",
        dataType: "json",
        success: function (result) {
            if (result.code === 20021) {
                alert("删除成功")
                window.location.reload()
            } else {
                alert(result.msg)
            }
        },
        error: function () {
            alert("系统繁忙")
        }
    })
}

function subCount(cartId, element) {
    let count = parseInt(element.innerHTML)
    if (count - 1 <= 0) {
        alert("商品数量最少为1")
        return
    }
    count --
    element.innerHTML = count.toString()

    $.ajax({
        url: "http://localhost:8080/carts",
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            id: cartId,
            count: count
        }),
        dataType: "json",
        success: function (result) {
            if (result.code === 20031) {
                console.log("修改成功")
            } else {
                alert(result.msg)
            }
        },
        error: function () {
            alert("系统繁忙")
        }
    })
}

function addCount(cartId, element) {
    let count = parseInt(element.innerHTML)

    count ++
    element.innerHTML = count.toString()

    $.ajax({
        url: "http://localhost:8080/carts",
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            id: cartId,
            count: count
        }),
        dataType: "json",
        success: function (result) {
            if (result.code === 20031) {
                console.log("修改成功")
            } else {
                alert(result.msg)
            }
        },
        error: function () {
            alert("系统繁忙")
        }
    })
}

function placeOrder() {
    const address = prompt("请输入收货地址");

    $.ajax({
        url: "http://localhost:8080/orders",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            address: address
        }),
        dataType: "json",
        success: function (result) {
            if (result.code === 20011) {
                alert("下单成功，付款：" + result.data)
                window.location.href = "../pages/goods.html"
            } else {
                alert(result.msg)
            }
        },
        error: function () {
            alert("系统繁忙")
        }
    })
}