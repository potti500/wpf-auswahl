$(document).ready(function(){

    var $checkboxes = $('#zaehlen td input[type="checkbox"]');
        
    $checkboxes.change(function(){
        var countCheckedCheckboxes = $checkboxes.filter(':checked').length;
        $('#count-checked-checkboxes').text(countCheckedCheckboxes);
        
        $('#anzahl-wpfs').val(countCheckedCheckboxes);
    });

});

