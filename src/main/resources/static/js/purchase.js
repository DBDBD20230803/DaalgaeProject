$(document).ready(function (){
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
                data: { memDogGum : memDogGum },
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
        }else{
            alert("개껌이 부족합니다.");
            return false;
        }
    });
});

$(document).ready(function (){
    $("#button3").click(function () {
        let memDogGum = $("#memberValue2").val();
        // let memDogGum = parseInt($("#memberValue").val(),10);
        let requiredDogGum = 2;
        if (memDogGum >= requiredDogGum) {
            // memDogGum - requiredDogGum;
            $("#memberValue2").val(memDogGum);
            $.ajax({
                url: "/webtoon/purchaseDogGum",
                type: "POST",
                data: { memDogGum : memDogGum },
                success: function (data) {
                    console.log("111");
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
        }else{
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
            data: { memDogGum : memDogGum },
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
    }else{
        alert("개껌이 부족합니다.");
        return false;
    }
}
/*$(document).ready(function (){
    $("#button5").click(function () {
        let memDogGum = $("#memberValue1").val();
        // let memDogGum = parseInt($("#memberValue").val(),10);
        let requiredDogGum = 2;
        if (memDogGum >= requiredDogGum) {
            // memDogGum - requiredDogGum;
            $("#memberValue1").val(memDogGum);
            $.ajax({
                url: "/webtoon/purchaseDogGum",
                type: "POST",
                data: { memDogGum : memDogGum },
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
        }else{
            alert("개껌이 부족합니다.");
            return false;
        }
    });
});*/


/*
$(document).ready(function () {
    $("form").submit(function () {

        let memDogGum = "${ member }";
        let requiredDogGum = 2;
        console.log(memDogGum);
        if (memDogGum >= requiredDogGum) {
            memDogGum -= requiredDogGum;
            console.log(memDogGum)
            $.ajax({
                url: "/webtoon/purchaseDogGum",
                type: "POST",
                data: {memDogGum: memDogGum},
                success: function (data) {
                    if (data === "success") {
                        console.log(memDogGum)
                        alert("구매하기 완료");
                        window.location.reload();
                    } else {
                        alert("개껌이 부족합니다.")
                    }
                }
            });
        }
    });
});
*/
