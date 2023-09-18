window.onload = function (){
    if(document.getElementById("regist")){
        const $regist = document.getElementById("regist");
        $regist.onclick = function (){
            location.href = "/regist/agreeTerms";
        }
    }

    if(document.getElementById("duplicationCheck")){

        const $duplication = document.getElementById("duplicationCheck");

        $duplication.onclick = function (){
            let memberId = document.getElementById("username").value.trim();

            fetch("/member/idDupCheck", {
                method: "POST",
                headers: {
                    'Content-Type' : 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({memberId: memberId})
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));
        }
    }
}