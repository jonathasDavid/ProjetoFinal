package gameNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import static java.lang.System.out;

public class ServerTeste {
    private static final int PORT = 12345;
    private static final String[] QUESTIONS_ROMANCE = {
            "Qual filme retrata um amor proibido entre Jack e Rose em um famoso navio?",
            "Em qual filme de romance, dois amantes trocam cartas anônimas em uma escola?",
            "Qual filme mostra um casal que se apaixona durante uma viagem de férias na Grécia?",
            "Que filme retrata um amor improvável entre um patrão rico e uma empregada pobre?",
            "Em que filme o casal de protagonistas luta pelo amor, apesar das diferenças sociais?",
            "Qual filme apresenta um romance no cenário de uma ilha deserta após um acidente de avião?",
            "Que filme narra a história de um músico que se apaixona por uma mulher com problemas de memória?",
            "Qual filme de época se passa na Inglaterra e aborda o relacionamento de Elizabeth Bennet e Mr. Darcy?",
            "Em que filme o amor floresce entre um soldado e uma enfermeira durante a Segunda Guerra Mundial?",
            "Qual filme mostra um escritor que se apaixona por um de seus personagens fictícios?",
            "Que filme apresenta a história de um casal que se conhece em uma noite mágica em Viena?",
            "Qual filme retrata um amor que floresce em meio a uma competição de dança de salão?",
            "Em que filme um homem e uma mulher se encontram periodicamente em uma estação de trem?",
            "Que filme conta a história de um amor proibido entre um padre e uma freira?",
            "Qual filme mostra o relacionamento complicado entre um adolescente e uma mulher mais velha?",
            "Em que filme o casal principal enfrenta um dilema quando ele viaja no tempo?",
            "Que filme se passa em uma escola de artes e aborda o romance entre um dançarino e uma pianista?",
            "Qual filme de romance se desenrola na paisagem deslumbrante de um campo de lavanda?",
            "Em que filme uma mulher se apaixona por um androide com inteligência artificial?",
            "Que filme retrata um amor de infância reacendendo quando os protagonistas se reencontram na idade adulta?",
            "Qual filme de romance gira em torno de um casal que se conhece em um cruzeiro?",
            "Em que filme um arquiteto se apaixona por uma mulher que tem problemas de visão?",
            "Que filme apresenta um romance entre um músico de jazz e uma aspirante a atriz?",
            "Qual filme mostra um relacionamento à distância entre um casal separado por oceanos?",
            "Em que filme um escritor se apaixona por uma mulher com um obscuro segredo?",
            "Que filme narra a história de um casal que se conhece durante um festival de música?",
            "Qual filme retrata um amor complicado entre um terapeuta e sua paciente?",
            "Em que filme um casal se reencontra após muitos anos em uma estação de trem?",
            "Que filme apresenta um amor que se desenvolve durante a guerra no Oriente Médio?",
            "Qual filme de romance gira em torno de um amor que se desenvolve em uma livraria?"

            // Adicione mais perguntas conforme necessário
    };
    private static final String[] ANSWER_ROMANCE = {
            "Titanic",
            "Cartas para Julieta",
            "Mamma Mia",
            "Uma Linda Mulher",
            "E o Vento Levou",
            "A Lagoa Azul",
            "Como se Fosse a Primeira Vez",
            "Orgulho e Preconceito",
            "Pearl Harbor",
            "A Rosa Purpura do Cairo",
            "Antes do Amanhecer",
            "Dança Comigo",
            "Encontro Marcado",
            "Duvida",
            "O Primeiro Amor",
            "Questao de Tempo",
            "Ritmo Quente",
            "Campo de Lavanda",
            "Ela",
            "O Diario de uma Paixão",
            "Passageiros",
            "Enquanto Você Dormia",
            "La La Land",
            "Mensagem para Voce",
            "Sabor da Paixao",
            "Mesmo Se Nada Der Certo",
            "Terapia do Amor",
            "Uma Carta de Amor",
            "Entre Dois Mundos",
            "Notting Hill"
            // Adicione mais respostas correspondentes às perguntas (em minúsculas)
    };

