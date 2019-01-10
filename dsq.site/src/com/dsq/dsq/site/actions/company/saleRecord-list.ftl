<div class="page">
	<div class="pageHeader">
        <@dwz.pageForm action="/company/saleRecord-list" showKeyword=false>
        	<@saleRecord.saleRecordSearch searchModel=searchModel />
        </@dwz.pageForm>
    </div>
    <div class="pageContent">
        <@sec.any name="SALES_MANAGE_WRITE">
	        <div class="panelBar">
	            <ul class="toolBar">
	                <li><@dwz.a href="/company/saleRecord-add" target="dialog" title="新增公司" height="SS"><span class="fa-plus">新增</span></@dwz.a></li>
	            </ul>
	        </div>
        </@sec.any>
        <table class="table" width="100%" layoutH="75">
            <thead>
                <tr>
                    <th width="120">编号</th>
                     <th width="120">产品名称</th>
                    <th width="120">客户名称</th>
                    <th width="120">商品规格</th>
                    <th width="120">包装单位</th>
                    <th width="120">销售数量</th>
                    <th width="100" align="center">销售日期</th>
                    <th width="100" align="center">生产日期</th>
                    <th width="100" align="center">商品批号</th>
					<th width="120">生产厂家</th>
					<#-- <th width="60" align="center">状态</th> -->
					<@sec.any name="SALES_MANAGE_WRITE">
                    	<th width="150">操作</th>
                    </@sec.any>
                </tr>
            </thead>
            <tbody>
                <#list saleRecordPages.contents as saleRecord>
                <tr>
                    <td>${saleRecord.number}</td>
                    <td>${saleRecord.productName}</td>
                    <td>${saleRecord.accountName}</td>
                    <td>${saleRecord.specification}</td>
                    <td>${saleRecord.unit}</td>
					<td>${saleRecord.amount}</td>
					<td>${saleRecord.saleDate?date}</td>
					<td>${saleRecord.produceDate?date}</td>
					<td>${saleRecord.batchNumber}</td>
					<td>${saleRecord.manufacture}</td>
					<@sec.any name="SALES_MANAGE_WRITE">
                    <td>
                    	<@dwz.a href="/company/saleRecord-edit?saleRecord=${saleRecord.id}" target="dialog" title="编辑销售记录" height="SS">编辑</@dwz.a>
						|
						<@dwz.a href="/company/saleRecord-delete?saleRecord=${saleRecord.id}" target="ajaxTodo" title="确定要删除该销售记录吗？">删除</@dwz.a>
						|
						<@dwz.a href="/system/log-detail-list?entityId=${saleRecord.id}" target="dialog" title="查看操作记录">操作记录</@dwz.a>
					</td>
					</@sec.any>
                </tr>
                </#list>
            </tbody>
        </table>
        <div class="panelBar">
        	<@dwz.pageNav pageModel=orderPages />
        </div>
    </div>
</div>