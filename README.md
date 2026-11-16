# Sistema de Gestão de Biblioteca Universitária — TP1

**Disciplina:** Projeto de Bloco: Desenvolvimento Back-End  
**Aluno:** Rafael Rainha de Orequio  
**Trilha Tecnológica:** Java 21 (LTS)

## Estrutura do Repositório
- `docs/`: Documento de levantamento de requisitos (RF/RNF), diagrama de casos de uso UML e justificativa de escopo.
- `src/`: Código-fonte do protótipo standalone inicial (`BibliotecaStandaloneTP1.java`), desenvolvido com tipos primitivos, operadores e estruturas de controle.

## Como Compilar e Executar
```bash
mkdir -p build
javac --release 21 -d build src/BibliotecaStandaloneTP1.java
java -cp build BibliotecaStandaloneTP1
