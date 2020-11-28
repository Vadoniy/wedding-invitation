function send() {
    let data = {
        "name": $("#nameInput").val(),
        "contactPhone": $("#phoneInput").val(),
        "plusOne": $("#plusOneSelect").val(),
        "isAlcoholic": $("#isAlcoholicSelect").val(),
        "isOvernight": $("#isOvernightSelect").val(),
        "withChildren": $("#withChildrenSelect").val(),
        "comment": $("#commentTextarea").val()
    };
    console.log(data);
    $.ajax({
        url: "send",
        type:"POST",
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(data) {
            if (data) {
                $("#nameInput").val("");
                $("#phoneInput").val("");
                $("#plusOneSelect").val("false");
                $("#isAlcoholicSelect").val("true");
                $("#isOvernightSelect").val("false");
                $("#withChildrenSelect").val("false");
                $("#commentTextarea").val("");
            }
        }
    });
}