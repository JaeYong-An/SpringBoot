console.log("boardDetail.js");

document.getElementById(`listBtn`).addEventListener(`click`,()=>{
    // location.href="/board/list";
    location.replace(`/board/list`);
});

document.getElementById(`delBtn`).addEventListener(`click`,()=>{
    document.getElementById(`delForm`).submit();

    const delForm = document.getElementById(`delForm`);
    delForm.bno.remove();
    delForm.setAttribute(`action`,`/board/remove`);
    delForm.setAttribute(`method`,`post`);
    delForm.submit();
});

document.getElementById(`modBtn`).addEventListener(`click`,()=>{
    document.getElementById(`title`).readOnly=false;
    document.getElementById(`content`).readOnly=false;

    let modBtn=document.createElement(`button`);
    modBtn.setAttribute(`type`,`submit`);
    modBtn.classList.add(`btn`,`btn-outline-warning`);

    document.getElementById(`modForm`).appendChild(modBtn);
    document.getElementById(`modBtn`).remove();
    document.getElementById(`delBtn`).remove();
});