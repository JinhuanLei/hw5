function calculate(operation) {
    // alert(operation);
    var isSelected = $("#default").prop("checked");
    var x = $("#inputX").val();
    var y = $("#inputY").val();
    var hash = $("#hash").val();
    // alert(isSelected);
    if (isSelected) {
        $.ajax({
            type: "POST",
            url: '/api/v1/' + operation+"?x="+x+"&y="+y,
            headers: {
                'hash-alg': hash
            },
            // data: {"x": x, "y": y},
            success: function (data) {
                $("#result").val(data.x+""+data.operation+""+data.y+"="+data.result);
                // $("#result").val(data.result);
                $("#hashInfer").val(data.hash);
                console.log(data);
            },
            error: function (data) {
                console.log(data.responseJSON.message);

                    alert(data.responseJSON.message);


            }
        });
    } else {
        $.ajax({
            type: "GET",
            url: '/api/v1/' + operation,
            data: {"x": x, "y": y},
            headers: {
                'hash-alg': hash
            },
            success: function (data) {
                $("#result").val(data.x+""+data.operation+""+data.y+"="+data.result);
                // $("#result").val(data.result);
                $("#hashInfer").val(data.hash);
                console.log(data);
            },
            error: function (data) {
                console.log(data.responseJSON.message);
                alert(data.responseJSON.message);
            }
        });

    }

}