    private static final String[] QUESTIONS_TERROR= {
            "Em qual filme de terror uma família é aterrorizada por espíritos malignos?",
            "Que filme apresenta um assassino com uma máscara de hóquei perseguindo adolescentes?",
            "Qual filme mostra um hotel isolado e seu cuidador enlouquecendo?",
            "Em que filme uma boneca possuída aterroriza uma família?",
            "Que filme gira em torno de um assassino que persegue suas vítimas em seus sonhos?",
            "Qual filme de terror se passa em uma casa onde estranhos eventos paranormais ocorrem?",
            "Em que filme uma jovem é possuída por uma entidade demoníaca após brincar com uma tábua de Ouija?",
            "Que filme narra a história de um escritor que fica isolado em um hotel e encontra o mal sobrenatural?",
            "Qual filme mostra um grupo de amigos sendo perseguido por canibais em uma floresta?",
            "Em qual filme uma criatura marinha aterroriza um pequeno vilarejo costeiro?",
            "Que filme apresenta uma casa assombrada em que uma equipe paranormal realiza investigações?",
            "Qual filme de terror envolve um vírus mortal que transforma as pessoas em zumbis?",
            "Em que filme um boneco assassino possui uma mente própria e aterroriza um garoto?",
            "Que filme mostra um grupo de adolescentes sendo aterrorizado por um palhaço assassino?",
            "Qual filme de terror se passa em uma cidade onde os mortos voltam à vida?",
            "Em qual filme uma família enfrenta atividades paranormais após se mudar para uma nova casa?",
            "Que filme apresenta um psicopata mascarado que persegue uma jovem no Dia das Bruxas?",
            "Em que filme um espírito vingativo é invocado por meio de um tabuleiro Ouija?",
            "Qual filme mostra um motel isolado e seu proprietário perturbado?",
            "Que filme de terror envolve um grupo de pessoas presas em um shopping center durante um apocalipse zumbi?",
            "Em qual filme uma mulher é perseguida por um estranho em sua própria casa?",
            "Que filme narra a história de um hospital psiquiátrico assombrado por espíritos?",
            "Qual filme de terror se passa em um acampamento onde um assassino misterioso ataca os conselheiros?",
            "Em que filme uma família enfrenta atividades paranormais após encontrar uma tábua de espíritos em seu sótão?",
            "Que filme mostra um grupo de pessoas sendo aterrorizado por alienígenas em uma cabana isolada?",
            "Qual filme envolve uma maldição que atinge aqueles que assistem a uma fita de vídeo amaldiçoada?",
            "Em qual filme uma criatura pré-histórica é despertada e causa destruição?",
            "Que filme apresenta um culto sinistro que persegue uma família em uma área rural?",
            "Em que filme um assassino sobrenatural é invocado por meio de um jogo infantil?",
            "Qual filme de terror se passa em uma escola assombrada por eventos paranormais?"
            // Adicione mais perguntas sobre atores conforme necessário
    };
    private static final String[] ANSWERS_TERROR = {
            "Invocacao do Mal",
            "Sexta Feira 13",
            "O Iluminado",
            "Annabelle",
            "A Hora do Pesadelo",
            "Atividade Paranormal",
            "Origem do Mal",
            "O Iluminado",
            "Canibais",
            "Tubarao",
            "Sobrenatural",
            "Madrugada dos Mortos",
            "O Boneco do Mal",
            "It A Coisa",
            "A Noite dos Mortos-Vivos",
            "Atividade Paranormal",
            "Halloween",
            "Ouija",
            "Psicose",
            "Madrugada dos Mortos",
            "Panico",
            "Grave Encounters",
            "Sexta Feira 13",
            "Ouija",
            "Sinais",
            "O Chamado",
            "Godzilla",
            "O Sacrificio do Cervo Sagrado",
            "Jumanji",
            "A Escolha Perfeita"
            // Adicione mais respostas para as perguntas sobre atores (em minúsculas)
    };

