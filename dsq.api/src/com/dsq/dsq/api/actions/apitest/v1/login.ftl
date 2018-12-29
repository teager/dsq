<div class="page">
	<div class="pageContent">
		<@dwz.form action="/api/v1/login">
	    	<div class="pageFormContent" layoutH="56">
	    		<fieldset>
		            <legend>请求参数</legend>
		            <dl>
		                <dt>用户名:</dt>
		                <dd><@s.input path="loginQuery.username" class="required" value="admin" /></dd>
		            </dl>
		            <dl>
		                <dt>密码:</dt>
		                <dd><@s.password path="loginQuery.password" class="required" value="admin"/></dd>
		            </dl>
		        </fieldset>
		        <fieldset>
			    	<legend>响应结果</legend>
			    	<textarea cols="120" layoutH="200"></textarea>
			    </fieldset>
	    	</div>
	    	<@dwz.formBar showSubmitBtn=false showCancelBtn=false>
	    		<@dwz.button text="测试.json请求" onclick="apitestSubmit(this,'json')" />
	    		<@dwz.button text="测试.xml请求" onclick="apitestSubmit(this,'xml')" />
	        </@dwz.formBar>
	    </@dwz.form>
    </div>
</div>