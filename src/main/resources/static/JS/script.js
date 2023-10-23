function performSearch() {
    const query = document.getElementById('search-box').value;

    fetch(`/search?query=${query}`)
        .then(response => response.json())
        .then(data => {
            // Faça algo com os resultados, por exemplo, exiba-os em uma lista
            console.log(data);
        })
        .catch(error => {
            console.error('Erro na pesquisa:', error);
        });
}