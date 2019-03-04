<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>订单查询</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/css/layui.css" media="all">
</head>

<body>
<div class="layui-collapse">
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">查询条件</h2>
        <div class="layui-colla-content layui-show">
            <div class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">时间类型</label>
                        <div class="layui-input-inline" style="width:100px;">
                            <select id="timeType" name="timeType" lay-verify="">
                                <option value="0">创建时间</option>
                                <option value="1" selected>处理时间</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width:330px;">
                            <input class="layui-input" id="timeRange" type="text" placeholder="请选择日期及时间范围">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">订单号</label>
                        <div class="layui-input-inline">
                            <input class="layui-input" id="outTradeNo" type="text" placeholder="请输入订单号">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">交易产品</label>
                        <div class="layui-input-inline">
                            <select id="productId" name="productId" lay-verify="">
                                <option value="">所有产品</option>
                                <#list products as p>
                                <option value="${p.productId}">${p.productName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">单据状态</label>
                        <div class="layui-input-inline">
                            <select id="billStatus" name="billStatus" lay-verify="">
                                <option value="">所有状态</option>
                                <option value="Failure">失败</option>
                                <option value="Paying">支付中</option>
                                <option value="Success">成功</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">排序字段</label>
                        <div class="layui-input-inline" style="width:100px">
                            <select id="sortField" name="sortField" lay-verify="">
                                <option value="createTime">创建时间</option>
                                <option value="dealTime">处理时间</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width:100px">
                            <select id="sort" name="sort" lay-verify="" ">
                            <option value="desc">降序</option>
                            <option value="asc">升序</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-btn-container" style="text-align:right">
                <button class="layui-btn" onclick="searchOrder()">查询数据</button>
                <button class="layui-btn">导出数据</button>
            </div>
        </div>
    </div>
</div>

<table id="table" class="layui-table" lay-data="{ page:true, id:'table'}" lay-filter="table">
    <thead>
    <tr>
        <th lay-data="{field:'billNo', width:220}">单据号</th>
        <th lay-data="{field:'productName', width:180}">产品名称</th>
        <th lay-data="{field:'outTradeNo',width:200}">商户订单号</th>
        <th lay-data="{field:'tradeAmt',align:'right',templet:'<div>{{formatCurrency(d.tradeAmt)}}</div>', width:120}">
            交易金额
        </th>
        <th lay-data="{field:'realAmt',align:'right',templet:'<div>{{formatCurrency(d.realAmt)}}</div>',width:120}">
            支付金额
        </th>
        <th lay-data="{field:'goodsName',width:150}">商品名称</th>
        <th lay-data="{field:'billStatus',width:120}">单据状态</th>
        <th lay-data="{field:'dealTime',width:180}">处理时间</th>
        <th lay-data="{field:'createTime',width:180}">创建时间</th>
        <th lay-data="{fixed: 'right', width:150, align:'center', toolbar: '#methodBar'}">操作</th>
    </tr>
    </thead>
</table>
<!--操作模板-->
<script type="text/html" id="methodBar">
    <a class="layui-btn" lay-event="detail">查看</a>
</script>

<script src="/layui.js" charset="utf-8"></script>
<script src="/script/xpay.js" charset="utf-8"></script>
<script src="/script/jquery-3.3.1.min.js" charset="utf-8"></script>
<script>
    // 日期选择
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        var element = layui.element;
        var now = new Date();
        //日期时间范围
        laydate.render({
            elem: '#timeRange'
            , type: 'datetime'
            , value: formatDate(now) + ' 00:00:00  -  ' + formatDate(now) + ' 23:59:59'
            , isInitValue: true
            , range: true
        });
    });

    // 表单控件相关（下拉选择、单选、多选）
    layui.use('form', function () {
        var form = layui.form;
    });

    // tab、折叠面板 相关
    layui.use('element', function () {
        var element = layui.element;
    });

    // 数据表格
    layui.use('table', function () {
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#table',
            text: {
                none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
            }
        });


    });
</script>
<script>
    function searchOrder() {
        // 开启遮罩，在done 事件中关闭
        var load = layer.load(2);
        var table = layui.table;
        // 按照新条件查询数据
        table.reload('table', {
            url: '/order/list',
            method: 'post',
            contentType: 'application/json',
            where: { //设定异步数据接口的额外参数，任意设
                timeType: $("#timeType").val(),
                timeRange: $("#timeRange").val(),
                status: $("#billStatus").val(),
                productId: $("#productId").val(),
                outTradeNo: $("#outTradeNo").val(),
                sortField: $("#sortField").val(),
                sort: $("#sort").val()
            },
            limit: 20,
            limits: [20, 50, 100],
            done: function () {
                // 关闭遮罩
                layer.close(load);
            },
            request: {
                pageName: 'pageIndex', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
        // 操作栏增加事件处理
        table.on('tool(table)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if (layEvent === 'detail') { //查看
                var outTradeNo = data.outTradeNo;
                var index = layer.open({
                    type: 2,
                    content: '/order/view?outTradeNo=' + outTradeNo,
                    area: ['640px', '500px'],
                    offset: '30px'
                });
                layer.iframeAuto(index)
            }
        });
    }
</script>
</body>

</html>