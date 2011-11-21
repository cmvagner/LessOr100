<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="f" uri="http://www.slim3.org/functions" %>
<c:import url="/layout.jsp">
    <c:param name="title" value="LessOr100"/>
    <c:param name="content">
        <div class="row">
            <div class="span16">
                <h1>Emails</h1>

                <h2>Outgoing</h2>
                <table>

                    <thead>
                    <tr>
                        <th>Created At</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Subject</th>
                        <th>Body</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="email" items="${outgoingEmails}">
                        <tr>
                            <td><fmt:formatDate value="${email.createdAt}" type="both" timeStyle="medium" dateStyle="medium"/></td>
                            <td>${f:h(email.from)}</td>
                            <td>${f:h(email.to)}</td>
                            <td>${f:h(email.subject)}</td>
                            <td>${f:h(email.body)}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:param>
</c:import>