    private static final String[] QUESTIONS_ACAO = {
            "Em qual filme de ação um ex-soldado se torna um justiceiro implacável?",
            "Que filme mostra um espião que realiza missões impossíveis para o governo?",
            "Qual filme gira em torno de um policial durão que combate criminosos em Los Angeles?",
            "Em que filme um grupo de ladrões executa roubos espetaculares em Las Vegas?",
            "Que filme apresenta um agente secreto britânico com uma licença para matar?",
            "Qual filme de ação se passa em um mundo pós-apocalíptico governado por gangues?",
            "Em que filme um motorista de fuga habilidoso enfrenta perigos nas ruas de Los Angeles?",
            "Que filme narra a história de um soldado que combate alienígenas em um traje robótico?",
            "Em qual filme um ex-militar busca vingança contra terroristas internacionais?",
            "Qual filme mostra um grupo de heróis superpoderosos lutando contra vilões?",
            "Em que filme uma equipe de agentes secretos trabalha para preservar a paz mundial?",
            "Que filme de ação envolve um agente policial infiltrado em uma organização criminosa?",
            "Qual filme gira em torno de um arqueólogo aventureiro em busca de tesouros antigos?",
            "Em que filme um ex-agente das forças especiais se torna um justiceiro urbano?",
            "Que filme apresenta um mercenário com um talento para a luta?",
            "Qual filme de ação se passa em um futuro distópico onde a lei é brutal?",
            "Em qual filme um detetive particular resolve crimes em uma cidade sombria?",
            "Que filme mostra um grupo de assaltantes roubando bancos com precisão militar?",
            "Em que filme um ex-agente secreto busca vingança após ser traído?",
            "Qual filme envolve uma corrida de carros de alta velocidade em busca de um prêmio?",
            "Em que filme uma equipe de elite enfrenta terroristas em um arranha-céu?",
            "Que filme narra a história de um espião que se disfarça para cumprir missões perigosas?",
            "Qual filme de ação se passa em uma selva onde soldados lutam contra alienígenas?",
            "Em qual filme um herói mascarado luta contra o crime nas sombras?",
            "Que filme apresenta um agente secreto que desafia a gravidade nas cenas de ação?",
            "Em que filme um piloto de caça enfrenta inimigos no ar durante uma guerra?",
            "Qual filme gira em torno de um grupo de guerreiros antigos em busca de justiça?",
            "Em qual filme uma equipe de operações especiais combate terroristas em solo estrangeiro?",
            "Que filme mostra um atirador de elite em missões de alto risco?",
            "Qual filme de ação envolve uma missão espacial para salvar a humanidade?"
            // Adicione mais perguntas sobre anos de filmes conforme necessário
    };
    private static final String[] ANSWERS_ACAO = {
            "O Protetor",
            "Missao Impossivel",
            "Duro de Matar",
            "Onze Homens e um Segredo",
            "007",
            "Mad Max",
            "Drive",
            "Oblivion",
            "Busca Implacavel",
            "Os Vingadores",
            "007",
            "Infiltrado",
            "Indiana Jones",
            "O Justiceiro",
            "Mercenarios",
            "Mad Max",
            "Blade Runner",
            "De Volta para o Futuro",
            "John Wick",
            "Velozes e Furiosos",
            "Duro de Matar",
            "007",
            "Predador",
            "Batman",
            "Matrix",
            "Top Gun",
            "300",
            "Atirador",
            "Sniper Americano",
            "Armageddon"
            // Adicione mais respostas para as perguntas sobre anos de filmes (em minúsculas)
    };

