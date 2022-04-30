# pagamento-services

1° Primeira parte - Criar analise modelagem do provedor de logistica.

Executado uma analise e modelagem de dados em cima de um modulo de logistica em cima de requisitos posto do trabalho, neste processo sugiram alguns diagramas ex: (Diagrama de caso de uso, Diagrama de atividade e dragrama de classe).

    Abaixo você encontrará um sumário com as os principais assuntos abordados.

    1.	ANÁLISE	
        1.1.	Requisitos	
        1.2.	Fronteiras	
        1.3.	Entrega	
        1.4.	Partes Envolvidas	
        1.5.	Partes Afetadas	
        1.6.	Sistemas Existentes	
    2.	FAZER MODELAGEM DOS SERVIÇOS	
        2.1.	Delivery	
        2.2.	Mensageiro	
        2.3.	Delivery	
        2.4.	Mensageiro	
        2.5.	Identificar a composição de serviços	
        2.6.	Revisar os agrupamentos de serviços	
        2.7.	Diagrama de classe	

2° Parte Criar projeto orientados a serviços do Provedor de logistica

Toda documentação gerada foi importante para continuidade do projeto, foi preciso criar o modulo de delivery onde será encontrado toda a estrutura do projeto. Abaixo sera mostrado a estrutura desenvolvida.
     
- *Modelo*: Foi necessairo criar o modelo de dados do projeto.
- *Repository*: Onde é responsável por criar toda a parte de persistencia dos dados no banco.
- *Rest*: Onde foram criados todos os enpoitns de pedido que setão consumidos pelo client.
- *Service*: Onde foi tratado toda a logica de negocio do rest e tambem comunicação com cadamdas internas do sistema.
- *Rest*: Onde foram criados todos os enpoitns de pedido que setão consumidos pelo client.

Projetos didáticos utilizados na disciplina DM112.

Descrição alto nível de cada projeto:
     
- *CommonsDM112*: Classes em comum entre os projetos PagamentoDM112 e PedidoDM112.

- *ExemploDM112_GAE*: Cópia modificada do projeto PedidoDM112, que armazena os valores em memória ao invés do banco de dados. Usado para implantação no Google App Engine.

- *ExemploDM112_OS*: Cópia modificada do projeto UtilityDM112. Usado para implantação no Open Shift.

- *ExemploServletDM112*: Exemplo básico de servlet.

- *PagamentoDM112*: Implementação do serviço de orquestração do pagamento. Usa cliente de boleto, pedido e email para consumir os outros serviços.

- *PedidoDM112*: Implementa o serviço de pedidos (REST). Usa hibernate para salvar os pedidos no banco sqlite.

- *UtilityDM112*: Implementa os serviços de Email e Boleto.

- *DeliveryDM112*: Implementa os serviços de entrega. Este serviço reusa os serviços de Pedido e Utility para listar os pedidos que precisam ser entregues, comunicar cliente via e-mail que pedido foi entregue e quando pedido for entregue ele faz update no pedido para status "Delivered"