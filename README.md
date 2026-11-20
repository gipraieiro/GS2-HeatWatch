# HeatWatch – Sistema de Monitoramento de Calor e Energia

**Global Solution 2º semestre de 2026 – Programação Orientada a Objetos (POO)**

## 1. Identificação do projeto

- **Integrantes:**
  - Giovanna Praieiro Pavani – RM 565681
  - Heitor Fernandes Barbosa – RM 563078
- **Turma:** 2CCPO
- **Disciplina:** Programação Orientada a Objetos
- **Projeto:** HeatWatch – Sistema de Monitoramento de Calor e Energia
- **Tema:** CitizenTech – Tecnologias para as necessidades reais das pessoas nas cidades e comunidades.

## 2. Sobre o projeto

O HeatWatch é um sistema de monitoramento territorial desenvolvido em Java, com o objetivo de acompanhar condições de temperatura, consumo de energia e umidade, identificando possíveis situações de risco relacionadas a ondas de calor e sobrecarga elétrica.

A aplicação simula leituras de sensores, armazena as medições em um banco de dados e utiliza estratégias de previsão para analisar o comportamento dos valores registrados. Com base nos resultados e nos limites configurados, o sistema gera alertas classificados em ATENÇÃO, ALERTA e CRÍTICO.

A proposta é oferecer informações que auxiliem na identificação antecipada de situações de risco, permitindo que responsáveis pelo território avaliem possíveis medidas preventivas.

## 3. Público beneficiado e problema que o sistema busca resolver

O sistema é direcionado a organizações comunitárias, associações de bairro, equipes de gestão territorial e órgãos públicos que precisam acompanhar condições ambientais e possíveis riscos relacionados ao calor e ao consumo de energia.

Em períodos de temperaturas elevadas, o aumento da utilização de equipamentos elétricos pode contribuir para a sobrecarga da rede. Além disso, ondas de calor podem representar riscos para a população, especialmente em regiões com maior vulnerabilidade.

O HeatWatch busca apoiar decisões como a identificação de áreas que precisam de maior atenção, a avaliação de possíveis riscos de sobrecarga elétrica e a necessidade de medidas preventivas. O sistema funciona como uma ferramenta de apoio, e não como substituto da avaliação humana.

## 4. Funcionalidades desenvolvidas

O sistema possui um menu interativo em console, com as seguintes funcionalidades:

- Geração de leituras simuladas dos sensores.
- Validação e armazenamento das medições.
- Consulta do histórico de temperatura.
- Consulta do histórico de consumo de energia.
- Execução de previsões utilizando diferentes estratégias.
- Geração automática de alertas conforme os limites configurados.
- Consulta dos alertas emitidos anteriormente.
- Alteração dos limiares de temperatura e energia.
- Consulta do status do território.
- Verificação do funcionamento dos sensores.

As leituras são simuladas, permitindo testar o comportamento do sistema sem depender de equipamentos físicos ou fontes externas de dados.

## 5. Tecnologias utilizadas

- **Java 17:** linguagem utilizada no desenvolvimento.
- **Maven:** gerenciamento de dependências e execução dos testes.
- **JDBC:** comunicação entre a aplicação e o banco de dados.
- **H2 Database:** banco de dados utilizado para persistência das informações.
- **JUnit 5:** desenvolvimento dos testes automatizados.
- **Mockito:** criação de objetos simulados para testes.
- **Visual Studio Code:** ambiente utilizado para desenvolvimento.

## 6. Estrutura e conceitos de orientação a objetos

O desenvolvimento utiliza os principais conceitos de Programação Orientada a Objetos solicitados na atividade:

- **Abstração:** utilização de classes abstratas para representar componentes territoriais e estratégias de previsão.
- **Encapsulamento:** atributos privados e métodos de acesso para proteger os dados.
- **Herança:** classes concretas que herdam características de classes abstratas.
- **Polimorfismo:** utilização de diferentes estratégias de previsão por meio de uma estrutura comum.
- **Interfaces:** implementação de diferentes tipos de sensores seguindo um contrato de funcionamento.

Entre as principais classes do projeto estão `ComponenteTerritorial`, `Sensor`, `DadosTerritorio`, `EstrategiaPrevisao`, `PrevisaoMediaMovel`, `PrevisaoComparacao`, `Medicao` e `Alerta`.

## 7. Estratégias de previsão

O HeatWatch utiliza duas estratégias para analisar as medições:

**Previsão por Média Móvel**

