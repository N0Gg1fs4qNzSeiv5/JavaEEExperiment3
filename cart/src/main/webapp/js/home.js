$(document).ready(function () {
    getAllGoods()
})

function getAllGoods() {
    $.ajax({
        url: "http://localhost:8080/goods",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.code === 20041) {
                addGoodsToPage(result.data)
            } else {
                alert(result.msg)
            }
        },
        error: function () {
            alert("系统繁忙")
        }
    })
}

function addGoodsToPage(data) {
    const classes = [".goods-list-left", ".goods-list-center", ".goods-list-right"]
    const len = data.length
    for (let i = 0; i < len; i ++) {
        // goods
        const divGoods = document.createElement("div")
        divGoods.className = "goods"
        const divGoodsLeft = document.createElement("div")
        divGoodsLeft.className = "goods-left"
        const imgGoodsImg = document.createElement("img")
        imgGoodsImg.className = "goods-img"
        imgGoodsImg.src = "../img/goods/" + data[i].img
        imgGoodsImg.alt = "图片没了"
        divGoodsLeft.appendChild(imgGoodsImg)
        divGoods.appendChild(divGoodsLeft)
        const divGoodsRight = document.createElement("div")
        divGoodsRight.className = "goods-right"
        const divGoodsRightTop = document.createElement("div")
        divGoodsRightTop.className = "goods-right-top"
        const divGoodsName = document.createElement("div")
        divGoodsName.className = "goods-name"
        divGoodsName.innerHTML = data[i].name
        divGoodsRightTop.appendChild(divGoodsName)
        divGoodsRight.appendChild(divGoodsRightTop)
        const divGoodsRightBottom = document.createElement("div")
        divGoodsRightBottom.className = "goods-right-bottom"
        const divGoodsPrice = document.createElement("div")
        divGoodsPrice.className = "goods-price"
        divGoodsPrice.innerHTML = "价格：" + data[i].price + "￥"
        divGoodsRightBottom.appendChild(divGoodsPrice)
        const divGoodsButton = document.createElement("div")
        divGoodsButton.className = "goods-button"
        const buttonAddToCart = document.createElement("button")
        buttonAddToCart.onclick = () => addToCart(data[i].id)
        buttonAddToCart.innerHTML = "添加到购物车"
        divGoodsButton.appendChild(buttonAddToCart)
        divGoodsRightBottom.appendChild(divGoodsButton)
        divGoodsRight.appendChild(divGoodsRightBottom)
        divGoods.appendChild(divGoodsRight)

        $(classes[i % 3]).append(divGoods)
    }
}

function addToCart(goodsId) {
    $.ajax({
        url: "http://localhost:8080/carts",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            goodsId: goodsId,
            count: 1
        }),
        dataType: "json",
        success: function (result) {
            if (result.code === 20011) {
                alert("添加成功")
            } else {
                alert(result.msg)
            }
        },
        error: function () {
            alert("系统繁忙")
        }
    })
}