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
                let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

                for (let i = 0; i < tourLocInfo.length; i ++) {
                    // 마커 이미지의 이미지 크기 입니다
                    let imageSize = new kakao.maps.Size(24, 35);

                    // 마커 이미지를 생성합니다
                    let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

                    // 마커를 생성합니다

                    if(tourLocInfo[i].category.includes("관광")) {
                        let marker = new kakao.maps.Marker({
                            map: map, // 마커를 표시할 지도
                            position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                            title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                            image : markerImage // 마커 이미지
                        });
                        marker.setMap(null);
                        marker1.push(marker);
                    }
                    if(tourLocInfo[i].category.includes("숙박")) {
                        let marker = new kakao.maps.Marker({
                            map: map, // 마커를 표시할 지도
                            position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                            title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                            image : markerImage // 마커 이미지
                        });
                        marker.setMap(null);
                        marker2.push(marker);
                    }
                    if(tourLocInfo[i].category.includes("식음료")) {
                        let marker = new kakao.maps.Marker({
                            map: map, // 마커를 표시할 지도
                            position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                            title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                            image : markerImage // 마커 이미지
                        });
                        marker.setMap(null);
                        marker3.push(marker);
                    }
                    if(tourLocInfo[i].category.includes("체험")) {
                        let marker = new kakao.maps.Marker({
                            map: map, // 마커를 표시할 지도
                            position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                            title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                            image : markerImage // 마커 이미지
                        });
                        marker.setMap(null);
                        marker4.push(marker);
                    }
                    if(tourLocInfo[i].category.includes("동물병원")) {
                        let marker = new kakao.maps.Marker({
                            map: map, // 마커를 표시할 지도
                            position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                            title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                            image : markerImage // 마커 이미지
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
                alert($("#mapSearchBox").val());

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
            for (let i = 0; i < marker1.length; i++) {
                marker1[i].setMap(null);
            }
            $('#tourCheck input').prop('checked', false);
        } else {
            for (let i = 0; i < marker1.length; i++) {
                marker1[i].setMap(constMap);
            }
            $('#tourCheck input').prop('checked', true);
        }
    });
    $('.listTour2').click(function(){
        if( $('#accomoCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker2.length; i++) {
                marker2[i].setMap(null);
            }
            $('#accomoCheck input').prop('checked', false);
        } else {
            for (let i = 0; i < marker2.length; i++) {
                marker2[i].setMap(constMap);
            }
            $('#accomoCheck input').prop('checked', true);
        }
    });
    $('.listTour3').click(function(){
        if( $('#foodCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker3.length; i++) {
                marker3[i].setMap(null);
            }
            $('#foodCheck input').prop('checked', false);
        } else {
            for (let i = 0; i < marker3.length; i++) {
                marker3[i].setMap(constMap);
            }
            $('#foodCheck input').prop('checked', true);
        }
    });
    $('.listTour4').click(function(){
        if( $('#activityCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker4.length; i++) {
                marker4[i].setMap(null);
            }
            $('#activityCheck input').prop('checked', false);
        } else {
            for (let i = 0; i < marker4.length; i++) {
                marker4[i].setMap(constMap);
            }
            $('#activityCheck input').prop('checked', true);
        }
    });
    $('.listTour5').click(function(){
        if( $('#hospitalCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker5.length; i++) {
                marker5[i].setMap(null);
            }
            $('#hospitalCheck input').prop('checked', false);
        } else {
            for (let i = 0; i < marker5.length; i++) {
                marker5[i].setMap(constMap);
            }
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

    $(".tourCheck").change(function(){
        if($('#tourCheck input').prop('checked') == true) {
            for (let i = 0; i < marker1.length; i++) {
                marker1[i].setMap(constMap);
            }
        } else {
            for (let i = 0; i < marker1.length; i++) {
                marker1[i].setMap(null);
            }
        }
        if( $('#accomoCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker2.length; i++) {
                marker2[i].setMap(constMap);
            }
        } else {
            for (let i = 0; i < marker2.length; i++) {
                marker2[i].setMap(null);
            }
        }
        if( $('#foodCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker3.length; i++) {
                marker3[i].setMap(constMap);
            }
        } else {
            for (let i = 0; i < marker3.length; i++) {
                marker3[i].setMap(null);
            }
        }
        if( $('#activityCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker4.length; i++) {
                marker4[i].setMap(constMap);
            }
        } else {
            for (let i = 0; i < marker4.length; i++) {
                marker4[i].setMap(null);
            }
        }
        if( $('#hospitalCheck input').prop('checked')  == true) {
            for (let i = 0; i < marker5.length; i++) {
                marker5[i].setMap(constMap);
            }
        } else {
            for (let i = 0; i < marker5.length; i++) {
                marker5[i].setMap(null);
            }
        }
    });
});