let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
    },

    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        // console.log(data);
        // 통신을 통해서, 3개의 데이터를 json 으로 변경
        $.ajax({
            type: "post",
            url: "/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8", // 바디데이터 타입
            dataType: "json" // 응답 
        }).done(function (resp) {
            alert("회원 가입이 완료 되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init();