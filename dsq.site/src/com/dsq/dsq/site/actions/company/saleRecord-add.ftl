<div class="page">
    <div class="pageContent">
        <@dwz.form action="/company/saleRecord-save">
	        <div class="pageFormContent" layoutH="60">
	            <dl>
	                <dt>编号：</dt>
	                <dd><@s.input path="saleRecord.number" maxlength="60" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>产品名称：</dt>
	                <dd><@s.input path="saleRecord.productName" maxlength="60" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>客户名称 ：</dt>
	                <dd><@s.input path="saleRecord.accountName" maxlength="60" class="required" /></dd>
	            </dl>
	             <dl>
	                <dt>商品规格：</dt>
	                <dd><@s.input path="saleRecord.specification" maxlength="60" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>销售数量：</dt>
	                <dd><@s.input path="saleRecord.amount" maxlength="60" class="required digits" /></dd>
	            </dl>
	            <dl>
	                <dt>包装单位：</dt>
	                <dd><@s.input path="saleRecord.unit" maxlength="60" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>销售日期：</dt>
	                <dd><@s.input path="saleRecord.saleDate" class="required date" /></dd>
	            </dl>
	            <dl>
	                <dt>生产日期：</dt>
	                <dd><@s.input path="saleRecord.produceDate" class="required date" /></dd>
	            </dl>
	            <dl class="nowrap">
	                <dt>生产厂家：</dt>
	                <dd><@s.input path="saleRecord.manufacture" maxlength="60" class="l-input" /></dd>
	            </dl>
	        </div>
	        <@dwz.formBar />
        </@dwz.form>
    </div>
</div>