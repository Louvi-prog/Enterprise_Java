const nonClick=document.querySelectorAll(".non-click");
var giMenuDuration=700;

function handleClick(event) {
    // div에서 모든 clik 제거한다.
    nonClick.forEach((e) => {
        e.classList.remove("click");
    });
    //클릭한 div만 "click" 클래스 추가
    event.target.classList.add("click");
}

nonClick.forEach((e)=>{
    e.addEventListener("click",handleClick);
});

//오른쪽으로 슬라이드
function showMenu() {
    $('.')
}

//왼쪽으로 슬라이드후 닫음
function hideMenu() {

}
