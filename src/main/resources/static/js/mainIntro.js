$(document).ready( function() {
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
                left:'+=326px'
            });
            img_position--;
        }
    }

    function next() {
        if(img_count-3>img_position){
            imgs.animate({
                left:'-=326px'
            });
            img_position++;
        }
    }
});