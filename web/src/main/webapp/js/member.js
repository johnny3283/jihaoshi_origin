document.querySelector('#info').addEventListener('click', searchByName);
const ItemContainer = document.querySelector("#ItemContainer");
//const id = sessionStorage.getItem('\${MemberNo}');

function searchByName() {
	         ItemContainer.innerHTML =' ';
	const url = './getOneMem';
	fetch(url)
		.then(res => res.json())
		.then(member => {

			ItemContainer.insertAdjacentHTML('beforeend', `<div class="content">
			<table>
        <tr>
            <td>會員編號 :</td><br>
            <td>${member.memberNo}</td>
        </tr>
        <tr>
            <td>會員密碼 :</td><br>
            <td>${member.memberPassword}</td>

        </tr>
        <tr>
            <td>會員姓名 :</td><br>
			<td>${member.memberName}</td>
        </tr>
        <tr>
            <td>會員電話 :</td><br>
			<td>${member.memberPhone}</td>
        </tr>
        <tr>
            <td>會員暱稱 :</td><br>
			<td>${member.memberNickname}</td>
        </tr>
        <tr>
            <td>會員地址 :</td><br>
			<td>${member.memberAddress}</td>
        </tr>
        <tr>
            <td>會員e-mail :</td><br>
            <td>${member.memberEmail}</td>
        </tr>       		 													
    </table>
    <tr>	
    		<FORM METHOD="post" ACTION="member/MemberServlet">
				<b></b> 
				<input type="hidden" name="memberNo" value="${member.memberNo}"> 
				<input type="hidden" name="action" value="getOne_For_Update">
				<input type="submit" value="修改">
					
			</FORM>	
		<tr>
							</div>`




			);
		});
} 