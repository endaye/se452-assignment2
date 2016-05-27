var req;
var isIE;
var completeField;
var completeTable;
var autoRow;

function init() {
    completeField = document.getElementById("complete-field");
    completeTable = document.getElementById("complete-table");
    autoRow = document.getElementById("auto-row");
    completeTable.style.top = getElementY(autoRow) + "px";
}

function doCompletion() {

    var url = "autocomplete?action=complete&id=" + encodeURI(completeField.value);
    req = initRequest();
    req.open("GET", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function callback() {
    clearTable();
    if (req.readyState == 4) {
        if (req.status == 200) {
            parseMessages(req.responseXML);
        }
    }
}

function parseMessages(responseXML) {
    // no matches returned
    if (responseXML == null) {
        return false;
    } else {

        var items = responseXML.getElementsByTagName("items")[0];

        if (items.childNodes.length > 0) {
            completeTable.setAttribute("bordercolor", "#aaa");
            completeTable.setAttribute("border", "0");

            for (loop = 0; loop < items.childNodes.length; loop++) {
                var item = items.childNodes[loop];
                var id = item.getElementsByTagName("id")[0];
                var type = item.getElementsByTagName("type")[0];
                var name = item.getElementsByTagName("name")[0];
                appendItem(id.childNodes[0].nodeValue,
                    type.childNodes[0].nodeValue,
                    name.childNodes[0].nodeValue);
            }
        }
    }
}

function appendItem(id, type, name) {

    var row;
    var cell;
    var linkElement;

    if (isIE) {
        completeTable.style.display = 'block';
        row = completeTable.insertRow(completeTable.rows.length);
        cell = row.insertCell(0);
    } else {
        completeTable.style.display = 'table';
        row = document.createElement("tr");
        cell = document.createElement("td");
        row.appendChild(cell);
        completeTable.appendChild(row);
    }

    cell.className = "popupCell";
    linkElement = document.createElement("a");
    linkElement.className = "popupItem";
    linkElement.setAttribute("href", "autocomplete?action=lookup&id=" + id + "&type=" + type);
    linkElement.appendChild(document.createTextNode(type + ": " + name));
    cell.appendChild(linkElement);

}

function clearTable() {
    if (completeTable.getElementsByTagName("tr").length > 0) {
        completeTable.style.display = 'none';
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

function getElementY(element){

    var targetTop = 0;

    if (element.offsetParent) {
        while (element.offsetParent) {
            targetTop += element.offsetTop;
            element = element.offsetParent;
        }
    } else if (element.y) {
        targetTop += element.y;
    }
    return targetTop;
}

