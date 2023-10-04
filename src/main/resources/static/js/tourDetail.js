function likeClick() {
    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    let no = urlParam.get("no");
    let toUrl = "/tour/bookmark/getTourBookmark?no=" + no;
    let toUrl1 = "/tour/bookmark/setTourBookmark?no=" + no;
    let toUrl2 = "/tour/bookmark/deleteTourBookmark?no=" + no;
    $.ajax({
        type:"get",
        url:toUrl,
        dataType:"json",
        success: function(data) {
            if(data == -1) {
                location.href='/login/login';
            }
            if(data == 0) {
                $.ajax({
                    type:"get",
                    url:toUrl1,
                    dataType:"json",
                    success: function(data) {
                        if(data > 0) {
                            $('.likeListImageLeft').prop('src', '/images/likeListClicked.png');
                        }
                    },
                    error:function(){
                        console.log("통신에러3");
                    }
                });
            } else {
                $.ajax({
                    type:"get",
                    url:toUrl2,
                    dataType:"json",
                    success: function(data) {
                        if(data > 0) {
                            $('.likeListImageLeft').prop('src', '/images/likeList.png');
                        }
                    },
                    error:function(){
                        console.log("통신에러3");
                    }
                });
            }
        },
        error:function(){
            console.log("통신에러3");
        }
    });
}
$(function () {
    if($('.isHospital').val() == "동물병원") {
        $('.hideHospital').remove();
    }

    let page = $('.findPage').val();
    page = page.replaceAll("\"\"", "\"");
    page = page.replaceAll("\"\<", "\<");
    page = page.replaceAll("\>\"", "\>");
    page = page.replaceAll("\>h", "\>\<div class=\"homePage\"\>h");
    page = page.replaceAll("\<\/a\>", "\<\/div\>\<\/a\>");

    if($('.tourPhotoVal').val().length > 0) {
        $('.tourPhoto').prop("src", $('.tourPhotoVal').val());
    } else {
        $('.tourPhoto').prop("src", "/images/dogTour.png");
        $('.tourPhoto').prop("title", "대체 이미지");
    }

    $('.tourDescText span').last().append(page + "&nbsp;");
    $('.tel').append("&nbsp;");
    $('.useTime').append("&nbsp;");
    $('.descTitle').append("&nbsp;");
    $('.descTitle').append("&nbsp;");
    let boxCount = 0;
    if($('.supplies').val().length > 0 && $('.supplies').val() != 'none') {
        $('.tourImageBox').append("<div class=\"tourImage1\">");
        $('.tourImage1').append("<img height=\"70px\" src=\"/images/dogHouseImage.png\" />");
        $('.tourImage1').append("<p class=\"tourImageDesc\">비품제공</p>");
        $('.tourImageBox').append("</div>");
        boxCount++;
    }

    if($('.facilities').val().includes("주차")) {
        if(boxCount > 0 && boxCount < 3) {
            $('.tourImageBox').append("<div class=\"tourImageMargin\">");
        }
        $('.tourImageBox').last().append("<div class=\"tourImage1\">");
        $('.tourImage1').last().append("<img height=\"70px\" src=\"/images/parkingArea.png\" />");
        $('.tourImage1').last().append("<p class=\"tourImageDesc\">주차장</p>");
        $('.tourImageBox').last().append("</div>");
        boxCount++;
    }

    if($('.facilities').val().includes("샤워")) {
        if(boxCount > 0 && boxCount < 3) {
            $('.tourImageBox').append("<div class=\"tourImageMargin\">");
        }
        $('.tourImageBox').last().append("<div class=\"tourImage1\">");
        $('.tourImage1').last().append("<img height=\"70px\" src=\"/images/shower.png\" />");
        $('.tourImage1').last().append("<p class=\"tourImageDesc\">샤워</p>");
        $('.tourImageBox').last().append("</div>");
        boxCount++;
    }

    if($('.facilities').val().includes("목욕")) {
        if(boxCount > 0 && boxCount < 3) {
            $('.tourImageBox').append("<div class=\"tourImageMargin\">");
        }
        $('.tourImageBox').last().append("<div class=\"tourImage1\">");
        $('.tourImage1').last().append("<img height=\"70px\" src=\"/images/bath1.png\" />");
        $('.tourImage1').last().append("<p class=\"tourImageDesc\">목욕</p>");
        $('.tourImageBox').last().append("</div>");
        boxCount++;
    }


    /* 지도 관련 */
    let tourMapx = $('.mapx').val();
    let tourMapy = $('.mapy').val();
    let tourTitle = $('.title').val();
    let tourCategory = $('.category').val();
    let container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(tourMapy, tourMapx),
        level: 3
    };

    let map = new kakao.maps.Map(container, options);
    let markerPosition  = new kakao.maps.LatLng(tourMapy, tourMapx);

    /*let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
    let imageSize = new kakao.maps.Size(24, 35);*/

    let imageSrc1 = "/images/markerTour.png";
    let imageSrc2 = "/images/markerAccomo.png";
    let imageSrc3 = "/images/markerFood.png";
    let imageSrc4 = "/images/markerActivity.png";
    let imageSrc5 = "/images/markerHospital.png";

    let imageSize2 = new kakao.maps.Size(36, 36);


    let markerImage1 = new kakao.maps.MarkerImage(imageSrc1, imageSize2);
    let markerImage2 = new kakao.maps.MarkerImage(imageSrc2, imageSize2);
    let markerImage3 = new kakao.maps.MarkerImage(imageSrc3, imageSize2);
    let markerImage4 = new kakao.maps.MarkerImage(imageSrc4, imageSize2);
    let markerImage5 = new kakao.maps.MarkerImage(imageSrc5, imageSize2);

    let markerImage;
    if(tourCategory.includes('관광지')) {
        markerImage = markerImage1;
    } else if(tourCategory.includes('숙박')) {
        markerImage = markerImage2;
    } else if(tourCategory.includes('식음료')) {
        markerImage = markerImage3;
    } else if(tourCategory.includes('체험')) {
        markerImage = markerImage4;
    } else if(tourCategory.includes('동물병원')) {
        markerImage = markerImage5;
    }

    // 마커 이미지를 생성합니다
    // let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    // 마커를 생성합니다
    let marker = new kakao.maps.Marker({
        map: map,
        position: markerPosition,
        title : tourTitle,
        image : markerImage
    });

    function makeOverListener(map, marker, infowindow) {
        return function() {
            infowindow.open(map, marker);
        };
    }

    let urlObject2 = new URL(decodeURI(window.location.href));
    let urlParam2 = urlObject2.searchParams;
    let noNum = urlParam2.get("no");

    if($('.tourPhotoVal').val().length == 0) {
        $('.tourPhotoVal').val("/images/dogTour.png");
    }

    let photoUrl = $('.tourPhotoVal').val();

    let deepLink = '<div class="toEllipsis" title="' + tourTitle + '" >' + tourTitle + '</div>' +
        '<img class="tourImage" width="148px" height="148px" src="' + photoUrl + '"><br>'
        + '<div class="toEllipsis" title="' + $('.tourAddr').val() + '" >' + '주소:&nbsp;' + $('.tourAddr').val() + '</div>' +
        '<a href="/tour/tourDetail?no=' + noNum +
        '">자세히 보기</a>'




    let infowindow = new kakao.maps.InfoWindow({
        content: deepLink, // 인포윈도우에 표시할 내용
        removable: true
    });

    infowindow.open(map, marker);

    kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));

    /* 주변 여행지 찾기*/
    let markers = [];
    let markerOrder = 0;
    let markerDistances = [];
    $.ajax({
        type:"get",
        url:"/tour/getTourKakaoMap",
        dataType:"json",
        success: function(data){
            let tourLocInfo = new Array(206);
            for(let i= 0; i<data.length; i++) {
                tourLocInfo[i] = {
                    latlng: new kakao.maps.LatLng(data[i].mapy, data[i].mapx),
                    category: data[i].tourCategory,
                    title: data[i].tourTitle,
                    photo: data[i].tourPhoto
                }
            }
            for (let i = 0; i < tourLocInfo.length; i ++) {
                // 마커를 생성합니다
                let marker = new kakao.maps.Marker({
                    map: map, // 마커를 표시할 지도
                    position: tourLocInfo[i].latlng, // 마커를 표시할 위치
                    title : tourLocInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                    image : markerImage // 마커 이미지
                });

                marker.setMap(null);
                markers.push(marker);
            }
            // 마커들이 담긴 배열
            markers.forEach(function(m) {
                let c1 = map.getCenter();
                let c2 = m.getPosition();
                let poly = new kakao.maps.Polyline({
                    // map: map, 을 하지 않아도 거리는 구할 수 있다.
                    path: [c1, c2]
                });
                let dist = poly.getLength(); // m 단위로 리턴
                let markerDistance = {};
                markerDistance.order = markerOrder;
                markerDistance.dist = dist;
                markerDistances.push(markerDistance);
                markerOrder++;
            });
            let markerDistanceSort;
            markerDistanceSort = markerDistances.sort(function (a, b) {
                return a.dist - b.dist;
            });
            let nearThree = 0;
            for(let nearTour of markerDistanceSort) {
                if(nearTour.order + 1 == $('.tourCode').val()) {
                    continue;
                }
                if(nearTour.dist > 30000) {
                    break;
                }
                if(nearThree > 2) {
                    break;
                }
                if(tourLocInfo[nearTour.order].category == "동물병원") {
                    break;
                }
                $('.nearTourImageBox').last().append("<a></a>");
                let url = "/tour/tourDetail?no=" + (Number(nearTour.order)+1);
                $('.nearTourImageBox a').last().prop("href", url);
                $('.nearTourImageBox a').last().append("<div class=\"nearImageAndText\"></div>");
                $('.nearImageAndText').last().append("<img class=\"nearTourImage\" width=\"325px\" height=\"325px\"/>");
                if(tourLocInfo[nearTour.order].photo != "") {
                    $('.nearImageAndText img').last().prop("src", tourLocInfo[nearTour.order].photo);
                } else {
                    $('.nearImageAndText img').last().prop("src", "/images/dogTour.png");
                    $('.nearImageAndText img').last().prop("title", "대체 이미지");
                }
                $('.nearImageAndText').last().append("<p class=\"nearImageText\"></p>");
                $('.nearImageAndText p').last().append(tourLocInfo[nearTour.order].title);
                $('.nearImageText').last().prop("title", tourLocInfo[nearTour.order].title);
                $('.nearTourImageBox').last().append("<div class=\"shortWhiteSpace\"></div>");
                nearThree++;
            }
            if(nearThree == 0) {
                $('#nearTourInfo').remove();
            }
        },
        error:function(){
            console.log("통신에러3");
        }
    });

    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    let no = urlParam.get("no");
    let toUrl = "/tour/bookmark/getTourBookmark?no=" + no;
    $.ajax({
        type:"get",
        url:toUrl,
        dataType:"json",
        success: function(data) {
            if(data == 1) {
                $('.likeListImageLeft').prop('src', '/images/likeListClicked.png');
            } else {
                $('.likeListImageLeft').prop('src', '/images/likeList.png');
            }
        },
        error:function(){
            console.log("통신에러3");
        }
    });
});