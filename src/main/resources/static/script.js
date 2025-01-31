function mascaraCEP(cep) {
    return cep.replace(/\D/g, '')
              .replace(/^(\d{5})(\d{3})$/, '$1-$2');
}

function aplicarMascara() {
    var cep = document.getElementById('cep').value;
    cep = mascaraCEP(cep);
    document.getElementById('cep').value = cep;
}

function buscarCEP() {
    var cep = document.getElementById('cep').value.replace("-", "");
    var url = 'http://localhost:8080/cep/' + cep;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.erro) {
                alert('CEP não encontrado.');
                return;
            }

            document.getElementById('cep-result').innerText = data.cep;
            document.getElementById('logradouro-result').innerText = data.logradouro;
            document.getElementById('complemento-result').innerText = data.complemento || 'Não disponível';
            document.getElementById('unidade-result').innerText = data.unidade || 'Não disponível';
            document.getElementById('bairro-result').innerText = data.bairro;
            document.getElementById('cidade-result').innerText = data.localidade;
            document.getElementById('estado-result').innerText = data.uf;
            document.getElementById('regiao-result').innerText = data.regiao || '';
            document.getElementById('ibge-result').innerText = data.ibge;
            document.getElementById('gia-result').innerText = data.gia || 'Não disponível';
            document.getElementById('ddd-result').innerText = data.ddd;
            document.getElementById('siafi-result').innerText = data.siafi;
        })
        .catch(error => {
            alert('Erro ao buscar o CEP. Tente novamente mais tarde.');
        });
}
