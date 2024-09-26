// "Graphically display the ratio of dirty vs clean data through the UI."
// charts tab

// stretch goal: custom tables?
$(document).ready(function () {
    dataChart();
});
function dataChart(){
    let chartStatus = Chart.getChart("pieChart1");
    if (chartStatus != undefined) {
        chartStatus.destroy();
    }
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            let labels = ['Clean', 'Dirty'];
            let data = [0, 0];
            for(let i = 0; i < response.length; i++){
                if (response[i] != null) {
                    data[0]++
                } else {
                    data[1]++
                }
            }
            let ctx = document.getElementById('pieChart1').getContext('2d');
            let myChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Dirty vs Clean Data',
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

        xhr.open("GET", "http://localhost:8080/ETL-E-Commerce/facts", true);
        xhr.send();
}
