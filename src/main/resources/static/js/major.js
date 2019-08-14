$(document).ready(function () {
    $('[data-toggle="offcanvas"]').click(function () {
        $('.row-offcanvas').toggleClass('active')
    });
});

function refreshMajors(subject) {
    $.get('/majors/'+subject.id)
}