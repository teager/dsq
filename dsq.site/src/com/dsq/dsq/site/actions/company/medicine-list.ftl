<div class="page">
	<div class="pageHeader">
        <@dwz.pageForm action="/company/medicine-list" showKeyword=false>
        	<@medicine.medicineSearch searchModel=searchModel />
        </@dwz.pageForm>
    </div>
    <div class="pageContent">
    	<@sec.any name="MEDICINE_MANAGE_WRITE">
	        <div class="panelBar">
	            <ul class="toolBar">
	                <li><@dwz.a href="/company/medicine-add" target="dialog" title="新增公司" height="SS"><span class="fa-plus">新增</span></@dwz.a></li>
	            </ul>
	        </div>
        </@sec.any>
        <table class="table" width="100%" layoutH="75">
            <thead>
                <tr>
                    <th width="120">药品名称</th>
                    <th width="120">库存数量</th>
                    <th width="120">商品规格</th>
                    <th width="120">包装单位</th>
                    <th width="100" align="center">生产日期</th>
                    <th width="100" align="center">失效日期</th>
                    <th width="120">批号</th>
					<th width="120" align="center">仓库名称</th>
					<#-- <th width="60" align="center">状态</th> -->
					<@sec.any name="MEDICINE_MANAGE_WRITE">
                    <th width="150">操作</th>
                    </@sec.any>
                </tr>
            </thead>
            <tbody>
                <#list medicinePages.contents as medicine>
                <tr>
                    <td>${medicine.name}</td>
                    <td>${medicine.amount}</td>
                    <td>${medicine.specification}</td>
                    <td>${medicine.unit}</td>
					<td>${medicine.produceDate?date}</td>
					<td>${medicine.expireDate?date}</td>
					<td>${medicine.number}</td>
					<td>${medicine.storeName}</td>
					<@sec.any name="MEDICINE_MANAGE_WRITE">
                    <td>
                    	<@dwz.a href="/company/medicine-edit?medicine=${medicine.id}" target="dialog" title="编辑库存" height="SS">编辑</@dwz.a>
						|
						<@dwz.a href="/company/medicine-delete?medicine=${medicine.id}" target="ajaxTodo" title="确定要删除该库存吗？">删除</@dwz.a>
						|
						<@dwz.a href="/system/log-detail-list?entityId=${medicine.id}" target="dialog" title="查看操作记录">操作记录</@dwz.a>
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