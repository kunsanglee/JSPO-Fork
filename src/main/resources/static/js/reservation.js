window.onload = function() {
    let IMP = window.IMP;
    IMP.init("imp52880818");

    function requestPay() {
        IMP.request_pay({
            pg: 'kakaopay.TC0ONETIME',
            // pg: 'kcp.T0000',
            pay_method: 'card',
            merchant_uid: /*"5700155333-33524"*/document.querySelector('.resId').textContent,
            name: /*'포시즌스 호텔 디럭스룸'*/document.querySelector('.fullName').textContent,
            amount: /*100*/document.querySelector('.rPrice').textContent,
            buyer_email: /*'Iamport@chai.finance'*/document.querySelector('.email').textContent,
            buyer_name: /*'아임포트 기술지원팀'*/document.querySelector('.name').textContent,
            buyer_tel: /*'010-1234-5678'*/document.querySelector('.phone').textContent,
            // buyer_addr: '서울특별시 강남구 삼성동',
            // buyer_postcode: '123-456',
            // notice_url : 'https://localhost:8080/reservation/complete',   //웹훅수신 URL 설정
        }, function (rsp) { // callback
            if (rsp.success) {
                // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
                // jQuery로 HTTP 요청
                jQuery.ajax({
                    url: encodeURI("/reservation/complete"),
                    method: "POST",
                    headers: {"Content-Type": "application/json"},
                    data: JSON.stringify({
                        imp_uid: rsp.imp_uid,            // 결제 고유번호
                        merchant_uid: rsp.merchant_uid,   // 주문번호
                        amount: rsp.amount              // 결제금
                    })
                }).done(function (e) {
                    // 가맹점 서버 결제 API 성공시 로직
                    alert("결제에 성공했습니다!");
                    window.location.replace("/my/reserved");
                })
            } else {
                alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
                window.location.href = document.referrer;
            }
        })
    }
}