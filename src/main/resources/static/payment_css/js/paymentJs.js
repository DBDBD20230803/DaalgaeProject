$(document).ready(function (){
    $("#kakaoPay").click(function (){
        //카카오페이 결제 요청 처리
        IMP.init('TC0ONETIME'); //가맹점 식별 코드

        IMP.requset_pay({
            pg : 'kakao',
            pay_method : 'phone', //결제 수단
            merchant_uid: 'merchant_' + new Date().getTime(),//주문번호 생성
            name: '주문명: 카카오페이 결제', //상품명
            amount: 1100,
            buyer_name: '구매자 이름',
            buyer_tel: '구매자 전화번호',
        }, function (rsp) {
            if (rsp.success){
                //결제 성공 시 처리
                $("#kakaoPay").text('결제가 완료되었습니다.');
                console.log(rsp);
            }else{
                //결제 실패 시 처리
                $("#kakaoPay").text('결제에 실패하였습니다.');
                console.log(rsp);
            }
        });
    });
});

