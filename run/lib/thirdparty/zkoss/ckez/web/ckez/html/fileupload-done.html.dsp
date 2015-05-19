<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %><%--
fileupload-done.html.dsp

	Purpose:
		The page after the user submits the fileupload dialog of the ckeditor.
	Description:

	History:
		Thu Apr 25 17:39:25     2012, Created by jimmyshiau

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under LGPL Version 2.1 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
--%><%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %>
<c:set var="arg" value="${requestScope.arg}"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<title>Upload Result</title>
${z:outDeviceStyleSheets('ajax')}
<%-- We cannot use ${z:outLangStyleSheets()} since Executions.getCurrent()
	is not available for this page.
 --%>
</head>
<body>
</body>
<script type="text/javascript">
<!--
	<%-- exec at the parent's scope --%>
	function exec(cmd) {
		parent.setTimeout(cmd, 0);
	}
	<c:if test="${!empty arg.path}">
		exec("window.parent.CKEDITOR.tools.callFunction(${arg.CKEditorFuncNum}, '${arg.path}');");
		<%-- Bug 1920877 --%>
	</c:if>
// -->
</script>
</html>
