<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>商户信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/css/layui.css" media="all">
</head>
<body>
<div class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商户编码</label>
            <div class="layui-input-inline">

                <input class="layui-input" value="${merchant.merchantId}" readonly="true"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商户名称</label>
            <div class="layui-input-inline">
                <input class="layui-input"  value="${merchant.company}" readonly="true"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">联系人</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${merchant.contacts}" readonly="true"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${merchant.phoneNumber}" readonly="true"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${merchant.email}" readonly="true"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-inline">
                <input class="layui-input" value="${merchant.address}" readonly="true"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>