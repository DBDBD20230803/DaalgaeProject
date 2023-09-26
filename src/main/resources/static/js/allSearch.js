$(document).ready( function() {
    $('#allSearchBox').focus();
    $("#allSearchBox").on("keyup",function(key){
        if(key.keyCode==13) {
            allSearch();
        }
    });

    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;

    // 완성형 아닌 한글 정규식
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;

    $("#allSearchBox").on("focusout", function() {
        let x = $(this).val();
        if (x.length > 0) {
            if (x.match(replaceChar) || x.match(replaceNotFullKorean)) {
                x = x.replace(replaceChar, "").replace(replaceNotFullKorean, "");
            }
            $(this).val(x);
        }
    }).on("keyup", function() {
        $(this).val($(this).val().replace(replaceChar, ""));
    });


    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    $('#allSearchBox').val(urlParam.get("keyword"));

    let category = "";

    $.ajax({
        type:"get",
        url:"/tour/getTourKakaoMap",
        dataType:"json",
        async: false,
        success: function(data){
            let tourLocInfo = [];
            for(let i= 0; i<data.length; i++) {
                tourLocInfo[i] = {
                    title : data[i].tourTitle,
                    latlng: new kakao.maps.LatLng(data[i].mapy, data[i].mapx),
                    category: data[i].tourCategory
                }
            }

            // 마커 이미지의 이미지 주소입니다
            let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

            for (let i = 0; i < tourLocInfo.length; i ++) {
                // 마커 이미지의 이미지 크기 입니다
                let imageSize = new kakao.maps.Size(24, 35);

                // 마커 이미지를 생성합니다
                let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

                // 마커를 생성합니다
                if(tourLocInfo[i].category.includes("동물병원")) {
                    let marker = new kakao.maps.Marker({
                        map: map, // 마커를 표시할 지도
                        position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                        title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                        image : markerImage // 마커 이미지
                    });

                }
                // 마커 스타일 달기
            }
        },
        error:function(){
            console.log("통신에러3");
        }
    });
});
function allSearch() {
    let keyword = $('#allSearchBox').val();
    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
    keyword = keyword.replace(replaceChar, "");
    keyword = keyword.replace(replaceNotFullKorean, "");
    location.href = "/allSearch?keyword="+keyword;
}