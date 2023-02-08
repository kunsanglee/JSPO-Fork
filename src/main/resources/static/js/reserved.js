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