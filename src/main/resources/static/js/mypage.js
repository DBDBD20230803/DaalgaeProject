const addPetBtn = document.querySelector(".addPet");
const dogInfo = document.querySelector(".dogInfo");

/*addPetBtn.addEventListener('click', function() {
    dogInfo.style.display = 'block';
});*/



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

