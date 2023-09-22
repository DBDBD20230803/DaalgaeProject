let main = {
    init: function () {
        let _this = this;
        $('#btn').on('click', function() {
        _this.post();
    });
    },
    post : function ( ){
    let data = {
        name: $('#name').val(),
        }
        $.ajax({
            type: 'POST',
            url:'/som',
            dataType:'json/',
            contentType:'application/json; charset=utf-8',
        }).done(function () {
            window.location.href = '/som';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};
main.init();