document.querySelector('#info').addEventListener('click', searchByName);
const ItemContainer = document.querySelector("#ItemContainer");
//const id = sessionStorage.getItem('\${MemberNo}');

function searchByName() {
	         ItemContainer.innerHTML =' ';
	const url = './getOneMem';
	fetch(url)
		.then(res => res.json())
		.then(memVO => {

			ItemContainer.insertAdjacentHTML('beforeend', `<div class="content">
			<table>
        <tr>
            <td>會員編號 :</td><br>
            <td>${memVO.memberNo}</td>
        </tr>
        <tr>
            <td>會員密碼 :</td><br>
            <td>${memVO.memberPassword}</td>

        </tr>
        <tr>
            <td>會員姓名 :</td><br>
			<td>${memVO.memberName}</td>
        </tr>
        <tr>
            <td>會員電話 :</td><br>
			<td>${memVO.memberPhone}</td>
        </tr>
        <tr>
            <td>會員暱稱 :</td><br>
			<td>${memVO.memberNickname}</td>
        </tr>
        <tr>
            <td>會員地址 :</td><br>
			<td>${memVO.memberAddress}</td>
        </tr>
        <tr>
            <td>會員e-mail :</td><br>
            <td>${memVO.memberEmail}</td>
        </tr>       		 													
    </table>
    <tr>	
    		<FORM METHOD="post" ACTION="member/MemberServlet">
				<b></b> 
				<input type="hidden" name="memberNo" value="${memVO.memberNo}"> 
				<input type="hidden" name="action" value="getOne_For_Update">
				<input type="submit" value="修改">
					
			</FORM>	
		<tr>
							</div>`




			);
		});
} 