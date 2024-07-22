# Gerenciamento de Memória - Trabalho Prático 2

## Introdução
Este projeto tem como objetivo explorar as abordagens de alocação de espaço contíguo para processos, utilizando diferentes técnicas e políticas de alocação de espaços em uma memória.

## Funcionalidades
- **Partições Variáveis**: Permite escolher entre as políticas de alocação Worst-Fit ou Circular-Fit.
- **Sistema Buddy**: Aloca memória a partir de um segmento de tamanho fixo que consiste em páginas fisicamente contíguas.
- **Visualização de Resultados**: Exibe o estado atual da memória após cada requisição.

## Como Compilar e Executar

### Requisitos
- Ambiente de desenvolvimento compatível com a linguagem escolhida para o projeto.
- Compilador adequado instalado no sistema.

### Passos para Compilação
1. Baixe ou clone este repositório.
2. Navegue até o diretório raiz do projeto.
3. Execute o comando de compilação (substitua `<compiler>` pelo compilador adequado para a linguagem escolhida) ou execute pelo xcode com a extensão do java instalado.

Entrada: 
Digite o tamanho da memória (Potência de 2): 64
Digite qual é a partição que deseja executar => (1) partição variável | (2) partições definidas: 1
Digite qual é a política de alocação a ser empregada => (1) worst-fit | (2) circular-fit: 2
Digite o nome do arquivo a ser testado: exemplos/input3.txt

Saída:
==> O programa começou a executar!!!
===> Foi escolhido a partição variável.
===> A política escolhida foi circular-fit.
O processo (A, 31) foi alocado com sucesso!
Memória livre: 33 unidades
Memória alocada: 31 unidades
Memória total: 64 unidades
Estado atual da memória: | ###############################....................... |

O processo (B, 15) foi alocado com sucesso!
Memória livre: 18 unidades
Memória alocada: 46 unidades
Memória total: 64 unidades
Estado atual da memória: | ##########################################............. |

O processo (C, 13) foi alocado com sucesso!
Memória livre: 5 unidades
Memória alocada: 59 unidades
Memória total: 64 unidades
Estado atual da memória: | ###################################################..... |

....

O processo (A) foi desalocado com sucesso!

Memória livre: 51 unidades
Memória alocada: 13 unidades
Memória total: 64 unidades

Estado atual da memória: | ................................................................ |

O processo (C) foi desalocado com sucesso!

Memória livre: 64 unidades
Memória alocada: 0 unidades
Memória total: 64 unidades

Estado atual da memória: | ................................................................ |



