const main = $("#MatchingTest1");
const qna = $("#qna");
const result = $("#result");




const imageSources = [
    "/images/TestImg2.png",
    "/images/TestImg3.png",
    "/images/TestImg4.png",
    "/images/TestImg5.png",
    "/images/TestImg6.png",
    "/images/TestImg7.png",
    "/images/TestImg8.png"
]

function next(pageIdx) {


    let title = $(".question .testQuestion");
    let answer_a = $(".answers #A");
    let answer_a_btn = $(".answers #A .selectBtn1");
    let answer_b = $(".answers #B");
    let answer_b_btn = $(".answers #B .selectBtn1");
    let page = $("input[id='page']");
    let pageCnt = $(".page #cnt");
    let image = $("#questionImg");


    title.html(qnaList[pageIdx].q);
    answer_a.attr("href", `javascript:select('${qnaList[pageIdx].a[0].type}')`);
    console.log(`${qnaList[pageIdx].a[0].type}`)
    answer_a_btn.html(qnaList[pageIdx].a[0].answer);
    answer_b.attr("href", `javascript:select('${qnaList[pageIdx].a[1].type}')`);
    console.log(`${qnaList[pageIdx].a[1].type}`)
    answer_b_btn.html(qnaList[pageIdx].a[1].answer);
    page.val(++pageIdx);
    pageCnt.text(pageIdx);

    if(pageIdx >= 0 && pageIdx < imageSources.length){
        image.attr("src", imageSources[pageIdx]);
    }else{
        image.attr("src", "/images/TestImg1.png")
    }
    console.log(`Setting image source for pageIdx ${pageIdx} to ${imageSources[pageIdx]}`);
    console.log(imageSources);
}


function select(type) {
    let petType = $(`#qna input[id=${type}]`);
    let pageIdx = parseInt($("input[id='page']").val());

    if (!isNaN(pageIdx)) {
        petType.val(function (index, currentValue){
            if(!isNaN(currentValue)){
                return parseInt(currentValue) + 1;
            }
            return currentValue;
        });

        if (pageIdx < 8) {
            next(pageIdx);
        } else {
            end();
        }
    } else {
        console.log("해당 요소를 찾을 수 없습니다.");
    }
}



const clipboard = new ClipboardJS('.btn');
clipboard.on('success', function(e) {
    console.log(e);
});

clipboard.on('error', function(e) {
    console.log(e);
});


function end() {
 /*   qna.hide();
    result.show();*/

    let pet = JSON.stringify({
        maltese: $("#maltese").val(),
        chiwawa: $("#chiwawa").val(),
        golden: $("#golden").val(),
        hursky: $("#hursky").val(),
        shihtzu: $("#shihtzu").val(),
        mix: $("#mix").val(),
        jindo: $("#jindo").val(),
        poodle: $("#poodle").val()
    });


    $.ajax({
        type: "POST",
        url: "/matchingTest/result",
        data: pet,
        contentType: "application/json; charset=UTF-8",
        success: function(data, textStatus, xhr) {
/*            $("#result #result_img").attr("src", `/images/${data}.png`);*/
            $("#result .loader").hide();
            $("#result .fin").show();
            alert("매칭테스트가 성공적으로 전송되었습니다🐶.!!")
            window.location.href = "/matchingTest/matchingTestResult";
        },
        error: function(request, status, error) {
            $("#qna").show();
            $("#result").hide();

            console.log(error);
            console.log("오류가 발생했어요..다시 시도해주세요😪" + error.responseText);
        }
    });

/*    petReset();*/
}

function retest() {
/*    main.css("display", "flex");
    qna.hide();
    result.hide();*/
}

function petReset() {
    $("#maltese").val(0);
    $("#chiwawa").val(0);
    $("#golden").val(0);
    $("#hursky").val(0);
    $("#shihtzu").val(0);
    $("#mix").val(0);
    $("#jindo").val(0);
    $("#poodle").val(0);
}

function copylink() {
    navigator.clipboard.writeText(window.location.href)
        .then(function() {
            alert("링크가 복사되었습니다. 친구에게 공유해보세요!");
        })
        .catch(function() {
            alert("다시 시도해보세요.");
        });
};
