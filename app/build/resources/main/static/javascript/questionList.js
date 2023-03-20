var didScroll;
var lastScrollTop = 0;
var delta = 5;
var floatButtonHeight = $('#question_list_container').outerHeight();

$(window).scroll(function (event){
    didScroll = true;
})

setInterval(function () {
    if(didScroll){
        hasScrolled();
        didScroll = false;
    }
} , 250);

function hasScrolled(){
    var st = $(this).scrollTop();

    if(Math.abs(lastScrollTop - st) <= delta)
        return;

    if(st > lastScrollTop && st > floatButtonHeight){
        $('#question_list_container').removeClass('')
    }
}