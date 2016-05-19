<!-- start #sidebar-->
<aside class="sidebar">
    <ul>
        <%
            if (session.getAttribute("usertype")!=null && session.getAttribute("usertype").toString().equals("manager")) {
        %>
        <%-- salesman sidebar --%>
        <li>
            <h4>Management</h4>
            <ul>
                <li id="first"><a href="AddNewUser">Add New User</a></li>
                <li><a href="AllOrderHistory">Order History</a></li>
            </ul>
        </li>
        <%
        } else {
        %>
        <%-- customer sidebar --%>
        <li>
            <h4>Search Product</h4>
            <ul>
                <li class="text">
                    <div name="autofillform">
                        <input type="text" name="complete-field" value="" class="input" id="complete-field" onkeyup="doCompletion()" autocomplete="off" placeholder="search here.." style="padding: 5px; font-size: 16px;" />
                        <div id="auto-row">
                        </div>
                    </div>
                </li>
            </ul>
        </li>
        <li>
            <h4>Consoles</h4>
            <ul>
                <li id="first"><a href="ConsoleList?maker=microsoft">Microsoft</a></li>
                <li><a href="ConsoleList?maker=sony">Sony</a></li>
                <li><a href="ConsoleList?maker=nintendo">Nintendo</a></li>
            </ul>
        </li>

        <li>
            <h4>Games</h4>
            <ul>
                <li><a href="GamesList?maker=electronicArts">Electronic Arts</a></li>
                <li><a href="GamesList?maker=activision">Activision</a></li>
                <li><a href="GamesList?maker=takeTwoInteractive">Take-Two Interactive</a></li>
            </ul>
        </li>
        <li>
            <h4>Tablets</h4>
            <ul>
                <li><a href="TabletList?maker=apple">Apple</a></li>
                <li><a href="TabletList?maker=microsoft">Microsoft</a></li>
                <li><a href="TabletList?maker=samsung">Samsung</a></li>
            </ul>
        </li>
        <%
            }
        %>
    </ul>
</aside>
<div class="clear"></div>
<!-- end #sidebar-->