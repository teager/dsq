<div class="accordion" fillSpace="sidebar">
    <div class="accordionHeader">
        <h2 class="fa-users">销售管理</h2>
    </div>
    <div class="accordionContent">
        <ul class="tree">
        <#-- 
            <@sec.any name="COMPANY_MANAGE">
	            <li><@dwz.a href="/company/company-list">公司管理</@dwz.a></li>
            </@sec.any>
            <@sec.any name="EMPLOYEE_MANAGE">
	            <li><@dwz.a href="/company/employee-list">职员管理</@dwz.a></li>
            </@sec.any>
        -->
            <@sec.any name="SALES_MANAGE_READ">
	            <li><@dwz.a href="/company/saleRecord-list">销售流向</@dwz.a></li>
            </@sec.any>
            <@sec.any name="MEDICINE_MANAGE_READ">
	            <li><@dwz.a href="/company/medicine-list">库存查询</@dwz.a></li>
            </@sec.any>
        </ul>
    </div>
    
</div>
