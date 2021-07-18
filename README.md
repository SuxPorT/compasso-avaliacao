<h1 align="center">Avaliação Sprint 4</h1>
<p align="center">Desenvolva uma aplicação em Spring Boot que possua três endpoints</p>

Exemplo da request

<p align="center">
  <img src="https://user-images.githubusercontent.com/51892110/126070703-71a531ef-9803-44c3-bec4-b1e85c3d16f5.png" />
</p>

---
## 1. Pessoa
### a. POST /pessoa
- No corpo da requisição é enviado o nome, cpf, salário e os outros atributos conforme a imagem acima.
- O armazenamento de dados pode ser feito no banco de dados h2, ou a sua escolha.
- Todos os campos não podem ser nulos, e precisam ser validados com a anotação ***@Valid*** no endpoint e inserindo o ***@NotBlank*** em cada campo.
- Para inserir dados de pessoa, não pode ser mapeado na classe de domínio Pessoa.
  - Exemplo:
    <code>PessoaForm()</code> ou <code>PessoaRequest()</code>
- O qual será responsável por capturar os dados do corpo da requisição.
- Como a pessoa tem relação de 1 para n com o endereco, é necessário fazer um form e um dto
também.
- Para a resposta, é ncessário utilizar o padrão DTO (Data Transfer Object) para manipular os responses dos serviços.
- Para essa requisição, mapear o array de endereços na requisição de inserção de pessoa.
- Uma dica antes de enviar a pessoa para o banco: enviar primeiro o endereço, capturar o endereço salvo no banco, inserir no relacionamento da pessoa e depois enviar a pessoa com sua relação endereço para o banco de dados.
  - Exemplo:
    <pre><code>Pessoa pessoa = pessoaForm.save(pessoa,pessoaRepository,enderecoRepository);</code></pre>
    - Dentro do método save, realizar a inclusão do endereço com a pessoa.
  - Exemplo de inserção:
    <pre>
    <code>Endereco enderecoSaved = enderecoRepository.save(pessoa.getEndereco());
    Pessoa pessoa = new Pessoa(enderecoSaved)
    Pessoa pessoaSaved = pessoaRepository.save(pessoa);</code>
    </pre>
    - Já que o endereço é um array, é ncessário fazer o getOne dentro do for, capturar o endereço salvo no banco e gravar no arraylist para enviar no final a pessoa com todos os endereços de uma só vez.
    - E retornando a pessoa salva no banco no formato DTO, onde somente se deseja mostrar o nome da pessoa, cpf, cidade e rua.
- Exemplo de response

<p align="center">
  <img src="https://user-images.githubusercontent.com/51892110/126070718-31bfd25c-822c-412e-bf41-4c0c859ca390.png" />
</p>

### b. GET /pessoa
- Nesse método verificar se existe pessoas cadastradas, se existir retornar o <code>ResponseEntity.ok</code>, se não existir retornar o <code>ResponseEntity.notFound</code>.
- A resposta dever ser feita no formato DTO mapeando somente os atributos do response exemplo acima.

### c. GET /pessoa/{id}

### d. PUT /pessoa/{id}

### e. DELETE /pessoa{id}

Para realizar alteração e exclusão, coloque a anotação ***@Transactional***, pois essas classes tem relação com outra classe, e o endereco.
Para realizar a exclusão da pessoa, é preciso excluir o endereço, o qual tem a relação ***@OneToMany***, sendo necessário mapear a exclusão em cascata, pois se excluir a pessoa, não faz sentindo deixar o endereço dele no banco.

- Exemplo:
*<code>@OneToMany(cascade = CascadeType.REMOVE)</code>*

---
## 2. Produto
### a. POST /produto
- No corpo da requisição é enviado a descrição e o preço unitário.
- Todos os campos não podem ser nulos, e precisam ser validados com a anotação ***@Valid*** no endpoint e inserindo o ***@NotBlank*** em cada campo.
- Para inserir dados do produto, não pode ser mapeado na classe de domínio Produto.
  - Exemplo: <code>ProdutoForm()</code> ou <code>ProdutoRequest()</code>
