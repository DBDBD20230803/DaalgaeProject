/*
document.getElementById("kakaoPay1").addEventListener("click", function () {
// function test1() {
    console.log('aaa');
    $.ajax({
        method: "GET",
        url: "/payment/ready",
 /!*       data: {
            cid: "TC0ONETIME",
            partner_order_id: "partner_order_id",
            partner_user_id: "partner_user_id",
            item_name: "개껌 10개",
            quantity: 1,
            total_amount: 1100,
            tax_free_amount : 0,
            approval_url: "https://localhost:8001",
            fail_url: "https://localhost:8001",
            cancel_url: "https://localhost:8001"
        },
        headers: {
            Authorization: "KakaoAK 5721bf5ed6c90b0c31cc8760364d89c1"
        }*!/
    })
        console.log("여기는 ??")
     /!*   .done(function (msg) {
            alert("Data Saved : " + msg);
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            alert("요청 실패 : " + textStatus + " - " + errorThrown);
            console.log(jqXHR);
        });*!/

});*/
function  openNewWindow() {

   var newWindowUrl = "/payment/ready";

  var  newWindow = window.open(newWindow, "_blank");

  if (newWindow ) {
     newWindow.onload = function () {
        var form = newWindow.document.getElementById("openNewWindow");
        if (form) {
           form.submit();
        }
     };
  }else {
     alert("팝업 차단이 활성화되어 있습니다. 팝업파단을 해제하고 다시시도하세요.")
  }
}

