# IFC Licitação

Projeto de aplicação para captura de licitações disponíveis no site do IFC. Dividido em duas partes: back-end e front-end.

No back-end, foi desenvolvida uma API RESTful utilizando a linguagem Java e o framework Spring. Essa API busca licitações do site do IFC, minerando o HTML da página para capturar os dados pertinentes e em seguida fazendo um parsing para JSON.

No front-end, foi desenvolvida uma aplicação web que se comunica com o back-end através de requisições AJAX, buscando as licitações ou as marcando como lidas. Foi utilizado o framework Vue.js no desenvolvimento.

As duas aplicações podem ser executadas separadamente, mas o front-end depende do back-end para buscar os dados de licitação.

Como esse projeto foi feito mais com o intuito de mostrar a lógica de programação do que realmente fazer uma aplicação plenamente funcional, alguns pontos do projeto não receberam muita atenção:
- Segurança na busca e envio de informações;
- Rapidez na execução (a busca das licitações no site do IFC se mostrou demorada nos testes);
- Geração de executáveis.

Passos recomendados para rodar a aplicação back-end:
- Instalar Spring Tool Suite ou outra IDE Java que tenha os plug-ins do Spring (no meu caso, instalei o plug-in do Spring no Eclipse);
- Executar a aplicação por dentro da IDE;
- No navegador, acessar localhost:8080/api/licitacoes para mostrar lista de licitações ou acessar localhost:8080/api/marcacao-leitura?id=*[um número inteiro]* para marcar uma das licitações como lida.

Passos recomendados para rodar a aplicação front-end:
- Rodar o back-end;
- Instalar Node.js no computador;
- Abrir cmd, ir para pasta projeto-licitacao-front-end e executar comando *npm run dev -- --port 3000* (foi usada porta 3000 nos testes, mas pode ser outra que não esteja sendo utilizada);
- No navegador, acessar localhost:3000.

A página web mostrará a lista de licitações. Ao marcar uma como lida clicando no botão correspondente, será enviado um AJAX para o back-end marcando a licitação como lida, a div ficará com o fundo verde e o botão sumirá.

Ao dar um refresh na página o conteúdo não será alterado, pois é refeita a requisição via AJAX para buscar as licitações, e as que estiverem marcadas como lidas no back-end serão novamente coloridas com o fundo verde.
