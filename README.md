A Explicação Correta: Duas Entidades + O Motor
O teu projeto é composto por Duas Classes de Objetos (Cliente e CartelaBingo) que são controladas e unidas pelo TrabalhoA3 (O Main).

                  [ TrabalhoA3 (O Main / O Motor) ]
                                 |
        +------------------------+------------------------+
        |                                                 |
[ Entidade 1: Cliente ]                         [ Entidade 2: CartelaBingo ]
- Nome, CPF e Saldo                             - ID, Preço e Dono
- Funciona como a Carteira                      - Gera e confere os números
                                                
1. Entidade Cliente (A Carteira do Jogador)
Esta classe representa a pessoa que entra no bingo. Ela serve apenas para guardar e proteger os dados do jogador.

O que ela faz de mais importante? O método descontarSaldo. Ele funciona exatamente como um leitor de cartões de débito. Quando o Main pede para comprar uma cartela, o Cliente verifica se tem dinheiro e faz a subtração.

2. Entidade CartelaBingo (O Bilhete de Jogo)
Esta classe representa o papelzinho com os números que o jogador compra.

O que ela faz de mais importante? Ela usa o Random para fabricar 5 números da sorte (de 1 a 50) sem os repetir. Além disso, ela tem o método conferirNumero, que vai metendo uma "cruz" virtual cada vez que o número sorteado pelo Main bate com um número que ela tem.

3. TrabalhoA3 (O Main / O Motor do Jogo)
Este não é uma entidade de negócio, é o chefe do programa. Ele não guarda dados de uma pessoa ou de uma cartela, ele serve para fazer o jogo acontecer.

É ele que cria as listas para guardar os clientes e as cartelas na memória.

É ele que mostra o menu de opções na consola (CRUD de clientes, vendas, etc).

É ele que faz o sorteio avançar na rodada e, quando a CartelaBingo avisa que completou os 5 números, ele descobre quem é o dono, tira R$ 50,00 do prémio e deposita no Cliente.

É ele que atualiza e exibe o Saldo do Dia no topo do ecrã para o gestor saber o lucro atual.

🎙️ Frase de Ouro para a Apresentação:
"Professor, o sistema foi modelado com duas classes de entidades principais: o Cliente, que encapsula os dados e o saldo do usuário, e a CartelaBingo, que gere os números e as marcações do jogo. A classe TrabalhoA3 funciona puramente como o nosso Main, ou seja, o ponto de entrada e o motor que orquestra estas duas entidades, controlando o fluxo do jogo, os sorteios e o balanço financeiro do dia.
