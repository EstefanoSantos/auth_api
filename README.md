Rest API para cadastro e consulta de produtos. Para isso, o usuário deverá fazer autenticação no sistema via login e senha. Seguindo o padrão stateless das API'S RESTFUL, após a autenticação do usuário no sistema, deverá ser gerado um Token JWT que será usado para requisições subsequentes.

Apenas usuários com autorização (role) ADMIN poderão fazer o cadastro de novos produtos. Usuários com role de USER apenas poderão consultar a lista de produtos armazenado na base de dados postgresql.
