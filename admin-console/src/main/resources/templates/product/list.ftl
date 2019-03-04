<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>支付产品</title>
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" href="/css/layui.css" media="all"/>
</head>

<body>
<div class="layui-collapse">
    <div class="layui-colla-item">
        <table class="layui-table" lay-data="{id:'table',page:true}" lay-filter="table">
            <thead>
            <tr>
                <th lay-data="{field:'productId',align:'center'}">产品ID</th>
                <th lay-data="{field:'productName',align:'center'}">产品名称</th>
                <th lay-data="{field:'productType',align:'center'}">产品类型</th>
                <th lay-data="{field:'status',align:'center',templet:'#tempStatus'}">开通状态</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<!--操作模板-->
<script src="/layui.js" charset="utf-8"></script>
<script type="text/html" id="tempStatus">
    <span style="color:green;">已开通</span>
</script>
<script>
    layui.use('table',function(){
        var table=layui.table;
        table.reload('table',{
            url: '/product/list'
            ,method:'get'
            ,page:false
            ,text:{
                none:'未开通任何支付产品'
            }
        });
    });
</script>
</body>
</html>