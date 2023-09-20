$(document).ready( function() {
    $('#headers').css("position", "fixed");
    $('#headers').css("width", "100%");
    $('#headers').css("z-index", "5");
    $('#menu').css("margin-top", "-16px");

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
    })
});
