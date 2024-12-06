$(document).ready(function () {
    getAllOrders()
})


function getAllOrders() {
    $.ajax({
        url: "http://localhost:8080/orders",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.code === 20041) {
                addOrderToPage(result.data)
            } else {
                alert(result.msg)
            }
        },
        error: function () {
            alert("系统繁忙")
        }
    })
}

function addOrderToPage(data) {
    const len = data.length
    for (let i = 0; i < len; i ++) {
        const divOrder = document.createElement("div")
        divOrder.className = "order"
        const divOrderLeft = document.createElement("div")
        divOrderLeft.className = "order-left"
        const divOrderLeftTop = document.createElement("div")
        divOrderLeftTop.className = "order-left-top"
        const divOrderNo = document.createElement("div")
        divOrderNo.className = "order-no"
        divOrderNo.innerHTML = "订单号：" + data[i].no
        divOrderLeftTop.appendChild(divOrderNo)
        divOrderLeft.appendChild(divOrderLeftTop)
        const divOrderLeftBottom = document.createElement("div")
        divOrderLeftBottom.className = "order-left-bottom"
        const divOrderAddress = document.createElement("div")
        divOrderAddress.className = "order-address"
        divOrderAddress.innerHTML = "收货地址：" + data[i].address
        divOrderLeftBottom.appendChild(divOrderAddress)
        divOrderLeft.appendChild(divOrderLeftBottom)
        divOrder.appendChild(divOrderLeft)
        const divOrderRight = document.createElement("div")
        divOrderRight.className = "order-right"
        const divOrderRightTop = document.createElement("div")
        divOrderRightTop.className = "order-right-top"
        const divOrderCreate = document.createElement("div")
        divOrderCreate.className = "order-create"
        divOrderCreate.innerHTML = "创建时间：" + data[i].createAt
        divOrderRightTop.appendChild(divOrderCreate)
        divOrderRight.appendChild(divOrderRightTop)
        const divOrderRightBottom = document.createElement("div")
        divOrderRightBottom.className = "order-right-bottom"
        const divOrderPay = document.createElement("div")
        divOrderPay.className = "order-pay"
        divOrderPay.innerHTML = "付款金额：" + data[i].pay + "￥"
        divOrderRightBottom.appendChild(divOrderPay)
        const buttonOrderShowButton = document.createElement("button")
        buttonOrderShowButton.className = "order-show-button"
        buttonOrderShowButton.innerHTML = "查看"
        buttonOrderShowButton.onclick = () => showOrderInfo(data[i].id)
        divOrderRightBottom.appendChild(buttonOrderShowButton)
        divOrderRight.appendChild(divOrderRightBottom)
        divOrder.appendChild(divOrderRight)
        $(".orders").append(divOrder)
    }
}

function showOrderInfo(orderId) {
    $.ajax({
        url: "http://localhost:8080/goods_orders/" + orderId,
        type: "GET",
        dataType: "json",
        success: async function (result) {
            if (result.code === 20041) {
                const data = result.data
                let info = ""
                const len = data.length
                for (let i = 0; i < len; i ++) {
                    const goods = await getGoodsInfo(data[i].goodsId)
                    if (goods == null) continue
                    info += goods.name + " * " + data[i].count + "  " + (goods.price * data[i].count) + "￥\n"
                }
                alert(info)
            } else {
                alert(result.msg)
            }
        },
        error: function () {
            alert("系统繁忙")
        }
    })
}

async function getGoodsInfo(goodsId) {
    let goods = null
    await $.ajax({
        url: "http://localhost:8080/goods/" + goodsId,
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.code === 20041) {
                const data = result.data
                goods = {
                    name: data.name,
                    price: data.price
                }
            } else {
                alert(result.msg)
            }
        },
        error: function () {
            alert("系统繁忙")
        }
    })

    return goods
}