# Desafio

Nesse desafio será desenvolvido uma API fictícia para uma empresa de cursos de programação, onde em um primeiro momento, você deverá utilizar o CRUD, para criação de cursos.

### Estrututa dos cursos

- `id` - Identificador único de cada curso
- `name` - Nome do curso
- `category` - Categoria do curso
- `Active` - Define se o curso está ativo ou não
- `created_at` - Data de quando o curso foi criado.
- `updated_at` - Deve ser sempre alterado para a data de quando o curso for atualizada.

### Requisitos Funcionais

- [] Deve ser possível criar um curso no banco de dados, enviando os campos `name` e `category` por meio do `body` da requisição.
  Ao criar um curso, os campos: `id`, `created_at`e`updated_at` devem ser preenchidos automaticamente, conforme a orientação das propriedades acima.

- [] Deve ser possível listar todas os cursos salvos no banco de dados.
  Também deve ser possível realizar uma busca, filtrando os cursos pelo `name` e `category`.

- [] Deve ser possível atualizar um curso pelo `id`.
  No `body` da requisição, deve receber somente o `name` e/ou `category` para serem atualizados.
  Se for enviado somente o `name`, significa que o `category` não pode ser atualizado e vice-versa. Além disso `active` for informado nessa rota, ele deverá ser ignorado, pois a rota que deverá fazer essa atualização, é a de patch.

- [] Deve ser possível remover um curso pelo `id`.

- [] Essa rota servirá para marcar se o curso está ativo ou não, ou seja, um toggle entre true or false.

#### Extra

- [] Validar se as propriedades name e category das rotas POST e PUT estão presentes no body da requisição.

- [] Para a parte de definição se o curso está ativo ou não, você pode definir um ENUM(enumerador) para fazer esse ‘’check’’.

- [] Para tratar as exceções, você pode se desafiar e criar as excpetion 😃
