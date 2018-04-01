<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
<#include "../common/nav.ftl">
<#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>
                                订单Id
                            </th>
                            <th>
                                姓名
                            </th>
                            <th>
                                手机号
                            </th>
                            <th>
                                地址
                            </th>
                            <th>
                                金额
                            </th>
                            <th>
                                订单状态
                            </th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2" align="center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderDTOPage.content as orderDTO>
                <tr>
                    <td>
                        ${orderDTO.orderId}
                    </td>
                    <td>
                        ${orderDTO.buyerName}
                    </td>
                    <td>
                        ${orderDTO.buyerPhone}
                    </td>
                    <td>
                        ${orderDTO.buyerAddress}
                    </td>
                    <td>
                        ${orderDTO.orderAmount}
                    </td>
                    <td>
                        ${orderDTO.getOrderStatusEnum().getMassage()}
                    </td>
                    <td>
                        ${orderDTO.getPayStatusEnum().getMassage()}
                    </td>
                    <td>
                        ${orderDTO.createTime}
                    </td>
                    <td>
                        <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                    </td>
                    <td>
                    <#if orderDTO.getOrderStatusEnum().massage =="新订单">
                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                    <#else>


                    </#if>
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                <#if currentPage lte 1>
                    <li class="disabled"><a href="#">上一页</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${currentPage -1}&size=${size}">上一页</a></li>
                </#if>

                <#list 1..orderDTOPage.getTotalPages()  as  index>
                    <#if currentPage == index>
                        <li class="disabled">
                            <a href="#"> ${index}</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/seller/order/list?page=${index}&size=${size}"> ${index}</a>
                        </li>
                    </#if>
                </#list>
                <#if currentPage gte orderDTOPage.getTotalPages()>
                    <li class="disabled">
                        <a href="#">下一页</a>
                    </li>
                <#else>
                <li>
                    <a href="/sell/seller/order/list?page=${currentPage +1}&size=${size}">下一页</a>
                </li>
                </#if>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>

            <div class="modal-footer">
                <button type="button" onclick="javasctipt:document.getElementById('notice').pause()"
                        class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>


<audio id="notice" loop="loop">
    <source src="/mp3/song.mp3" type="audio.mpeg"/>
</audio>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket('ws://localhost:8080/seller/order/list');
    } else {
        alert("浏览器不支持");
    }

    websocket.onopen = function (event) {
        console.info("dakai ")
    }
    websocket.onclose = function (event) {
        console.info("关闭")
    }

    websocket.onmessage = function (event) {
        console.info('收到消息' + event.data);
        $('#modal').modal('show');
        document.getElementById('notice').play();
    }
    websocket.onerror = function (event) {
        alert("发生错误");
    }
    websocket.onbeforeunload = function (event) {
        websocket.close();

    }


</script>
</body>


</html>


<#---->
<#--${orderDTO.orderId}<br>-->





<#--</#list>-->