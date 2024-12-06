package xyz.djma.domain;

public class GoodsOrder {
    private Integer id;
    private Integer goodsId;
    private Integer orderId;
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "GoodsOrder{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", orderId=" + orderId +
                ", count=" + count +
                '}';
    }
}
