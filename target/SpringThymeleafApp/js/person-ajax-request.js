/**
 * Created by gsigua on 12/11/2015.
 */
function sendFormData() {

    var data = {};

    data.name = document.getElementById('name').value;
    data.age = document.getElementById('age').value;

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        mimeType: 'application/json',
        async: true,
        url: "/process-ajax",
        data: JSON.stringify(data),
        success: function(data) {
            console.log(data);
        }
    });
}