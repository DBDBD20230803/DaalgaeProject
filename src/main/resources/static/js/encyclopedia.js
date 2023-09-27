$(function () {
    let getUrl = $('input[name=getUrl]').val();
    $.ajax({
        url: getUrl,
        success: function(data, status, xhr) {
            // let a = data[0].encycleOrder;
            // let b = a.substring(2, 3);
            // let c = a.substring(4, 5);
            // console.log(b);
            // console.log(c);
            // $('Afterdesc1:eq(0)').after("<div>1111123</div>")
            // for(let replace of data) {
            //
            // }
            // $('.AfterText1 img').prop('src' , data[0].encycleImage);
            // $('.AfterTextSubstring1').text(data[0].encycleTitle);
            // $('.AfterTextContent1').text(data[0].encycleContent);
            let d = 0;
            let e = 0;
            for(let i = 0; i < data.length; i++) {
                let a = data[i].encycleOrder;
                let b = a.substring(2, 3);
                let c = a.substring(4, 5);
                if(d < b) {
                    $('.Afterdesc1').eq(b-1).after("<div class=\"AfterText1\">");
                    d++;
                }
                if(data[i].encycleImage != 'none') {
                    $('.AfterText1').last().append("<img>");
                    $('.AfterText1 img').last().prop('src' , data[i].encycleImage);
                }
                if(data[i].encycleTitle != 'none') {
                    $('.AfterText1').last().append("<div class=\"AfterTextSubstring1\"></div>");
                    $('.AfterTextSubstring1').last().append(data[i].encycleTitle);
                }
                $('.AfterText1').last().append("<div class=\"AfterTextContent1\">");
                $('.AfterTextContent1').last().append(data[i].encycleContent);
            }
        },
        error: function(xhr, status, error) {
            console.log(error);
        }
    });
});

function likeClick(num) {
    console.log(num);
}
