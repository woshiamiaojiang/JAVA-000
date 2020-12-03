DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info(
  user_info_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  user_name VARCHAR(20) NOT NULL COMMENT '用户昵称',
  mobile_phone INT UNSIGNED COMMENT '手机号',
  user_email VARCHAR(50) COMMENT '邮箱',
  gender TINYINT COMMENT '性别',
  register_time TIMESTAMP NOT NULL COMMENT '注册时间',
  modified_user BIGINT(20)    COMMENT '最后修改人' ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY pk_userinfid(user_info_id)
) ENGINE = innodb COMMENT '用户信息表';

DROP TABLE IF EXISTS goods_info;
CREATE TABLE goods_info(
  goods_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '商品ID',
  goods_serial CHAR(16) NOT NULL COMMENT '商品编码',
  goods_name VARCHAR(20) NOT NULL COMMENT '商品名称',
  price DECIMAL(8,2) NOT NULL COMMENT '商品销售价格',
  description TEXT NOT NULL COMMENT '商品描述',
  modified_user BIGINT(20)    COMMENT '最后修改人' ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY pk_goodsid(goods_id)
) ENGINE = innodb COMMENT '商品信息表';

DROP TABLE IF EXISTS order_main;
CREATE TABLE order_main(
  order_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  order_sn BIGINT UNSIGNED NOT NULL COMMENT '订单编号 yyyymmddnnnnnnnn',
  user_id INT UNSIGNED NOT NULL COMMENT '下单人ID',
  receiver_name VARCHAR(10) NOT NULL COMMENT '收货人姓名',
  province SMALLINT NOT NULL COMMENT '省',
  city SMALLINT NOT NULL COMMENT '市',
  district SMALLINT NOT NULL COMMENT '区',
  address VARCHAR(100) NOT NULL COMMENT '地址',
  pay_way TINYINT NOT NULL COMMENT '支付方式：1现金，2余额，3网银，4支付宝，5微信',
  pay_id VARCHAR(32) NOT NULL COMMENT '支付流水号',
  order_amt DECIMAL(8,2) NOT NULL COMMENT '订单金额',
  district_amt DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '优惠金额',
  ship_amt DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '运费金额',
  pay_amt DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '支付金额',
  ship_com_name VARCHAR(10) COMMENT '快递公司名称',
  ship_sn VARCHAR(50) COMMENT '快递单号',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  ship_time DATETIME COMMENT '发货时间',
  pay_time DATETIME COMMENT '支付时间',
  receive_time DATETIME COMMENT '收货时间',
  order_status TINYINT NOT NULL DEFAULT 0 COMMENT '订单状态',
  modified_user BIGINT(20)    COMMENT '最后修改人' ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY pk_orderid(order_id)
)ENGINE = innodb COMMENT '订单主表';

DROP TABLE IF EXISTS order_detail;
CREATE TABLE order_detail(
  order_detail_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单详情表ID',
  order_id INT UNSIGNED NOT NULL COMMENT '订单表ID',
  goods_id INT UNSIGNED NOT NULL COMMENT '订单商品ID',
  goods_name VARCHAR(50) NOT NULL COMMENT '商品名称',
  goods_cnt INT NOT NULL DEFAULT 1 COMMENT '购买商品数量',
  goods_price DECIMAL(8,2) NOT NULL COMMENT '购买商品单价',
  modified_user BIGINT(20)    COMMENT '最后修改人' ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY pk_orderdetailid(order_detail_id)
)ENGINE = innodb COMMENT '订单详情表'
