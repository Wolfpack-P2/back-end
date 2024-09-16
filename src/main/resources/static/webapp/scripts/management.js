//Management Questions
// Charts
// What is the most common payment type? Table/barchart/pie chart?
// What percentage of all transactions fail to process? Pie Chart
// Tables
//Not a question: show all from data mart
//What is the most common payment type per country? table
//Which three countries have the most sales revenue? table (switch with button)

// Extras in table
// Transaction Success Rates by Country
// Total Orders by country
// Total Product Sold by country
// Top transaction Fail reason by country? stretch goal

//properties needed:
    // product: price
    // payment: id?
    // payment: paymentType
    // payment: paymentTxnSuccess
    // payment: failureReason?
    // order: qty
    // customer: country




// ---------------------------
// Event Handlers for Buttons
// ---------------------------
//document.addEventListener("load", getFactsFactory);
//document.addEventListener("load", getMgmtMart); // load mgmt mart
//document.getElementById("getMgmtMart").addEventListener("load", getMgmtMart);
//document.getElementById("getByCountry").addEventListener("click", getByCountry)
//document.getElementById("SalesByCountry").addEventListener("click", getSalesByCountry);

// -------------------------------
// Get All Facts
// -------------------------------
let factsArray = [];
function getAllFacts(){
    let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let facts = JSON.parse(xhr.responseText);
                for(i = 0; i <= facts.length; i++){
                    factsArray.push(facts[i]);
                }

            }
        }

        xhr.open("GET", "http://localhost:8080/ETL-E-Commerce/facts", true);
        xhr.send();
        getTxnChartData();
        getTypeChartData();
        getByCountry();
}
console.log(factsArray);

// -----------------------------------------------------
// Table 1: Get Payment Type & Sales Revenue by Country
// -----------------------------------------------------
function getByCountry(){
//     aggregate payment type
//     aggregate quantity (total Sales)
//     aggregate Revenue
    let x = document.getElementById("mgmtStatsTable");
    x.remove();

    let table = document.createElement("table");
    table.setAttribute("id", "mgmtStatsTable");
    table.setAttribute("class", "table table-striped table-bordered table-sort sortable");
    let thead = document.createElement("thead");
    thead.setAttribute("id", "thead");
    let tbody = document.createElement("tbody");
    tbody.setAttribute("id", "tbody");
    table.appendChild(thead);
    table.appendChild(tbody);
    document.getElementById("mgmtDiv").appendChild(table);

    // Create table header array
    let header = [
        "Country",
        "Top Payment Type",
        "Revenue", // arrow
        "Product Sold", // arrow
        "Transaction Success Rates", // arrow
        "Total Orders",
        //"Top Transaction Failure Reason"
    ];

    // Create table header row
    let headerRow = document.createElement("tr");
    header.forEach(function (header) {
        let th = document.createElement("th");
        th.setAttribute("style", "cursor: pointer");
        th.innerHTML = header;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);

//    let mgmtObjectArray = [];
//    for (let i = 0; i < response.length; i++) {
//        let mgmtObject = response[i];
//        mgmtObjectArray.push(mgmtObject);
//    }
    let mgmtObjectArray = factsArray;
    // Create table arrays with no duplicate countries
    let countries = [];
    let topPaymentType = [];
    let revenue = [];
    let productSold = [];
    let txnSuccess = [];
    let txnFailed = [];
    let totalOrders = [];
    //let topTxnFailReason = [];
    for (let i = 0; i < mgmtObjectArray.length; i++) {
        if (countries.indexOf(mgmtObjectArray[i].country) == -1) {
            countries.push(mgmtObjectArray[i].country);

            topPaymentType.push(0);
            revenue.push(0);
            productSold.push(0);
            txnSuccess.push(0);
            txnFailed.push(0);
            totalOrders.push(0);
           // topTxnFailReason.push(0);

            // count the number of payment types for each country and push the top payment type to payment array
            // Stretch goal: count number of failure reasons for each country and push top failure reason to failure array
            let paymentTypeCount = {};
            // let topTxnFailReasonCount = {};
            for(let i = 0; j < mgmtObjectArray.length; j++){
                if (mgmtObjectArray[j].country == countries[countries.length - 1]) {
                    if (paymentTypeCount[mgmtObjectArray[j].paymentType] == undefined) {
                    paymentTypeCount[mgmtObjectArray[j].paymentType] = 1;
                    }else {
                    paymentTypeCount[mgmtObjectArray[j].paymentType]++;
                    }// format for TopTxnFailReason
//                    if (paymentTypeCount[mgmtObjectArray[j].paymentType] == undefined) {
//                    paymentTypeCount[mgmtObjectArray[j].paymentType] = 1;
//                    }else {
//                    paymentTypeCount[mgmtObjectArray[j].paymentType]++;
//                    }
                }
            }
            let topPT = "";
            let topPaymentTypeCount = 0;
            for (let key in topPaymentTypeCount) {
                if (paymentTypeCount[key] > topPaymentTypeCount) {
                    topPT = key;
                    topPaymentTypeCount = paymentTypeCount[key];
                }
            }
            paymentType.push(topPT);
            totalOrders.push(0);
        }

        let countryIndex = countries.indexOf(mgmtObjectArray[i].country);
        let totalProductSold = productSold.indexOf(mgmtObjectArray[i].qty);
        let totalRevenue = revenue.indexOf(mgmtObjectArray[i].price);
        if (mgmtObjectArray[i].txnStatus == "Y") {
            txnSuccess[countryIndex]++;
            totalProductSold = totalProductSold + mgmtObjectArray[i].qty;
            totalRevenue = totalRevenue + mgmtObjectArray[i].price;

        } else {
            txnFailed[countryIndex]++;
        }
        totalOrders[countryIndex]++;

    }
    // Create table body
    for (let i = 0; i < countries.length; i++) {
        let row = document.createElement("tr");
        let country = document.createElement("td");
        country.innerHTML = countries[i];
        row.appendChild(country);
        let paymentType = document.createElement("td");
        paymentType.innerHTML = topPaymentType[i];
        row.appendChild(paymentType);
        let revenue = document.createElement("td");
        revenue.innerHTML = revenue[i];
        row.appendChild(revenue);
        let productSold = document.createElement("td");
        productSold.innerHTML = productSold[i];
        row.appendChild(productSold);
        let txnSuccess = document.createElement("td");
        txnSuccess.innerHTML = txnSuccess[i];
        row.appendChild(txnSuccess);
        let totalOrders = document.createElement("td");
        totalOrders.innerHTML = totalOrders[i];
        row.appendChild(totalOrders);

    }
//    sorttable.makeSortable(table);

}



// -------------------------------------------
// Pie Chart for Transaction Success/Failure
// -------------------------------------------
function getTxnChartData(){
    //     Transactions failed:
    //     access fail/success
    //     calculate percentage
    //     Display in pie chart

    
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            let labels = [];
            let data = [];
            for (let i = 0; i < response.length; i++) {
                if (labels.indexOf(response[i].paymentTxnSuccess) == -1) {
                    labels.push(response[i].paymentTxnSuccess);
                    data.push(1);
                } else {
                    data[labels.indexOf(response[i].paymentTxnSuccess)]++;
                }
            }
                let ctx = document.getElementById('pieChart1').getContext('2d');
                let myChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'Number of Transactions',
                            data: data,
                            backgroundColor: ['rgba(0, 182, 0, .75)', 'rgba(237, 20, 0, .75)'],
                            borderColor: 'rgba(0, 0, 0, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        scales: {
                            y: {
                                display: false,
                                beginAtZero: true
                            }
                        },
                    }
                });
            }
        }
        xhr.open("GET", "http://localhost:8080/ETL-E-Commerce/payments", true);
        xhr.send();
    
}
    
