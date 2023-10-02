$(document).ready( function() {
    /* 이미지 슬라이더용 이미지 */
    $.ajax({
        type:"get",
        url:"/getBoast",
        dataType:"json",
        async: false,
        success: function(data){
            for(boastInfo of data) {
                let locationHref = "location.href='/board/boastBoardSelect?no=" + boastInfo.post_code;
                let imageUrl = "/thumbPath" + boastInfo.attach_thumb_addr;
                let append = "<li><button onClick=\"" + locationHref + "'\"><img src=\" " + imageUrl + "\" alt=\"\" width=\"398\" height=\"250\"></button></li>";
                $('.boastImage').append(append);

            }
        },
        error:function() {
            // console.log("통신에러3");
        }
    });
    
    /* 이미지 슬라이더 */
    let imgs;
    let img_count;
    let img_position = 1;

    imgs = $(".slide ul");
    img_count = imgs.children().length;

    //버튼을 클릭했을 때 함수 실행
    $('#back').click(function () {
        back();
    });
    $('#next').click(function () {
        next();
    });

    function back() {
        if(1<img_position){
            imgs.animate({
                left:'+=398px'
            });
            img_position--;
        }
    }

    function next() {
        if(img_count-3>img_position){
            imgs.animate({
                left:'-=398px'
            });
            img_position++;
        }
    }

    /* 공지사항 관련 */

    

});