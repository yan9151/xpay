<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>订单详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/css/layui.css" media="all">
</head>

<body>
<div class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">单据号</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.billNo}" style="width: 260px" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">产品ID</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.productId}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">产品名称</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.productName}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商户订单号</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.outTradeNo}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">交易金额</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.tradeAmt?string.currency}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">支付金额</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.realAmt?string.currency}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.goodsName}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商品描述</label>
            <div class="layui-input-inline" style="width: 260px">
                <textarea name="desc" class="layui-textarea">${order.goodsDetail}</textarea>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">过期时间</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.timeoutExpressMinute}分钟" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">订单状态</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.billStatus}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">用户IP</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.userIp}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">提交IP</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.fromIp}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">处理时间</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.dealTime}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">创建时间</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${order.createTime}" readonly="readonly"/>
            </div>
        </div>
    </div>
</div>

<script src="/layui.js" charset="utf-8"></script>
<script>
    // 表单控件相关（下拉选择、单选、多选）
    layui.use('form', function () {
        var form = layui.form;
    });
</script>
</body>

</html>