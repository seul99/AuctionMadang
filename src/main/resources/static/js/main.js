window.openSearch = function () {
    document.getElementById("searchModal").classList.remove("hidden");
    document.body.style.overflow = "hidden";
};

window.closeSearch = function () {
    document.getElementById("searchModal").classList.add("hidden");
    document.body.style.overflow = "auto";
};

// 파일 개수 세기
document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById("file");
    const previewImg = document.getElementById("preview");

    if (fileInput) {
        fileInput.addEventListener("change", function (event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    previewImg.src = e.target.result;
                    previewImg.style.display = "block";
                };
                reader.readAsDataURL(file);
            }
        });
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById("file");
    const imagePreview = document.getElementById("imagePreview");
    const imageCountLabel = document.getElementById("imageCountLabel");

    if (fileInput && imagePreview && imageCountLabel) {
        fileInput.addEventListener("change", function (event) {
            const files = event.target.files;
            const maxImages = 3;

            imagePreview.innerHTML = "";

            const displayCount = Math.min(files.length, maxImages);
            imageCountLabel.textContent = `상품 이미지 (${displayCount}/1)`;

            console.log("선택된 파일 수:", files.length); // 로그 확인

            for (let i = 0; i < displayCount; i++) {
                const reader = new FileReader();

                reader.onload = function (e) {
                    const img = document.createElement("img");
                    img.src = e.target.result;
                    img.style.maxWidth = "100px";
                    img.style.borderRadius = "10px";
                    img.style.objectFit = "cover";
                    img.style.aspectRatio = "1 / 1";

                    imagePreview.appendChild(img);
                    console.log("이미지 추가됨:", img.src); // 로그 확인
                };

                reader.readAsDataURL(files[i]);
            }
        });
    }
});

let isEmailEditable = false;

function enableEmailEdit() {
    const input = document.getElementById('emailInput');
    const userId = document.getElementById('userIdHidden').value;

    if (!isEmailEditable) {
        // 편집 가능하게
        input.removeAttribute('readonly');
        input.focus();
        isEmailEditable = true;
    } else {
        // 서버에 이메일 업데이트 요청
        const newEmail = input.value;

        fetch('/api/user/email/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userId: userId,
                newEmail: newEmail
            })
        })
            .then(res => {
                if (res.ok) {
                    alert("이메일이 변경 완료");
                    input.setAttribute('readonly', true);
                    isEmailEditable = false;
                } else {
                    alert("변경 실패");
                }
            })
            .catch(err => {
                console.error(err);
                alert("서버 오류");
            });
    }
}

let isPasswordEditable = false;

function togglePasswordEdit() {
    const input = document.getElementById('passwordInput');
    const userId = document.getElementById('userIdHidden').value;

    if (!isPasswordEditable) {
        input.value = ''; // 기존 ******** 제거
        input.removeAttribute('readonly');
        input.focus();
        isPasswordEditable = true;
    } else {
        const newPassword = input.value;

        fetch('/api/user/password/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userId: userId,
                newPassword: newPassword
            })
        })
            .then(res => {
                if (res.ok) {
                    alert("비밀번호가 변경완료");
                    input.setAttribute('readonly', true);
                    input.value = '**************'; // 다시 가려진 값
                    isPasswordEditable = false;
                } else {
                    alert("변경 실패");
                }
            })
            .catch(err => {
                console.error(err);
                alert("서버 오류");
            });
    }
}
let isPhoneEditable = false;

function togglePhoneEdit() {
    const input = document.getElementById('phoneInput');
    const userId = document.getElementById('userIdHidden').value;

    if (!isPhoneEditable) {
        input.removeAttribute('readonly');
        input.focus();
        isPhoneEditable = true;
    } else {
        const newPhoneNumber = input.value;

        fetch('/api/user/phone/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userId: userId,
                newPhoneNumber: newPhoneNumber
            })
        })
            .then(res => {
                if (res.ok) {
                    alert("휴대폰 번호가 변경완료");
                    input.setAttribute('readonly', true);
                    isPhoneEditable = false;
                } else {
                    alert("변경 실패");
                }
            })
            .catch(err => {
                console.error(err);
                alert("서버 오류");
            });
    }
}


function confirmWithdraw() {
    return confirm("정말로 탈퇴하시겠어요? \n삭제 후에는 복구할 수 없습니다.");
}





