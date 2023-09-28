    if (document.getElementById("editButton")) {
    const editButton = document.getElementById("editButton");
    const saveButton = document.getElementById("saveButton");
    const postTitleField = document.getElementById("postTitle");
    const postContentField = document.getElementById("postContentText");

    editButton.addEventListener("click", function () {
    editButton.style.display = "none";
    saveButton.style.display = "block";
    postTitleField.style.border = "1px solid #DBDBDB";
    postContentField.style.border = "1px solid #DBDBDB";
    postTitleField.readOnly = false;
    postContentField.readOnly = false;

});

    saveButton.addEventListener("click", function () {

    const postCode = document.getElementById("boardNo").value;
    const postTitle = postTitleField.value;
    const postContent = postContentField.value;
    if (postTitle.trim() == "") {
        alert('제목을 입력해 주세요..😅')
        return;
    }

    if (postContent.trim() == "") {
        alert('내용을 입력해 주세요..😅')
        return;
    }
    postTitleField.style.border = "0";
    postContentField.style.border = "0";
    postTitleField.readOnly = true;
    postContentField.readOnly = true;
    saveButton.style.display = "none";
    editButton.style.display = "block";


    fetch("/board/updatePost", {
    method: "POST",
    headers: {
    'Content-Type': 'application/json;charset=UTF-8'
},
    body: JSON.stringify({
    postCode: postCode,
    postTitle: postTitle,
    postContent: postContent
}), mode: 'cors'
})
    .then(result => {
    console.log("Server Response:", result);
    if (!result.ok) {
    throw new Error("서버 응답이 실패했습니다.");
}
    return result.json();
})
    .then(data => {
    console.log("Received Data:", data);
    postTitleField.value = data.postTitle;
    postContentField.value = data.postContent;
})
    .catch((error) => {
    console.error("Error Details:", error);
    const errorMessage = "오류가 발생했습니다. 나중에 다시 시도해 주세요.";
    alert(errorMessage);
});
});
}

    if(document.getElementById("postSubmit")) {

        const $postSubmit = document.getElementById("postSubmit");
        const $title = document.getElementById("title");
        const $postContent = document.getElementById("postContent");

        $postSubmit.onclick = function () {
            if($postContent.value.trim() == ""){
                alert('내용을 입력해 주세요..😅')
                event.preventDefault();
            }
            if($title.value.trim() == ""){
                alert('제목을 입력해 주세요..😅')
                event.preventDefault();
            }
        }
    }

    if(document.getElementById("registReply")) {

    const $registReply = document.getElementById("registReply");
    const $replyBody = document.getElementById("replyBody");
    let previousReply = '';

    $registReply.onclick = function() {

    if($replyBody.value.trim() == ""){
        alert('댓글을 입력해 주세요..😅')
        return ;
    }

    let boardNo = document.getElementById("boardNo").value;
    let replyBody = document.getElementById("replyBody").value;

    const maxLength = 150;

    if ($replyBody.value.length > maxLength) {
        alert("150자를 초과할 수 없습니다.. 😥");
        return;
    }

    if (previousReply === replyBody) {
        alert('같은 내용의 댓글은 달 수 없습니다.');
        return;
    }

    console.log('BoardNo : ', boardNo);
    console.log('replyBody : ', replyBody);
    alert("댓글을 달았습니다~ 😊")

    fetch("/board/registReply", {
    method: "POST",
    headers: {
    'Content-Type': 'application/json;charset=UTF-8'
},
    body: JSON.stringify(
{
    refReplyWriter: memCode,
    refPostCode: boardNo,
    replyCon: replyBody
}), mode: 'cors'
})

    .then(result => result.json())
    .then(data => {
    const $table = $("#replyResult");
    $table.html("");

    data.map((reply) => {
    $tr = $("<div class='comment-content'>");
    $bodyTd = $("<div id='reply-body'>").text(reply.replyCon);
    $writerTd = $("<div id='reply-writer'>").text(reply.replyWriterDetail.memId);
    $createDateTd = $("<div id='reply-date'>").text(reply.replyDate);
    $reportButton = $("<div id='comment-report-button'>").append("<button><img src='/images/report-comment-button.png' alt='comment-report-button'></button>")
    if(reply.refReplyWriter == memCode){
    $removeTd = $("<div id='comment-delete-button'>").append("<button type='button' onclick='removeReply(" + reply.replyCode + ")'>삭제</button>");
} else {
    $removeTd = $("<div>");
}

    $tr.append("<input type='hidden' id=" + reply.replyCode + " value='" + reply.replyCode + "'>");
    $tr.append($writerTd);
    $tr.append($bodyTd);
    $tr.append($createDateTd);
    $tr.append($reportButton);
    $tr.append($removeTd);

    $table.append($tr);

    previousReply = replyBody;
    $replyBody.value = '';
});

})
    .catch((error) => error.text().then((res) => alert(res)));
}
}

    /* 댓글 삭제 이벤트 처리 함수*/
    function removeReply(replyCode){

    let boardNo = document.getElementById("boardNo").value;
    alert("댓글을 삭제했습니다.")

    fetch("/board/removeReply", {
    method: "DELETE",
    headers: {
    'Content-Type': 'application/json;charset=UTF-8'
},
    body: JSON.stringify(
{
    refPostCode:boardNo,
    replyCode:replyCode
}), mode: 'cors'
})
    .then(result => result.json())
    .then(data => {
    console.table(data);

    const $table = $("#replyResult");
    $table.html("");

    data.map((reply) => {
    $tr = $("<div class='comment-content'>");
    $bodyTd = $("<div id='reply-body'>").text(reply.replyCon);
    $writerTd = $("<div id='reply-writer'>").text(reply.replyWriterDetail.memId);
    $createDateTd = $("<div id='reply-date'>").text(reply.replyDate);
    $reportButton = $("<div id='comment-report-button'>").append("<button><img src='/images/report-comment-button.png' alt='comment-report-button'></button>")
    if(reply.refReplyWriter == memCode){
    $removeTd = $("<div id='comment-delete-button'>").append("<button type='button' onclick='removeReply(" + reply.replyCode + ")'>삭제</button>");
} else {
    $removeTd = $("<div>");
}

    $tr.append("<input type='hidden' id=" + reply.replyCode + " value='" + reply.replyCode + "'>");
    $tr.append($writerTd);
    $tr.append($bodyTd);
    $tr.append($createDateTd);
    $tr.append($reportButton);
    $tr.append($removeTd);

    $table.append($tr);
});


})
    .catch((error) => error.text().then((res) => alert(res)));
}

    /* 이미지 미리보기 관련 함수(File API 활용하기) */
    function loadImg(value, num) {

        /* https://developer.mozilla.org/ko/docs/Web/API/FileReader 참고 (MDN 사이트 참고)*/
        if (value.files && value.files[0]) {			// value.files[0]는 파일 이름
            const reader = new FileReader();

            /*
                FileReader 객체는 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는 File 혹은 Blob 객체를
                이용해 파일의 내용을(혹은 raw data버퍼로) 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 하는 것
                Blob(Binary large object) : 파일류의 불변하는 미가공 데이터로 텍스트와 이진 데이터의 형태로 읽을 수 있음
            */

            // reader.readAsText(value.files[0]);			// 텍스트 파일을 읽을 때 사용 (이미지 파일을 텍스트로 읽어서 글자가 깨짐)
            // reader.readAsArrayBuffer(value.files[0]);	// 데이터를 일정한 크기로 조금씩(파일을 표현하는 ArrayBuffer) 서버로 보낼 때
            // reader.readAsBinaryString(value.files[0]);	// 이진 데이터를 서버로 보낼 때 사용
            reader.readAsDataURL(value.files[0]);		// Base64방식으로 파일을 FileReader에 전달 (Base64로 인코딩한 것은 브라우저가 원래 데이터로 만들게 됨)

            /* load 이벤트의 핸들러로 읽기 동작이 성공적으로 완료 되었을 때마다 발생한다. */
            reader.onload = function(e) {				// load EventListener에 function 등록 (FileReader에서 전달 받은 파일을 다 읽으면 리스너에 등록한 function이 호출 됨)
                switch(num){
                    case 1:
                        console.log(e.target.result);		// e.target.result는 인코딩한 결과로 img태그의 src로 사용할 수 있음
                        document.getElementById("titleImg").src = e.target.result;
                        break;
                    case 2:
                        document.getElementById("contentImg1").src = e.target.result;
                        break;
                    case 3:
                        document.getElementById("contentImg2").src = e.target.result;
                        break;
                    case 4:
                        document.getElementById("contentImg3").src = e.target.result;
                        break;
                }
            }
        }
    }

    // 라벨 엘리먼트와 input 엘리먼트를 가져옵니다.
    const labels = document.querySelectorAll('.label-imageUpload');
    const inputs = document.querySelectorAll('input[type="file"]');
    const imgs = document.querySelectorAll('.imgs');

    // 현재 활성화된 라벨의 인덱스를 추적합니다.
    let currentLabelIndex = 0;

    // 이미지 선택 이벤트 리스너를 설정합니다.
    inputs.forEach((input, index) => {
        input.addEventListener('change', () => {
            // 이미지를 업로드하면 현재 라벨를 비활성화합니다.
            labels[currentLabelIndex].style.pointerEvents = 'none'; // 라벨 클릭 이벤트 비활성화
            labels[currentLabelIndex].style.opacity = 0; // 라벨을 투명하게 만들어 비활성화처럼 보이게 함
            imgs[currentLabelIndex].style.display = 'none';

            // 다음 라벨를 활성화합니다.
            currentLabelIndex++;
            if (currentLabelIndex < labels.length) {
                labels[currentLabelIndex].style.pointerEvents = 'auto'; // 다음 라벨 클릭 이벤트 활성화
                labels[currentLabelIndex].style.display = 'inline-block'; // 라벨을 원래 상태로 복구
                labels[currentLabelIndex].style.opacity = 1; // 라벨을 원래 상태로 복구
            } else {
                // 모든 라벨을 사용한 경우에는 알림을 표시할 수 있습니다.
                alert('더 이상 이미지를 업로드할 수 없습니다.');
                labels[0].style.opacity = 1; // 라벨을 원래 상태로 복구
                imgs[0].style.display = 'inline-block';
            }
        });
    });

    function autoResizeWrite(textarea) {
        var threshold = 120; // 예시 임계값, 필요에 따라 조절 가능

        // 입력된 글자 수를 계산합니다.
        var text = textarea.value;
        var textLength = text.length;

        var container = document.querySelector('.board-write-content');
        // 글자 수가 임계값을 넘으면 높이를 늘립니다.
        if (textLength > threshold) {
            textarea.style.height = '100%'; // 먼저 높이를 초기화
            textarea.style.height = (textarea.scrollHeight) + 'px';
            container.style.height = (container.scrollHeight) + 'px';
        }
    }


    function autoResizeRead(textarea) {
        var scrollHeight = textarea.scrollHeight;

        if (textarea.clientHeight < scrollHeight) {
            textarea.style.height = scrollHeight + 'px';
        }
    }

    function navigateToAnnoPage0() {
        window.location.href = '/board/annoBoard';
    }
    function navigateToAnnoPage1() {
        window.location.href = '/board/annoBoard?currentPage=1&searchCondition=postSort&searchValue=공지';
    }
    function navigateToAnnoPage2() {
        window.location.href = '/board/annoBoard?currentPage=1&searchCondition=postSort&searchValue=이벤트';
    }

    function navigateToFreePage0() {
        window.location.href = '/board/freeBoard';
    }
    function navigateToFreePage1() {
        window.location.href = '/board/freeBoard?currentPage=1&searchCondition=postSort&searchValue=자유';
    }
    function navigateToFreePage2() {
        window.location.href = '/board/freeBoard?currentPage=1&searchCondition=postSort&searchValue=먹거리';
    }
    function navigateToFreePage3() {
        window.location.href = '/board/freeBoard?currentPage=1&searchCondition=postSort&searchValue=용품';
    }
    function navigateToFreePage4() {
        window.location.href = '/board/freeBoard?currentPage=1&searchCondition=postSort&searchValue=정보';
    }

    function navigateToBoastPage0() {
        window.location.href = '/board/boastBoard';
    }

    function navigateToAbanPage0() {
        window.location.href = '/board/abanBoard';
    }

    function navigateToAbanPage1() {
        window.location.href = '/board/abanBoardCenter';
    }

    function navigateToAbanPage2() {
        window.location.href = '/board/abanAdoptInfo';
    }

    function navigateToAbanPage3() {
        window.location.href = '/board/abanPickupInfo';
    }

    document.querySelectorAll('.image-link').forEach(function(link) {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            var modal = document.getElementById('imageModal');
            var modalImg = document.getElementById('modalImage');
            modal.style.display = 'block';
            modalImg.src = this.getAttribute('data-image-src');
        });
    });

    document.getElementById('modalImage').addEventListener('click', function() {
        var modal = document.getElementById('imageModal');
        modal.style.display = 'none';
    });

    // 모달 팝업 밖을 클릭하면 모달 팝업을 닫습니다.
    document.getElementById('imageModal').addEventListener('click', function(e) {
        if (e.target === this) {
            this.style.display = 'none';
        }
    });

    function countCharacters() {
        const textarea = document.getElementById("replyBody");
        const charCount = document.getElementById("charCount");
        const maxLength = 150;
        const registReplyButton = document.getElementById("registReply");

        const currentLength = textarea.value.length;
        charCount.textContent = currentLength;

        if (currentLength > maxLength) {
            charCount.style.color = "red";
        } else {
            charCount.style.color = "";
            registReplyButton.disabled = false;
        }
    }

    function getCurrentBoardFromURL() {
        const url = window.location.href;
        if (url.startsWith("http://localhost:8001/board/boastBoard")) {
            return "boastBoard";
        } else if (url.startsWith("http://localhost:8001/board/freeBoard")) {
            return "freeBoard";
        } else if (url.startsWith("http://localhost:8001/board/annoBoard")) {
            return "annoBoard";
        } else if (url.startsWith("http://localhost:8001/board/abanBoardCenter")) {
            return "abanBoardCenter";
        }
    }




