$(document).ready( function() {

    // 검색어 가져오기
    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    $('.allSearchBox').val(urlParam.get("keyword"));

    // 검색 관련
    $('.allSearchBox').focus();
    $(".allSearchBox").on("keyup",function(key){
        if(key.keyCode==13) {
            allSearch();
        }
    });

    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;

    // 완성형 아닌 한글 정규식
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;

    $(".allSearchBox").on("focusout", function() {
        let x = $(this).val();
        if (x.length > 0) {
            if (x.match(replaceChar) || x.match(replaceNotFullKorean)) {
                x = x.replace(replaceChar, "").replace(replaceNotFullKorean, "");
            }
            $(this).val(x);
        }
    }).on("keyup", function() {
        $(this).val($(this).val().replace(replaceChar, ""));
    });

    getTour("전체", 0);
    getTour("관광", 1);
    getTour("숙박", 2);
    getTour("식음료", 3);
    getTour("체험", 4);
    getTour("동물병원", 5);

    getPost(0, "자유");
    getPost(1, "공지");

});

function allSearch() {
    let keyword = $('.allSearchBox').val();
    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
    keyword = keyword.replace(replaceChar, "");
    keyword = keyword.replace(replaceNotFullKorean, "");
    location.href = "/allSearch?keyword="+keyword;
}

function allSearchDetail(sort) {
    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    let keyword = urlParam.get("keyword");
    switch (sort) {
        case 1:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=분류"
            break;
        case 2:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=관광지"
            break;
        case 3:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=숙박시설"
            break;
        case 4:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=식당 및 카페"
            break;
        case 5:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=체험 활동"
            break;
        case 6:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=동물병원"
            break;
        case 7:
            location.href = "/board/freeBoard?currentPage=1&searchCondition=postTitle&searchValue=" + keyword;
            break;
        case 8:
            location.href = "/board/annoBoard?currentPage=1&searchCondition=postTitle&searchValue=" + keyword;
            break;
        case 9:
            location.href = "/board/boastBoard?currentPage=1&searchCondition=postTitle&searchValue=" + keyword;
            break;
    }
}

function getTour(categoreValue, orderApply) {
    let urlObject1 = new URL(decodeURI(window.location.href));
    let urlParam1 = urlObject1.searchParams;
    let keyword= urlParam1.get("keyword");
    let getUrl = "/tour/getTourListAllSearch?keyword=" + keyword + "&category=" + categoreValue;
    $.ajax({
        type:"get",
        url:getUrl,
        dataType:"json",
        async: false,
        success: function(data){
            if(data.length == 0) {
                $('.tourBoard').eq(orderApply).append("<div class=\"noSearchData\">");
                $('.noSearchData').last().append("검색 결과가 없습니다");
                $('.isNoData').eq(orderApply).prop("display", "none");
            }
            for(let tourInfo of data) {
                $('.tourBoard').eq(orderApply).append("<div class=\"tourBoardUnit\">");
                let url = "<button onclick=\"location.href='/tour/tourDetail?no=" + tourInfo.tourCode + "\'\">";
                $('.tourBoardUnit').last().append(url);
                $('.tourBoardUnit button').last().append("<img class=\"tourBoardUnitThumbnail\">");
                if(tourInfo.tourPhoto.length > 0)    {
                    $('.tourBoardUnitThumbnail').last().prop("src", tourInfo.tourPhoto);
                } else {
                    $('.tourBoardUnitThumbnail').last().prop("src", "/images/dogTour.png");
                    $('.tourBoardUnitThumbnail').last().prop("title", "대체 이미지");
                }

                $('.tourBoardUnit button').last().append("<div class=\"tourBoardUnitDesc\"></div>");
                $('.tourBoardUnitDesc').last().append("<div class=\"locationName\"></div>");
                $('.locationName').last().append(tourInfo.tourTitle);
                $('.tourBoardUnitDesc').last().append("<img class=\"locationLoc\" src=\"/images/location.png\" />");
                $('.tourBoardUnitDesc').last().append("<div class=\"locTextUp\"></div>");
                $('.locTextUp').last().append(tourInfo.addr);
                $('.locTextUp').last().prop("title", tourInfo.addr);
                $('.tourBoardUnitDesc').last().append("<img class=\"telNumber\" src=\"/images/phone.png\" />");
                $('.tourBoardUnitDesc').last().append("<div class=\"telNums\">\</div>");
                $('.telNums').last().append(tourInfo.tel);
                $('.telNums').last().prop("title", tourInfo.tel);
            }
            if(data.length != 0) {
                $('.tourBoard').eq(orderApply).append("<div class=\"allSearchWhiteSpace\"></div>");
            }
        },
        error:function() {
            // console.log("통신에러3");
        }
    });
}

function getPost(orderApply, postType) {
    let urlObject1 = new URL(decodeURI(window.location.href));
    let urlParam1 = urlObject1.searchParams;
    let keyword= urlParam1.get("keyword");
    let postUrl = "/allSearchPost?postType=" + postType + "&keyword=" + keyword;
    $.ajax({
        type:"get",
        url:postUrl,
        dataType:"json",
        async: false,
        success: function(data){
            if(data.length == 0) {
                $('.postBoard').eq(orderApply).append("<div class=\"noSearchData\">");
                $('.noSearchData').last().append("검색 결과가 없습니다");
                $('.isNoData').eq(orderApply).remove();
            }
            if(data.length != 0) {
                $('.postBoard').eq(orderApply).append("<div class=\"board-container\">\n" +
                    "            <div class=\"board-list-container\">\n" +
                    "                <div class=\"board-postCategory\">\n" +
                    "                    <h5 class=\"postNo\">번호</h5>\n" +
                    "                    <h5 class=\"postCategory\">말머리</h5>\n" +
                    "                    <h5 class=\"postTitle\">제목</h5>\n" +
                    "                    <h5 class=\"postUser\">작성자</h5>\n" +
                    "                    <h5 class=\"postCount\">조회수</h5>\n" +
                    "                    <h5 class=\"postDate\">등록일</h5>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>");
            }
            for(let postInfo of data) {
                let url;
                if(postType == '자유') {
                    url = "<a class='boardLink' href=\"/board/freeBoardSelect?no=" + postInfo.post_Code + "\">";
                }
                if(postType == '공지') {
                    url = "<a class='boardLink' href=\"/board/annoBoardSelect?no=" + postInfo.post_Code + "\">";
                }

                $('.board-container').last().append(url);
                $('.boardLink').last().append("<div class=\"postList\">");
                $('.postList').last().append("<h6 class=\"testPost-postNo\">" + postInfo.post_Code +"</h6>");
                $('.postList').last().append("<h6 class=\"testPost-postCategory\">" + postInfo.post_Sort +"</h6>");
                $('.postList').last().append("<h6 class=\"testPost-postTitle\">" + postInfo.post_Title +"</h6>");
                $('.postList').last().append("<h6 class=\"testPost-postUser\">" + postInfo.post_Writer +"</h6>");
                $('.postList').last().append("<h6 class=\"testPost-postCount\">" + postInfo.post_Count +"</h6>");
                $('.postList').last().append("<h6 class=\"testPost-postDate\">" + postInfo.post_Date +"</h6>");
            }


        },
        error:function(){
            // console.log("통신에러3");
        }
    });
}