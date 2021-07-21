<script th:inline="javascript">
    $(function(){

};

    function getReplyList(){

    var seq = [[${boardSeq}]];
    var link = "comment/" + seq;

    $.ajax({
    url : link,
    data : {seq:seq},
    dataType : "json",
    success : function(data){
    console.log(data);

    $tableBody = $("#commentTable");
    $tableBody.html("");

    /*$("#rCount").text("댓글("+data.length+")");*/

    if(data.length > 0){
    for(var i in data){

    var $tr = $("<tr>");

    var $rWriter = $("<td width='100'>").text(data[i].rWriter);
    var $rContent = $("<td>").text(data[i].rContent);
    var $rCreateDate = $("<td width='100'>").text(data[i].rCreateDate);

    $tr.append($rWriter);
    $tr.append($rContent);
    $tr.append($rCreateDate);

    $tableBody.append($tr);
}
}else{
    var $tr = $("<tr>");
    var $rContent = $("<td colspan='3'>").text("등록된 댓글이 없습니다.");

    $tr.append($rContent);
    $tableBody.append($tr);
}

},
    error : function(e){
    console.log(e);
}
});
}


</script>