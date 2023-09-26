
const dogInfo = document.querySelector(".dogInfo");
const spreadBtn = document.querySelector(".spreadBtn");

spreadBtn.addEventListener('click', function (){

    if( dogInfo.style.display === 'none'){
        dogInfo.style.display = 'block';
        spreadBtn.innerHTML = "접기 <i class='xi-angle-up-thin xi-x'></i>";

    }else{
        dogInfo.style.display = 'none';
        spreadBtn.innerHTML = "펼치기 <i class='xi-angle-down-thin xi-x'></i>";

    }

});


(function($) {
    $(function (){

        let $myInfobtn = $('#myInfoChange');
        let $changInputs = $('input[class="changeInputs"]');
        let $myId = $('#myId');

        $myInfobtn.click(function (){

            if($myInfobtn.text() === "수정") {
                $changInputs.prop('readonly', false);
                $myId.prop('readonly', false);
                $changInputs.css('color', '#DAECFF');
                $myId.css('color', '#DAECFF');
                $myInfobtn.text("완료");
            } else if($myInfobtn.text() === "완료"){

                $changInputs.prop('readonly', true);
                $myId.prop('readonly', true);
                $changInputs.css('color', '');
                $myId.css('color', '');
                $myInfobtn.text("수정");

                $(".myPageSubmit").submit();

             }

        });

    });



})(jQuery);
