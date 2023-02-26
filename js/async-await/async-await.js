// JavaScript 관련 구글링
// 👉 site:developer.mozilla.org {연관_키워드}
'use strict';

//기존 promise
function myPromise(){
	return new Promise((resolve, reject) => {
		resolve("apple");
	});
}
console.log(myPromise());

// async -> 알아서 promise 반환
async function myAsync(){
	return "apple";
}
console.log(myAsync());

// await은 async 안에서만 사용 가능
//비동기 결과를 기다림
// promise chaining 개선 

// 당근 준비
function carrotPromise(){
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			if(Math.random()<0.5){
				resolve("carrot");
			}else{
				reject("watermelon");
			}
		}, 1000);
	});
}

// promise로 요리하기 : 당근 -> 당근스프
/*function cookCarrotSoup(){
	carrotPromise()
	.then((carrot) => {
		console.log(`${carrot} 스프를 만듬`);
	});
}*/

//async와 await으로 개선해서 요리하기 : 당근 -> 당근스프
async function cookCarrotSoup(){
	try{
		const carrot = await carrotPromise();
		console.log(`${carrot} 스프를 만듬`);
		return "당근스프"; // resolve("당근스프")
	}catch(err){
		console.log(`요리실패: ${err}`);
		//return "망함"; // reject("망함s"); 이거 대신 에러 던지기
		throw new Error("망함");
	}finally{
		console.log("요리 끗");		
	}
}

//여기서 다시 체이닝 호출도 가능하다
cookCarrotSoup()
	.then((data) => { console.log(data); })
	.catch((err) => { console.log(err); })
	.finally(() => { console.log("진짜끗"); });
