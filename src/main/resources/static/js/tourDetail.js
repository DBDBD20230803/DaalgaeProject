function likeClick() {

}
$(function () {
    let page = $('.findPage').val();
    page = page.replaceAll("\"\"", "\"");
    page = page.replaceAll("\"\<", "\<");
    page = page.replaceAll("\>\"", "\>");
    page = page.replaceAll("\>h", "\>\<div class=\"homePage\"\>h");
    page = page.replaceAll("\<\/a\>", "\<\/div\>\<\/a\>");


    console.log(page);
    $('.tourDescText span').last().append(page + "&nbsp;");
    $('.tel').append("&nbsp;");
    $('.useTime').append("&nbsp;");
    $('.descTitle').append("&nbsp;");
    $('.descTitle').append("&nbsp;");

    let tourMapx = $('.mapx').val();
    let tourMapy = $('.mapy').val();
    let tourTitle = $('.title').val();
    let container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(tourMapy, tourMapx),
        level: 3
    };

    let map = new kakao.maps.Map(container, options);
    let markerPosition  = new kakao.maps.LatLng(tourMapy, tourMapx);

    let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
    let imageSize = new kakao.maps.Size(24, 35);

    // 마커 이미지를 생성합니다
    let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    // 마커를 생성합니다
    let marker = new kakao.maps.Marker({
        map: map,
        position: markerPosition,
        title : tourTitle,
        image : markerImage
    });
});