<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="f" uri="http://www.slim3.org/functions" %>
<c:import url="/layout.jsp">
    <c:param name="title" value="Servers"/>
    <c:param name="content">
        <div class="row">
            <div class="span16">
                <h1>Servers</h1>
                <c:forEach var="server" items="${servers}">
                    <h2>${f:h(server.name)}</h2>
                    <table>
                        <thead>
                        <tr>
                            <th>Created At</th>
                            <th>Mount</th>
                            <th>Usage In Percent</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="mountGroup" items="${server.mountListRef.modelList}">
                            <c:forEach var="mount" items="${mountGroup.mountListRef.modelList}">
                                <tr>
                                    <td><fmt:formatDate value="${mountGroup.createdAt}" type="both" timeStyle="medium" dateStyle="medium"/></td>
                                    <td>${f:h(mount.mountedOn)}</td>
                                    <td>${f:h(mount.usageInPercent)}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </div>
    </c:param>
</c:import>