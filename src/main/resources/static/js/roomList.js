
    $(function () {
    $('input[name="daterange"]').daterangepicker({
        "locale": {
            "format": "YYYY-MM-DD",
            "separator": " ~ ",
            "applyLabel": "확인",
            "cancelLabel": "취소",
            "fromLabel": "체크인",
            "toLabel": "체크아웃",
            "customRangeLabel": "Custom",
            "weekLabel": "주",
            "daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
            "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월",
                "7월", "8월", "9월", "10월", "11월", "12월"],
            "firstDay": 1
        },
        "startDate": new Date(),
        "endDate": new Date(new Date().setDate(new Date().getDate() + 1)),
        "minDate": new Date(),
        "maxDate": new Date(new Date().setDate(new Date().getDate() + 90)),
        "opens": "center",
    }, function (start, end, label) {
        console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
        window.document.querySelector('.start').value = start.format('YYYY-MM-DD');
        window.document.querySelector('.end').value = end.format('YYYY-MM-DD');
    })
});