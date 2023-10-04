saveButton.addEventListener("click", function () {
    const postCode = document.getElementById("boardNo").value;
    const postTitle = postTitleField.value;
    const postContent = postContentField.value;
    if (postTitle.trim() == "") {
        alert('제목을 입력해 주세요.');
        return;
    }

    if (postContent.trim() == "") {
        alert('내용을 입력해 주세요.');
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
        }),
        mode: 'cors'
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

if (document.getElementById("webtoonSubmit")) {
    const $postSubmit = document.getElementById("webtoonSubmit");
    const $title = document.getElementById("title");
    const $postContent = document.getElementById("postContent");

    $postSubmit.onclick = function (event) {
        if ($title.value.trim() == "") {
            alert('제목을 입력해 주세요.');
            event.preventDefault();
        }
    };
}