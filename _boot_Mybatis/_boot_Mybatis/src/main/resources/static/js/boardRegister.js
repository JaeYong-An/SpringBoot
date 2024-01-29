console.log(`register in`);

document.getElementById(`trigger`).addEventListener(`click`,()=>{
    document.getElementById(`files`).click();
})

const regExp = new RegExp("\.(exe|sh|bat|js|dll|msi)$");
const regExpImg = new RegExp("\.(jpg|jpeg|png|bmp|gif)$");
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }else if(fileSize>maxSize){
        return 0;
    }else{
        return 1;
    }
}

document.addEventListener(`change`, (e)=>{
    if(e.target.id==`files`){
        const fileObject = document.getElementById(`files`).files;
        console.log(fileObject)
        document.getElementById(`regBtn`).disabled = false;
        
        const div = document.getElementById(`fileZone`);
        div.innerHTML=``;
        let ul = `<ul class="list-group list-group-flush">`;
        // fileValidation 함수의 리턴 여부를 체크, 모든 파일이 1이어야 가능
        let isOK = 1;
        for(let file of fileObject){
            let validResult = fileValidation(file.name, file.size);
            isOK *= validResult;
            ul += `<li class="list-group-item">`;
            //업로드 가능 여부 표시
            ul += `<div class="ms-2 me-auto">${validResult? `<div class="fw-bold">업로드 가능`:`<div class="fw-bold text-danger">업로드 불가능`}</div>`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge rounded-pill text-bg-${validResult? `primary`:`danger`}">${file.size}</span>`;
            ul += `</li>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul;

        if(isOK==0){
            document.getElementById(`regBtn`).disabled = true;
        }
    }
})