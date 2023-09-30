let marker1 = [];
let marker2 = [];
let marker3 = [];
let marker4 = [];
let marker5 = [];
let constMap;
let searchWord;
$(document).ready( function() {
    $(window).on('resize', function() {
        w = window.innerWidth - 100;
        h = window.innerHeight - 100;
        $('#map').css('width',w);
        $('#map').css('height',h);
    });
    $(function(){
        $('#mapSearchBox').focus();
        w = window.innerWidth - 100;
        h = window.innerHeight - 100;
        $('#map').css('width',w);
        $('#map').css('height',h);

        let container = document.getElementById('map');
        let options;
        let userX = Number(localStorage.getItem("userX"));
        let userY = Number(localStorage.getItem("userY"));
        if(userX && userY) {
            options = {
                center: new kakao.maps.LatLng(userX, userY),
                level: 12
            };
        } else {
            options = {
                center: new kakao.maps.LatLng(37.5652352, 126.992384),
                level: 12
            };
        }

        let map = new kakao.maps.Map(container, options);
        constMap = map;
        map.relayout();
        $.ajax({
            type:"get",
            url:"/tour/getTourKakaoMap",
            dataType:"json",
            async: false,
            success: function(data){
                let tourLocInfo = new Array(206);
                for(let i= 0; i<data.length; i++) {
                    tourLocInfo[i] = {
                        title : data[i].tourTitle,
                        latlng: new kakao.maps.LatLng(data[i].mapy, data[i].mapx),
                        category: data[i].tourCategory
                    }
                }

                // 마커 이미지의 이미지 주소입니다
                /*let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";*/
                let imageSrc1 = "/images/markerTour.png";
                let imageSrc2 = "/images/markerAccomo.png";
                let imageSrc3 = "/images/markerFood.png";
                let imageSrc4 = "/images/markerActivity.png";
                let imageSrc5 = "/images/markerHospital.png";

                function makeOverListener(map, marker, infowindow) {
                    return function() {
                        infowindow.open(map, marker);
                    };
                }

                // 인포윈도우를 닫는 클로저를 만드는 함수입니다
                function makeOutListener(infowindow) {
                    return function() {
                        infowindow.close();
                    };
                }


                for (let i = 0; i < tourLocInfo.length; i ++) {
                    // 마커 이미지의 이미지 크기 입니다
                    /*let imageSize = new kakao.maps.Size(24, 35);*/
                    let imageSize2 = new kakao.maps.Size(36, 36);

                    // 마커 이미지를 생성합니다
                    /*let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);*/
                    let markerImage1 = new kakao.maps.MarkerImage(imageSrc1, imageSize2);
                    let markerImage2 = new kakao.maps.MarkerImage(imageSrc2, imageSize2);
                    let markerImage3 = new kakao.maps.MarkerImage(imageSrc3, imageSize2);
                    let markerImage4 = new kakao.maps.MarkerImage(imageSrc4, imageSize2);
                    let markerImage5 = new kakao.maps.MarkerImage(imageSrc5, imageSize2);

                    // 마커를 생성합니다

                    if(tourLocInfo[i].category.includes("관광")) {
                        let marker = new kakao.maps.Marker({
                            map: map, // 마커를 표시할 지도
                            position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                            title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                            image : markerImage1 // 마커 이미지
                        });

                        let deepLink = '<a href="/tour/tourDetail?no=' + i +
                            '">자세히 보기</a>'

                        let infowindow = new kakao.maps.InfoWindow({
                            content: deepLink, // 인포윈도우에 표시할 내용
                            removable: true
                        });

                        kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
                        $('.closeInfo').click(function () {
                            makeOutListener(infowindow);
                        });
                        /*kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));*/

                        marker.setMap(null);
                        marker1.push(marker);
                    }
                    if(tourLocInfo[i].category.includes("숙박")) {
                        let marker = new kakao.maps.Marker({
                            map: map, // 마커를 표시할 지도
                            position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                            title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                            image : markerImage2 // 마커 이미지
                        });

                        let deepLink = '<a href="/tour/tourDetail?no=' + i +
                            '">자세히 보기</a>'

                        let infowindow = new kakao.maps.InfoWindow({
                            content: deepLink, // 인포윈도우에 표시할 내용
                            removable: true
                        });

                        kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
                        $('.closeInfo').click(function () {
                            makeOutListener(infowindow);
                        });

                        marker.setMap(null);
                        marker2.push(marker);
                    }
                    if(tourLocInfo[i].category.includes("식음료")) {
                        let marker = new kakao.maps.Marker({
                            map: map, // 마커를 표시할 지도
                            position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                            title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                            image : markerImage3 // 마커 이미지
                        });

                        let deepLink = '<a href="/tour/tourDetail?no=' + i +
                            '">자세히 보기</a>'

                        let infowindow = new kakao.maps.InfoWindow({
                            content: deepLink, // 인포윈도우에 표시할 내용
                            removable: true
                        });

                        kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
                        $('.closeInfo').click(function () {
                            makeOutListener(infowindow);
                        });

                        marker.setMap(null);
                        marker3.push(marker);
                    }
                    if(tourLocInfo[i].category.includes("체험")) {
                        let marker = new kakao.maps.Marker({
                            map: map, // 마커를 표시할 지도
                            position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                            title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                            image : markerImage4 // 마커 이미지
                        });

                        let deepLink = '<a href="/tour/tourDetail?no=' + i +
                            '">자세히 보기</a>'

                        let infowindow = new kakao.maps.InfoWindow({
                            content: deepLink, // 인포윈도우에 표시할 내용
                            removable: true
                        });

                        kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
                        $('.closeInfo').click(function () {
                            makeOutListener(infowindow);
                        });

                        marker.setMap(null);
                        marker4.push(marker);
                    }
                    if(tourLocInfo[i].category.includes("동물병원")) {
                        let marker = new kakao.maps.Marker({
                            map: map, // 마커를 표시할 지도
                            position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                            title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                            image : markerImage5 // 마커 이미지
                        });

                        let deepLink = '<a href="/tour/tourDetail?no=' + i +
                            '">자세히 보기</a>'

                        let infowindow = new kakao.maps.InfoWindow({
                            content: deepLink, // 인포윈도우에 표시할 내용
                            removable: true
                        });

                        kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
                        $('.closeInfo').click(function () {
                            makeOutListener(infowindow);
                        });

                        marker.setMap(null);
                        marker5.push(marker);
                    }

                    // 마커 스타일 달기
                }
                if(selectNum == 1) {
                    for (let i = 0; i < marker1.length; i++) {
                        marker1[i].setMap(constMap);
                    }
                }
                if(selectNum == 2) {
                    for (let i = 0; i < marker2.length; i++) {
                        marker2[i].setMap(constMap);
                    }
                }
                if(selectNum == 3) {
                    for (let i = 0; i < marker3.length; i++) {
                        marker3[i].setMap(constMap);
                    }
                }
                if(selectNum == 4) {
                    for (let i = 0; i < marker4.length; i++) {
                        marker4[i].setMap(constMap);
                    }
                }
                if(selectNum == 5) {
                    for (let i = 0; i < marker5.length; i++) {
                        marker5[i].setMap(constMap);
                    }
                }
                if(selectNum == 0) {
                    for (let i = 0; i < marker1.length; i++) {
                        marker1[i].setMap(constMap);
                    }
                    for (let i = 0; i < marker2.length; i++) {
                        marker2[i].setMap(constMap);
                    }
                    for (let i = 0; i < marker3.length; i++) {
                        marker3[i].setMap(constMap);
                    }
                    for (let i = 0; i < marker4.length; i++) {
                        marker4[i].setMap(constMap);
                    }
                    for (let i = 0; i < marker5.length; i++) {
                        marker5[i].setMap(constMap);
                    }
                }
            },
            error:function(){
                console.log("통신에러3");
            }
        });
        $("#mapSearchBox").on("keyup",function(key){
            if(key.keyCode==13) {
                let searchValue = $("#mapSearchBox").val();
                for (let i = 0; i < marker1.length; i++) {
                    let checkWord = marker1[i].Gb;
                    if(!checkWord.includes(searchValue)) {
                        marker1[i].setMap(null);
                    } else {
                        if($('#tourCheck input').prop('checked') == true) {
                            marker1[i].setMap(constMap);
                        }
                    }
                }
                for (let i = 0; i < marker2.length; i++) {
                    let checkWord = marker2[i].Gb;
                    if(!checkWord.includes(searchValue)) {
                        marker2[i].setMap(null);
                    }else {
                        if($('#accomoCheck input').prop('checked') == true) {
                            marker2[i].setMap(constMap);
                        }
                    }
                }
                for (let i = 0; i < marker3.length; i++) {
                    let checkWord = marker3[i].Gb;
                    if(!checkWord.includes(searchValue)) {
                        marker3[i].setMap(null);
                    }else {
                        if($('#foodCheck input').prop('checked') == true) {
                            marker3[i].setMap(constMap);
                        }
                    }
                }
                for (let i = 0; i < marker4.length; i++) {
                    let checkWord = marker4[i].Gb;
                    if(!checkWord.includes(searchValue)) {
                        marker4[i].setMap(null);
                    }else {
                        if($('#activityCheck input').prop('checked') == true) {
                            marker4[i].setMap(constMap);
                        }
                    }
                }
                for (let i = 0; i < marker5.length; i++) {
                    let checkWord = marker5[i].Gb;
                    if(!checkWord.includes(searchValue)) {
                        marker5[i].setMap(null);
                    }else {
                        if($('#hospitalCheck input').prop('checked') == true) {
                            marker5[i].setMap(constMap);
                        }
                    }
                }
                // $("#mapSearchBox").prop('value', '');
            }
        });
    });

    // 체크 관련, 검색 관련

    let selectNum = $('input[name=selectNum]').val();
    if(selectNum == 0) {
        $('#tourCheck input').prop('checked', 'true');
        $('#accomoCheck input').prop('checked', 'true');
        $('#foodCheck input').prop('checked', 'true');
        $('#activityCheck input').prop('checked', 'true');
        $('#hospitalCheck input').prop('checked', 'true');
    }

    /*if(selectNum == 1) {
        $('#tourCheck input').prop('checked', 'true');
    }
    if(selectNum == 2) {
        $('#accomoCheck input').prop('checked', 'true');
    }
    if(selectNum == 3) {
        $('#foodCheck input').prop('checked', 'true');
    }
    if(selectNum == 4) {
        $('#activityCheck input').prop('checked', 'true');
    }
    if(selectNum == 5) {
        $('#hospitalCheck input').prop('checked', 'true');
    }*/
    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;

    $("#mapSearchBox").on("focusout", function() {
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

    $('.listTour1').click(function(){
        let searchValue = $("#mapSearchBox").val();
        searchValue = searchValue.replace(replaceChar, "");
        searchValue = searchValue.replace(replaceNotFullKorean, "");
        location.href='/tour/tourList?no=1&keyword=' + searchValue +'&country=지역&category=관광지'
    });
    $('.listTour2').click(function(){
        let searchValue = $("#mapSearchBox").val();
        searchValue = searchValue.replace(replaceChar, "");
        searchValue = searchValue.replace(replaceNotFullKorean, "");
        location.href='/tour/tourList?no=1&keyword=' + searchValue +'&country=지역&category=숙박시설'
    });
    $('.listTour3').click(function(){
        let searchValue = $("#mapSearchBox").val();
        searchValue = searchValue.replace(replaceChar, "");
        searchValue = searchValue.replace(replaceNotFullKorean, "");
        location.href='/tour/tourList?no=1&keyword=' + searchValue +'&country=지역&category=식당 및 카페'
    });
    $('.listTour4').click(function(){
        let searchValue = $("#mapSearchBox").val();
        searchValue = searchValue.replace(replaceChar, "");
        searchValue = searchValue.replace(replaceNotFullKorean, "");
        location.href='/tour/tourList?no=1&keyword=' + searchValue +'&country=지역&category=체험 활동'
    });
    $('.listTour5').click(function(){
        let searchValue = $("#mapSearchBox").val();
        searchValue = searchValue.replace(replaceChar, "");
        searchValue = searchValue.replace(replaceNotFullKorean, "");
        location.href='/tour/tourList?no=1&keyword=' + searchValue +'&country=지역&category=동물병원'
    });

    /* 이제 안 쓰는 내용*/

    /*$('.listTour1').click(function(){
        let searchValue = $("#mapSearchBox").val();
        if($('#tourCheck input').prop('checked') == true) {
            for (let i = 0; i < marker1.length; i++) {
                marker1[i].setMap(null);
            }
            $('#tourCheck input').prop('checked', false);
        } else {
            for (let i = 0; i < marker1.length; i++) {
                let checkWord = marker1[i].Gb;
                if(checkWord.includes(searchValue)) {
                    marker1[i].setMap(constMap);
                }
            }
            $('#tourCheck input').prop('checked', true);
        }
    });
    $('.listTour2').click(function(){
        let searchValue = $("#mapSearchBox").val();
        if( $('#accomoCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker2.length; i++) {
                marker2[i].setMap(null);
            }
            $('#accomoCheck input').prop('checked', false);
        } else {
            for (let i = 0; i < marker2.length; i++) {
                let checkWord = marker2[i].Gb;
                if(checkWord.includes(searchValue)) {
                    marker2[i].setMap(constMap);
                }
            }
            $('#accomoCheck input').prop('checked', true);
        }
    });
    $('.listTour3').click(function(){
        let searchValue = $("#mapSearchBox").val();
        if( $('#foodCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker3.length; i++) {
                marker3[i].setMap(null);
            }
            $('#foodCheck input').prop('checked', false);
        } else {
            for (let i = 0; i < marker3.length; i++) {
                let checkWord = marker3[i].Gb;
                if(checkWord.includes(searchValue)) {
                    marker3[i].setMap(constMap);
                }
            }
            $('#foodCheck input').prop('checked', true);
        }
    });
    $('.listTour4').click(function(){
        let searchValue = $("#mapSearchBox").val();
        if( $('#activityCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker4.length; i++) {
                marker4[i].setMap(null);
            }
            $('#activityCheck input').prop('checked', false);
        } else {
            for (let i = 0; i < marker4.length; i++) {
                let checkWord = marker4[i].Gb;
                if(checkWord.includes(searchValue)) {
                    marker4[i].setMap(constMap);
                }
            }
            $('#activityCheck input').prop('checked', true);
        }
    });
    $('.listTour5').click(function(){
        let searchValue = $("#mapSearchBox").val();
        if( $('#hospitalCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker5.length; i++) {
                marker5[i].setMap(null);
            }
            $('#hospitalCheck input').prop('checked', false);
        } else {
            for (let i = 0; i < marker5.length; i++) {
                let checkWord = marker5[i].Gb;
                if(checkWord.includes(searchValue)) {
                    marker5[i].setMap(constMap);
                }
            }
            $('#hospitalCheck input').prop('checked', true);
        }
    });*/

    /* 표시창 관련*/
    const arrow = document.querySelector(".tourArrow");
    const map = document.querySelector(".mapSearch");
    arrow.addEventListener('click', function(){
        if($('.mapSearch').css("left") == "-370px" ) {
            $('.mapSearch').css("left", "0px");
        } else {
            $('.mapSearch').css("left", "-370px");
        }
    });

    /* 체크 박스 관련 */

    $(".tourCheck").change(function(){
        if($('#tourCheck input').prop('checked') == true) {
            let searchValue = $("#mapSearchBox").val();
            for (let i = 0; i < marker1.length; i++) {
                let checkWord = marker1[i].Gb;
                if(checkWord.includes(searchValue)) {
                    marker1[i].setMap(constMap);
                }
            }
        } else {
            for (let i = 0; i < marker1.length; i++) {
                marker1[i].setMap(null);
            }
        }
        if( $('#accomoCheck input').prop('checked')  == true) {
            let searchValue = $("#mapSearchBox").val();
            for (let i = 0; i < marker2.length; i++) {
                let checkWord = marker2[i].Gb;
                if(checkWord.includes(searchValue)) {
                    marker2[i].setMap(constMap);
                }
            }
        } else {
            for (let i = 0; i < marker2.length; i++) {
                marker2[i].setMap(null);
            }
        }
        if( $('#foodCheck input').prop('checked')  == true) {
            let searchValue = $("#mapSearchBox").val();
            for (let i = 0; i < marker3.length; i++) {
                let checkWord = marker3[i].Gb;
                if(checkWord.includes(searchValue)) {
                    marker3[i].setMap(constMap);
                }
            }
        } else {
            for (let i = 0; i < marker3.length; i++) {
                marker3[i].setMap(null);
            }
        }
        if( $('#activityCheck input').prop('checked')  == true) {
            let searchValue = $("#mapSearchBox").val();
            for (let i = 0; i < marker4.length; i++) {
                let checkWord = marker4[i].Gb;
                if(checkWord.includes(searchValue)) {
                    marker4[i].setMap(constMap);
                }
            }
        } else {
            for (let i = 0; i < marker4.length; i++) {
                marker4[i].setMap(null);
            }
        }
        if( $('#hospitalCheck input').prop('checked')  == true) {
            let searchValue = $("#mapSearchBox").val();
            for (let i = 0; i < marker5.length; i++) {
                let checkWord = marker5[i].Gb;
                if(checkWord.includes(searchValue)) {
                    marker5[i].setMap(constMap);
                }
            }
        } else {
            for (let i = 0; i < marker5.length; i++) {
                marker5[i].setMap(null);
            }
        }
    });
});