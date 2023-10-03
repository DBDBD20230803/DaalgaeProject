$(document).ready( function() {
    let w;
    w = window.innerWidth;
    if(w > 1770) {
        $('#next').css('right', '-968px');
        $('.slide').css('width', '1592px');
    } else if(w > 1410) {
        $('#next').css('right', '-570px');
        $('.slide').css('width', '1194px');
    } else if(w > 963) {
        $('#next').css('right', '-172px');
        $('.slide').css('width', '796px');
    } else if(w <= 963) {
        $('#next').css('right', '226px');
        $('.slide').css('width', '398px');
    }
    $(window).on('resize', function() {
        w = window.innerWidth;
        if(w > 1770) {
            $('#next').css('right', '-968px');
            $('.slide').css('width', '1592px');
        } else if(w > 1410) {
            $('#next').css('right', '-570px');
            $('.slide').css('width', '1194px');
        } else if(w > 963) {
            $('#next').css('right', '-172px');
            $('.slide').css('width', '796px');
        } else if(w <= 963) {
            $('#next').css('right', '226px');
            $('.slide').css('width', '398px');
        }
    });


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
    let noticeData;
    $.ajax({
        type:"get",
        url:"/getNotice",
        dataType:"json",
        async: false,
        success: function(data){
            noticeData = data;
        },
        error:function() {
            // console.log("통신에러3");
        }
    });
    let notice1 = noticeData[0].postTitle;
    let date1 = noticeData[0].postDate;
    let url1 = "/board/annoBoardSelect?no=" + noticeData[0].postCode;

    $('.noticeEllipsis').append(notice1);
    $('.informDate').append(date1);
    $('.noticeUrl').prop('href', url1);
    if(noticeData[0].postSort == "이벤트") {
        $('.informIconBox1').prop('src', "/images/informIconBox2.png");
    }
    let noticeOrder = 0;
    playAlert = setInterval(function() {
        $('.imageBox').fadeTo(50, 0.5);
        $('.noticeUrl').fadeTo(50, 0.5);
        $('.imageBox').fadeTo(50, 1);
        $('.noticeUrl').fadeTo(50, 1);
        if(noticeOrder == 5) {
            noticeOrder = 0;
        } else {
            noticeOrder++;
        }
        let notice2 = noticeData[noticeOrder].postTitle;
        let date2 = noticeData[noticeOrder].postDate;
        let url2 = "/board/annoBoardSelect?no=" + noticeData[noticeOrder].postCode;
        $('.noticeEllipsis').html(notice2);
        $('.informDate').html(date2);
        $('.noticeUrl').prop('href', url2);
        if(noticeData[noticeOrder].postSort == "이벤트") {
            $('.largeNotice').prop('src', "/images/informIconBox2.png");
            $('.informIconBox1').prop('src', "/images/informIconBox2.png");
        } else {
            $('.largeNotice').prop('src', "/images/informIconBox1.png");
            $('.informIconBox1').prop('src', "/images/informIconBox1.png");
        }
    }, 5000);

    $('.Silder1').on('click', function () {
        clearInterval(playAlert);
        $('.imageBox').fadeTo(50, 0.5);
        $('.noticeUrl').fadeTo(50, 0.5);
        $('.imageBox').fadeTo(50, 1);
        $('.noticeUrl').fadeTo(50, 1);
        if(noticeOrder == 0) {
            noticeOrder = 5;
        } else {
            noticeOrder--;
        }
        let notice2 = noticeData[noticeOrder].postTitle;
        let date2 = noticeData[noticeOrder].postDate;
        let url2 = "/board/annoBoardSelect?no=" + noticeData[noticeOrder].postCode;
        $('.noticeEllipsis').html(notice2);
        $('.informDate').html(date2);
        $('.noticeUrl').prop('href', url2);
        if(noticeData[noticeOrder].postSort == "이벤트") {
            $('.largeNotice').prop('src', "/images/informIconBox2.png");
            $('.informIconBox1').prop('src', "/images/informIconBox2.png");
        } else {
            $('.largeNotice').prop('src', "/images/informIconBox1.png");
            $('.informIconBox1').prop('src', "/images/informIconBox1.png");
        }
    });
    $('.Silder3').on('click', function () {
        clearInterval(playAlert);
        $('.imageBox').fadeTo(50, 0.5);
        $('.noticeUrl').fadeTo(50, 0.5);
        $('.imageBox').fadeTo(50, 1);
        $('.noticeUrl').fadeTo(50, 1);
        if(noticeOrder == 5) {
            noticeOrder = 0;
        } else {
            noticeOrder++;
        }
        let notice2 = noticeData[noticeOrder].postTitle;
        let date2 = noticeData[noticeOrder].postDate;
        let url2 = "/board/annoBoardSelect?no=" + noticeData[noticeOrder].postCode;
        $('.noticeEllipsis').html(notice2);
        $('.informDate').html(date2);
        $('.noticeUrl').prop('href', url2);
        if(noticeData[noticeOrder].postSort == "이벤트") {
            $('.largeNotice').prop('src', "/images/informIconBox2.png");
            $('.informIconBox1').prop('src', "/images/informIconBox2.png");
        } else {
            $('.largeNotice').prop('src', "/images/informIconBox1.png");
            $('.informIconBox1').prop('src', "/images/informIconBox1.png");
        }
    });
});