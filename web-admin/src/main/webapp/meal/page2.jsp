<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<FORM METHOD="post" ACTION="mealController?action=listAll">
    <%if (rowsPerPage < rowNumber) {%>
    <%if (pageIndex >= rowsPerPage) {%>
    <A href="mealController?action=listAll&whichPage=1">至第一頁</A>&nbsp;
    <A href="mealController?action=listAll&whichPage=<%=whichPage-1%>">上一頁 </A>&nbsp;
    <%}%>
    <%if (pageNumber > 1) {%>
    <select size="1" name="whichPage">
        <%for (int i = 1; i <= pageNumber; i++) {%>
        <option value="<%=i%>">跳至第<%=i%>頁
                <%}%>
    </select>
    <input type="submit" value="確定">
    <%}%>
    <%if (pageIndex < pageIndexArray[pageNumber - 1]) {%>
    <A href="mealController?action=listAll&whichPage=<%=whichPage+1%>">下一頁 </A>&nbsp;
    <A href="mealController?action=listAll&whichPage=<%=pageNumber%>">至最後一頁</A>&nbsp;
    <%}%>
    <%}%>

</FORM>

<br><br>

