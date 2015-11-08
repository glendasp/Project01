package com.company;

import java.util.*;

/**
 * Created by hbrtxito on 11/1/2015.
 */
public class CrazyEight  {
    private Deck deck = new Deck(); //usando deck class e obj
    private Hand myHand = new Hand(); // mão do jogador
    private Hand computerHand = new Hand(); // computador
    private Card discard; // discarta carta na mesa

    //Bloco de cartas na mesa com todas as cartas descartadas pelos jogadores
    private ArrayList<Card> discardPile = new ArrayList<>();
    private Random rand = new Random(); //pega uma carta qualquer
    private char activeSuit = ' '; // inicializa com vazio para que depois possa pegar o suit


    private int countHearts = 0 ;
    private int countDiammonds = 0 ;
    private int countSpades = 0 ;
    private int countClubs = 0 ;


    public CrazyEight() { // my constructor

        for (int i = 0; i < 7; i++) {
            Card card1 = deal();
            myHand.add(card1);
            Card card2 = deal();

            computerHand.add(card2);

        }
        discard = deal(); //carta discartada

        //Se for numero 8, pede ao jogador para escolher outro suit, caso contrario fica vazio.
        if (discard.getRank() == '8') {
            activeSuit = discard.getSuit();
        } else {
            activeSuit = ' ';
        }
        // Escolhe quem joga primeiro ... joga ate um dos jogadores nao possue mais nenhuma carta
        int turn = rand.nextInt(2);
        if (turn == 1) {
            System.out.println("Computer plays first");
            playComputerCard();
        } else {
            System.out.println("You play first ");
        }
        boolean done = false;



        // Enquanto os dois jogadores tem cartas em mãos
        while (!done) {
            playMyCard();
            if (myHand.size() == 0) {
                done = true;
            } else {
                playComputerCard();
                if (computerHand.size() == 0) {
                    done = true;
                }
            }
        }
        System.out.println("---------------");


        //VErifica quem zerou as cartas primeiro
        if (myHand.size() == 0) {
            System.out.println("CONGRATULATIONS!!!! You won this turn. \n The computer still had " + computerHand.size() + "cards .");
        } else {
            System.out.println("Sorry, You lost. :( \nYou still have " + myHand.size() + " cards.");
            System.out.println("My hand : " + myHand);
            System.out.println("Discard : " + discard);
        }
    }
    // Method para retornar cartas
    private Card deal() {
        //Se acabar as cartas disponiveis, entao o computador utiliza as que foram descartadas
        if (deck.size() == 0) {
            deck.reuse(discardPile);//reutiliza cartas descartadas
            deck.Shuffle();
            discardPile.clear();
            System.out.println("\n Re-Shuffle the discard pile");
        }
        Card card = deck.deal();//joga uma carta do bloco e retorna
        return card;
    }
    //Este method ira iniciar o jogador e o computador
    private void ShowStatus() {
        System.out.println("\n Computer has " + computerHand.size() + " cards.");
        System.out.println("My Hand : " + myHand);
        System.out.println("Discard :" + discard);
        if (discard.getRank() == '8') {
            System.out.println("Suit is " + activeSuit);
        }
    }

    // Este method compra uma carta quando um dos jogadores precisarem
    private void drawMyCard() {
        Card drewCard = deal();
        System.out.println("\n You drew " + drewCard);
        myHand.add(drewCard);


        //Se a carta comprada for valida discarta. Apenas aceita string ou argumento
        if (isValidPLay(drewCard.toString())) {
            System.out.println("You played " + drewCard);
            discardMyCard(drewCard);
        }

    }

    //Este method remore uma carta da mao de um dos jogadores e sera jogada ao bloco de cartas descartadas
    private void discardMyCard(Card mycard) {
        myHand.remove(mycard);
        discardPile.add(discard);
        discard = mycard;
        // Verifica caso a carta venha com o numero 8
        if (mycard.getRank()=='8'){
            activeSuit= promptforSuit();
        }


    }
    // Nao pega parametro algum e retorna
    private void playMyCard() {
        Scanner scanner = new Scanner(System.in);
        ShowStatus();
        boolean validPlay = false;
        while (!validPlay) {
            System.out.println("Chose a card if you would you like to play a card or D to draw?)");
            String rankSuit = scanner.nextLine();
            rankSuit = rankSuit.toUpperCase();
            // Caso escolha descartar
            if (rankSuit.equals("D")) {
                drawMyCard();
                validPlay = true;
            }
            //verificar se e valido
            else if (isValidPLay(rankSuit)) {
                Card selectedCard = new Card(rankSuit);
                discardMyCard(selectedCard);
                validPlay = true;
            }
        }
    }