    private static final String[] QUESTIONS_ANIMACAO = {
            "Em qual filme de animação um leão jovem parte em uma jornada para se tornar rei da selva?",
            "Que filme de animação conta a história de brinquedos que ganham vida quando seus donos não estão por perto?",
            "Qual filme apresenta um peixinho-palhaço que se aventura pelo oceano para encontrar seu filho?",
            "Em que filme de animação uma princesa rebelde se aventura na floresta e encontra criaturas mágicas?",
            "Que filme gira em torno de uma jovem que se transforma em uma princesa após um encontro com uma bruxa?",
            "Qual filme de animação narra a jornada de um robô solitário em um futuro pós-apocalíptico?",
            "Em que filme um ratinho talentoso sonha em se tornar um chef de renome?",
            "Que filme apresenta um panda desajeitado que se torna o Dragão Guerreiro?",
            "Qual filme de animação leva o público para uma cidade habitada por carros falantes?",
            "Em qual filme um jovem se aventura em uma terra de monstros para resgatar sua irmã mais nova?",
            "Que filme de animação envolve um jovem que faz amizade com um gigante amigável?",
            "Em que filme uma jovem se transforma em uma deusa após um encontro com espíritos da floresta?",
            "Qual filme de animação segue as travessuras de um grupo de pinguins em uma missão de resgate?",
            "Em qual filme um ogro relutante se apaixona por uma princesa e enfrenta vilões mágicos?",
            "Que filme de animação apresenta um jovem apaixonado por aviação que resgata uma cidade de insetos?",
            "Em que filme uma família de super-heróis oculta seus poderes e luta contra um vilão?",
            "Qual filme de animação se passa em um mundo de monstros onde sustos são a moeda?",
            "Em qual filme uma jovem enfrenta uma bruxa má para resgatar sua irmã congelada?",
            "Que filme de animação conta a história de uma jovem que vive no mar com seu amigo peixe?",
            "Em que filme um jovem se aventura em um mundo subterrâneo repleto de criaturas estranhas?",
            "Qual filme de animação narra a jornada de um jovem viking e seu dragão?",
            "Em qual filme uma garota curiosa segue um coelho branco em uma aventura mágica?",
            "Que filme de animação se passa em uma floresta onde os animais se comunicam e ajudam uma princesa perdida?",
            "Em que filme de animação um sapo é transformado em príncipe por um beijo inesperado?",
            "Qual filme apresenta um rato talentoso que sonha em ser um chef renomado?",
            "Em qual filme um urso selvagem se torna amigo de um rato em uma aventura na floresta?",
            "Que filme de animação envolve um robô que faz amizade com um estranho visitante?",
            "Em que filme uma jovem é transportada para um mundo de sonhos com criaturas mágicas?",
            "Qual filme de animação segue as aventuras de um panda guerreiro que protege seu vale?",
            "Em qual filme um inventor jovem cria uma máquina que transforma água em comida?"
            // Adicione mais perguntas sobre anos de filmes conforme necessário
    };
    private static final String[] ANSWERS_ANIMACAO = {
            "O Rei Leao",
            "Toy Story",
            "Procurando Nemo",
            "Valente",
            "A Princesa e o Sapo",
            "Wall-E",
            "Ratatouille",
            "Kung Fu Panda",
            "Carros",
            "Monstros SA",
            "O Bom Gigante Amigo",
            "Princesa Mononoke",
            "Os Pinguins de Madagascar",
            "Shrek",
            "Avioes",
            "Os Incriveis",
            "Monstros SA.",
            "Frozen: Uma Aventura Congelante",
            "A Pequena Sereia",
            "Coraline e o Mundo Secreto",
            "Como Treinar o Seu Dragao",
            "Alice no Pais das Maravilhas",
            "A Princesa Encantada",
            "A Princesa e o Sapo",
            "Ratatouille",
            "Irmao Urso",
            "Wall-E",
            "O Estranho Mundo de Jack",
            "Kung Fu Panda",
            "Ta Chovendo Hamburguer"
            // Adicione mais respostas para as perguntas sobre anos de filmes (em minúsculas)
    };

