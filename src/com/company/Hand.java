package com.company;

import java.util.ArrayList;

/**
 * Created by hbrtxito on 11/1/2015.
 */
public class Hand {

    private ArrayList<Card> cards= new ArrayList<>();

    //Method para trocar o baralho do pequeno para o maior
    public void add (Card card){
        int index = 0 ;
        boolean done = false ;

        //Loop percorre todas as cartas em mãos para comparar uma carta com a carta do jogador
        while (!done && index <cards.size()){
            Card carInHand = cards.get(index);
            // Se a carta for maior que a sua index +1
            if (card.isGreaterthan(carInHand)){
                index ++ ;}
            //caso contrario termina
            else {done = true;}}
        cards.add(index ,card);
    }

    public String toString (){ //method para imprimir a carta
        String string ="";

        /**
         * Esse loop separa as cartas caso tenha suits diferente
         * Se nao for o primeiro baralho, sera comprado com o anterior. E se for diferente, acrescenta mais um espaço
         * para diferenciar as cartas em maos.
         * string+=card.toString() + " "; adiciona espaco para baralho com o mesmo suit.
         */

        for (int i=0 ; i<cards.size() ; i++){
            Card card = cards .get(i);
            if (i >0){
                Card priorCard= cards.get(i-1);
                char priorSuit = priorCard.getSuit();
                char currentSuit = card.getSuit();

                if (priorSuit != currentSuit){
                    string+="  ";}
            }string+=card.toString() + " ";}
        return string ;
    }

    /**
     * Este method é para remover cards.Logo, retorna nada.
     * Primeiro percorre as cartas na mao do jogador, verificar o tamanho, ao achar a carta que foi jogada,
     * remove e incrementa o index.
     */
    public void remove (Card card){
        int index =0 ;
        boolean found = false ;
        while (!found && index < cards.size()){
            Card compareCard = cards.get(index);
            if (compareCard.equals(card)){
                cards.remove(index);
                found = true ;
            }
            else {
                index++;
            }
        }
    }

    /**
     * Este method verifica se a mao do jogador possui a carta descartada.
     * Inicializa com false, e quando encontra a carta vira true
     *
     */

    public  boolean contains (Card card){
        int index =0 ;
        boolean contains = false ;
        while (!contains && index < cards.size()){
            Card compareCard = cards.get(index);
            if (compareCard.equals(card)){
                contains = true ;
            }
            else {
                index++;
            }
        }
        return contains ; // retorna boolean
    }



    /**
     * Retorna a carta
     * Retorna an inteher chamada index e retorna a carta
     *
     */

    public Card cardAt (int i){

        return cards.get(i);
    }


    /**
     * Conta os numero de baralho que cada jogador possui
     */
    public  int size(){
        return cards.size();
    }
}