- Onde será responsável por capturar os dados do corpo da requisição.
- Para a resposta, deseja-se utilizar o padrão DTO (Data Transfer Object) para manipular os responses dos serviços.
- Para realizar o cadastro do produto, antes será preciso se autenticar no endpoint /auth, onde retornará um token, que será passado por baixo como Bearer Token.

<p align="center">
  <img src="https://user-images.githubusercontent.com/51892110/126070837-c6b68184-7db2-49c7-8d0f-04ec4d01c447.png" />
</p>

- Para, enfim, enviar o produto na base de dados.

<p align="center">
  <img src="https://user-images.githubusercontent.com/51892110/126070861-45c79cb9-1609-402a-a416-abb20c68249f.png" />
</p>

<p align="center">
  <img src="https://user-images.githubusercontent.com/51892110/126070868-dd71307f-74b3-4dbf-a967-f2222117fe75.png" />
</p>

- Depois de realizer o envio do produto, retornar o seguinte response no formato produtoDto com o status code ***201 created***:

<p align="center">
  <img src="https://user-images.githubusercontent.com/51892110/126070899-dc2aba0e-508c-4c63-ae42-7fbcd6f26be3.png" />
</p>

### b. GET /produto
- Nesse método verificar se existe produtos cadastrados, se existir retornar o <code>ResponseEntity.ok</code>, se não existir retornar o <code>ResponseEntity.notFound</code>.
- A resposta dever ser feita no formato DTO mapeando somente os atributos do response exemplo acima.

### c. GET /produto/{id}

### d. PUT /produto/{id}

### e. DELETE /produto{id}

- No método delete, realizar o delete soft, não deletando o produto e sim alterando ele como status desativado, usando uma variável booleana para ativar e desativar a visualização do produto.

---
## 3. Pedido
### a. POST protected/pedido
- No corpo da requisição é enviado os ids dos produtos, para buscar os produtos cadastrados para realizar o pedido.

<p align="center">
  <img src="https://user-images.githubusercontent.com/51892110/126071005-a9c6ac6a-ad5d-40a5-b2c9-6fa76078c20b.png" />
</p>

- Para inserir dados do produto, não pode ser mapeado na classe de domínio Pedido.
  - Exemplo: <code>PedidoForm()</code> ou <code>PedidoRequest()</code>
- Onde será responsável por capturar os dados do corpo da requisição.
- Para a resposta, deseja-se utilizar o padrão DTO (Data Transfer Object) para manipular os responses dos serviços.
- Para realizar o cadastro do pedido, antes será preciso se autenticar no endpoint /auth, onde retornara um token, que será passado por baixo como Bearer Token.

<p align="center">
  <img src="https://user-images.githubusercontent.com/51892110/126070837-c6b68184-7db2-49c7-8d0f-04ec4d01c447.png" />
</p>

- Para, enfim, enviar o pedido na base de dados.

<p align="center">
  <img src="https://user-images.githubusercontent.com/51892110/126071018-1ceee210-8371-4e64-b62f-621969349b20.png" />
</p>

<p align="center">
  <img src="https://user-images.githubusercontent.com/51892110/126071090-5563e8e4-85d3-4c8b-bfce-03fb5ae5a8f5.png" />
</p>

- Cada id simboliza um produto
  - 1 – Arroz
    - R$ 10.00
  - 2 – Feijão
    - R$ 5.00
  - 3 – Açucar
    - R$ 3.50
  - 4 – Lentilha
    - R$ 4.00

- Dando o total no pedido de 22.50, no momento de salvar calcular o total de produtos que tem no array e inserir no pedido, junto com a data atual.
- Depois de realizar o envio do pedido retornar o seguinte response no formato pedidoDto com o status code ***201 created***:

<p align="center">
  <img src="https://user-images.githubusercontent.com/51892110/126071166-a1e12920-d654-4043-b0ff-4b71d0be4783.png" />
</p>

### b. GET protected/pedido
- Nesse método verificar se existe pedidos cadastrados, se existir retornar o <code>ResponseEntity.ok</code>, se não existir retornar o <code>ResponseEntity.notFound</code>.
- A resposta dever ser feita no formato DTO mapeando somente os atributos do response exemplo acima.

### c. GET protected/pedido/{id}

### d. PUT protected/pedido/{id}

### e. protected/pedido/{id}

