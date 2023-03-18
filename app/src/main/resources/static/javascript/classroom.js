$(document).ready(function(){

    var tag = {};
    var counter = 0;

    function addTag(value){
        tag[counter] = value;
        counter ++;
    }

    function marginTag(){
        return Object.values(tag).filter(function(word){
            return word !== "";
        });
    }

    $('#tag-form').on('submit', function(e){
        var value = marginTag();
        $('#rdTag').val(value);
        $(this).submit();
    });

    $('#tag').on("keypress", function(e){

        var self=$(this);
        if(e.key === 'Enter' || e.keyCode === 32){
            //e.preventDefault();
            var tagValue = self.val();
            if(tagValue !== ''){
                var result = Object.values(tag).filter(function(word){
                    return word === tagValue;
                })
                if(result.length === 0){
                    $('#tag-list').append('<li class="tag-item">' + tagValue + '<span class="del-btn" idx="' + counter + '">x</span></li>');
                    $('#tag').val('');
                    addTag(tagValue);
                    self.value("");
                }else{
                    alert("Duplicate Tag values");
                }
            }

            e.preventDefault();
        }
    });
    $(document).on('click', '.del-btn', function(e){
        var index = $(this).attr('idx');
        tag[index] = '';
        $(this).parent().remove();
    })
});