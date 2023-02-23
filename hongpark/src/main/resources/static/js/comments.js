{
	const commentCreateBtn = document.querySelector("#comment-create-btn");
	commentCreateBtn.addEventListener("click", function(){
		const comment = {
			nickname: document.querySelector("#new-comment-nickname").value,
			body: document.querySelector("#new-comment-body").value,
			article_id: document.querySelector("#new-comment-article-id").value
		};
				
		const url = "/api/articles/" + comment.article_id + "/comments";
		fetch(url, {
			method: "post",
			body: JSON.stringify(comment),
			headers: {
				"Content-Type": "application/json"
			}
		}).then(response => {
			const msg = (response.ok) ? "댓글 등록됨" : "댓글 등록 실패";
			alert(msg);
			window.location.reload();
		});
	});
	
}

{
	const commentDeleteBtns = document.querySelectorAll(".comment-delete-btn");
	commentDeleteBtns.forEach(btn => {
		btn.addEventListener("click", (event) => {
			const commentDeleteBtn = event.srcElement;
			const commentId = commentDeleteBtn.getAttribute("data-comment-id");

			const url = `/api/comments/${commentId}`;
			
			fetch(url, {
				method: "delete"
			}).then(response => {
				if(!response.ok){
					alert("댓글 삭제 실패");
					return;
				}
				const target = document.querySelector(`#comments-${commentId}`);
				target.remove();
			});
						
		});
	});
}

{
	const commentEditModal = document.querySelector("#comment-edit-modal");
	commentEditModal.addEventListener("show.bs.modal", function(event){
		
		const button = event.relatedTarget;
		const id  = button.getAttribute("data-bs-id");
		const nickname  = button.getAttribute("data-bs-nickname");
		const body  = button.getAttribute("data-bs-body");
		const articleId  = button.getAttribute("data-bs-article-id");
		
		document.querySelector("#edit-comment-id").value = id;
		document.querySelector("#edit-comment-nickname").value = nickname;
		document.querySelector("#edit-comment-body").value = body;
		document.querySelector("#edit-comment-article-id").value = articleId;
	});
}

{
	const commentUpdateBtns = document.querySelector("#comment-update-btn");
	commentUpdateBtns.addEventListener("click", function(){
			const comment = {
				id: document.querySelector("#edit-comment-id").value,
				nickname: document.querySelector("#edit-comment-nickname").value,
				body: document.querySelector("#edit-comment-body").value,
				article_id: document.querySelector("#edit-comment-article-id").value
			};
						
			const url = "/api/comments/" + comment.id;
			
			fetch(url, {
				method: "PATCH", //뭐임이거 method: "PATCH",
				body: JSON.stringify(comment),
				headers: {
					"Content-Type": "application/json"
				}
			}).then(response => {
				const msg = (response.ok) ? "댓글 수정됨" : "댓글 수정 실패";
				alert(msg);
				window.location.reload();
			});
						
		});	
}

