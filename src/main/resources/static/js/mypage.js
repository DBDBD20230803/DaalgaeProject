
const dogInfo = document.querySelector(".dogInfo");
const spreadBtn = document.querySelector(".spreadBtn");

spreadBtn.addEventListener('click', function () {

    if (dogInfo.style.display === 'none') {
        dogInfo.style.display = 'block';
        spreadBtn.innerHTML = "접기 <i class='xi-angle-up-thin xi-x'></i>";

    } else {
        dogInfo.style.display = 'none';
        spreadBtn.innerHTML = "펼치기 <i class='xi-angle-down-thin xi-x'></i>";

    }

});


(function($) {
    $(function () {

        let $myInfobtn = $('#myInfoChange');
        let $changInputs = $('input[class="changeInputs"]');
        let $myId = $('#myId');

        $myInfobtn.click(function () {

            if ($myInfobtn.text() === "수정") {
                $changInputs.attr('readonly', false);
                $myId.attr('readonly', false);
                $changInputs.css('color', '#DAECFF');
                $myId.css('color', '#DAECFF');
                $myInfobtn.text("완료");
            } else if ($myInfobtn.text() === "완료") {

                $changInputs.attr('readonly', true);
                $myId.attr('readonly', true);
                $changInputs.css('color', '');
                $myId.css('color', '');
                $myInfobtn.text("수정");

                $(".myPageSubmit").submit();

            }

        });

    });


    $(function () {

        $("#addDoneBtn").click(function () {
            let $addDoneBtn = $('#addDoneBtn');
            let $petInfoInputsTitle = $('.petInfoInputsTitle');
            let $petInput = $('.petInput');
            let $petSelect = $('.petSelect');

            if ($addDoneBtn.text() === "완료") {

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
                    petWeight: $("#petWeightAnswer").val(),
                    petNeutered: $("#petOperAnswer").val()
                };


                $.ajax({
                    type: "POST",
                    url: "/pet/insertPetInfo",
                    data: JSON.stringify(insertFormData),
                    contentType: "application/json; charset=UTF-8",

                    success: function (data, textStatus, xhr) {
                        alert("반려견 정보가 성공적으로 등록되었습니다🐶.!!")
                    },
                    error: function (xhr, status, error) {
                        console.log("오류가 발생했어요..다시 시도해주세요😪" + error.responseText);
                    }
                });
            };

        });



        $("#addDoneBtn1").click(function (){

            let $addDoneBtn1 = $('#addDoneBtn1');
            let $petInfoInputsTitle1 = $('.petInfoInputsTitle');
            let $petInput1 = $('.petInput');
            let $petSelect1 = $('.petSelect');

            if ($addDoneBtn1.text() === "수정") {
            /*    $petInfoInputsTitle1.attr('readonly', true);
                $petInput1.attr('readonly', true);
                $petInput1.css('color', '#bbbbbb');
                $petSelect1.attr('disabled', true);
                $petSelect1.css('color', '#bbbbbb');
*/
                alert("수정하시겠습니까?");

                $addDoneBtn1.text("수정완료");


            } else if ($addDoneBtn1.text() === "수정완료") {
                $addDoneBtn1.text("완료");
             /*   $petInfoInputsTitle1.prop('readonly', false);
                $petInput1.prop('readonly', false);
                $petSelect1.prop('disabled', false);*/

                let updateFormData = {
                    petNick: $("#petName1").val(),
                    petKind: $("#petBreedName1").val(),
                    petBirth: $("#petBirthDay1").val(),
                    petGender: $("#petGenderAnswer1").val(),
                    petWeight: $("#petWeightAnswer1").val(),
                    petNeutered: $("#petOperAnswer1").val()

                };

                $.ajax({
                    type: "POST",
                    url: "/pet/updatePetInfo",
                    data: JSON.stringify(updateFormData),
                    contentType: "application/json; charset=UTF-8",
                    success: function (data, textStatus, xhr) {
                        alert("반려견 정보가 성공적으로 수정되었습니다!🎀");
                    },
                    error: function (xhr, status, error) {
                        alert("오류가 발생했어요..다시 시도해주세요😪" + error.responseText);
                    }
                });
                $addDoneBtn1.text("수정");
            };

        });


    });

})(jQuery);
