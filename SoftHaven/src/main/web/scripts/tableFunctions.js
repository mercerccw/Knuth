function filter() {
    //Declare needed variables
    let port, quay, type, number, ship_imo, portFilter, quayFilter, typeFilter, numberFilter, ship_imoFilter, table,
        tr, tdP, tdQ, tdT, tdN, tdS, i;

    //Set inputs by getElementById
    port = document.getElementById('port');
    quay = document.getElementById('quay');
    type = document.getElementById('type');
    number = document.getElementById('number');
    ship_imo = document.getElementById('ship_imo');
    //Set filters
    portFilter = port.value.toUpperCase();
    quayFilter = quay.value.toUpperCase();
    typeFilter = type.value.toUpperCase();
    numberFilter = number.value.toUpperCase();
    ship_imoFilter = ship_imo.value.toUpperCase();
    //Set the table and tr variables
    table = document.getElementById("berths");
    tr = document.getElementsByClassName("data-row");

    //Loop through items and hide those that don't match the query -->
    for (i = 0; i < tr.length; i++) {
        tdP = tr[i].getElementsByTagName("td")[0];
        tdQ = tr[i].getElementsByTagName("td")[1];
        tdT = tr[i].getElementsByTagName("td")[2];
        tdN = tr[i].getElementsByTagName("td")[3];
        tdS = tr[i].getElementsByTagName("td")[4];

        if (tdP.textContent.toUpperCase().indexOf(portFilter) > -1 && tdQ.textContent.toUpperCase().indexOf(quayFilter) > -1 && tdT.textContent.toUpperCase().indexOf(typeFilter) > -1 && tdN.textContent.toUpperCase().indexOf(numberFilter) > -1 && tdS.textContent.toUpperCase().indexOf(ship_imoFilter) > -1) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }
    }

}