Utiliza o histórico de medições para calcular uma média dos valores registrados, permitindo observar uma tendência com base nos dados anteriores.

**Previsão por Comparação**

Compara as medições anteriores para identificar variações e estimar o próximo valor com base no comportamento observado.

A utilização dessas estratégias permite analisar os dados de maneiras diferentes, sem depender de modelos estatísticos complexos.

## 8. Alertas e classificação de riscos

Os alertas são gerados automaticamente a partir da comparação entre os valores previstos e os limiares definidos para o território.

O sistema trabalha com três níveis de alerta:

- **ATENÇÃO:** indica aproximação do limite configurado.
- **ALERTA:** indica que o limite foi atingido.
- **CRÍTICO:** indica que o valor ultrapassou significativamente o limite estabelecido.

Cada alerta apresenta informações relacionadas ao sensor, ao valor identificado e ao limite utilizado na classificação.

## 9. Persistência e tratamento de exceções

A persistência é realizada utilizando JDBC e banco de dados H2. As medições e os alertas são armazenados para que possam ser consultados posteriormente, inclusive após o encerramento e a reinicialização da aplicação.

O projeto também utiliza exceções personalizadas para tratar situações que podem comprometer o funcionamento correto do sistema:

- `DadosInvalidosException`: utilizada para representar dados inválidos ou fora dos limites permitidos.
- `FonteIndisponivelException`: utilizada para representar situações em que uma fonte de dados ou sensor não está disponível.

Essas exceções permitem apresentar mensagens mais claras e específicas durante a utilização do sistema.

## 10. Padrão de projeto utilizado

Foi aplicado o padrão criacional **Factory**, por meio da classe `FabricaPrevisao`.

A escolha desse padrão tem como objetivo centralizar a criação das estratégias de previsão, evitando que a lógica de instanciação fique espalhada pelo código.

Dessa forma, a aplicação consegue selecionar a estratégia desejada de maneira organizada, facilitando a manutenção e a possibilidade de adicionar novas estratégias futuramente.

## 11. Testes automatizados

Foram desenvolvidos testes unitários utilizando JUnit 5 e Mockito para verificar o comportamento das regras de negócio e a classificação dos alertas.

Os testes contemplam diferentes situações, incluindo:

- Classificação de risco no nível ATENÇÃO.
- Classificação de risco no nível ALERTA.
- Classificação de risco no nível CRÍTICO.
- Classificação de situações dentro da normalidade.
- Verificação de comportamentos do serviço de monitoramento.

A execução dos testes é realizada pelo Maven:

```bash
mvn clean test
```

Na última execução realizada durante o desenvolvimento, foram obtidos os seguintes resultados:

