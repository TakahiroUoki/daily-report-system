<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_EMP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="${AttributeConst.CLI_NAME.getValue()}">氏名</label><br />
<input type="text" name="${AttributeConst.CLI_NAME.getValue()}" value="${client.name}" />
<br /><br />

<label for="${AttributeConst.CLI_DEPART.getValue()}">部署</label><br />
<input type="text" name="${AttributeConst.CLI_DEPART.getValue()}" value="${client.depart}" />
<br /><br />

<label for="${AttributeConst.CLI_DIVISION.getValue()}">課</label><br />
<input type="text" name="${AttributeConst.CLI_DIVISION.getValue()}" value="${client.division}" />
<br /><br />

<label for="${AttributeConst.CLI_POSITION.getValue()}">役職</label><br />
<select name="役職">
    <option value="">以下より選択</option>
    <option value="なし">なし</option>
    <option value="主任">主任</option>
    <option value="課長代理">課長代理</option>
    <option value="課長">課長</option>
    <option value="部長">部長</option>
    <option value="その他">その他</option>
</select>
<br /><br />

<input type="hidden" name="${AttributeConst.CLI_ID.getValue()}" value="${client.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>