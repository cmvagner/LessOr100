<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="f" uri="http://www.slim3.org/functions" %>
<c:import url="/layout.jsp">
    <c:param name="title" value="Servers"/>
    <c:param name="content">
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            google.load("visualization", "1", {packages:["corechart"]});
            function drawDiskUsageChart(elementId, used) {
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Type');
                data.addColumn('number', 'Percent');
                data.addRows(2);
                data.setValue(0, 0, 'Used');
                data.setValue(0, 1, used);
                data.setValue(1, 0, 'Free');
                data.setValue(1, 1, 100 - used);
                var chart = new google.visualization.PieChart(document.getElementById(elementId));
                chart.draw(data, {width:250, height:100, title:'Disk Usage', colors: ['blue', 'green']});
            }
        </script>
        <div class="row">
            <div class="span16">
                <h1>Servers</h1>
                <c:forEach var="server" items="${servers}">
                    <c:set var="mountGroup" value="${server.latestMountGroup}"/>
                    <h2>${f:h(server.name)} <fmt:formatDate value="${mountGroup.createdAt}" type="both" timeStyle="medium" dateStyle="medium"/></h2>
                    <table class="bordered-table zebra-striped">
                        <thead>
                        <tr>
                            <th>Mount</th>
                            <th>Usage In Percent</th>
                            <th>Chart</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="mount" items="${mountGroup.mountListRef.modelList}">
                            <tr>
                                <td style="vertical-align: middle;">${f:h(mount.mountedOn)}</td>
                                <td style="vertical-align: middle;">${f:h(mount.usageInPercent)}%</td>
                                <td id="${f:h(mount.key)}" style="vertical-align: middle;">
                                    <script type="text/javascript">
                                        drawDiskUsageChart('${f:h(mount.key)}', ${f:h(mount.usageInPercent)});
                                    </script>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </div>
    </c:param>
</c:import>