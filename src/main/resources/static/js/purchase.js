/*$(document).ready(function () {
    $("#button3").click(function () {
        let memDogGum = $("#memberValue2").val();
        let requiredDogGum = 2;

        if (memDogGum >= requiredDogGum) {
            $.ajax({
                url: "/use/checkPaymentStatus",
                type: "GET",
                date: {memCode : memCode},
                success: function (data) {
                    console.log("결제 상태 확인 성공", data);
                    if (data.paid) {
                        alert("이미 구매한 상품입니다.");
                    } else {
                        $.ajax({
                            url: "/use/purchaseDogGum",
                            type: "POST",
                            data: { memDogGum: memDogGum },
                            success: function (purchaseData) {
                                console.log("구매하기 성공");
                                if (purchaseData === "success") {
                                    alert("구매하기 완료");
                                    window.location.href = "/webtoon/webtoonDetail";
                                } else {
                                    alert("개껌이 부족합니다.");
                                }
                            },
                            error: function () {
                                alert("서버 오류 또는 요청 실패");
                                return false;
                            }
                        });
                    }
                },
                error: function () {
                    alert("서버 오류 또는 요청 실패");
                }
            });

            return false;
        } else {
            alert("개껌이 부족합니다.");
            return false;
        }
    });
});*/

$(document).ready(function () {
    $("#button3").click(function () {
        let memDogGum = $("#memberValue2").val();
        // let memDogGum = parseInt($("#memberValue").val(),10);
        let requiredDogGum = 2;
        if (memDogGum >= requiredDogGum) {
            // memDogGum - requiredDogGum;
            $("#memberValue").val(memDogGum);
            $.ajax({
                url: "/webtoon/purchaseDogGum",
                type: "POST",
                data: {memDogGum},
                success: function (data) {
                    if (data === "success") {
                        alert("구매하기 완료")
                        window.location.href = "/webtoon/webtoonDetail";
                    } else {
                        alert("개껌이 부족합니다.")
                    }
                },
                error: function () {
                    alert("서버 오류 또는 요청실패");
                }
            });
            return false;
        } else {
            alert("개껌이 부족합니다.");
            return false;
        }
    });
});


$(document).ready(function () {
    $("#button4").click(function () {
        let memDogGum = $("#memberValue").val();
        // let memDogGum = parseInt($("#memberValue").val(),10);
        let requiredDogGum = 2;
        if (memDogGum >= requiredDogGum) {
            // memDogGum - requiredDogGum;
            $("#memberValue").val(memDogGum);
            $.ajax({
                url: "/webtoon/purchaseDogGum",
                type: "POST",
                data: {memDogGum},
                success: function (data) {
                    if (data === "success") {
                        alert("구매하기 완료")
                        window.location.href = "/webtoon/webtoonDetail";
                    } else {
                        alert("개껌이 부족합니다.")
                    }
                },
                error: function () {
                    alert("서버 오류 또는 요청실패");
                }
            });
            return false;
        } else {
            alert("개껌이 부족합니다.");
            return false;
        }
    });
});



function buyGum() {
    let memDogGum = $("#memberValue1").val();
    // let memDogGum = parseInt($("#memberValue").val(),10);
    let requiredDogGum = 2;
    if (memDogGum >= requiredDogGum) {
        // memDogGum - requiredDogGum;
        $("#memberValue1").val(memDogGum);
        $.ajax({
            url: "/webtoon/purchaseDogGum",
            type: "POST",
            data: {memDogGum: memDogGum},
            success: function (data) {
                if (data === "success") {
                    alert("구매하기 완료")
                    window.location.href = "/webtoon/webtoonDetail";
                } else {
                    alert("개껌이 부족합니다.")
                }
            },
            error: function () {
                alert("서버 오류 또는 요청실패");
            }
        });
        return false;
    } else {
        alert("개껌이 부족합니다.");
        return false;
    }
}


/*
$(document).ready(function () {
    $("#button3").click(function () {
        let memDogGum = $("#memberValue2").val();
        // let memDogGum = parseInt($("#memberValue").val(),10);
        let requiredDogGum = 2;
        if (memDogGum >= requiredDogGum) {
            // memDogGum - requiredDogGum;
            $("#memberValue2").val(memDogGum);
            $.ajax({
                url : "",
                type : "GET",
                success: function (date) {
                    console.log("결제 상태 확인 성공", data);
                    if (data.paid) {
                        console.log("이미 결제한 상품");
                    } else {
                        $.ajax({
                            url: "/webtoon/purchaseDogGum",
                            type: "POST",
                            data: {memDogGum: memDogGum},
                            success: function (purchaseDate) {
                                console.log("111");
                                if (purchaseDate === "success") {
                                    alert("구매하기 완료")
                                    window.location.href = "/webtoon/webtoonDetail";
                                } else {
                                    alert("개껌이 부족합니다.")
                                }
                            },
                            error: function () {
                                alert("서버 오류 또는 요청실패");
                            }
                        });
                        return false;
                    })

                else
                    {
                        alert("개껌이 부족합니다.");
                        return false;
                    }

                },
                type: "GET",
                url: ""
            });
        }
    });
});*/
