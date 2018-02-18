<-- hibernate.cfg.xml --> 

		<mapping resource="${ package?replace(".","/") }/domain/${ className }.hbm.xml"/>


<-- applicationContext-service.xml --> 

	<bean id="${ className?uncap_first }Service" class="${ package }.service.impl.${ className }ServiceImpl">
		<property name="baseDao" ref="baseDao"/>
	</bean>


<-- applicationContext-action.xml --> 

	<bean id="${ className?uncap_first }Action" class="${ package }.action.cargo.${ className }Action" scope="prototype">
		<property name="${ className?uncap_first }Service" ref="${ className?uncap_first }Service"/>
	</bean>


<-- struts-xxx.xml --> 

		<action name="${ className?uncap_first }Action_*" method="{1}" class="${ className?uncap_first }Action">
			<result name="alist" type="redirect">${ className?uncap_first }Action_list</result>
			<result name="plist">/WEB-INF/pages/sysadmin/${ className?lower_case }/j${ className }List.jsp</result>
			<result name="pcreate">/WEB-INF/pages/sysadmin/${ className?lower_case }/j${ className }Create.jsp</result>
			<result name="pupdate">/WEB-INF/pages/sysadmin/${ className?lower_case }/j${ className }Update.jsp</result>
			<result name="pview">/WEB-INF/pages/sysadmin/${ className?lower_case }/j${ className }View.jsp</result>
		</action>
		
		
		