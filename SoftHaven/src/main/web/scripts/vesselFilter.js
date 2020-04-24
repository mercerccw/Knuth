function filterVessels() {
    //Declare needed variables
    let imo, flag, name, built, call_sign, length, breadth, tonnage, mmsi, type, owner_code, status,
        imoFilter, flagFilter, nameFilter, built_filter, call_signFilter, lengthFilter, breadthFilter, tonnageFilter, mmsiFilter, typeFilter, owner_codeFilter, statusFilter,
        table, tr, tdI, tdF, tdN, tdB, tdC, tdL, tdBR, tdT, tdM, tdTT, tdO, tdS, i;

    //Set inputs by getElementById
    imo = document.getElementById('imo');
    flag = document.getElementById('flag');
    name = document.getElementById('name');
    built = document.getElementById('built');
    call_sign = document.getElementById('call_sign');
    length = document.getElementById('length');
    breadth = document.getElementById('breadth');
    tonnage = document.getElementById('tonnage');
    mmsi = document.getElementById('mmsi');
    type = document.getElementById('type');
    owner_code = document.getElementById('owner_code');
    status = document.getElementById('status');
    //Set filters
    imoFilter = imo.value.toUpperCase();
    flagFilter = flag.value.toUpperCase();
    nameFilter = name.value.toUpperCase();
    built_filter = built.value.toUpperCase();
    call_signFilter = call_sign.value.toUpperCase();
    lengthFilter = length.value.toUpperCase();
    breadthFilter = breadth.value.toUpperCase();
    tonnageFilter = tonnage.value.toUpperCase();
    mmsiFilter = mmsi.value.toUpperCase();
    typeFilter = type.value.toUpperCase();
    owner_codeFilter = owner_code.value.toUpperCase();
    statusFilter = status.value.toUpperCase();
    //Set the table and tr variables
    table = document.getElementById("vessels");
    tr = document.getElementsByClassName("data-row");

    //Loop through items and hide those that don't match the query -->
    for (i = 0; i < tr.length; i++) {
        tdI = tr[i].getElementsByTagName("td")[0];
        tdF = tr[i].getElementsByTagName("td")[1];
        tdN = tr[i].getElementsByTagName("td")[2];
        tdB = tr[i].getElementsByTagName("td")[3];
        tdC = tr[i].getElementsByTagName("td")[4];
        tdL = tr[i].getElementsByTagName("td")[5];
        tdBR = tr[i].getElementsByTagName("td")[6];
        tdT = tr[i].getElementsByTagName("td")[7];
        tdM = tr[i].getElementsByTagName("td")[8];
        tdTT = tr[i].getElementsByTagName("td")[9];
        tdO = tr[i].getElementsByTagName("td")[10];
        tdS = tr[i].getElementsByTagName("td")[11];

        if (tdI.textContent.toUpperCase().indexOf(imoFilter) > -1
            && tdF.textContent.toUpperCase().indexOf(flagFilter) > -1
            && tdN.textContent.toUpperCase().indexOf(nameFilter) > -1
            && tdB.textContent.toUpperCase().indexOf(built_filter) > -1
            && tdC.textContent.toUpperCase().indexOf(call_signFilter) > -1
            && tdL.textContent.toUpperCase().indexOf(lengthFilter) > -1
            && tdBR.textContent.toUpperCase().indexOf(breadthFilter) > -1
            && tdT.textContent.toUpperCase().indexOf(tonnageFilter) > -1
            && tdM.textContent.toUpperCase().indexOf(mmsiFilter) > -1
            && tdTT.textContent.toUpperCase().indexOf(typeFilter) > -1
            && tdO.textContent.toUpperCase().indexOf(owner_codeFilter) > -1
            && tdS.textContent.toUpperCase().indexOf(statusFilter) > -1)
        {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }
    }
}