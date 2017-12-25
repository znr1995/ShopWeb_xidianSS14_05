<div class="section_container">
    <!-- 购物车 -->
    <div id="shopping_cart">
        <div class="message success">我的购物车</div>
        <table class="data-table cart-table" cellpadding="0" cellspacing="0">
            <tr>
                <th class="align_center" width="10%">商品编号</th>
                <th class="align_left" width="35%" colspan="2">商品名称</th>
                <th class="align_center" width="10%">销售价格</th>
                <th class="align_center" width="20%">数量</th>
                <th class="align_center" width="15%">小计</th>
                <th class="align_center" width="10%">删除</th>
            </tr>
            <c:forEach items="${sessionScope.forder.sorders }" var="sorder" varStatus="num">
                <tr lang="${sorder.product.id}">
                    <td class="align_center"><a href="#" class="edit">${num.count }</a></td>
                    <td width="80px"><img src="${shop}/files/${sorder.product.pic}" width="80" height="80" /></td>
                    <td class="align_left"><a class="pr_name" href="#">${sorder.name }</a></td>
                    <td class="align_center vline">${sorder.price }</td>
                    <td class="align_center vline">
                        <!-- 文本框 -->
                        <input class="text" style="height: 20px;" value="${sorder.number }" lang="${sorder.number }">
                    </td>
                    <td class="align_center vline">${sorder.price*sorder.number }</td>
                    <td class="align_center vline"><a href="#" class="remove"></a></td>
                </tr>
            </c:forEach>
        </table>
        <!-- 结算 -->
        <div class="totals">
            <table id="totals-table">
                <tbody>
                <tr>
                    <td width="60%" colspan="1" class="align_left"><strong>小计</strong></td>
                    <td class="align_right" style=""><strong>￥<span
                            class="price" id="total">${sessionScope.forder.total}</span>
                    </strong></td>
                </tr>
                <tr>
                    <td width="60%" colspan="1" class="align_left">运费</td>
                    <td class="align_right" style="">￥<span class="price" id="yunfei">0.00</span></td>
                </tr>
                <tr>
                    <td width="60%" colspan="1" class="align_left total"><strong>总计</strong></td>
                    <td class="align_right" style="">￥<span class="total" id="totalAll"><strong>${sessionScope.forder.total}</strong></span>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="action_buttonbar">
                <font><a href="${shop}/user/confirm.jsp">
                    <button type="button" title="" class="checkout fr" style="background-color: #f38256;">订单确认</button></a>
                </font>
                <font><a href="#">
                    <button type="button" title="" class=" fr">
                        <font>清空购物车</font>
                    </button>
                </font>
                <a href="${shop}/index.jsp">
                    <button type="button" title="" class="continue fr">
                        <font>继续购物</font>
                    </button></a>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>