    //Valida a carta e retorna true ou falso
    private boolean isValidPLay(String rankSuit) {
        boolean validPlay = true;
        Card card = new Card(rankSuit);
        if (!card.isValid()) {
            System.out.println(rankSuit + " is not a valid Card");
            validPlay = false;

        }
        // Caso eu nao tenha a carta certa
        else if (!myHand.contains(card)) {
            System.out.println(rankSuit + " is not  your Hand ");
            validPlay = false;
        }
        // 8s sera sempre valido
        else if (card.getRank() != '8') {
            if (discard.getRank() == '8') {
                //verifica se a carta combina com o suit escolhido
                if (card.getSuit() != activeSuit) {
                    System.out.println(rankSuit + "Cannot be played on " + discard + " because the suit was set to " + activeSuit);
                    validPlay = false;
                }
            }

            // Se a carta descartada nao for 8 ou se nao combina com o suit ou rank
            else if (card.getSuit() != discard.getSuit() && card.getRank() != discard.getRank()) {
                System.out.println(rankSuit + " cannot be played on " + discard);
                validPlay = false;
            }
        }

        return validPlay;
    }

    //Remove a carta da mao do computador e adiciona para o bloco de cartas descartadas.
    private void discardComputerCard(Card computerCard) {
        computerHand.remove(computerCard);
        discardPile.add(discard);
        discard = computerCard;


        //Escolhe o suit mais disponivel nas mao do computador
        if (discard.getRank()=='8'){
            int highestCount = countHearts ;
            activeSuit= 'H';
            if (countDiammonds >highestCount){
                highestCount =  countDiammonds ;
                activeSuit = 'D' ;

            }
            if (countClubs> highestCount){
                highestCount = countClubs;
                activeSuit = 'C';

            }
            if (countSpades> highestCount){
                highestCount = countSpades;
                activeSuit = 'S';
            }
        }
    }

    //Este method joga da mao do computador
    private void playComputerCard() {
        System.out.println("Computer Hand : " + computerHand);
        //ArrayList para jogar as cartas
        ArrayList<Card> playableCards = new ArrayList<>();
        ArrayList<Card> eights = new ArrayList<>();
        countHearts = 0 ;
        countDiammonds = 0 ;
        countSpades = 0 ;
        countClubs = 0 ;
        //Conta 8s e numero de cada suit
        for (int i=0 ; i<computerHand.size() ; i++){
            Card card = computerHand.cardAt(i);
            //Se for 8 salva
            if (card.getRank() == '8'){
                eights.add(card);

            }
            //ou conta o numero de cada suit
            else {
                switch (card.getSuit()){
                    case 'H':
                        countHearts++;
                        break;
                    case 'D':
                        countDiammonds++;
                        break;
                    case 'C':
                        countClubs++;
                        break;
                    case 'S':
                        countSpades++;
                        break;
                }
            }
        }
        //Faz uma lista de cartas jogaveis
        for (int i = 0; i < computerHand.size(); i++) {
            Card card = computerHand.cardAt(i);
            //Se sair 8, todas as cartas serao jogaveis
            if (discard.getRank()=='8'){
                if (card.getSuit()==activeSuit){
                    playableCards.add(card);
                }
            }
            // Se nao for 8, somente as carta do mesmo suits or rank serao jogaveis
            else  if (card.getSuit() == (discard.getSuit()) || card.getRank() == (discard.getRank())) {
                playableCards.add(card);
            }
        }
        //Escolhe uma carta quanquer que sea jogavel
        int numberOfPLayableCard = playableCards.size();
        if (numberOfPLayableCard > 0) {
            int pick = rand.nextInt(numberOfPLayableCard);
            Card playedCard = playableCards.get(pick);
            discardComputerCard(playedCard);
        }
        //ou caso tenha 8, joga 8
        else if (eights.size()>0){
            Card playedCard = eights.get(0);
            discardComputerCard(playedCard);
        }
        //caso nenhuma carta seja boa, compra
        else {
            Card drewCard = deal();
            computerHand.add(drewCard);
            System.out.println("\n Computer drew a card");

            //caso contrario, joga
            if (drewCard.getSuit() == discard.getSuit() || drewCard.getRank() == discard.getRank()) {
                discardComputerCard(drewCard);
            }
        }
    }

    //Esse method pergunta ou jogador ou computador se possue um carta 8.
    private char promptforSuit() {

        char suit = ' ';
        boolean validSuit = false;
        while (!validSuit) {
            //Chama o method promptForChar da class Changeofsuits
            suit = changeofSuits.promptForChar("Change the suit to H , D , C , or S?");

            suit = Character.toUpperCase(suit); //pego o input do user e faz virar o suit
            if (Card.isValidSuit(suit)) {
                validSuit = true;
            }
        }
        return suit;
    }
}