    private static final String[] QUESTIONS_ALEATORIO = {
            "Que filme mostra a vida de um famoso artista durante o Renascimento italiano?",
            "Qual filme apresenta um grupo de amigos em uma aventura épica em um mundo de fantasia?",
            "Em que filme uma equipe de exploradores viaja para o espaço profundo em busca de vida extraterrestre?",
            "Que filme gira em torno de um casal que enfrenta desafios em seu relacionamento durante uma viagem?",
            "Qual filme narra a história de um músico talentoso que luta contra o vício e a fama?",
            "Em qual filme uma jovem se muda para uma casa assombrada e descobre segredos sombrios?",
            "Que filme apresenta a jornada de um jovem que se torna um mestre das artes marciais?",
            "Qual filme se passa em um futuro distópico onde a humanidade luta pela sobrevivência?",
            "Em que filme uma equipe de mergulhadores explora os segredos de um navio naufragado?",
            "Que filme mostra um grupo de cientistas enfrentando criaturas pré-históricas em uma ilha remota?",
            "Em qual filme um grupo de astronautas enfrenta perigos desconhecidos em um planeta distante?",
            "Qual filme envolve uma competição feroz entre equipes esportivas rivais?",
            "Em que filme um homem comum se vê envolvido em eventos extraordinários após encontrar um objeto misterioso?",
            "Que filme apresenta a história de um grupo de artistas tentando alcançar o sucesso em Nova York?",
            "Qual filme se desenrola em um mundo pós-apocalíptico onde a luta pela sobrevivência é constante?",
            "Em qual filme uma mulher é transportada no tempo e se apaixona em uma época passada?",
            "Que filme narra a vida de um cientista brilhante que faz descobertas revolucionárias?",
            "Qual filme se passa em uma cidade onde os sonhos se tornam realidade, mas com consequências sombrias?",
            "Em que filme uma equipe de exploradores enfrenta desafios mortais em uma expedição na selva?",
            "Que filme mostra um grupo de amigos que embarca em uma viagem de férias inesquecível?",
            "Em qual filme uma mulher luta contra inimigos mortais em uma missão de vingança?",
            "Qual filme envolve um agente secreto que desvenda conspirações internacionais?",
            "Em que filme um adolescente descobre superpoderes e enfrenta um vilão poderoso?",
            "Que filme gira em torno de um grupo de estranhos que se encontram em uma situação de vida ou morte?",
            "Qual filme narra a história de um músico que se apaixona por uma mulher com um segredo sombrio?",
            "Em qual filme um grupo de sobreviventes luta contra zumbis em um mundo apocalíptico?",
            "Que filme mostra um professor inspirador que transforma a vida de seus alunos?",
            "Qual filme se passa em um futuro onde a inteligência artificial ameaça a humanidade?",
            "Em que filme um grupo de aventureiros embarca em uma busca épica por um tesouro lendário?"
            // Adicione mais perguntas sobre anos de filmes conforme necessário
    };
    private static final String[] ANSWERS_ALEATORIO = {
            "Sherlock Holmes",
            "Misterio do Prado",
            "O Senhor dos Aneis",
            "Contato",
            "Antes do Amanhecer",
            "Whiplash",
            "A Casa dos Espiritos",
            "Karate Kid",
            "Mad Max",
            "O Segredo do Abismo",
            "Jurassic Park",
            "Interestelar",
            "Duelo de Titas",
            "Donnie Darko",
            "Cisne Negro",
            "Mad Max",
            "Efeito Borboleta",
            "Janela Indiscreta",
            "A Origem",
            "Circulo de Fogo",
            "Se Beber Nao Case",
            "Kill Bill",
            "007",
            "Homem Aranha",
            "O Nevoeiro",
            "Amnesia",
            "Resident Evil",
            "Sociedade dos Poetas Mortos",
            "Ex Machine",
            "Indiana Jones e a ultima Cruzada"
            // Adicione mais respostas para as perguntas sobre anos de filmes (em minúsculas)
    };


    private static final String COMMAND_HELP = "#help";
    private static final String COMMAND_START = "#iniciar";
    private static final String COMMAND_EXIT = "#sair";

    private static final String COMMAND_CAT = "#cat";

    private static final String COMMAND_DISCONECT = "#disconect";

