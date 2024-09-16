//Marketing Questions
// CHARTS
// What is the top selling category of items?
// How does the popularity of products change throughout the year? (by most popular product each month) line graph
// What times have the highest traffic of sales?

// TABLES
//What is the top selling category of items Per Country? (country table- most popular item)
//How does the popularity of products change throughout the year Per Country? (country table - view button)
//Which locations see the highest traffic of sales?(country table - highest performing city)
//What times have the highest traffic of sales? Per Country? (country table-highest traffic)
function $(x) {
    return document.getElementById(x)
}

let catUrl="http://localhost:8080/ETL-E-Commerce/order/category"
/* function drawChart() {
    // Define the chart to be drawn.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Element');
    data.addColumn('number', 'Percentage');
    data.addRows([
      ['Nitrogen', 0.78],
      ['Oxygen', 0.21],
      ['Other', 0.01]
    ]);

    var options = {
        backgroundColor: '#1b232e'
    }

    // Instantiate and draw the chart.
    var chart = new google.visualization.PieChart($('my-pie-chart'));
    chart.draw(data, options);
  } */

  function drawDataComp() {
    let chartData = new google.visualization.arrayToDataTable([
      ['Data', 'Clean', 'Dirty'],
      [1000, 732, 268]
    ])

    let chart = new google.visualization.BarChart(document.getElementById('bar-div'));
    let options = {
      bars: 'horizontal',
      width: '900',
      backgroundColor: '#363c4d',
      hAxis: {
        textStyle: {
          color: '#ffffff'
        }
      },
      vAxis: {
        textStyle: {
          color: '#ffffff'
        }
      },
      legend: {
        textStyle: {
          color: '#ffffff'
        }
      }
    }

    chart.draw(chartData, options)
  }

  async function drawColumnChart() {

    let chartData = new google.visualization.DataTable();
    chartData.addColumn('string', 'Product Category');
    chartData.addColumn('number', 'Order Volume');

    await fetch(catUrl)
    .then((resp) => {return resp.json()})
    .then(data => {data.forEach(element => {
        chartData.addRow([element.product_category, element.count])});
    })
    .catch(error => {console.log(error)})
                      
    var chart = new google.visualization.ColumnChart(document.getElementById('column-div'));
    
    let options = {
      title: "Order Volume per Product Category",
      titleTextStyle: {
        color: '#ffffff'
      },
      backgroundColor: '#363c4d',
      width: '1000',
      height: '450',
      hAxis: {
        textStyle: {
          color: '#ffffff'
        }
      },
      vAxis: {
        textStyle: {
          color: '#ffffff'
        }
      },
      legend: {
        textStyle: {
          color: '#ffffff'
        }
      },
      annotations: {
        textStyle: {
          fontSize: 10
        }
      }
    }

    chart.draw(chartData, options);


  }

  async function drawTable() {
    await fetch(catUrl)
    .then(resp => {
      return resp.json()
    })
    .then(data => {
      data.forEach(element => {
        let tr = document.createElement('tr')
        let th = document.createElement('th')
        th.innerHTML = element.product_category
        let td = document.createElement('td')
        td.innerHTML = element.count
        tr.append(th)
        tr.append(td)
        $('category-table').append(tr)
      })
    })
  }

  async function drawRegionsMap() {


    let chartData = new google.visualization.DataTable();
    chartData.addColumn('string', 'Country');
    chartData.addColumn('string', 'Product Category');
    chartData.addColumn('number', 'Order Volume');
      

    await fetch ('http://localhost:8080/ETL-E-Commerce/order/category/country')
    .then(resp => {
      return resp.json()
    })
    .then(data => {
      data.forEach(element => {
        chartData.addRow([element.country, element.product_category, element.count])
      })
    })
    .catch(error => {
      console.log(error)
    })

    var options = {
        backgroundColor: '#363c4d',
        width: '900',
        height: '450'
    };

    var chart = new google.visualization.GeoChart(document.getElementById('regions-div'));

    chart.draw(chartData, options);
  }

  async function drawBarChart() {
    let chartData = [
      ['Month', 'Baby', 'Clothing', 'Electronics', 'Grocery', 'Home Essentials', 'Home Improvement', 'Patio & Garden'],
    ['Jan'],
    ['Feb'],
    ['Mar'],
    ['Apr'],
    ['May'],
    ['Jun'],
    ['Jul'],
    ['Aug'],
    ['Sep'],
    ['Oct'],
    ['Nov'],
    ['Dec']
    ]
    await fetch('http://localhost:8080/ETL-E-Commerce/order/year')
    .then(resp => {
      return resp.json()
    })
    .then(data => {
      data.forEach(element => {
       chartData[parseInt(element.month)].push(element.count)
      })
    })

    let viz = google.visualization.arrayToDataTable(chartData)

      

      var options = {
       backgroundColor: { fill: "#363c4d" },
        width: '1050',
        height: '500',
        hAxis: {
        textStyle: {
          color: '#ffffff'
        }
      },
      vAxis: {
        textStyle: {
          color: '#ffffff'
        }
      },
      legend: {
        textStyle: {
          color: '#ffffff'
        }
      },
      annotations: {
        textStyle: {
          fontSize: 10
        }
      }
      };

      var chart = new google.charts.Bar(document.getElementById('step-div'));
      chart.draw(viz, google.charts.Bar.convertOptions(options));
    }

    async function drawGeoChart() {


      let chartData = new google.visualization.DataTable();
      chartData.addColumn('string', 'Country');
      chartData.addColumn('number', 'Order Volume');
        
  
      await fetch ('http://localhost:8080/ETL-E-Commerce/order/sales/country')
      .then(resp => {
        return resp.json()
      })
      .then(data => {
        data.forEach(element => {
          chartData.addRow([element.country, element.count])
        })
      })
      .catch(error => {
        console.log(error)
      })
  
      var options = {
          backgroundColor: '#363c4d',
          width: '1000',
          height: '500'
      };
  
      var chart = new google.visualization.GeoChart(document.getElementById('geo-div'));
  
      chart.draw(chartData, options);
    }

