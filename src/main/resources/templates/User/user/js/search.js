$(function () {
    $(".bodys p").not(":first").hide();
    $(".searchbox ul li").mouseover(function () {
        var index = $(this).index();
        if (index == 0) {
            $(this).find("a").addClass("style1");
            $("li").eq(1).find("a").removeClass("style2");
            $("li").eq(2).find("a").removeClass("style3");
        }
        if (index == 1) {
            $(this).find("a").addClass("style2");
            $("li").eq(0).find("a").removeClass("style1");
            $("li").eq(2).find("a").removeClass("style3");
        }
        if (index == 2) {
            $(this).find("a").addClass("style3");
            $("li").eq(0).find("a").removeClass("style1");
            $("li").eq(1).find("a").removeClass("style2");
        }
        var index = $(this).index();
        $(".bodys p").eq(index).show().siblings().hide();
    });
});
