// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if(deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
        .then(() => {
            alert('삭제가 완료되었습니다.');
            location.replace('/articles');
        });
    });
}
//이 자바스크립트 코드는 HTML에서 id를 delete-btn으로 설정한 엘리먼트를 찾아 그 엘리먼트에서 클릭 이벤트가 발생하면
//fetch() 메서드를 통해 /api/articles DELETE 요청을 보내는 역할을 합니다.
//fetch() 메서드에 이어지는 then() 메서드는 fetch()가 잘 완료되면 연이어 실행되는 메서드입니다.
//alert() 메서드는 then() 메서드가 실행되는 시점에 웹 브라우저 화면으로 삭제가 완료되었음을 알리는 팝업을 띄워주는
//메서드이고, location.replace() 메서드는 실행 시 사용자의 웹 브라우저 화면을 ㄷ현재 주소를 기반에 옮겨주는 역할을 합니다.

//      02 단계 - [삭제] 버튼을 눌렀을 때 삭제하도록 [삭제] 버튼, 즉 button 엘리먼트에 delete-btn이라는 아이디 값을
//          추가하고 앞서 작성한 article.js가 이 화면에서 동작하도록 import합니다.

// 수정 기능
// 1. id가 modify-btn인 엘리먼트 조회
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    // 2. 클릭 이벤트가 감지되면 수정 API 요청
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
            "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
        .then(() => {
            alert('수정이 완료되었습니다.');
            location.replace(`/articles/${id}`);
        });
    });
}

//이 코드는 id가 modify-btn인 엘리먼트를 찾고 그 엘리먼트에서 클릭 이벤트가 발생하면 id가 title, content인 엘리먼트 값을 가져와
//fetch() 메서드를 통해 수정 API로 /api/articles로 PUT 요청을 보냅니다. 요청을 보낼 때는 headers에 요청 형식을 지정하고,
//body에 HTML에 입력한 데이터를 JSON 형식으로 바꿔 보냅니다. 요청이 완료되면 then() 메서드로 마무리 작업을 합니다.
//
//03 단계 - 이제 article.html 파일을 열어 [수정] 버튼에 id값과 클릭 이벤트를 추가합니다. 기존에 입력했던 수정 버튼을 수정하면 됩니다.
//      article.html 파일로 이동합니다.

// 등록 기능
// 1. id가 created-btn인 엘리먼트
const createButton = document.getElementById("create-btn");

if (createButton) {
    // 2. 클릭 이벤트가 감지되면 생성 API 요청
    createButton.addEventListener("click", (event) => {
        fetch("/api/articles", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            }),
        }).then(() => {
            alert("등록 완료되었습니다.");
            location.replace("/articles");
        });
    });
}
//1. id가 create-btn인 엘리먼트를 찾아 그 엘리먼트에서 2. 클릭 이벤트가 발생하면 id가 title, content인 엘리먼트의 값을 가져와
//fetch() 메서드를 통해 생성 API로 /api/articles/ POST 요청을 보내줍니다.
//
//    02 단계 - 계속해서 articleList.html 파일을 수정해 id가 create-btn인 [생성] 버튼을 추가하겠습니다.
//
//        articleList.html 파일로 이동하세요.
