$(document).ready(function () {
    partcodeflag = false
    partwidthflag = false;
    partlengthflag = false;
    partheightflag = false;
    partbasecostflag = false;
    partbasecurrencyflag = false;
    partuommodelflag = false;
    partdescriptionflag = false;

    $("#partsubmit").submit(function () {


        $("#partcode").keypress(function () {
            var val = $("#partcode").val();
            if (val == "") {
                $("#partcodeerror").show();
                $("#partcodeerror").html("<b>please give a partcode</b>");
                $("#partcodeerror").css("color", "red");
                partcodeflag = false;
            } else {
                $("#partcodeerror").hide();
                partcodeflag = true;
            }
            return partcodeflag;
        })

        $("#partlength").keypress(function () {
            var val = $("#partlength").val();
            if (val <= 0) {
                $("#partlengtherror").show();
                $("#partlengtherror").html("<b>please set Partlen morethan 0</b>");
                $("#partlengtherror").css("color", "red");
                partlengthflag = false;
            } else {
                $("#partlengtherror").hide();
                partlengthflag = true;
            }
            return partlengthflag;
        })
        $("#partheight").keypress(function () {
            var val = $("#partheight").val();
            if (val == "") {
                $("#partheighterror").show();
                $("#partheighterror").html("<b>please set partlen > 0</b>");
                $("#partheighterror").css("color", "red");
                partheightflag = false;
            } else {
                $("#partheighterror").hide();
                partheightflag = true;
            }
            return partheightflag;
        })
        $("#partwidth").keypress(function () {
            var val = $("#partwidth").val();
            if (val <= 0) {
                $("#partwidtherror").show();
                $("#partwidtherror").html("<b>please give a partwidthflag > 0</b>");
                $("#partwidtherror").css("color", "red");
                partwidthflag = false;
            } else {
                $("#partwidtherror").hide();
                partwidthflag = true;
            }
            return partwidthflag;
        })
        $("#basecost").keypress(function () {
            var val = $("#basecost").val();
            if (val <= 0) {
                $("#basecosterror").show();
                $("#basecosterror").html("<b>please give a partcode</b>");
                $("#basecosterror").css("color", "red");
                partbasecostflag = false;
            } else {
                $("#basecosterror").hide();
                partbasecostflag = true;
            }
            return partbasecostflag;
        })


        $("basecurrency").change(function ()
        {
            var val=$("#basecurrency").val();
            if (val=="") {
                $("#basecurrencyerror").show();
                $("#basecurrencyerror").html("<b>Please choose one base currency</b>");
                $("#basecurrencyerror").css("color", "red");
                partbasecurrencyflag = false;
            } else {
                $("#basecurrencyerror").hide();
                partbasecurrencyflag= true;
            }
            return partbasecurrencyflag;
        })
        $("#description").keypress(function () {
            var val = $("#description").val();
            if (val.length < 10) {
                $("#descriptionerror").show();
                $("#descriptionerror").html("<b>please give description >10 </b>");
                $("#descriptionerror").css("color", "red");
                partdescriptionflag = false;
            } else if (val.length > 20) {
                $("#descriptionerror").show();
                $("#descriptionerror").html("<b>please give description <20 </b>");
                $("#descriptionerror").css("color", "pink");
                partdescriptionflag = false;
            } else {
                $("#descriptionerror").hide();
                partdescriptionflag = true;
            }
            return partdescriptionflag;


        })//end of description flag


        if (partcodeflag && partwidthflag && partlengthflag && partheightflag && partbasecostflag && partbasecurrencyflag)
        {
            return true;
        }
        else
            {
            return false;
        }

    })


})