
$(document).ready( function() {
    $(".tourListSearch").focus();
    $(".tourListSearch").on("keyup",function(key){
        if(key.keyCode==13) {
            tourListSearch();
        }
    });
    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;

    // 완성형 아닌 한글 정규식
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;

    $(".tourListSearch").on("focusout", function() {
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

    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    let keyword = urlParam.get("keyword");
    let country = urlParam.get("country");
    let no = urlParam.get("no");

    $('.tourListSearch').val(keyword);
    if($('.selectSearchOption').val(country).length > 0) {
        $('.selectSearchOption').val(country);
    } else {
        $('.selectSearchOption').val("지역");
    }


    let getUrl = "/tour/getTourList?no=" + no + "&keyword=" + keyword + "&country=" + country;
    console.log(getUrl);
    let urlNo = 1;
    $.ajax({
        type:"get",
        url:getUrl,
        dataType:"json",
        success: function(data){
            for(let tourInfo of data) {
                $('.tourBoard').last().append("<div class=\"tourBoardUnit\">");

                let url = "<button onclick=\"location.href='/tour/tourDetail?no=" + urlNo++ + "\'\">";
                $('.tourBoardUnit').last().append(url);
                $('.tourBoardUnit button').last().append("<img class=\"tourBoardUnitThumbnail\">");
                if(tourInfo.tourPhoto.length > 0) {
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
        },
        error:function(){
            // console.log("통신에러3");
        }
    });
});
function tourListSearch() {
    let keyword = $('.tourListSearch').val();
    let country = $('.selectSearchOption option:selected').val();
    let no = $('.noTour').val();
    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
    keyword = keyword.replace(replaceChar, "");
    keyword = keyword.replace(replaceNotFullKorean, "");
    location.href = "/tour/tourList?no=" + no +"&keyword="+keyword+"&country="+country;
}