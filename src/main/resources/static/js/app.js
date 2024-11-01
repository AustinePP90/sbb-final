// meta 태그에서 CSRF 토큰과 헤더 이름 가져오기
const csrfToken = document.querySelector('meta[name="_csrf"]').content;
const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

async function searchProducts() {
	const query = document.getElementById("query").value;
	const response = await fetch(`/api/search?query=${query}`);
	const products = await response.json();
	
	const resultsDiv = document.getElementById("results");
	resultsDiv.innerHTML = '';
	
	products.forEach(product => {
		const productDiv = document.createElement("div");
		productDiv.innerHTML = `
            <h3>${product.title}</h3>
            <img src="${product.image}" alt="${product.title}">
			<p>${product.productId}</p>
            <button onclick="addToFavorites('${product.productId}', '${product.title}', '${product.link}', '${product.image}')">즐겨찾기 추가</button>
        `;
		resultsDiv.appendChild(productDiv);
	});
}

async function addToFavorites(productId, productName, productUrl, productImage) {
	const favorite = {
		productId,
		productName,
		productUrl,
		productImage
	};
	await fetch('/api/favorites', {
		method: 'POST',
		headers: { 
			'Content-Type': 'application/json',
			[csrfHeader]: csrfToken // CSRF 헤더와 토큰 추가
		 },
		body: JSON.stringify(favorite)
	});
	alert("즐겨찾기에 추가되었습니다");
}