```text
Tests run: 9
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

## 12. Decisões de desenvolvimento

O projeto foi desenvolvido utilizando uma aplicação Java com menu em console, pois essa abordagem atende ao escopo da atividade e permite concentrar o desenvolvimento nos conceitos de orientação a objetos, persistência, previsões, alertas e testes.

Foi escolhido o JDBC com H2 para facilitar a comunicação com o banco de dados e permitir a persistência do histórico sem a necessidade de configurar um servidor externo.

As leituras dos sensores foram simuladas para representar situações possíveis relacionadas à temperatura, energia e umidade. Essa decisão permite testar diferentes cenários sem depender de sensores físicos.

Também foi escolhida a utilização do padrão Factory para organizar a criação das estratégias de previsão e facilitar futuras alterações na aplicação.

## 13. Reflexão crítica

O HeatWatch é uma ferramenta de apoio à identificação de possíveis riscos relacionados ao calor e ao consumo de energia. Entretanto, é importante reconhecer que as previsões geradas possuem limitações e não devem ser interpretadas como certezas.

O sistema deve evitar emitir previsões quando não houver uma quantidade suficiente de medições históricas ou quando os dados disponíveis não forem confiáveis. Com poucas leituras, uma previsão pode não representar corretamente o comportamento do território. Nesses casos, o mais adequado é informar que ainda não existem dados suficientes para uma análise segura, evitando gerar alertas sem fundamento.

Outro ponto importante é que os dados utilizados não representam completamente a realidade de uma região. Uma medição de temperatura ou consumo de energia não consegue demonstrar todas as condições existentes em um território. Além disso, como as leituras são simuladas, elas não substituem informações coletadas por equipamentos reais. Fatores como diferenças entre bairros, condições das instalações elétricas e quantidade de pessoas afetadas também precisam ser considerados.

Por isso, o HeatWatch deve ser utilizado como uma ferramenta de apoio à tomada de decisão. Os alertas ajudam a identificar situações que merecem atenção, mas não devem decidir automaticamente quais medidas precisam ser tomadas. A avaliação humana continua sendo essencial para interpretar os resultados, considerar as características locais e escolher as ações mais adequadas.

Dessa forma, o sistema busca contribuir para uma atuação mais preventiva, sem desconsiderar as limitações dos dados e a importância da análise das pessoas responsáveis pelo território.

## 14. Uso de Inteligência Artificial

A Inteligência Artificial foi utilizada como ferramenta de apoio durante o desenvolvimento do projeto.

Seu uso ocorreu principalmente para esclarecer dúvidas sobre algumas partes dos códigos, compreender conceitos e auxiliar na interpretação de erros encontrados durante a implementação.

Também foi utilizada na etapa de revisão final para conferir os requisitos descritos no enunciado da Global Solution, verificar se a estrutura do projeto estava alinhada ao que foi solicitado e identificar possíveis pontos que precisavam de ajustes antes da entrega.

As sugestões e explicações foram utilizadas como apoio ao aprendizado e à revisão. O código foi executado, testado e revisado durante o desenvolvimento, incluindo a correção de comportamentos identificados nos testes.

A utilização da IA não substituiu a necessidade de compreender o funcionamento da aplicação. Os integrantes devem conhecer as classes, os métodos e as decisões implementadas no projeto.

## 15. Divisão das atividades

O projeto foi desenvolvido em dupla por Giovanna Praieiro Pavani e Heitor Fernandes Barbosa. Todas as etapas foram realizadas em conjunto, por meio de reuniões e chamadas, nas quais os integrantes discutiram as decisões, desenvolveram as funcionalidades, analisaram os resultados e realizaram os testes.

A participação conjunta contemplou:

- Definição do tema e planejamento da solução.
- Estruturação das classes e aplicação dos conceitos de orientação a objetos.
- Desenvolvimento dos sensores e das leituras simuladas.
- Implementação das estratégias de previsão e da lógica de alertas.
- Desenvolvimento da persistência com JDBC e H2.
- Implementação das exceções personalizadas.
- Aplicação do padrão Factory.
- Desenvolvimento e execução dos testes automatizados.
- Testes manuais, correções e validação das funcionalidades.
- Elaboração e revisão da documentação.

A organização dos arquivos e o ambiente de desenvolvimento ficaram centralizados no computador de Giovanna Praieiro Pavani, que também será responsável por realizar os commits e publicar o projeto no GitHub. Essa organização não altera o fato de que o desenvolvimento e as decisões do projeto foram realizados em conjunto pelos dois integrantes.

## 16. Limitações e melhorias futuras

Atualmente, o sistema utiliza dados simulados e não está conectado a sensores físicos ou a fontes públicas de informações meteorológicas e energéticas.

Os limiares alterados pelo menu permanecem durante a execução atual da aplicação e retornam aos valores padrão após uma nova inicialização.

Como melhorias futuras, podem ser consideradas a integração com fontes reais de dados, a persistência das configurações do território, a ampliação dos recursos de análise e a exportação do histórico de medições em formato CSV.

## 17. Como executar o projeto

### Pré-requisitos

- Java JDK 17 ou superior.
- Maven instalado.

### Executar os testes

Na pasta raiz do projeto, abra o terminal e execute:

```bash
mvn clean test
```

Esse comando compila o projeto e executa os testes automatizados utilizando JUnit 5 e Mockito.

### Executar a aplicação

Na pasta raiz do projeto, execute:

```powershell
mvn "-Dexec.mainClass=br.com.heatwatch.SistemaMonitoramento" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

Após a execução, o menu principal será apresentado no terminal, permitindo acessar as funcionalidades do HeatWatch.

### Banco de dados

O projeto utiliza o banco de dados H2 em modo arquivo, com persistência realizada por JDBC.

O banco utiliza o caminho `./data/heatwatch`, não sendo necessário instalar ou configurar um servidor de banco de dados externo.

As medições e os alertas são armazenados no banco e permanecem disponíveis após o encerramento e a reinicialização da aplicação, desde que os arquivos do banco sejam mantidos.

Ao executar o projeto em outro computador, a aplicação poderá criar a estrutura do banco automaticamente, conforme a configuração de conexão definida no código. A pasta `data` poderá ser criada durante a primeira execução.

---

**HeatWatch – Monitorar hoje para prevenir riscos amanhã.**