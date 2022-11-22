async function login() {
            const ac = document.getElementById("account").value
            const pas = document.getElementById("password").value
            console.log(ac);
            console.log(pas);
            const url = './MemberServlet?action=Login&memberAccount=ac&memberPassword=pas';
//                    member/MemberServlet?action=Login&memberAccount=tony&memberPassword=password
            fetch(url)
                .then(res => res.json())
                .then(memVO => {
					const account = memVO.memberAccount;
					const NO= memVO.memberNo;
                    sessionStorage.setItem("account", account);
					sessionStorage.setItem("NO", NO);
					
                });
        }