// ------------------------------
// Pie Chart for Payment Types?
// ------------------------------
function getTypeChartData(){
    // create an array of unique payment types
    // count how many types there are
    let chartStatus = Chart.getChart("barChart1");
    if (chartStatus != undefined) {
        chartStatus.destroy();
    }
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            let labels = [];
            let data = [];
            let paymentTypeCount = {};
            let topPaymentType = [];
            let paymentType = [];
            for (let i = 0; i < response.length; i++) {
                // if the paymentType at index i of response is not in paymentTypeCount, include that payment type and add 1
                if (paymentTypeCount[response[i].paymentType] == undefined) {
                paymentTypeCount[response[i].paymentType] = 1;
                }else {
                // if it is, just add 1 to the corresponding paymentType count
                paymentTypeCount[response[i].paymentType]++;
                }
                // find the top 5? most popular payment types
                let topPT = "";
                let topPaymentTypeCount = 0;
                for (let key in topPaymentTypeCount) {
                    if (paymentTypeCount[key] > topPaymentTypeCount) {
                        topPT = key;
                        topPaymentTypeCount = paymentTypeCount[key];
                    }
                }
                paymentType.push(topPT);

//                 push labels in paymentType to labels
//                 push data in paymentTypeCount to Data

                if (labels.indexOf(paymentType[i]) == -1) {
                    labels.push(paymentType[i].paymentType);
                    data.push(1);
                } else {
                    data[labels.indexOf(paymentType[i].paymentType)]++;
                }
            }

            let ctx = document.getElementById('barChart1').getContext('2d');
            let myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Payment Types',
                        data: data,
                        backgroundColor: ['rgba(0, 182, 0, .75)', 'rgba(237, 20, 0, .75)'],
                        borderColor: 'rgba(0, 0, 0, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        y: {
                            display: false,
                            beginAtZero: true
                        }
                    },
                }
            });
        }
    }
    xhr.open("GET", "http://localhost:8080/ETL-E-Commerce/payments", true);
    xhr.send();

}











