const searchInput = document.getElementById("searchInput");
const dateTable = document.getElementById("dataTable").getElementsByTagName("tbody")[0].getElementsByTagName("tr");

searchInput.addEventListener("input", function (){
    const searchValue = searchInput.value.toLowerCase();
    for(let i = 0; i < dataTable.length; i++) {
        const row = dateTable[i];
        const searchDate = row.getAttribute("date-search").toLowerCase();
        if (searchDate.includes(searchValue)){
            row.style.display = "";
        }else{
            row.style.display = "none";
        }
    }
});