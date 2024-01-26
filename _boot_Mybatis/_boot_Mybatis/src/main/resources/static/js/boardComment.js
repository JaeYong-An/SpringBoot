console.log(`board comment`);

document.getElementById(`cmtPostBtn`).addEventListener(`click`, ()=>{
    const cmtText = document.getElementById(`cmtText`);
    if(cmtText.value == null || cmtText.value ==``){
        alert(`댓글을 입력하세요`);
        cmtText.focus();
        return false;
    }else{
        let cmtData={
            bno:bnoVal,
            writer:document.getElementById(`cmtWriter`).innerText,
            content:cmtText.value
        };
        console.log(cmtData);
        postCommentToServer(cmtData).then(result =>{
            if(result==`1`){
                alert(`댓글 등록 성공`);
                cmtText.value=``;
            }
            //화면에 뿌리기
            spreadCommentList(bnoVal);
        })
    }

})

async function postCommentToServer(cmtData){
    try {
        const url = `/comment/post`;
        const config ={
            method:`post`,
            headers:{
                'content-type':`application/json; charset=utf-8`
            },
            body:JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function getCommentListFromServer(bno, page){
    try {
        const resp = await fetch("/comment/"+bno+"/"+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}


function spreadCommentList(bno, page=1){
    getCommentListFromServer(bno, page).then(result=>{
        console.log(result);
        //댓글 뿌리기
        const ul = document.getElementById(`cmtListArea`);
        if(result.cmtList.length>0){
            // 댓글을 다시 뿌릴 때 기존 값을 삭제 x , 1page일 경우만 삭제
            if(page==1){
                ul.innerHTML=``;
            }
            for(let cvo of result.cmtList){
                let li = `<li class="list-group-item" data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
                li += `<div class="mb-3">`;
                li += `<div class="fw-bold"> ${cvo.writer}</div>`;
                li += `${cvo.content}`;
                li += `</div>`;
                li += `<span>${cvo.modAt}</span>`;
                li += `<button type="button" class="btn btn-primary mod ms-1 me-1" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                li += `<button type="button" class="btn btn-primary del">X</button>`;
                li += `</li>`;
                ul.innerHTML += li;
            }
            let moreBtn = document.getElementById(`moreBtn`);
            console.log(moreBtn);
            //more버튼 표시 조건
            if(result.pgvo.pageNo < result.endPage){
                moreBtn.style.visibility = `visible`;
                moreBtn.dataset.page = page+1;
            }else{
                moreBtn.style.visibility = `hidden`;
            }
        }else{
            let li = `<li class="list-group-item">Comment List Empty</li>`;
            ul.innerHTML = li;
        }
    })
}