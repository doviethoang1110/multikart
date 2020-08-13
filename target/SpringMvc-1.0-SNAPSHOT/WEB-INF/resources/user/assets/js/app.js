$('.size-select ul li').on('click', function(e) {
    $(".size-select ul li").removeClass("active");
    $(this).addClass("active");
});
$('.material-select ul li').on('click', function(e) {
    $(".material-select ul li").removeClass("active");
    $(this).addClass("active");
});
$('ul.color-select li').on('click', function(e) {
    $("ul.color-select li").removeClass("active");
    $(this).addClass("active");
});
$(document).ready(function () {
    $("ul.color-select li:first-child").addClass("active");
    // $(".size-select ul li:first-child").addClass("active");
    // $(".material-select ul li:first-child").addClass("active");
});