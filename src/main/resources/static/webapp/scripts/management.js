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

$(document).ready(function () {
    getAllFacts();
});
let factsArray = [];
function getAllFacts(){
    let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            console.log("-----------Begin-----------------")
            if (xhr.readyState == 4 && xhr.status == 200) {
                let response = JSON.parse(xhr.responseText);
                console.log(response);
//                 for(let i = 0; i < response.length; i++){
//                     factsArray.push(response[i]);
//                 }
                factsArray = response;
                console.log(factsArray);
                getByCountry();
            }
        }

        xhr.open("GET", "http://localhost:8080/ETL-E-Commerce/facts", true);
        xhr.send();
       getTxnChartData();
       getTypeChartData();
        
}

// -----------------------------------------------------
// Table 1: Get Payment Type & Sales Revenue by Country
// -----------------------------------------------------
function getByCountry(){
    mainMap=createTable(factsArray);
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
        "Total Potential Revenue",
        "Total Realized Revenue", // arrow
        "Top Product Sold", // arrow
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


    let mgmtObjectArray = factsArray;
    // Create table arrays with no duplicate countries
  

    
    // Create table body
    for (let key of mainMap.keys()) {
        
        let row = document.createElement("tr");
        let country = document.createElement("td");
        country.innerHTML = key;
        
        row.appendChild(country);
        let paymentType = document.createElement("td");
        paymentType.innerHTML = mainMap.get(key).get("TopPaymentType");
        
        row.appendChild(paymentType);
        let revenue = document.createElement("td");
        revenue.innerHTML = round(mainMap.get(key).get("Revenue"));
       
        row.appendChild(revenue);

        let realRevenue = document.createElement("td");
        realRevenue.innerHTML = round(mainMap.get(key).get("Revenue")*(mainMap.get(key).get("TransSuc")/(mainMap.get(key).get("TransSuc")+mainMap.get(key).get("TransFail"))));
       
        row.appendChild(realRevenue);

        let productSold = document.createElement("td");
        productSold.innerHTML = mainMap.get(key).get("TopProductType");
        
        row.appendChild(productSold);
        let txnSuccess = document.createElement("td");
        txnSuccess.innerHTML = round(((mainMap.get(key).get("TransSuc")/(mainMap.get(key).get("TransSuc")+mainMap.get(key).get("TransFail")))*100));
        
        row.appendChild(txnSuccess);
        let totalOrders = document.createElement("td");
        totalOrders.innerHTML = mainMap.get(key).get("TotalOrder");
        

        row.appendChild(totalOrders);
        table.append(row);

   
    
//    sorttable.makeSortable(table);

}
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
            console.log(response)
            arr = paymentType1(response);
            console.log(arr);

            let ctx = document.getElementById('barChart1').getContext('2d');
            let myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: arr[0],
                    datasets: [{
                        label: 'Payment Types',
                        data: arr[1],
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

function paymentType1(data){
    labelArr=[]
    barHeightArr=[]
    centralArray=[]
    packagedArray=[]
    theMap=new Map();
    for( i=0;i<data.length;i++){
        theMap.set(data[i]['paymentType'],0)

    }
    console.log(theMap)
    for( i=0;i<data.length;i++){
       
        theMap.set(data[i]['paymentType'],theMap.get(data[i]['paymentType'])+1)
        

    }
    const iterator1 = theMap.entries();

    for(const val of iterator1){
        arr1=[]
        arr1.push(val[0])
        arr1.push(val[1])
        centralArray.push(arr1)

    }
    newarr=centralArray.sort(function compare(a,b){
        if(a[1]>b[1]){
            return 1
        }else if(a[1]<b[1]){
            return -1
        }else{
            return 0
        }

    })
    for(i=0;i<newarr.length;i++){
        labelArr.push(newarr[i][0])
        barHeightArr.push(newarr[i][1])
    }
    packagedArray.push(labelArr)
    packagedArray.push(barHeightArr)
    return packagedArray;

}


function createTable(data){
    map=new Map();
    innerMap0=new Map();
    innerMap1=new Map();
    innerMap2=new Map();
    startVal=0;
    topPymntType=''
    topProductType=''
    for(i=0; i<data.length;i++){
        if(map.get(data[i].customerEntity.country)==undefined){
        map.set(data[i].customerEntity.country,new Map())}
        map.get(data[i].customerEntity.country).set('Revenue',0)
        map.get(data[i].customerEntity.country).set('TransSuc',0)
        map.get(data[i].customerEntity.country).set('TransFail',0)
        map.get(data[i].customerEntity.country).set('TotalOrder',0)

        if(map.get(data[i].customerEntity.country).get('Products')==undefined){
            map.get(data[i].customerEntity.country).set('Products',new Map())}
            map.get(data[i].customerEntity.country).get('Products').set(data[i].productEntity.productName,0)

        if(map.get(data[i].customerEntity.country).get('PaymentType')==undefined){
            map.get(data[i].customerEntity.country).set('PaymentType',new Map())}
            map.get(data[i].customerEntity.country).get('PaymentType').set(data[i].paymentEntity.paymentType,0)
        
        
        
    }
    console.log(map)
    for(i=0; i<data.length;i++){
        
       
        map.get(data[i].customerEntity.country).set('Revenue',
        map.get(data[i].customerEntity.country).get('Revenue')+
        data[i].orderEntity.price*data[i].orderEntity.qty)


            
        if(data[i].paymentEntity.paymentTxnSuccess=='Y'){
            map.get(data[i].customerEntity.country).set('TransSuc',
            map.get(data[i].customerEntity.country).get('TransSuc')+1) 
        }else{
            map.get(data[i].customerEntity.country).set('TransFail',
            map.get(data[i].customerEntity.country).get('TransFail')+1) 
        }

        
        map.get(data[i].customerEntity.country).set('TotalOrder',
        map.get(data[i].customerEntity.country).get('TotalOrder')+data[i].orderEntity.qty)

        map.get(data[i].customerEntity.country).get('PaymentType').set(data[i].paymentEntity.paymentType,
        map.get(data[i].customerEntity.country).get('PaymentType').get(data[i].paymentEntity.paymentType)+1)

        map.get(data[i].customerEntity.country).get('Products').set(data[i].productEntity.productName,
            map.get(data[i].customerEntity.country).get('Products').get(data[i].productEntity.productName)+1)
        
        
    }
    for(i=0;i<data.length;i++){
        for(let key of map.get(data[i].customerEntity.country).get('Products').keys()){
            if(map.get(data[i].customerEntity.country).get('Products').get(key)>startVal){
                topProductType=key
                startVal=map.get(data[i].customerEntity.country).get('Products').get(key)
            }


        }
        map.get(data[i].customerEntity.country).set('TopProductType',topProductType)
        topProductType=''
        startVal=0

  
    
}
for(i=0;i<data.length;i++){
    for(let key of map.get(data[i].customerEntity.country).get('PaymentType').keys()){
        if(map.get(data[i].customerEntity.country).get('PaymentType').get(key)>startVal){
            topPaymntType=key
            startVal=map.get(data[i].customerEntity.country).get('PaymentType').get(key)
        }


    }
    map.get(data[i].customerEntity.country).set('TopPaymentType',topPaymntType)
    topPaymentType=''
    startVal=0



}
console.log(map)

return map;


}


function round(num) {
    var m = Number((Math.abs(num) * 100).toPrecision(15));
    return Math.round(m) / 100 * Math.sign(num);
}

