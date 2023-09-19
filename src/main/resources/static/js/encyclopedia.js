$(function () {
    $.ajax({
        url: "getKnowAfterAdopt",
        success: function(data, status, xhr) {
            console.log(data);
            console.log(data[0].encycleImage);
            console.log(data[0].encycleTitle);
            console.log(data[0].encycleOrder);
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
                console.log(b);
                console.log(d);
                console.log("--");
                console.log(c);
                console.log(e);
                console.log("--");
                if(d < b) {
                    $('.Afterdesc1').eq(b-1).after("<div class=\"AfterText1\">");
                    d++;
                }
                $('.AfterText1').last().append("<img>");
                $('.AfterText1 img').last().prop('src' , data[i].encycleImage);
                $('.AfterText1').last().append("<div class=\"AfterTextSubstring1\"></div>");
                $('.AfterTextSubstring1').last().append(data[i].encycleTitle);
                $('.AfterText1').last().append("<div class=\"AfterTextContent1\">");
                $('.AfterTextContent1').last().append(data[i].encycleContent);
            }
        },
        error: function(xhr, status, error) {
            console.log(error);
        }
    });
});
