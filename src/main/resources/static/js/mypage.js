
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
                $changInputs.attr('readonly', false);
                $myId.attr('readonly', false);
                $changInputs.css('color', '#DAECFF');
                $myId.css('color', '#DAECFF');
                $myInfobtn.text("완료");
            } else if($myInfobtn.text() === "완료"){

                $changInputs.attr('readonly', true);
                $myId.attr('readonly', true);
                $changInputs.css('color', '');
                $myId.css('color', '');
                $myInfobtn.text("수정");

                $(".myPageSubmit").submit();

             }

        });

    });




    $(document).ready(function () {
        checkFieldValues();

        let $addDoneBtn = $('#addDoneBtn');
        let $petInfoInputsTitle = $('.petInfoInputsTitle');
        let $petInput = $('.petInput');
        let $petSelect = $('.petSelect');


        $("#addDoneBtn").click(function (){

            if($addDoneBtn.text() === "완료"){

                $addDoneBtn.text("수정")
                $petInfoInputsTitle.attr('readonly', true);
                $petInput.attr('readonly', true);
                $petSelect.attr('disabled', true);
                $petSelect.css('color', '#222222');

            let insertFormData = {
                petNick: $("#petName").val(),
                petKind: $("#petBreedName").val(),
                petBirth: $("#petBirthDay").val(),
                petGender: $("#petGenderAnswer").val(),
                petNeutered: $("#petOperAnswer").val(),
                petWeight: $("#petWeightAnswer").val(),
                profileText: $(".profileText").val()
            };


            $.ajax({
                type:"POST",
                url:"/pet/insertPetInfo",
                data: JSON.stringify(insertFormData),
                contentType: "application/json; charset=UTF-8",

                success: function (data, textStatus, xhr){
                    alert("반려견 정보가 성공적으로 등록되었습니다🐶.!!")
                },
                error: function (xhr, status, error){
                    console.log("오류가 발생했어요..다시 시도해주세요😪" + error.responseText);
                }
            });


            }else if($addDoneBtn.text() === "수정"){
                $petInfoInputsTitle.attr('readonly', true);
                $petInput.attr('readonly', false);
                $petInput.css('color', '#bbbbbb');
                $petSelect.attr('disabled', false);
                $petSelect.css('color', '#bbbbbb');

                alert("수정하시겠습니까?");

                $addDoneBtn.text("수정완료");


            }else if($addDoneBtn.text() === "수정완료"){
                $addDoneBtn.text("완료");
                $petInfoInputsTitle.prop('readonly', false);
                $petInput.prop('readonly', false);
                $petSelect.prop('disabled', false);

                let updateFormData = {
                    petNick: $("#petName").val(),
                    petKind: $("#petBreedName").val(),
                    petBirth: $("#petBirthDay").val(),
                    petGender: $("#petGenderAnswer").val(),
                    petNeutered: $("#petOperAnswer").val(),
                    petWeight: $("#petWeightAnswer").val(),
                    profileText: $(".profileText").val()
                };

                $.ajax({
                    type: "POST",
                    url: "/pet/updatePetInfo",
                    data: JSON.stringify(updateFormData),
                    contentType: "application/json; charset=UTF-8",
                    success: function (data, textStatus, xhr){
                        alert("반려견 정보가 성공적으로 수정되었습니다!🎀");
                    },
                    error: function (xhr, status, error){
                        alert("오류가 발생했어요..다시 시도해주세요😪" + error.responseText);
                    }
                });
                $addDoneBtn.text("수정");
            }

        });


        function checkFieldValues() {
            let hasValue = false;
            $("input[type='text'], input[type='date'], select").each(function () {
                if ($(this).val()) {
                    hasValue = true;
                    return false;
                }
            });


            if (hasValue) {
                let $addDoneBtn = $('#addDoneBtn');
                let $petInfoInputsTitle = $('.petInfoInputsTitle');
                let $petInput = $('.petInput');
                let $petSelect = $('.petSelect');

                $addDoneBtn.text("수정");
                $petInfoInputsTitle.attr('readonly', true);
                $petInput.attr('readonly', true);
                $petInput.css('color', '#222222');
                $petSelect.attr('disabled', true);
                $petSelect.css('color', '#222222');
            } else {
                $addDoneBtn.text("완료");
            }
        }


    });
})(jQuery);
