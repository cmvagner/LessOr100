<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="f" uri="http://www.slim3.org/functions" %>
<c:import url="/layout.jsp">
    <c:param name="title" value="Thresholds"/>
    <c:param name="content">
        <div class="row">
            <div class="span16">
                <h1>Threshold</h1>

                <form method="post" action="${f:url('create')}">
                    <fieldset>
                        <legend>New threshold</legend>
                        <div class="clearfix">
                            <label for="serversSelect">Server</label>

                            <div class="input">

                                <select name="server" id="serversSelect">
                                    <c:forEach items="${servers}" var="server">
                                        <option ${f:select("server", f:h(server.key))}>${f:h(server.name)}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="${f:errorClass("mount", "clearfix error")}">
                            <label for="xlInput">Mount</label>

                            <div class="input">
                                <input class="${f:errorClass("mount", "xlarge error")}" id="xlInput" ${f:text("mount")} size="30" type="text"/><span class="help-inline">${f:h(errors.mount)}</span>
                            </div>
                        </div>
                        <div class="clearfix">
                            <label for="thresholdSelect">Threshold</label>

                            <div class="input">
                                <select name="threshold" id="thresholdSelect">
                                    <option>10</option>
                                    <option>20</option>
                                    <option>30</option>
                                    <option>40</option>
                                    <option>50</option>
                                    <option>60</option>
                                    <option>70</option>
                                    <option>80</option>
                                    <option>90</option>
                                    <option>99</option>
                                </select>
                            </div>
                        </div>
                        <div class="actions">
                            <input type="submit" class="btn primary" value="Save changes">&nbsp;
                            <button type="reset" class="btn">Cancel</button>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </c:param>
</c:import>