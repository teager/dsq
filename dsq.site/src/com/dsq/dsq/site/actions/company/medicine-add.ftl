<div class="page">
    <div class="pageContent">
        <@dwz.form action="/company/medicine-save">
	        <div class="pageFormContent" layoutH="60">
	            <dl>
	                <dt>编号：</dt>
	                <dd><@s.input path="medicine.number" maxlength="60" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>名称：</dt>
	                <dd><@s.input path="medicine.name" maxlength="60" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>库存数量：</dt>
	                <dd><@s.input path="medicine.amount" maxlength="60" class="required digits" /></dd>
	            </dl>
	            <dl>
	                <dt>商品规格：</dt>
	                <dd><@s.input path="medicine.specification" maxlength="60" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>包装单位：</dt>
	                <dd><@s.input path="medicine.unit" maxlength="60" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>生产日期：</dt>
	                <dd><@s.input path="medicine.produceDate" class="required date" /></dd>
	            </dl>
	            <dl>
	                <dt>失效日期：</dt>
	                <dd><@s.input path="medicine.expireDate" class="required date" /></dd>
	            </dl>
	            <dl class="nowrap">
	                <dt>仓库名称：</dt>
	                <dd><@s.input path="medicine.storeName" maxlength="60" class="l-input" /></dd>
	            </dl>
	        </div>
	        <@dwz.formBar />
        </@dwz.form>
    </div>
</div>