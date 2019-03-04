<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>运营管理系统</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- <link rel="icon" type="image/x-icon" href="images/favicon.ico"> -->
    <link rel="stylesheet" href="/css/layui.css" media="all"/>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">XPay 运营管理</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="#">控制台</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <i class="layui-icon layui-icon-username"></i>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/logout">安全退出</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <i class="layui-icon layui-icon-layer"></i>
                </a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="menuNav">
                <li class="layui-nav-item">
                    <a  class="" href="javascript:;">商户信息</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:addTab('基本信息','merchant/view.html')">基本信息</a> </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">产品中心</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:addTab('支付产品','product/list.html')">支付产品</a> </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">交易订单</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:addTab('交易查询','order/list.html');">交易查询</a></dd>
                        <dd><a href="javascript:addTab('通知查询','notify/list.html');">通知查询</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">退款订单</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:addTab('退款查询','refund/list.html');">退款查询</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 5px;">
            <div class="layui-tab layui-tab-card" lay-filter="tab" lay-allowClose="true">
                <ul class="layui-tab-title">

                </ul>
                <div class="layui-tab-content">

                </div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>

<script type="text/javascript" src="/layui.js"></script>
<script type="text/javascript" src="/script/xpay.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
    });

    function setFrameHeight() {
        var iframes = document.getElementsByTagName('iframe');
        for (var i = 0, j = iframes.length; i < j; ++i) {
            // 放在闭包中，防止iframe触发load事件的时候下标不匹配
            (function (_i) {
                iframes[_i].setAttribute('height', iframes[_i].contentWindow.document.body.scrollHeight);
            })(i);
        }
    }

    window.setInterval("setFrameHeight()", 250);
</script>
</body>

</html>