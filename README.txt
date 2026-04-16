=====================================================================
  SISTEMA DE MONITORAMENTO DE BPM COM ESTIMULO MUSICAL
  Disciplina: Complexidade de Algoritmos — IFBA
  Professor : Luis Paulo da Silva Carvalho
  Entrega   : 29/04/2026
=====================================================================

DESCRICAO DO MINI-MUNDO
-----------------------
Usuarios utilizam smartwatches que registram seus batimentos
cardíacos (BPM) enquanto ouvem diferentes generos musicais.
O sistema gera dados pseudoaleatorios, organiza e analisa as
leituras, comparando as respostas cardiacas de cada usuario.

Dispositivo de sensoriamento: Smartwatch (sensor de BPM)
Quantidade monitorada       : 10 usuarios
Leituras por usuario        : 15 (configuravel em App.java)


ARQUIVOS DO PROJETO
-------------------
  App.java        — Ponto de entrada (metodo main)
  Usuario.java    — Entidade: usuario monitorado
  LeituraBPM.java — Entidade: leitura de BPM de um usuario
  GeradorDados.java — Geracao pseudoaleatoria com LCG
  Processador.java  — Algoritmos d.1, d.2, d.3 e d.4


COMO COMPILAR E EXECUTAR
-------------------------
Pre-requisito: Java JDK instalado (versao 8 ou superior)

  1. Abrir terminal na pasta do projeto

  2. Compilar todos os arquivos:
       javac *.java

  3. Executar:
       java App

  OU usando o script abaixo (Linux/Mac):
       javac *.java && java App


FUNCIONALIDADES E COMPLEXIDADES
---------------------------------
  [d.1] Listar usuarios monitorados           O(N)
  [d.2] Listar leituras por usuario           O(N * M)
  [d.3] Ordenar BPM crescente (Bubble Sort)   O(N * M^2)
  [d.4] Matriz de Similaridade MSE            O(N^2 * M)
  [+]   Resumo estatistico (media e maximo)   O(N * M)

  Onde N = numero de usuarios, M = leituras por usuario.


ALGORITMO PSEUDOALEATORIO
--------------------------
  Utiliza Gerador Congruente Linear (LCG):
    Xn+1 = (A * Xn + C) mod M
    A = 1664525, C = 1013904223, M = 2^32

  A semente (SEED = 2026) garante reproducibilidade.


OBSERVACOES
-----------
  - Sem banco de dados
  - Sem bibliotecas externas/internas para ordenacao ou filtragem
  - Todos os algoritmos implementados manualmente
  - Linguagem Java (suporte nativo a Threads)
  - Acentuacao removida dos comentarios em codigo para compatibilidade
    com diferentes encodings de terminal

=====================================================================
