// JavaScript 관련 구글링
// 👉 site:developer.mozilla.org {연관_키워드}
'use strict';

//=====콜백지옥형 함수(start))=====
// 주문하기
function orderAPI(doNext) {
    setTimeout(() => {
        console.log("[주문] 완료!");
        doNext();
    }, 1000);
}
// 결제하기
function paymentAPI(doNext) {
    setTimeout(() => {
        console.log("[결제] 완료!");
        doNext();
    }, 1000);
}
// 배달하기
function deliveryAPI(doNext) {
    setTimeout(() => {
        console.log("[배달] 완료!");
        doNext();
    }, 1000);
}
// 리뷰작성
function reviewAPI(doNext) {
    setTimeout(() => {
        console.log("[리뷰] 완료!");
        doNext();
    }, 1000);
}

/*orderAPI(()=>{
	paymentAPI(()=>{
		deliveryAPI(()=>{
			reviewAPI(()=>{
				console.log("끗");
			})
		})
	});
});*/
//=====콜백지옥형 함수(end)=====


//=====개선된 promise형 함수(start))=====
// 주문하기
function orderAPI() {
	return new Promise((resolve, reject) => {
	    setTimeout(() => {
			if(Math.random()<0.8){
		        console.log("[주문] 완료!");
		        resolve();				
			}else{
				reject("주문 실패");
			}
	    }, 1000);		
	});
}
// 결제하기
function paymentAPI() {
	return new Promise((resolve, reject) => {
	    setTimeout(() => {
			if(Math.random()<0.8){
        		console.log("[결제] 완료!");
		        resolve();				
			}else{
				reject("결제 실패");
			}        	
    	}, 1000);		
	});
}
// 배달하기
function deliveryAPI() {
	return new Promise((resolve, reject) => {
	    setTimeout(() => {
			if(Math.random()<0.8){
	        	console.log("[배달] 완료!");
		        resolve();				
			}else{
				reject("배달 실패");
			}        	
    	}, 1000);		
	});
}
// 리뷰작성
function reviewAPI() {
	return new Promise((resolve, reject) => {
	    setTimeout(() => {
			if(Math.random()<0.8){
		        console.log("[리뷰] 완료!");
		        resolve();				
			}else{
				reject("리뷰 작성 실패");
			}	        
	    }, 1000);
	});
}

orderAPI()
.then(()=>{ return paymentAPI(); })
.then(()=>{ return deliveryAPI(); })
.then(()=>{ return reviewAPI(); })
.catch((result)=>{ console.log(result); })
.finally(()=>{ console.log("끆"); });
//=====개선된 promise형 함수(end))=====



//=====promise예시(start)=====
/*const promise1 = new Promise((resolve, reject)=>{
	
	setTimeout(()=>{
		if(Math.random() < 0.5){
			resolve("성공");
		}else{
			reject("실패");
		}
	}, 1000);
	
});*/

//늬낌이 자바의 try catch final
/*promise1.then((result)=>{ console.log(result); })
.catch((result)=>{ console.log(result); })
.finally(()=>{ console.log("끅"); });*/

//=====promise예시(end)=====