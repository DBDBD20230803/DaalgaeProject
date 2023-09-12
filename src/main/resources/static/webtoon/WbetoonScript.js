document.addEventListener("DOMContentLoaded", function () {

    const buttons = document.querySelectorAll('.webtoonText9');
    buttons.forEach(function (button) {
        button.addEventListener("click", function (){
            window.location.href = '../webtoon/WebtoonDetail.html';
        })
})


    const payButtons = document.querySelectorAll('.webtoonText8');
    payButtons.forEach(function (button) {
        button.addEventListener("click", function (){
            window.location.href = '../payment/Payment.html'
        })
    })
});