package com.company;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by hbrtxito on 11/1/2015.
 */
public class Deck {
    /**
     * ArrayList cards - Guardo os baralhos
     *
     * ArrayList rn_number - Embaralha numeros do deck
     */
    private ArrayList<Card> cards = new ArrayList<>();
    private Random rn_number =  new Random();


    public Deck(){ // Criado baralho
        for (int i =0 ; i<52 ; i++){
            Card card = new Card(i);
            cards.add(card);
        }
        Shuffle();

    }


    /**
     * ToString => Esse method verifica o tamanho do arraylist e adiciona espacho entre eles.
     */
    public String toString(){
        String deckString = "";
        for (int i =0 ; i<cards.size() ; i++){
            Card card = cards.get(i) ;
            deckString += card + " ";
        }
        return deckString ;
    }


    /**
     * Shuffle => Embaralha as cartas;
     * Pega a quantidade de vezes que ira percorrer o baralho
     * Para cada carta, remove random e adiciona para embaralhar.
     */

    public void Shuffle (){
        ArrayList <Card> shuffle = new ArrayList<>();

        int numberOfTimes = cards.size();

        for (int i = 0 ; i<numberOfTimes ; i++){
            int deckSize = cards.size();
            int pick = rn_number.nextInt(deckSize);
            Card card = cards.remove(pick);
            shuffle.add(card);
        }
        cards = shuffle ;//set cards to shuffle
    }


    //Remove carta do baralho e retorna zero
    public  Card deal () {
        Card card = cards.remove(0);
        return card ;
    }


    //Reutiliza a primeira carta descartada e adiciona ao deck.
    public void reuse (ArrayList <Card> newCards){ cards = newCards; }


    public int size() { return cards.size(); } // Mostra ao usuario quantas cartas tem no deck
}
