<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>通知详情</title>
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
                <input class="layui-input" style="width:360px; " value="${notify.billNo}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">产品ID</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${notify.productId}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">产品名称</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${notify.productName}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商户订单号</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${notify.outTradeNo}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">交易金额</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${notify.tradeAmt?string.currency}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">支付金额</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${notify.realAmt?string.currency}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">订单状态</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${notify.billStatus}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">通知URL</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="width: 360px" value="${notify.notifyUrl}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">同步URL</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="width: 360px" value="${notify.callbackUrl}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">通知参数</label>
            <div class="layui-input-inline">
                <textarea readonly="readonly" style="width: 360px" name="desc"
                          class="layui-textarea">${notify.notifyParams}</textarea>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">通知次数</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${notify.notifyCount}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">通知时间</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${notify.notifyTime}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">通知状态</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${notify.notifyStatus}" readonly="readonly"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">返回消息</label>
            <div class="layui-input-inline">
                   <textarea readonly="readonly" style="width: 360px" name="desc"
                             class="layui-textarea">${notify.notifyFeedback}</textarea>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">创建时间</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${notify.createTime}" readonly="readonly"/>
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