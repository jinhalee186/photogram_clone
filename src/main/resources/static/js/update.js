// (1) 회원정보 수정
function update(userId, event) {
	
	event.PreventDefault(); //폼태그 액션 막기
	
	//profileUpdate 는 가져오려는 form 의 id 값 
	//serialize() 를 통해 form 모든 정보 가져오기 
	let data = $("#profileUpdate").serialize();
	
	console.log(data);
	
	$.ajax({
		type: "put",
		url : `/api/user/${userId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res=>{ // HttpStatus 상태코드 200번대 
		console.log("성공", res);
		//location.href = `/user/${userId}`;
	}).fail(error=>{ // HttpStatus 상태코드 200번대가 아닐 때 실패
		console.log("실패", error);
	});
}