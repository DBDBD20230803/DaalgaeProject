$(document).ready( function() {
    $(window).on('resize', function() {
        w = window.innerWidth - 100;
        h = window.innerHeight - 100;
        $('#map').css('width',w);
        $('#map').css('height',h);
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

    // 마커 관련
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
                level: 5
            };
        } else {
            options = {
                center: new kakao.maps.LatLng(34.54513393, 126.8998852),
                level: 5
            };
        }

        let map = new kakao.maps.Map(container, options);
        map.relayout();

        $.ajax({
            type:"get",
            url:"tour/getTourKakaoMap",
            dataType:"json",
            success: function(data){
                let positions = new Array(206);
                console.log(data);
                for(let i=0; i<206; i++) {
                    positions[i] = {
                        title : data[i].tourTitle,
                        latlng: new kakao.maps.LatLng(data[i].mapy, data[i].mapx)
                    }
                }

                // 마커 이미지의 이미지 주소입니다
                let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

                for (let i = 0; i < positions.length; i ++) {

                    // 마커 이미지의 이미지 크기 입니다
                    let imageSize = new kakao.maps.Size(24, 35);

                    // 마커 이미지를 생성합니다
                    let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

                    // 마커를 생성합니다
                    let marker = new kakao.maps.Marker({
                        map: map, // 마커를 표시할 지도
                        position: positions[i].latlng, // 마커를 표시할 위치
                        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                        image : markerImage // 마커 이미지
                    });
                }
            },
            error:function(){
                console.log("통신에러3");
            }
        });
    });
});
