document.addEventListener('DOMContentLoaded', function() {
    function select(type) {
        let petType = document.querySelector(`#qna input[id=${type}]`);
        let pageIdx = document.querySelector("input[id='page']").value;

        if(pageIdx){
            petType.value = ++petType.value;

            if (pageIdx < 8) {
                next(pageIdx);
            } else {
                end();
            }
        } else{
            console.log("해당 요소를 찾을 수 없습니다.");
        }

    }



const main = document.querySelector("#MatchingTest1");
const qna = document.querySelector("#qna");
const result = document.querySelector("#result");

const clipboard = new ClipboardJS('.btn');
clipboard.on('success', function(e) {
    console.log(e);
});
clipboard.on('error', function(e) {
    console.log(e);
});

function start() {
    main.style.display = "none";
    qna.style.display = "block";
    result.style.display = "none";

    next(0);
}

function next(pageIdx) {
    let title = document.querySelector(".question .testQuestion");
    let answer_a = document.querySelector(".answers #A");
    let answer_a_btn = document.querySelector(".answers #A .selectBtn1");
    let answer_b = document.querySelector(".answers #B");
    let answer_b_btn = document.querySelector(".answers #B .selectBtn1");
    let page = document.querySelector("input[id='page']");
    let pageCnt = document.querySelector(".page #cnt");

    title.innerHTML = qnaList[pageIdx].q;
    answer_a.href = `javascript:select('${qnaList[pageIdx].a[0].type}')`;
    answer_a_btn.innerHTML = qnaList[pageIdx].a[0].answer;
    answer_b.href = `javascript:select('${qnaList[pageIdx].a[1].type}')`;
    answer_b_btn.innerHTML = qnaList[pageIdx].a[1].answer;
    page.value = ++pageIdx;
    pageCnt.innerText = pageIdx;
}



function end() {
    qna.style.display = "none";
    result.style.display = "block";

    let pet = JSON.stringify({
        maltese : document.getElementById("maltese").value,
        chiwawa : document.getElementById("chiwawa").value,
        golden : document.getElementById("golden").value,
        hursky : document.getElementById("hursky").value,
        shihtzu : document.getElementById("shihtzu").value,
        mix : document.getElementById("mix").value,
        jindo : document.getElementById("jindo").value,
        poodle : document.getElementById("poodle").value
    });
    petReset();

    $.ajax({
        type: "POST",
        url : "/matchingTest/result",
        data : JSON.stringify(pet),
        contentType: "application/json; charset=UTF-8",

        success: function(data, textStatus, xhr) {
            document.querySelector("#result #result_img").src = `/imgages/${data}.png`;

            document.querySelector("#result .loader").style.display = "none";
            document.querySelector("#result .fin").style.display = "block";
        },

        error: function(request, status, error) {
            qna.style.display = "block";
            result.style.display = "none";

            console.log(error);
        }
    });
}

function retest() {
    main.style.display = "flex";
    qna.style.display = "none";
    result.style.display = "none";
}

function petReset() {
    document.getElementById("maltese").value = 0;
    document.getElementById("chiwawa").value = 0;
    document.getElementById("golden").value = 0;
    document.getElementById("hursky").value = 0;
    document.getElementById("shihtzu").value = 0;
    document.getElementById("mix").value = 0;
    document.getElementById("jindo").value = 0;
    document.getElementById("poodle").value = 0;
}

function copylink() {
    navigator.clipboard.writeText(window.location.href)
        .then(() => alert("링크가 복사되었습니다. 친구에게 공유해보세요!"))
        .catch(() => alert("다시 시도해보세요."))
}


});