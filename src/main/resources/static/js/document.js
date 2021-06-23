$(document).ready(function () {
    $("#docnameerror").hide();
    $("#docdataerror").hide();
    $("#docdata").empty();
    docnameflag = false;
    docdataflag = false;
    $("#docname").keyup(function () {
        docname_validation();
    })


    function docname_validation() {

        var val = $("#docname").val();

        if (val == "") {
            $("#docnameerror").show();
            $("#docnameerror").html("<b>please give doc name </b>")
            $("#docnameerror").css("color", "red");
            docnameflag = false;
        } else {
            $("#docnameerror").hide();
            docnameflag = false;
        }
        return docnameflag;

    }

    $("input[type='file'][name='documentdata']").change(function () {

        var val = $("input[type='file'][name='documentdata']").val();
        var fex = val.substring(val.lastIndexOf(".") + 1);
        var allowed = ["jpg", "jpeg", "doc", "docx"];
        if ($.inArray(fex, allowed )== -1) {
            $("#docdataerror").show();
            $("#docdataerror").html("<b>please give valid file </b>")
            $("#docdataerror").css("color", "red");
            docdataflag = false;

        }
        else
        {

            $("#docdataerror").hide();
            docdataflag = true;
        }
        return docdataflag;

alert("end of the thing")
        }
    )


})