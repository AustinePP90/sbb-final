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
		userId: 2, // 예시로 고정 사용자 ID 사용
		productId,
		productName,
		productUrl,
		productImage
	};
	await fetch('/api/favorites', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(favorite)
	});
	alert("즐겨찾기에 추가되었습니다");
}