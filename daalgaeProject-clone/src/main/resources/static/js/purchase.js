

$(document).ready(function () {
    $("#button3").click(function () {
        let currentDate = new Date();
        let year = currentDate.getFullYear();
        let month = String(currentDate.getMonth() + 1).padStart(2, '0');
        let day = String(currentDate.getDate()).padStart(2, '0');
        let dogGumUseDate = `${year}-${month}-${day}`;

        let memDogGum = $("#memberValue2").val();
        let refMemCode = $("#user").val();
        let refPostCode = $("#refPostCode")
        let requiredDogGum = 2;
        if (memDogGum >= requiredDogGum) {
            let dogGumUseAmount = requiredDogGum
            $("#memberValue").val(memDogGum);
            $.ajax({
                url: "/webtoon/purchaseDogGum",
                type: "POST",
                data: {
                    memDogGum : memDogGum,
                    dogGumUseDate: dogGumUseDate,
                    dogGumUseAmount : dogGumUseAmount,
                    refMemCode : refMemCode,
                },
                success: function (data) {
                    if (data === "success") {
                        alert("구매하기 완료")
                        $("#button31").text("보기")
                        window.location.href = "/webtoon/webtoonDetail";
                    } else if (date === "duplicate"){
                        alert("이미 구매한 상품입니다.")
                        $("#button31").text("보기")
                        window.location.href = "/webtoon/webtoonDetail";
                    }else {
                        alert ("개껌이 부족합니다.")
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
