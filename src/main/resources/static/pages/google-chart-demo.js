$(function () {
'use strict';

$('#datatable-buttons').on('click', '.show-pie-chart', function () {
    var row = $(this).closest('tr');
    var exnessId = row.find('td:eq(0) span').text();
    var exnessInfo = resultData.filter((item) => item.exnessId === exnessId);
    // Load gói biểu đồ và sau đó gọi hàm callback
    google.charts.load('current', { 'packages': ['bar'] });
    google.charts.setOnLoadCallback(() => drawChart5(exnessInfo));
});


function drawChart5(exnessInfo) {
    var chartData = [];
    // Thêm mảng con với giá trị tương ứng từng cột
    var rowDataName = [''];
    var rowDataValue = [''];

    // Lặp qua từng phần tử trong exnessInfo để lấy listData
    exnessInfo.forEach((exness) => {
        // Lặp qua từng phần tử trong listData của mỗi exness
        exness.listData.forEach((currency) => {
            // Thêm tên và giá trị của currency vào mảng con
            rowDataName.push(currency.name);
            rowDataValue.push(currency.value);
        });
    });
    chartData.push(rowDataName, rowDataValue);
    // Tạo DataTable từ mảng dữ liệu
    var data = google.visualization.arrayToDataTable(chartData);

    var options = {
        chart: {
            title: '',
            subtitle: '',
        },
        legend: { position: 'none' },
        fontName: 'inherit',
        height: 400,
        fontSize: 14,
        colors: ['#f8ac5a', '#7266bb', '#23b5e2', '#3F51B5', '#00c2b2', '#23b5e2', '#e83e8c', '#f8ac5a']
    };

    var chart = new google.charts.Bar(document.getElementById('column-chart'));

    chart.draw(data, google.charts.Bar.convertOptions(options));
}

});