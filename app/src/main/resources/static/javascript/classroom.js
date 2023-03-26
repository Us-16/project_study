$(document).ready(function(){

    var tag = {};
    var counter = 0;
    var item = "";


    function addTag(value){
        tag[counter] = value;
        counter ++;
        $('#test2').val(value);
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
                    console.log(tagValue);
                    addTag(tagValue);
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
    });

    $('#copyBtn').click(function(){
        copyToClipboard(document.querySelector('#code').textContent);
        console.log(document.querySelector('#code'));
        alert('주소를 복사했습니다.\n' + document.querySelector('#code').textContent);
    })
});
$('#c_create_title').on('keyup', function (){
    var content = $(this).val().length;

    if(content < 3){
        alert('제목은 최소 3자 이상임');
    }
});

function copyToClipboard(val){
    var t = document.createElement("textarea");
    document.body.appendChild(t);
    t.value = val;
    t.select();
    document.execCommand('copy');
    document.body.removeChild(t);
}
