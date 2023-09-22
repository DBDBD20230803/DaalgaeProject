window.onload = function (){
    if(document.getElementById("regist")){
        const $regist = document.getElementById("regist");
        $regist.onclick = function (){
            location.href = "/regist/regist";
        }
    }

    if(document.getElementById("duplicationCheck")){

        const $duplication = document.getElementById("duplicationCheck");

        $duplication.onclick = function (){
            let memId = document.getElementById("username").value.trim();

            fetch("/regist/idDupCheck", {
                method: "POST",
                headers: {
                    'Content-Type' : 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({memId: memId})
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));
        }
    }




    /*유효성검사*/

    $(function () {

        /**
         * 로그인 js
         */
        //로그인버튼 비활성화
        $("#loginBtn").css({"pointer-events" : "none", "backgroundColor" : "#cbcbcb"});
        //아이디, 비밀번호 입력하면 버튼 활성화
        $("#username, #password").on("keyup",function(){
            let loginId = $("#username").val().trim();
            let loginPass = $("#password").val().trim();
            if(loginId.length != 0 && loginPass.length != 0){
                $("#loginBtn").css({"pointer-events" : "", "backgroundColor" : "#9DC4E7"});
            } else{
                $("#loginBtn").css({"pointer-events" : "none", "backgroundColor" : "#cbcbcb"});
            }
        });

        /**
         * 회원가입 js
         * */

        //아이디 유효성 검사
        $(".btn").css({"pointer-events" : "none", "backgroundColor" : "#cbcbcb"});
        $("#username").on("keyup", function () {
            $(".btn").css({"pointer-events" : "none", "backgroundColor" : "#cbcbcb"});
            $("#idCheckMsg").css("margin-top", "5px");
            let idRegExp = /^[a-z0-9]{8,20}$/;
            let id = $("#username").val().trim();

            if(id.length == 0){
                $("#idCheckMsg").html("아이디를 입력해 주세요.").css("color", "#9DC4E7").attr("value", "Y");
            } else if(!idRegExp.test(id)){
                $("#idCheckMsg").html("8~20자의 영문 소문자+숫자를 사용하세요.").css("color", "#9DC4E7").attr("value", "N");
            } else{
                $("#idCheckMsg").html("").attr("value", "N");
                $(".btn").css({"pointer-events" : "", "backgroundColor" : "#9DC4E7"});
            }

        });


        //비밀번호 유효성 검사
        $("#password").on("keyup",function(){
            $("#passCheckMsg").css("margin-top", "5px");
            let passRegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}$/;
            let pass = $(this).val().trim();

            if(passRegExp.test(pass) && pass.length > 0){
                $("#passCheckMsg").html("사용가능한 비밀번호입니다.").css("color", "#9DC4E7").attr("value", "Y");
            } else if(!passRegExp.test(pass) && pass.length > 0){
                $("#passCheckMsg").html("8~20자의 영문 대소문자+숫자+특수문자를 사용하세요.").css("color", "#9DC4E7").attr("value", "N");
            } else{
                $("#passCheckMsg").html("").attr("value", "N");
            }
        });

        // 비밀번호 일치 확인
        $("#passwordConfirm").on("keyup",function(){
            $("#passCheck-msg2").css("margin-top", "5px");
            if($("#password").val() != $(this).val().replace(/\s/gi,"")){
                $("#passCheckMsg2").html("비밀번호가 일치하지 않습니다.").css("color", "red").attr("value", "N");
            }else if($("#passCheckMsg2").val() == $(this).val().replace(/\s/gi,"")){
                $("#passCheckMsg2").html("비밀번호가 일치합니다.").css("color", "blue").attr("value", "Y");
            }else{
                $("#passCheckMsg2").html("").attr("value", "N");
            }
        });

        //이름 유효성 검사
        $("#nameUser").on("keyup",function(){
            $("#nameCheckMsg").css("margin-top", "5px");
            let nameRegExp = /^[가-힣]{2,}$/;
            let name = $("#nameUser").val().trim();

            if(!nameRegExp.test(name) && name.length > 0){
                $("#nameCheckMsg").html("2자 이상의 한글만 사용 가능합니다.").css("color", "#9DC4E7").attr("value", "N");
            } else if(name.length == 0) {
                $("#nameCheckMsg").html("").attr("value", "N");
            } else{
                $("#nameCheckMsg").html("").attr("value", "Y");
            }
        });



        //이메일 유효성 검사
        $("#putEmailAddress").on("keyup",function(){
            $("#emailCheckMsg").css("margin-top", "5px");
            let emailRegExp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+[.][A-Za-z]{2,6}$/;
            let email = $("#putEmailAddress").val().trim();

            if(!emailRegExp.test(email) && email.length > 0){
                $("#emailCheckMsg").html("이메일 형식이 올바르지 않습니다.").css("color", "#9DC4E7").attr("value", "N");
            } else if (email.length == 0) {
                $("#emailCheckMsg").html("").attr("value", "N");
            } else {
                $("#emailCheckMsg").html("").attr("value", "Y");
            }
        });








        /**
         * 아이디찾기 js
         * */
        $("#findId-hp").on("keyup",function(){
            //자동하이픈 + 숫자가 아니면 입력 불가
            $(this).val($(this).val()
                .replace(/[^0-9]/g, "")
                .replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3")
                .replace("--", "-"));
        });

    });

// 회원 가입 시 유효성 최종 체크
    function fn_lastCheck(){
        let check1 = $("#idCheck-msg").attr("value");
        let check2 = $("#passCheck-msg").attr("value");
        let check3 = $("#passCheck-msg2").attr("value");
        let check4 = $("#nameCheck-msg").attr("value");
        let check5 = $("#emailCheck-msg").attr("value");
        let check6 = $("#hpCheck-msg").attr("value");

        if(check1 == "N"){
            alert("아이디 중복체크를 해주세요");
            return false;
        } else if (check2 == "N" || check3 == "N") {
            alert("유효하지 않은 비밀번호입니다.");
            return false;
        } else if (check4 == "N") {
            alert("유효하지 않은 이름입니다.")
            return false;
        } else if (check5 == "N") {
            alert("유효하지 않은 이메일입니다.")
            return false;
        } else if (check6 == "N") {
            alert("유효하지 않은 휴대폰 번호입니다.")
            return false;
        }
        return true;
    }

//회원가입 폼 취소버튼 이벤트
    function fn_cancel(){
        let check = confirm("작성한 정보는 저장되지 않습니다. \n 홈 화면으로 이동하시겠습니까?")
        if(!check){
            return false;
        }
        return location.href="/";
    }

//아이디찾기 체크
    function fn_findId_check() {
        let nameRegExp = /^[가-힣]{2,}$/;
        let emailRegExp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+[.][A-Za-z]{2,6}$/;


        let name = $("#findId-name").val().trim();
        let email = $("#findId-email").val().trim();
        let hp = $("#findId-hp").val().trim();

        if(!nameRegExp.test(name) || name.length == 0){
            alert("이름을 다시 확인해주세요");
            return false;
        } else if (!emailRegExp.test(email) || email.length == 0) {
            alert("이메일 형식이 올바르지 않습니다.");
            return false;
        }
        return true;
    }

//비밀번호찾기 체크
    function fn_findPass_check() {
        let idRegExp = /^[a-z0-9]{5,20}$/;
        let emailRegExp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+[.][A-Za-z]{2,6}$/;

        let id = $("#findPass-id").val().trim();
        let email = $("#findPass-email").val().trim();

        if(!idRegExp.test(id) || id.length == 0){
            alert("아이디를 다시 확인해주세요.");
            return false;
        } else if (!emailRegExp.test(email) || email.length == 0) {
            alert("이메일 형식이 올바르지 않습니다.");
            return false;
        }
        return true;
    }
}
