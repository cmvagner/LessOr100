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

                <h2>Incoming</h2>
                <table class="bordered-table zebra-striped">

                    <thead>
                    <tr>
                        <th>Created At</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Subject</th>
                        <th>Body</th>
                        <th>Attachments</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="email" items="${incomingEmails}">
                        <tr>
                            <td><fmt:formatDate value="${email.createdAt}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                            <td>${f:h(email.from)}</td>
                            <td>${f:h(email.to)}</td>
                            <td>${f:h(email.subject)}</td>
                            <td>${f:h(email.body)}</td>
                            <td>
                                <c:forEach var="attachment" items="${email.attachmentListRef.modelList}">
                                    <a href="#">
                                        <img src="/images/document_attachment.png" title="${attachment.fileName}" alt="${attachment.fileName}"/><br/>
                                            ${attachment.fileName}
                                    </a>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>

            </div>
        </div>
    </c:param>
</c:import>