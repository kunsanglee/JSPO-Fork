function modal(id) {
    var zIndex = 9999;
    var modal = $('#' + id);

    // 모달 div 뒤에 희끄무레한 레이어
    var bg = $('<div>')
        .css({
            position: 'fixed',
            zIndex: zIndex,
            left: '0px',
            top: '0px',
            width: '100%',
            height: '100%',
            overflow: 'auto',
            // 레이어 색갈은 여기서 바꾸면 됨
            backgroundColor: 'rgba(0,0,0,0.4)'
        })
        .appendTo('body');

    modal
        .css({
            position: 'fixed',
            boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',
            // display: 'block',

            // 시꺼먼 레이어 보다 한칸 위에 보이기
            zIndex: zIndex + 1,

            // div center 정렬
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            msTransform: 'translate(-50%, -50%)',
            webkitTransform: 'translate(-50%, -50%)'
        })
        .show()
        // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
        .find('.modal_close_btn')
        .on('click', function () {
            bg.remove();
            modal.hide();
        });
}

$('.btn-danger').on('click', function () {
    // 모달창 띄우기
    modal('cancelReserve');
});

function cancelPay() {
    jQuery.ajax({
        // 예: http://www.myservice.com/payments/cancel
        "url": "/reservation/cancel/"+window.document.querySelector('.resId').textContent,
        "type": "POST",
        "contentType": "application/json",
        "data": JSON.stringify({
            "merchant_uid": window.document.querySelector('.resId').textContent, // 예: ORD20180131-0000011
            "cancel_request_amount": window.document.querySelector('.resPrice').textContent, // 환불금액
            "reason": "테스트 결제 환불", // 환불사유
            // [가상계좌 환불시 필수입력] 환불 수령계좌 예금주
            "refund_holder": window.document.querySelector('.memberName').textContent
            // [가상계좌 환불시 필수입력] 환불 수령계좌 은행코드(예: KG이니시스의 경우 신한은행은 88번)
            // "refund_bank": "88"
            // [가상계좌 환불시 필수입력] 환불 수령계좌 번호
            // "refund_account": "56211105948400"
        }),
        "dataType": "json"
    }).done(function(result) { // 환불 성공시 로직
        alert("환불 성공");
        window.location.reload();
    }).fail(function(error) { // 환불 실패시 로직
        alert("환불 실패");
    });
}