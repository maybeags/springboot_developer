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