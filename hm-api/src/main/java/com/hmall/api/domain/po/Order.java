package com.hmall.api.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单实体（下沉至 hm-api 以便多模块复用）
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 订单id */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 总金额，单位为分 */
    private Integer totalFee;

    /** 支付类型，1、支付宝，2、微信，3、扣减余额 */
    private Integer paymentType;

    /** 用户id */
    private Long userId;

    /** 订单状态：1未付款 2已付款未发货 3已发货未确认 4确认收货交易成功 5交易取消订单关闭 6交易结束已评价 */
    private Integer status;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 支付时间 */
    private LocalDateTime payTime;

    /** 发货时间 */
    private LocalDateTime consignTime;

    /** 交易完成时间 */
    private LocalDateTime endTime;

    /** 交易关闭时间 */
    private LocalDateTime closeTime;

    /** 评价时间 */
    private LocalDateTime commentTime;

    /** 更新时间 */
    private LocalDateTime updateTime;
}

