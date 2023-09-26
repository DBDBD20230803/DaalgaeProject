
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






    $(function (){

        $("#addDoneBtn").click(function (){

            let formData = {
                petNick: $("#petName").val(),
                petKind: $("#petBreedName").val(),
                petBirth: $("#petBirthDay").val(),
                petGender: $("#petGenderAnswer").val(),
                petNeutered: $("#petOperAnswer").val(),
                petWeight: $("#petWeightAnswer").val()
            };


            $.ajax({
                type:"POST",
                url:"/pet/insertPetInfo",
                data: JSON.stringify(formData),
                contentType: "application/json; charset=UTF-8",

                success: function (data, textStatus, xhr){
                    console.log("반려견 정보가 성공적으로 등록되었습니다🐶.!!")
                },
                error: function (xhr, status, error){
                    console.log("20230926.오류발발 1차: " + error.responseText);
                }
            })
        });
    });


})(jQuery);
