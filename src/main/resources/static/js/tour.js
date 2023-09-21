let saveTourData;
$(document).ready( function() {
    $(window).on('resize', function() {
        w = window.innerWidth - 100;
        h = window.innerHeight - 100;
        $('#map').css('width',w);
        $('#map').css('height',h);
    });
    $(function(){
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
                level: 10
            };
        } else {
            options = {
                center: new kakao.maps.LatLng(37.5652352, 126.992384),
                level: 10
            };
        }

        let map = new kakao.maps.Map(container, options);
        map.relayout();
        $.ajax({
            type:"get",
            url:"/tour/getTourKakaoMap",
            dataType:"json",
            async: false,
            success: function(data){
                localStorage.setItem("isData", data);
                let tourLocInfo = new Array(206);
                saveTourData = data;
                console.log(data);
                for(let i= 0; i<data.length; i++) {
                    tourLocInfo[i] = {
                        title : data[i].tourTitle,
                        latlng: new kakao.maps.LatLng(data[i].mapy, data[i].mapx),
                        category: data[i].tourCategory
                    }
                }
                console.log(tourLocInfo);

                // 마커 이미지의 이미지 주소입니다
                let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

                for (let i = 0; i < tourLocInfo.length; i ++) {
                    // 마커 이미지의 이미지 크기 입니다
                    let imageSize = new kakao.maps.Size(24, 35);

                    // 마커 이미지를 생성합니다
                    let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

                    // 마커를 생성합니다
                    if(selectNum == 1) {
                        if(tourLocInfo[i].category.includes("관광")) {
                            let marker = new kakao.maps.Marker({
                                map: map, // 마커를 표시할 지도
                                position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                                title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                                image : markerImage // 마커 이미지
                            });
                        }
                    } else if(selectNum == 2) {
                        if(tourLocInfo[i].category.includes("숙박")) {
                            let marker = new kakao.maps.Marker({
                                map: map, // 마커를 표시할 지도
                                position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                                title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                                image : markerImage // 마커 이미지
                            });
                        }
                    } else if(selectNum == 3) {
                        if(tourLocInfo[i].category.includes("식음료")) {
                            let marker = new kakao.maps.Marker({
                                map: map, // 마커를 표시할 지도
                                position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                                title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                                image : markerImage // 마커 이미지
                            });
                        }
                    } else if(selectNum == 4) {
                        if(tourLocInfo[i].category.includes("체험")) {
                            let marker = new kakao.maps.Marker({
                                map: map, // 마커를 표시할 지도
                                position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                                title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                                image : markerImage // 마커 이미지
                            });
                        }
                    } else if(selectNum == 5) {
                        if(tourLocInfo[i].category.includes("동물병원")) {
                            let marker = new kakao.maps.Marker({
                                map: map, // 마커를 표시할 지도
                                position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                                title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                                image : markerImage // 마커 이미지
                            });
                        }
                    } else {
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

    // 체크 관련, 검색 관련
    let selectNum = $('input[name=selectNum]').val();
    if(selectNum == 0) {
        $('#tourCheck input').prop('checked', 'true');
        $('#accomoCheck input').prop('checked', 'true');
        $('#foodCheck input').prop('checked', 'true');
        $('#activityCheck input').prop('checked', 'true');
        $('#hospitalCheck input').prop('checked', 'true');
    }
    if(selectNum == 1) {
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
    }
    $('.listTour1').click(function(){
        if($('#tourCheck input').prop('checked') == true) {
            console.log(saveTourData);
            b(saveTourData);
            $('#tourCheck input').prop('checked', false);
        } else {
            $('#tourCheck input').prop('checked', true);
        }
    });
    $('.listTour2').click(function(){
        if( $('#accomoCheck input').prop('checked')  == true) {
            $('#accomoCheck input').prop('checked', false);
        } else {
            $('#accomoCheck input').prop('checked', true);
        }
    });
    $('.listTour3').click(function(){
        if( $('#foodCheck input').prop('checked')  == true) {
            $('#foodCheck input').prop('checked', false);
        } else {
            $('#foodCheck input').prop('checked', true);
        }
    });
    $('.listTour4').click(function(){
        if( $('#activityCheck input').prop('checked')  == true) {
            $('#activityCheck input').prop('checked', false);
        } else {
            $('#activityCheck input').prop('checked', true);
        }
    });
    $('.listTour5').click(function(){
        if( $('#hospitalCheck input').prop('checked')  == true) {
            $('#hospitalCheck input').prop('checked', false);
        } else {
            $('#hospitalCheck input').prop('checked', true);
        }
    });
    const arrow = document.querySelector(".tourArrow");
    const map = document.querySelector(".mapSearch");
    arrow.addEventListener('click', function(){
        if($('.mapSearch').css("left") == "-370px" ) {
            $('.mapSearch').css("left", "0px");
        } else {
            $('.mapSearch').css("left", "-370px");
        }
    });

    function b(a) {
        console.log(a);
    }
    $(".tourCheck").change(function(){
        if($(".tourCheck").prop("checked") == true){
            console.log("체크박스 체크했음!");
        } else{
            console.log("체크박스 체크 해제!");
        }
    });
    $(".listTourImag").click(function(){
        if($(".listTourImag").prop("checked") == true){
            console.log("체크박스 체크했음!");
        } else{
            console.log("체크박스 체크 해제!");
        }
    });
});
