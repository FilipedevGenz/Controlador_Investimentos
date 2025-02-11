# UFRPE-CarteiraDeInvestimentos

## Projeto Carteira de Investimentos

## [Peter Brothers & CO]

### Ideias que inspiram o projeto (sites, blogs, sistemas, fontes de dados)

* [XP Investimentos](https://www.xpi.com.br)
* [Clear Corretora](https://corretora.clear.com.br)
* [Banco Inter](https://inter.co)
* [Nubank](https://nubank.com.br)
* [TradeMap](https://trademap.com.br)
* [Blog do Thiago Nigro - O Primo Rico](https://www.oprimorico.com.br/thiago.nigro/blog/)
* [Me Poupe!](https://mepoupe.com/)

## Integrantes do grupo e Link para o repositório no GitHub

* Arthur Coelho Moraes Matias - [arthurcmm21@gmail.com]
* Filipe Albuquerque Santos Moreira - [filipe.asmoreira0704@gmail.com]
* Pedro Henrique Soares Rossiter - [pedrorossiter@gmail.com]
* Pedro Henrique Tenório Costa - [pedrohtenorioufrpe@gmail.com]
* Pedro Miguel Lima Da Silva Vitorino - [pedro.lima@ufrpe.com]

* link de acesso para o repositório do GitHub [https://github.com/pedrinho-prog/UFRPE-projeto-de-avalia-o/tree/main]

## Descrição geral do projeto

O projeto consiste em um sistema simples de carteira de investimentos programado na linguagem Java. Nele, o usuário (investidor) tem acesso a uma gama de ativos do mercado financeiro disponíveis para compra. Os ativos, ao serem comprados, são direcionados para a carteira de investimentos do cliente. Os ativos alocados em sua carteira podem ser vendidos a depender do seu desejo. Ademais, o sistema é monitorado e gerido por administradores, os quais podem controlar taxas, gerenciar a conta dos usuários e administrar os ativos.

## Diagrama de Classes

![Diagrama de Classes](![Image](https://github.com/user-attachments/assets/1c7e439e-809c-4841-8b00-b17060c54624))

## Funcionalidades do Sistema

### Público-alvo

O programa será utilizado por dois tipos principais de usuários: os clientes/investidores e os administradores (ADMs).

* **Clientes/Investidores**: Indivíduos que desejam aprender sobre investimentos, testar estratégias e gerenciar suas carteiras de ativos financeiros. Eles podem ser iniciantes, experientes ou até mesmo estudantes e entusiastas da área financeira. Seu principal interesse é simular investimentos sem risco financeiro real e acompanhar o desempenho de suas carteiras ao longo do tempo.

* **Administradores**: Responsáveis pela gestão do sistema, manutenção e atualização dos ativos financeiros disponíveis, controle das taxas de mercado e gerenciamento de usuários. Eles garantem o bom funcionamento da plataforma e que as informações estejam sempre atualizadas.

### Serviços Essenciais

#### Para os clientes/investidores

* Gerenciamento de carteiras: adicionar e remover ativos.
* Realização de transações: compra e venda de ativos.
* Visualização de desempenho: acompanhar a performance da carteira ao longo do tempo.
* Simulação de performance: testar estratégias de investimento.
* Geração de relatórios detalhados: sobre transações, rentabilidade e histórico de investimentos.
* Definição de metas de rentabilidade: estabelecer objetivos financeiros.
* Alertas de metas: notificações quando metas são atingidas ou se desviam significativamente.
* Projeções de rentabilidade: estimar a performance futura com base em diferentes indicadores econômicos.

#### Para os administradores

* Gerenciamento de usuários: criação, edição e remoção de contas.
* Manutenção de ativos financeiros: cadastro, edição e remoção de ativos disponíveis.
* Atualização das taxas de mercado: ajustes de taxas de juros, câmbio e outros índices financeiros.
* Monitoramento de atividades: acompanhar ações dos usuários no sistema.
* Geração de relatórios do sistema: obter relatórios sobre o desempenho da plataforma e suas operações.
* Ajustes no sistema: realizar correções ou melhorias no funcionamento do sistema.

## Requisitos Funcionais do projeto

* **RF01**: Gerenciamento de administradores, que podem cadastrar, editar ou remover ativos financeiros do sistema.
* **RF02**: Gerenciamento de usuários, que podem criar contas, realizar login, visualizar e gerenciar suas carteiras de investimentos.
* **RF03**: Gerenciamento da carteira de investimentos do usuário, permitindo a inclusão, edição e remoção de ativos.
* **RF04**: Adição de ativos à carteira do usuário, com informações como tipo de ativo, quantidade, preço de compra, etc.
* **RF05**: Realização de compras e vendas de ativos, incluindo a atualização da carteira e do saldo financeiro do usuário.
* **RF06**: Simulação de performance da carteira, incluindo a variação do valor de cada ativo ao longo do tempo e o impacto disso no valor total da carteira.
* **RF07**: Exibição de gráficos e indicadores financeiros que mostrem a evolução dos ativos ao longo do tempo (ex.: preços, variações diárias, rentabilidade).
* **RF08**: Relatórios detalhados da carteira de investimentos, incluindo compras e vendas realizadas, valor total investido e rentabilidade atual.
* **RF09**: Geração de relatórios comparativos entre o desempenho da carteira do usuário e o mercado (ex.: comparando a carteira com um índice de referência como o Ibovespa).
* **RF10**: Definição de metas de rentabilidade por parte do usuário, com indicadores de progresso.
* **RF11**: Exibição de alertas para o usuário caso sua carteira atinja ou fique distante das metas estabelecidas.
* **RF12**: Validação de saldo do usuário para a realização de compras de ativos, com verificação de que o usuário tem fundos suficientes para a transação.
* **RF13**: Atualização automática dos preços dos ativos com base em dados históricos ou em tempo real, conforme a implementação.
* **RF14**: Simulação do impacto de aportes mensais na carteira do usuário, com base em uma taxa de retorno esperada, utilizando índices como IPCA, SELIC ou dólar. A projeção deve considerar os aportes mensais ao longo do tempo e o crescimento composto dos investimentos.
* **RF15**: Projeção de rentabilidade futura considerando a valorização dos ativos e a inflação (IPCA), taxas de juros (SELIC), ou variação cambial (dólar), de acordo com as condições econômicas para estimar o valor futuro da carteira.

## Cronograma de MVPs

A implementação do sistema será dividida em etapas, com MVPs organizados para priorizar as funcionalidades mais essenciais no início.

### MVP1 - Cadastro e Autenticação de Usuários e Administradores

* **Requisitos:** [RF01, RF02]: Gerenciamento de administradores e usuários, permitindo cadastro, login e autenticação básica.

### **MVP2 - Gerenciamento Básico da Carteira de Investimentos**

* **Requisitos:** [RF03, RF04, RF12]: Gerenciamento e adição de ativos à carteira, com validação de saldo.

### **MVP3 - Transações e Atualização Automática de Preços**

* **Requisitos:** [RF05, RF13]: Realização de compras e vendas, com atualização dinâmica de preços.

### **MVP4 - Simulações e Projeções de Rentabilidade**

* **Requisitos:** [RF06, RF14, RF15]: Simulação de performance e projeção de rentabilidade futura.

### **MVP5 - Relatórios e Comparativos de Performance**

* **Requisitos:** [RF08, RF09]: Relatórios detalhados da carteira e comparações com índices de mercado.

### **MVP6 - Metas, Indicadores e Alertas**

* **Requisitos:** [RF07, RF10, RF11]: Definição de metas financeiras, gráficos e alertas personalizados.