    private static List<PrintWriter> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            out.println("Server is listening on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                out.println("Accepted connection from client: " + clientSocket.getInetAddress().getHostAddress());
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                clients.add(out);

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String clientInputt = in.readLine();
                if (COMMAND_START.equals(clientInputt)) {
                    new ClientHandler(clientSocket, out).start();
                } else if (COMMAND_EXIT.equals(clientInputt)) {
                    out.println("Você escolheu sair. Desconectando...");
                    out.close();
                    in.close();
                    clientSocket.close();
                    break;
                }else if (COMMAND_HELP.equals(clientInputt)) {
                    out.println("Comandos disponíveis: " + COMMAND_START + ", " + COMMAND_CAT + ", " + COMMAND_HELP + ", " + COMMAND_EXIT  + ", " + "#next");
                    String clienteInputeDois = in.readLine();
                    if(clienteInputeDois.equals(COMMAND_START)){
                        new ClientHandler(clientSocket, out).start();
                        break;
                    }
                }else if (COMMAND_CAT.equals(clientInputt)){
                    new ClientHandler(clientSocket, out).start();
                }

                else {
                    out.println("Comando inválido. Digite " + COMMAND_HELP + " para ver a lista de comandos.");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter clientOut;

        private static final Set<Integer> usedQuestionIndexes = new HashSet<>();

        public ClientHandler(Socket socket, PrintWriter out) {
            this.clientSocket = socket;
            this.clientOut = out;
        }

        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                out.println("Escolha qual categoria ROMANCE, TERROR, AÇÃO, ANIMAÇÃO, ALEATÓRIO");
                // Receber escolha do cliente
                String choice = in.readLine();
                String[] questions;
                String[] answers;

                // Determinar a categoria escolhida e usar as perguntas e respostas correspondentes
                if ("#romance".equals(choice)) {
                    questions = QUESTIONS_ROMANCE;
                    answers = ANSWER_ROMANCE;
                } else if ("#terror".equals(choice) ) {
                    questions = QUESTIONS_TERROR;
                    answers = ANSWERS_TERROR;
                } else if ("#acao".equals(choice) ) {
                    questions = QUESTIONS_ACAO;
                    answers = ANSWERS_ACAO;
                }  else if ("#animacao".equals(choice) ) {
                    questions = QUESTIONS_ANIMACAO;
                    answers = ANSWERS_ANIMACAO;
                } else if ("#aleatorio".equals(choice) ) {
                    questions = QUESTIONS_ALEATORIO;
                    answers = ANSWERS_ALEATORIO;
                }else {
                    // Categoria inválida, encerrar a conexão
                    clientOut.println("Escolha de categoria inválida. A conexão será encerrada.");
                    clientSocket.close();
                    return;
                }
                int pulos_atuais = 0;
                int pulos_totais = 3;
                int score = 0;
                for (int i = 0; i < 6; i++) {  // Limitar a 10 perguntas
                    int questionIndex = getUniqueQuestionIndex(questions.length);
                    clientOut.println(questions[questionIndex]);

                    // Esperar pela resposta do cliente
                    String clientResponse = in.readLine();
                    if (clientResponse != null && clientResponse.toLowerCase().equals(answers[questionIndex].toLowerCase())) {
                        score++;

                    }else if (clientResponse.equals(COMMAND_DISCONECT)) {
                        out.println("Você escolheu sair. Desconectando...");
                        out.close();
                        in.close();
                        clientSocket.close();
                        break;
                    }else if (clientResponse.equals("#next")){
                        i = i -1;
                        pulos_atuais++;
                        pulos_totais--;

                    }else if(clientResponse.equals("#pulos")){
                        out.println("O total de pulos restatantes é: "+ pulos_totais + "Digite ENTER para continuar");

                    }else if(clientResponse.equals("#restart")){
                        score = 0;
                        i=5;
                        run();

                    }else if (clientResponse.equals("#score")){
                        out.println("Sua pontuação é " + score);
                        continue;
                    }
                }

                out.println("Você acertou " + score + " perguntas! Deseja jogar novamente? (Digite #novojogo para jogar novamente ou #sair para sair)");
                String clientResponse = in.readLine();
                if (clientResponse.equals("#novojogo")) {
                    usedQuestionIndexes.clear();  // Limpa as perguntas usadas para o novo jogo
                    score = 0;  // Zera a pontuação para o novo jogo
                    run();
                } else if (clientResponse.equals("#sair")) {
                    out.println("Você escolheu sair. Desconectando...");
                    out.close();
                    in.close();
                    clientSocket.close();
                    return;  // Sai do loop e desconecta o cliente
                } else {
                    out.println("Comando inválido. Desconectando...");
                    out.close();
                    in.close();
                    clientSocket.close();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clients.remove(clientOut);
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.println("Connection closed for client: " + clientSocket.getInetAddress().getHostAddress());

            }
        }
        private synchronized int getUniqueQuestionIndex(int arrayLength) {
            int questionIndex;
            do {
                questionIndex = (int) (Math.random() * arrayLength);
            } while (usedQuestionIndexes.contains(questionIndex));
            usedQuestionIndexes.add(questionIndex);
            return questionIndex;
        }
    }

}
