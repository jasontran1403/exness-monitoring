$(function () {
        'use strict';

        var currentPage = 0; // Trang mặc định
        var pageSize = 30; // Kích thước trang

        // Hàm để vẽ lại biểu đồ dựa trên trang hiện tại
        function drawChart() {
	    var resultChart = resultData.slice(currentPage * pageSize, (currentPage + 1) * pageSize);
	    if ($("#morris-bar-example").length) {
	        $("#morris-bar-example").empty();
	        
	        if (resultChart.length > 0) {
	            Morris.Bar({
	                element: 'morris-bar-example',
	                barColors: ['#0fa7ff', '#e8de1e'],
	                data: resultChart,
	                xkey: 'exnessId',
	                ykeys: ['balance', 'equity'],
	                hideHover: 'auto',
	                gridLineColor: '#eef0f2',
	                resize: true,
	                barSizeRatio: 0.4,
	                labels: ['Tổng vốn', 'Tổng equity'],
	                xLabelFormat: function (x) { return 'ID#' + x.src.exnessId; },
	                hoverCallback: function (index, options, content, row) {
	                    var currencyFormat = function(value) {
						    var exnessIdString = row.exnessId.toString(); // Chuyển đổi exnessId thành chuỗi
						    var formattedValue = parseFloat(value).toFixed(2); // Làm tròn đến 2 chữ số thập phân
						    formattedValue = formattedValue.replace(/\B(?=(\d{3})+(?!\d))/g, ","); // Thêm dấu phẩy ngăn cách hàng nghìn
						
						    if (exnessIdString.startsWith('2') || exnessIdString.startsWith('4')) {
						        return '$ ' + formattedValue;
						    } else {
						        return '¢ ' + formattedValue;
						    }
						};


	                    
	                    return '<div class="morris-hover-row-label">ID#' + row.exnessId + '</div>' +
	                        '<div class="morris-hover-point" style="color: #0fa7ff">Tổng vốn: ' + currencyFormat(row.balance) + '</div>' +
	                        '<div class="morris-hover-point" style="color: #e8de1e">Tổng equity: ' + currencyFormat(row.equity) + '</div>';
	                }
	            });
	        }
	    }
	}


        // Xử lý khi nhấn nút "Trang trước"
        $("#prevPage").on("click", function () {
            if (currentPage > 0) {
                currentPage--;
                drawChart();
            }
        });

        // Xử lý khi nhấn nút "Trang sau"
        $("#nextPage").on("click", function () {
            if ((currentPage + 1) * pageSize < resultData.length) {
                currentPage++;
                drawChart();
            }
        });

        // Gọi hàm vẽ biểu đồ ban đầu
        drawChart();
    });