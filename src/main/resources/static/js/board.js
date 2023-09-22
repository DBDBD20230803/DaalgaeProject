    if (document.getElementById("editButton")) {
    const editButton = document.getElementById("editButton");
    const saveButton = document.getElementById("saveButton");
    const postTitleField = document.getElementById("postTitle");
    const postContentField = document.getElementById("postContent");

    editButton.addEventListener("click", function () {
    editButton.style.display = "none";
    saveButton.style.display = "block";
    postTitleField.style.border = "1px solid #DBDBDB";
    postContentField.style.border = "1px solid #DBDBDB";
    postTitleField.readOnly = false;
    postContentField.readOnly = false;

});

    saveButton.addEventListener("click", function () {

    saveButton.style.display = "none";
    editButton.style.display = "block";
    const postCode = document.getElementById("boardNo").value;
    const postTitle = postTitleField.value;
    const postContent = postContentField.value;
    postTitleField.style.border = "0";
    postContentField.style.border = "0";
    postTitleField.readOnly = true;
    postContentField.readOnly = true;

    fetch("/board/updatePost", {
    method: "POST",
    headers: {
    'Content-Type': 'application/json;charset=UTF-8'
},
    body: JSON.stringify({
    postCode: postCode,
    postTitle: postTitle,
    postContent: postContent
})
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

    if(document.getElementById("registReply")) {

    const $registReply = document.getElementById("registReply");
    const $replyBody = document.getElementById("replyBody");

    $registReply.onclick = function() {

    if($replyBody.value.trim() == ""){
    alert('댓글을 입력해 주십시오');
    return ;
}

    let boardNo = document.getElementById("boardNo").value;
    let replyBody = document.getElementById("replyBody").value;

    console.log('BoardNo : ', boardNo);
    console.log('replyBody : ', replyBody);
    alert("수준 낮은 댓글은 쓰질마세요~ 🤔")

    fetch("/board/registReply", {
    method: "POST",
    headers: {
    'Content-Type': 'application/json;charset=UTF-8'
},
    body: JSON.stringify(
{
    refReplyWriter: '[[${#authentication.getPrincipal().getMemCode()}]]',
    refPostCode: boardNo,
    replyCon: replyBody
}
    )
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
    if(reply.refReplyWriter == '[[${#authentication.getPrincipal().getMemCode()}]]'){
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
}



    /* 댓글 삭제 이벤트 처리 함수*/
    function removeReply(replyCode){

    let boardNo = document.getElementById("boardNo").value;
    alert("그래요. 그런 댓글은 차라리 지우는게 나아요 😅")

    fetch("/board/removeReply", {
    method: "DELETE",
    headers: {
    'Content-Type': 'application/json;charset=UTF-8'
},
    body: JSON.stringify(
{
    refPostCode:boardNo,
    replyCode:replyCode
}
    )
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
    if(reply.refReplyWriter == '[[${#authentication.getPrincipal().getMemCode()}]]'){
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

    const $titleImgArea = document.getElementById("titleImgArea");
    const $contentImgArea1 = document.getElementById("contentImgArea1");
    const $contentImgArea2 = document.getElementById("contentImgArea2");
    const $contentImgArea3 = document.getElementById("contentImgArea3");

    $titleImgArea.onclick = function() {
    document.getElementById("thumbnailImg1").click();
}

    $contentImgArea1.onclick = function() {
    document.getElementById("thumbnailImg2").click();
}

    $contentImgArea2.onclick = function() {
    document.getElementById("thumbnailImg3").click();
}

    $contentImgArea3.onclick = function() {
    document.getElementById("thumbnailImg4").click();
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
