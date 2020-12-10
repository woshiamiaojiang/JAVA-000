package com.geekbang.shardingproxy.order.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("order_info")
public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 唯一订单号
     */
    private String orderNumber;

    /**
     * 买家id
     */
    private String userId;

    /**
     * 商品id
     */
    private String goodId;

    /**
     * 订单状态 0未支付 1已支付 2已发出 3已送达 4已评价
     */
    private Integer orderStatus;

    /**
     * 付款金额
     */
    private BigDecimal payAmount;

    /**
     * 真实付款金额
     */
    private BigDecimal realAmount;

    /**
     * 缴费账号(卡号)
     */
    private String payAccountNo;

    /**
     * 快递号
     */
    private String trackNo;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 付款时间
     */
    private LocalDateTime payTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @Override
    protected Serializable pkVal() {
        return this.goodId;
    }
}