No método delete, realizar o delete soft, não deletando o pedido e sim alterando ele como status desativado, usando uma variável booleana para ativar e desativar a visualização do pedido.
Para permitir os acessos aos métodos protegidos, é ncessário habilitar o acesso nas configurações de segurança.

---
Como parte do trabalho e como uma forma de documentar a API, crie a documentação dela pelo Swagger.

## Ferramentas e dependências utilizadas
[![Java Badge](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/en/)
[![Spring Badge](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://start.spring.io/)
[![Eclipse Badge](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)](https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers)
[![Postman Badge](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)](https://www.postman.com/)
[![Git Badge](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)](https://git-scm.com/)
[<img src="https://static1.smartbear.co/swagger/media/assets/images/swagger_logo.svg" width="90px;"></img>](https://swagger.io/)

- **[Spring Initializr](https://start.spring.io/):** gera um projeto de Spring Boot com dependências iniciais de forma rápida. Todas as dependências se encontram no arquivo **[pom.xml](/pom.xml)**.
  * **Projeto Maven com Spring Boot versão 2.5.2 e Java versão 11.**
  * **Spring Data JPA**: persiste dados em SQL com Java Persistence API usando Spring Data e Hibernate.
  * **Validation**: Bean Validation com validador do Hibernate.
  * **Spring Web**: cria aplicações web, incluindo RESTful, usando Spring MVC, utilizando o Apache Tomcat como contêiner integrado padrão.
  * **Spring cache abstraction**: fornece operações relacionadas ao cache, como a capacidade de atualizar o conteúdo do cache, mas não fornece o armazenamento de dados reais.
  * **Spring Security**: autenticação altamente personalizável e estrutura de controle de acesso para aplicações Spring.
  * **Spring Boot Actuator**: suporta endpoints integrados (ou personalizados) que permitem monitorar e gerenciar a aplicação - como a integridade, métricas, sessões, etc.
  * **SpringFox**: documentação de API JSON automatizada para APIs construídas com Spring 
  * **Spring Boot DevTools**: fornece reinicializações rápidas de aplicativos, LiveReload e configurações para uma experiência de desenvolvimento aprimorada.
  * **H2 Database**: fornece um banco de dados em memória que suporta acesso JDBC API e R2DBC, com um aplicativo de console baseado em navegador.
  * **Java JWT: JSON Web Token**: o JJWT pretende ser a biblioteca mais fácil de usar e compreensível para criar e verificar JSON Web Tokens (JWTs) na JVM e Android. 
  * As configurações do DataSource, JPA e H2 se encontram no arquivo **[application.properties](/src/main/resources/application.properties)**.
  * Os registros do banco de dados utilizados como teste se encontram no arquivo **[data.sql](/src/main/resources/data.sql)**.
- **[Eclipse IDE for Java EE Developers](https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers)**: ferramentas para desenvolvedores Java criando aplicativos Java EE e Web, incluindo Java IDE, ferramentas para Java EE, JPA, JSF, Mylyn, EGit e outros.
- **[Postman](https://www.postman.com/)**: plataforma de colaboração para desenvolvimento de API, utilizado para requisições do tipo GET/POST/PUT/DELETE.
- **[Git](https://git-scm.com/)**: sistema de controle de versão distribuído gratuito e de código aberto.
- **[Swagger](https://swagger.io/)**: simplifica o desenvolvimento de API, ajudando a projetar e documentar APIs. A documentação criada para esse projeto se encontra em **[swagger-openapi.yml](/swagger-openapi.yml)**.

## Configurações
- Foi utilizado profiles para simular ambientes de produção e de desenvolvimento.
  - Para testar o ambiente de produção (autenticação ativada) com o <code>Profile("prod")</code>, é necessário configurar os argumentos da VM com o comando <code>-Dspring.profiles.active=prod</code>
  - Para o ambiente de desenvolvimento (autenticação desabilitada) com o <code>Profile("dev")</code>, usa-se a configuração <code>-Dspring.profiles.active=dev</code>.
- Para deletar os dados dos endpoints, é nécessário possuir a autenticação de moderador.
- *Obs.: as senhas criptografadas dos usuários criados para testes são **"123456"***.
