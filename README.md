# Tabela Hash com Sondagem Linear

## Objetivo

Implementar, em Java, uma tabela hash de 100 posições com controle de ocupação. O programa realiza **100 tentativas de inserção**, mas deixa de armazenar novos valores assim que a tabela chega a **90 posições ocupadas (90%)**.

## Como funciona

Os valores são sorteados com `Random` e a semente fixa `1090`, que permite repetir o mesmo teste. Para esta implementação, foi escolhido o intervalo de **0 a 100.000**, inclusive.

O índice inicial é obtido com a expressão:

```java
int posicao = numero % 100;
```

Se o índice estiver disponível, o número é armazenado imediatamente. Caso esteja ocupado, o programa faz a **sondagem linear**, verificando as posições seguintes até encontrar uma livre:

```java
posicao = (posicao + 1) % 100;
```

Cada posição ocupada encontrada durante essa procura conta como **uma colisão** para o número que está sendo inserido. O valor `-1` identifica uma posição vazia da tabela; por isso, o número `0` pode ser armazenado normalmente.

## Controle de ocupação

A inserção só acontece se houver menos de 90 elementos armazenados. Quando a quantidade chega a 90, as próximas tentativas são recusadas, sem alterar a tabela. Não é realizado aumento de capacidade.

## Informações apresentadas

Ao terminar, o programa mostra:

- Quantos números foram inseridos e quantos foram bloqueados;
- A soma de todas as colisões ocorridas durante as inserções;
- Os índices das posições que permaneceram livres;
- Os três números com o maior número individual de colisões;
- O resultado da busca por esses mesmos três números.

A busca aplica a **mesma sondagem linear** usada no armazenamento. Em caso de empate no número de colisões, o ranking mantém a prioridade de quem foi inserido primeiro.

## Resultados com a seed 1090

| Medida | Resultado |
| --- | --- |
| Tentativas | 100 |
| Inserções realizadas | 90 |
| Inserções impedidas | 10 |
| Colisões totais | 247 |
| Índices livres | 35, 36, 37, 46, 47, 48, 49, 50, 88 e 90 |

Os números que mais colidiram foram:

1. **55998:** 28 colisões — encontrado no índice **26**.
2. **52201:** 26 colisões — encontrado no índice **27**.
3. **19454:** 23 colisões — encontrado no índice **77**.

Todos os três testes de busca retornaram os valores esperados.

## Execução

O projeto contém o arquivo `Main.java` e pode ser executado em um terminal com Java instalado:

```bash
javac Main.java
java Main
```

Não são usadas coleções prontas, como `ArrayList`, `HashMap` ou `LinkedList`. O código trabalha com arrays, operações aritméticas, `if`, `else`, `for`, `while` e `Random`, além dos comandos básicos de impressão.
