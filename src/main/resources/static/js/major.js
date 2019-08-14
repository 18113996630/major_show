$(document).ready(function () {
    $('[data-toggle="offcanvas"]').click(function () {
        $('.row-offcanvas').toggleClass('active')
    });
});

function refreshMajors(subject) {
    $('#majors-contain').load('/majors/'+subject.id+'/1?size=20')
}