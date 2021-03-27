// Endereço da API. Definido como localhost:8080 para testes
const endereco = "http://localhost:8080/api/";

// AJAX para requisitar licitações da API
var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
      doProcessaLicitacoes(this.responseText);
  }
};
xhttp.open("GET", endereco + "licitacoes", true);
xhttp.send();

function doProcessaLicitacoes(respostaRequisicao) {
  // Parsing da String de resposta para um objeto JSON
  var licitacoes = JSON.parse(respostaRequisicao).licitacoes;

  // Componente vue que ficará acoplado à div principal no HTML
  new Vue({
    el: '#app',
    data: {
      licitacoes: licitacoes
    },
    methods: {
      marcarLido(licitacao) {
        // AJAX para marcar licitação como lida na API
        $.ajax({
          url: endereco + "marcacao-leitura",
          type: "GET",
          data: { 
            id: licitacao.id
          }
        })
      
        licitacao.lido = true;
      }
    }
  });
};