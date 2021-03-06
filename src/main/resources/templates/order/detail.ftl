<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
<#include "../common/nav.ftl">
<#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                订单ID
                            </th>
                            <th>
                                总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                            ${orderDTO.orderId}
                            </td>
                            <td>
                            ${orderDTO.orderAmount}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            <#--订单详情-->
                <div class="col-md-12 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                商品ID
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th>
                                价格
                            </th>
                            <th>
                                数量
                            </th>
                            <th>
                                总额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderDTO.orderDetailList as detail>
                <tr>
                    <td>
                        ${detail.productId}
                    </td>
                    <td>
                        ${detail.productName}
                    </td>
                    <td>
                        ${detail.productPrice}
                    </td>
                    <td>
                        ${detail.productQuantity}
                    </td>
                    <td>
                        ${detail.productQuantity * detail.productPrice}
                    </td>
                </tr>


                </#list>

                        </tbody>
                    </table>
                </div>

            <#--操作-->
                <div class="col-md-12 column">
              <#if orderDTO.getOrderStatusEnum().massage =="新订单">



              </#if>
                    <a type="button" href="/sell/seller/order/finish?orderId=${orderDTO.orderId}"
                       class="btn btn-default btn-primary">完结订单</a>
                    <a type="button" href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}"
                       class="btn btn-default btn-danger">取消订单</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>