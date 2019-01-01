<#macro medicineSearch searchModel=searchModel>
	<li>编号：</li><li><@s.input path="searchModel.number"/></li>
	<li>药品名称：</li><li><@s.input path="searchModel.name"/></li>
	
	<li>生产日期：</li>
	<li><@s.input path="searchModel.startDate" class="date" dateFmt="yyyy-MM-dd" readonly="true" /></li>
	<li> 至 </li>
	<li><@s.input path="searchModel.endDate" class="date" dateFmt="yyyy-MM-dd" readonly="true" minRelation="#startDate" /></li>
	
</#macro>	